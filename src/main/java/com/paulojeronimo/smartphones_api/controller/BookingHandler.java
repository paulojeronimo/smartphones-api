package com.paulojeronimo.smartphones_api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import com.paulojeronimo.smartphones_api.service.BookingService;
import com.paulojeronimo.smartphones_api.model.BookingDto;
import com.paulojeronimo.smartphones_api.model.Smartphone;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@RequiredArgsConstructor
@Slf4j
@Component
public class BookingHandler
{
   private final BookingService repository;

   public Mono<ServerResponse> bookASmartphone(ServerRequest request)
   {
      final String smartphoneId = request.pathVariable("smartphoneId");
      final String userId = request.pathVariable("userId");
      log.info("Booking a {} for user {}", smartphoneId, userId);

      return ok()
         .contentType(MediaType.APPLICATION_JSON)
         .body(repository.bookASmartphone(smartphoneId, userId), Smartphone.class);
   }

   public Mono<ServerResponse> releaseABooking(ServerRequest request)
   {
      final String smartphoneId = request.pathVariable("smartphoneId");
      final String userId = request.pathVariable("userId");
      log.info("Releasing a booking made by {} to {}", userId, smartphoneId);

      return ok()
         .contentType(MediaType.APPLICATION_JSON)
         .body(repository.releaseABooking(smartphoneId, userId), Smartphone.class);
   }

   public Mono<ServerResponse> showBookings(ServerRequest request)
   {
      return ok()
         .contentType(MediaType.APPLICATION_JSON)
         .body(repository.getBookings(), BookingDto.class);
   }
}