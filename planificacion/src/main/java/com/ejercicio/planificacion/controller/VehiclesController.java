package com.ejercicio.planificacion.controller;

import com.ejercicio.planificacion.model.dto.RegionsDto;
import com.ejercicio.planificacion.model.dto.VehiclesDto;
import com.ejercicio.planificacion.service.RegionsService;
import com.ejercicio.planificacion.service.VehiclesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicles")
public class VehiclesController {

  @Autowired
  private VehiclesService vehiclesService;
  @Autowired
  private RegionsService regionsService;

  @PostMapping
  public VehiclesDto save(@RequestBody VehiclesDto vehiclesDto) {
    vehiclesDto.setId(0);
    vehiclesDto.setRegions(loadRegions(vehiclesDto.getRegions().getId()));
    return vehiclesService.save(vehiclesDto);
  }

  @PutMapping
  public VehiclesDto update(@RequestBody VehiclesDto vehiclesDto) {
    return vehiclesService.update(vehiclesDto);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    vehiclesService.delete(id);
  }

  private RegionsDto loadRegions(Integer integer){
    return regionsService.findById(integer);
  }

  @GetMapping
  public List<VehiclesDto> delete() {
    return vehiclesService.findAll();
  }

}
