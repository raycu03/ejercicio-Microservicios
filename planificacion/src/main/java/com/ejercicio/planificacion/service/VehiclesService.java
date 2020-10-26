package com.ejercicio.planificacion.service;

import com.ejercicio.planificacion.model.dto.VehiclesDto;
import java.util.List;

public interface VehiclesService {

  VehiclesDto save(VehiclesDto vehiclesDto);

  VehiclesDto update(VehiclesDto vehiclesDto);

  VehiclesDto findById(Integer id);

  List<VehiclesDto> findAll();

  void delete(Integer id);

}
