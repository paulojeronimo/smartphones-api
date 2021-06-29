package com.paulojeronimo.smartphones_api.controller;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

//@Configuration
public class SmartphoneRouter
{
	//@Bean
	public RouterFunction<ServerResponse> route(SmartphoneHandler handler){
		return RouterFunctions
				.route(GET("/smartphone").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
				.andRoute(GET("/smartphone/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::findById)
				.andRoute(POST("/smartphone").and(accept(MediaType.APPLICATION_JSON)), handler::save);
			
	}
}
