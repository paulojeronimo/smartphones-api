package com.paulojeronimo.smartphones_api.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import com.paulojeronimo.smartphones_api.dao.SmartphoneRepository;

@Slf4j
@Component
@RequiredArgsConstructor
public class DummyData implements CommandLineRunner
{

   public static final Smartphone SamsungGalaxyS9 =
      Smartphone.build("Samsung", "GalaxyS9", 2015);

   private final SmartphoneRepository repository;

   @Override
   public void run(String... args) throws Exception
   {
      repository.deleteAll()
         .thenMany(Flux.fromArray(defaultSmartphones())
            .flatMap(repository::save))
         .subscribe(this::showAddingSmartphone);
   }

   private Smartphone[] defaultSmartphones()
   {
      return new Smartphone[]{
         SamsungGalaxyS9,
         Smartphone.build("Samsung", "GalaxyS8", 2014),
         Smartphone.build("Samsung", "GalaxyS7", 2013),
         Smartphone.build("Motorola", "Nexus6"),
         Smartphone.build("LG", "Nexus5X"),
         Smartphone.build("Huawei", "Honor7X"),
         Smartphone.build("Apple", "iPhoneX"),
         Smartphone.build("Apple", "iPhone8"),
         Smartphone.build("Apple", "iPhone4s"),
         Smartphone.build("Nokia", "3310")
      };
   }

   private void showAddingSmartphone(Smartphone smartphone)
   {
      log.info("Adding " + smartphone);
   }
}