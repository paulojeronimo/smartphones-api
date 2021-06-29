package com.paulojeronimo.smartphones_api.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paulojeronimo.smartphones_api.dao.SmartphoneRepository;
import com.paulojeronimo.smartphones_api.model.Smartphone;

@Service
public class SmartphoneServiceImpl implements SmartphoneService
{
   @Autowired
   SmartphoneRepository repository;

   @Override
   public Flux<Smartphone> findAll()
   {
      return repository.findAll();
   }

   @Override
   public Mono<Smartphone> findById(String id)
   {
      return repository.findById(id);
   }

   @Override
   public Mono<Smartphone> save(Smartphone smartphone)
   {
      return repository.save(smartphone);
   }
}
