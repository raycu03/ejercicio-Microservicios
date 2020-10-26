package com.ejercicio.planificacion.controller;

import com.ejercicio.planificacion.model.dto.RegionsDto;
import com.ejercicio.planificacion.service.RegionsService;
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
@RequestMapping("/regions")
public class RegionsController {


  @Autowired
  private RegionsService regionsService;

  @PostMapping
  public RegionsDto save(@RequestBody RegionsDto regions) {
    regions.setId(0);
    return regionsService.save(regions);
  }

  @PutMapping
  public RegionsDto update(@RequestBody RegionsDto regions) {
    return regionsService.update(regions);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    regionsService.delete(id);
  }


  @GetMapping
  public List<RegionsDto> findAll() {
    return regionsService.findAll();
  }
}
