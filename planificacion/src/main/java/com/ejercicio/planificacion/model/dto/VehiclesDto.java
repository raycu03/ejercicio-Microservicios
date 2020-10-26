package com.ejercicio.planificacion.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VehiclesDto {

  private Integer id;
  private String code;
  private Integer maxNumberDeliveries;
  private RegionsDto regions;
}
