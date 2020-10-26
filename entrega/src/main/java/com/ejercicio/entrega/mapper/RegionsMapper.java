package com.ejercicio.entrega.mapper;

import com.ejercicio.entrega.model.dao.RegionsDAO;
import com.ejercicio.entrega.model.dto.RegionsDto;
import com.ejercicio.entrega.shared.mapper.MapperDto;
import org.springframework.stereotype.Component;

@Component
public class RegionsMapper implements MapperDto<RegionsDAO, RegionsDto> {

  public static final RegionsMapper INSTANCE = new RegionsMapper();

  @Override
  public RegionsDto write(RegionsDAO instance) {
    return RegionsDto.builder().id(instance.getId()).name(instance.getName()).code(
        instance.getCode()).build();
  }

  @Override
  public RegionsDAO read(RegionsDto instance) {
    return RegionsDAO.builder().id(instance.getId()).name(instance.getName()).code(
        instance.getCode()).build();
  }
}
