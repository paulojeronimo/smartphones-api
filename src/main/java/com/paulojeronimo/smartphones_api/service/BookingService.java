package com.paulojeronimo.smartphones_api.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.paulojeronimo.smartphones_api.model.BookingDto;

public interface BookingService
{
   Flux<BookingDto> getBookings();
   Mono<BookingDto> bookASmartphone(String smartphoneId, String userId);
   Mono<BookingDto> releaseABooking(String smartphoneId, String userId);
}