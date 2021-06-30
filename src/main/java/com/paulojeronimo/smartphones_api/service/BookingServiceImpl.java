package com.paulojeronimo.smartphones_api.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.paulojeronimo.smartphones_api.model.BookingDto;

import static com.paulojeronimo.smartphones_api.model.BookingDto.Status.*;

@Component
public class BookingServiceImpl implements BookingService
{

   private final Map<String, BookingDto> bookings = new ConcurrentHashMap<>();

   @Autowired SmartphoneService smartphoneService;

   @Override
   public Flux<BookingDto> getBookings()
   {
      return Flux.fromStream(bookings.values().stream());
   }

   @Override
   public Mono<BookingDto> bookASmartphone(String smartphoneId, String userId)
   {
      final BookingDto newBookingDto = BookingDto.builder()
         .smartPhoneId(smartphoneId)
         .userId(userId)
         .build();
      BookingDto existingBookingDto = bookings.get(newBookingDto.id());

      // TODO here: develop a way to check if smartphone is existing ...

      // TODO: use the state design pattern to do the state transitions in a better way than this:
      if (existingBookingDto == null)
      {
         return Mono.just(addOrModifyBooking(newBookingDto, BOOKED));
      }
      if (existingBookingDto.getUserId().equals(userId))
      {
         if (existingBookingDto.getStatus() == RELEASED)
         {
            removeExistingBooking(existingBookingDto);
            existingBookingDto = addOrModifyBooking(newBookingDto, BOOKED);
         }
         else
         {
            existingBookingDto.setStatus(ALREADY_BOOKED);
         }
      }
      else
      {
         if (existingBookingDto.getStatus() == RELEASED)
         {
            removeExistingBooking(existingBookingDto);
            existingBookingDto = addOrModifyBooking(newBookingDto, BOOKED);
         }
         else
         {
            existingBookingDto.setStatus(BOOKED_BY_ANOTHER_USER);
         }
      }
      return Mono.just(existingBookingDto);
   }

   private BookingDto addOrModifyBooking(BookingDto newBookingDto, BookingDto.Status status)
   {
      newBookingDto.setBookingTime(LocalDateTime.now());
      newBookingDto.setStatus(status);
      bookings.put(newBookingDto.id(), newBookingDto);
      return newBookingDto;
   }

   private void removeExistingBooking(BookingDto existingBookingDto)
   {
      bookings.remove(existingBookingDto.id());
   }

   @Override
   public Mono<BookingDto> releaseABooking(String smartphoneId, String userId)
   {
      final BookingDto newBookingDto = BookingDto.builder()
         .smartPhoneId(smartphoneId)
         .userId(userId)
         .build();
      BookingDto existingBookingDto = bookings.get(newBookingDto.id());

      // TODO here: develop a way to check if smartphone is existing ...

      // TODO: refactor this code (probably there are bugs and I need more time to check it! ;)
      if (existingBookingDto == null)
      {
         newBookingDto.setStatus(NOT_BOOKED);
         return Mono.just(newBookingDto);
      }
      if (existingBookingDto.getUserId().equals(userId))
      {
         existingBookingDto = addOrModifyBooking(existingBookingDto, RELEASED);
         existingBookingDto.setReturnTime(LocalDateTime.now());
      }
      else
      {
         existingBookingDto.setStatus(BOOKED_BY_ANOTHER_USER);
      }
      return Mono.just(existingBookingDto);
   }
}