package com.paulojeronimo.smartphones_api.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class BookingRouter
{
   @Bean
   public RouterFunction<ServerResponse> route(BookingHandler handler){
      return RouterFunctions
         .route(GET("/booking/{smartphoneId}/{userId}/release").and(accept(MediaType.APPLICATION_JSON)), handler::releaseABooking)
         .andRoute(GET("/booking/{smartphoneId}/{userId}").and(accept(MediaType.APPLICATION_JSON)), handler::bookASmartphone)
         .andRoute(GET("/booking").and(accept(MediaType.APPLICATION_JSON)), handler::showBookings);
   }
}