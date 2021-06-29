package com.paulojeronimo.smartphones_api.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import com.paulojeronimo.smartphones_api.model.Smartphone;

public interface SmartphoneService
{
	Flux<Smartphone> findAll();
	Mono<Smartphone> findById(String id);
	Mono<Smartphone> save(Smartphone smartphone);
}
