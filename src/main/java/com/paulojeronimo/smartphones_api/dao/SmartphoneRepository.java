package com.paulojeronimo.smartphones_api.dao;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import com.paulojeronimo.smartphones_api.model.Smartphone;

public interface SmartphoneRepository extends ReactiveMongoRepository<Smartphone, String>
{

}
