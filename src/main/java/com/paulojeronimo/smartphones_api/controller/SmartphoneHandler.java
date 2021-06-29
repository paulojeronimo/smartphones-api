package com.paulojeronimo.smartphones_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

import reactor.core.publisher.Mono;

import com.paulojeronimo.smartphones_api.model.Smartphone;
import com.paulojeronimo.smartphones_api.service.SmartphoneService;

//@Component
public class SmartphoneHandler
{
   @Autowired
   SmartphoneService service;

   public Mono<ServerResponse> findAll(ServerRequest request)
   {
      return ok()
         .contentType(MediaType.APPLICATION_JSON)
         .body(service.findAll(), Smartphone.class);
   }

   public Mono<ServerResponse> findById(ServerRequest request)
   {
      String id = request.pathVariable("id");
      return ok()
         .contentType(MediaType.APPLICATION_JSON)
         .body(service.findById(id), Smartphone.class);
   }

   public Mono<ServerResponse> save(ServerRequest request)
   {
      final Mono<Smartphone> smartphoneList = request.bodyToMono(Smartphone.class);
      return ok()
         .contentType(MediaType.APPLICATION_JSON)
         .body(fromPublisher(smartphoneList.flatMap(service::save), Smartphone.class));
   }
}
