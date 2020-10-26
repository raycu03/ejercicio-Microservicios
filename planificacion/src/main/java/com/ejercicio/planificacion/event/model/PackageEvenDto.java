package com.ejercicio.planificacion.event.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackageEvenDto {

  private String regionsCode;
  private String code;
  private Integer day;

}
