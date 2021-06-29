package com.paulojeronimo.smartphones_api.controller;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.paulojeronimo.smartphones_api.model.Smartphone;
import com.paulojeronimo.smartphones_api.service.SmartphoneService;

@CrossOrigin(origins = "*")
@RestController
public class SmartphoneController
{

   @Autowired
   SmartphoneService service;

   @GetMapping(value = "/smartphone")
   public Flux<Smartphone> findAll()
   {
      return service.findAll();
   }

   @GetMapping(value = "/smartphone/{id}")
   public Mono<Smartphone> findById(
      @PathVariable
         String id)
   {
      return service.findById(id);
   }

   @PostMapping(value = "/smartphone")
   public Mono<Smartphone> save(
      @RequestBody
         Smartphone smartphone)
   {
      return service.save(smartphone);
   }

   @GetMapping(value = "/smartphone/webflux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
   public Flux<Tuple2<Long, Smartphone>> findAllWithWebFlux()
   {
      System.out.println("---Start get Smartphones by WEBFLUX--- " + LocalDateTime.now());
      Flux<Long> interval = Flux.interval(Duration.ofSeconds(3));
      Flux<Smartphone> smartphoneFlux = service.findAll();
      return Flux.zip(interval, smartphoneFlux);
   }
}