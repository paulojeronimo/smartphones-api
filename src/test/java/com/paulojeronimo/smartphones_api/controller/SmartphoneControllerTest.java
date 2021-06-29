package com.paulojeronimo.smartphones_api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;
import com.paulojeronimo.smartphones_api.model.DummyData;
import com.paulojeronimo.smartphones_api.model.Smartphone;

@SpringBootTest
@AutoConfigureWebTestClient
@ActiveProfiles("test")
@DirtiesContext
public class SmartphoneControllerTest
{
   @Autowired
   private WebTestClient client;

   @Test
   public void testFindAll() {
      client.get()
         .uri("/smartphone")
         .accept(MediaType.APPLICATION_JSON)
         .exchange()
         .expectStatus().isOk()
         .expectBodyList(Smartphone.class)
         .hasSize(10);
   }

   @Test
   public void testFindById() {
      client.get()
         .uri("/smartphone/{id}", DummyData.SamsungGalaxyS9.getId())
         .accept(MediaType.APPLICATION_JSON)
         .exchange()
         .expectStatus().isOk()
         .expectBody(Smartphone.class)
         .isEqualTo(DummyData.SamsungGalaxyS9);
   }
}