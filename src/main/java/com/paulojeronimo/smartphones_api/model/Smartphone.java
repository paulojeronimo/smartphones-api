package com.paulojeronimo.smartphones_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Smartphone
{

   @Id
   private String id;
   private String manufacturer;
   private String model;
   private Integer year;

   public static Smartphone build(String manufacturer, String model, Integer year)
   {
      //String id = UUID.randomUUID().toString();
      String id = manufacturer + model;
      return new Smartphone(id, manufacturer, model, year);
   }
}