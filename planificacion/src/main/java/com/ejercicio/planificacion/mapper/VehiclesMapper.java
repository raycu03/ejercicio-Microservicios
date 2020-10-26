package com.ejercicio.planificacion.mapper;

import com.ejercicio.planificacion.model.dao.VehiclesDAO;
import com.ejercicio.planificacion.model.dto.VehiclesDto;
import com.ejercicio.planificacion.shared.mapper.MapperDto;

public class VehiclesMapper implements MapperDto<VehiclesDAO, VehiclesDto> {

  public static final VehiclesMapper INSTANCE = new VehiclesMapper();

  @Override
  public VehiclesDto write(VehiclesDAO instance) {
    return VehiclesDto.builder().id(instance.getId()).code(instance.getCode()).maxNumberDeliveries(
        instance.getMaxNumberDeliveries()).regions(RegionsMapper.INSTANCE.write(instance.getRegions())).build();
  }

  @Override
  public VehiclesDAO read(VehiclesDto instance) {
    return VehiclesDAO.builder().id(instance.getId()).code(instance.getCode()).maxNumberDeliveries(
        instance.getMaxNumberDeliveries()).regions(RegionsMapper.INSTANCE.read(instance.getRegions())).build();
  }
}
