package com.ejercicio.entrega.controller;

import com.ejercicio.entrega.model.dto.PackageDto;
import com.ejercicio.entrega.model.dto.RegionsDto;
import com.ejercicio.entrega.service.PackageService;
import com.ejercicio.entrega.service.RegionsService;
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
@RequestMapping("/package")
public class PackageController {

  @Autowired
  private PackageService packageService;
  @Autowired
  private RegionsService regionsService;

  @PostMapping
  public PackageDto save(@RequestBody PackageDto packageDAO) {
    packageDAO.setId(0);
    packageDAO.setRegions(loadRegions(packageDAO.getRegions()));
    packageDAO.setDay(null);
    return packageService.save(packageDAO);
  }

  @PutMapping
  public PackageDto update(@RequestBody PackageDto packageDAO) {
    packageDAO.setDay(null);
    packageDAO.setRegions(loadRegions(packageDAO.getRegions()));
    return packageService.update(packageDAO);
  }

  @GetMapping
  public List<PackageDto> findAll() {
    return packageService.findAll();
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Integer id) {
    packageService.delete(id);
  }

  @PostMapping("/generate")
  public void generate() {
    packageService.generate();
  }

  private RegionsDto loadRegions(RegionsDto regionsDto) {
    return regionsService.findById(regionsDto.getId());

  }


}
