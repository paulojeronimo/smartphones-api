package com.paulojeronimo.smartphones_api.model;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.paulojeronimo.smartphones_api.dao.SmartphoneRepository;

@Slf4j
@Component
public class DummyData implements CommandLineRunner
{
   private final SmartphoneRepository repository;

   DummyData(SmartphoneRepository repository)
   {
      this.repository = repository;
   }

   private Smartphone[] defaultSmartphones() {
      Smartphone[] array = {
         Smartphone.build("Samsung", "GalaxyS9", 2015),
         Smartphone.build("Samsung", "GalaxyS8", 2014),
         Smartphone.build("Samsung", "GalaxyS7", 2013),
         Smartphone.build("Motorola", "Nexus6", 2000),
         Smartphone.build("LG", "Nexus5X", 2000),
         Smartphone.build("Huawei", "Honor7X", 2000),
         Smartphone.build("Apple", "iPhoneX", 2000),
         Smartphone.build("Apple", "iPhone8", 2000),
         Smartphone.build("Apple", "iPhone4s", 2000),
         Smartphone.build("Nokia", "3310", 2000)
      };
      return array;
   }

   private void showAddingSmartphone(Smartphone smartphone) {
      log.info("Adding " + smartphone);
   }

   @Override
   public void run(String... args) throws Exception
   {
      repository.deleteAll()
         .thenMany(Flux.fromIterable(Arrays.asList(defaultSmartphones()))
            .flatMap(repository::save))
            .subscribe(this::showAddingSmartphone);
   }
}