package com.ejercicio.entrega.model.dto;

import java.util.Calendar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackageDto {

  private Integer id;
  private RegionsDto regions;
  private String code;
  private Calendar day;
}
