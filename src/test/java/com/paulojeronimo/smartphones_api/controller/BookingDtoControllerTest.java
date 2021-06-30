package com.paulojeronimo.smartphones_api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.paulojeronimo.smartphones_api.model.BookingDto;
import com.paulojeronimo.smartphones_api.model.DummyData;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@DirtiesContext
public class BookingDtoControllerTest
{
   private static final String BASE_URL = "https://example.com";

   @Autowired
   private WebTestClient webTestClient;

   @Test
   public void book_a_smartphone() {
      final String smartphoneId = DummyData.SamsungGalaxyS9.getId();
      final String userId = "user1";

      webTestClient.get()
         .uri(uriBuilder -> uriBuilder
            .path("/booking/{smartphoneId}/{userId}")
            .build(smartphoneId, userId))
         .exchange()
         .expectStatus().isOk()
         .expectBody(BookingDto.class)
         .consumeWith(consumer ->
            verifyBooking(smartphoneId, userId, consumer.getResponseBody()));
   }

   private void verifyBooking(String smartphoneId, String userId, BookingDto bookingDto)
   {
      assertEquals(smartphoneId, bookingDto.getSmartPhoneId());
      assertEquals(userId, bookingDto.getUserId());
      assertNotNull(bookingDto.getBookingTime());
   }
}