package com.ejercicio.entrega.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegionsDto {

  private Integer id;
  private String code;
  private String name;
}
