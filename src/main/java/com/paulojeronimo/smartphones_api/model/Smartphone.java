package com.paulojeronimo.smartphones_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
      String id = manufacturer + model;
      return new Smartphone(id, manufacturer, model, year);
   }

   public static Smartphone build(String manufacturer, String model) {
      return Smartphone.build(manufacturer, model, 2010);
   }
}