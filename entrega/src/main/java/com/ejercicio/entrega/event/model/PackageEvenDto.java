package com.ejercicio.entrega.event.model;


import java.util.Calendar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackageEvenDto {

  private Integer id;
  private String regionsCode;
  private String code;
  private Calendar day;

}
