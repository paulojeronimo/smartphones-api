package com.paulojeronimo.smartphones_api.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@Builder
public class BookingDto
{
   private String smartPhoneId;
   private String userId;
   private LocalDateTime bookingTime;
   private LocalDateTime returnTime;

   private Status status;

   public String id() {
      return smartPhoneId;
   }

   public enum Status
   {
      BOOKED_BY_ANOTHER_USER, BOOKED, NOT_BOOKED, RELEASED, ALREADY_BOOKED, NOT_FOUND
   }
}