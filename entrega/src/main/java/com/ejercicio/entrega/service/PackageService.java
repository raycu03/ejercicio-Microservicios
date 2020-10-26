package com.ejercicio.entrega.service;


import com.ejercicio.entrega.model.dto.PackageDto;
import java.util.List;

public interface PackageService {

  PackageDto save(PackageDto packageDto);

  PackageDto update(PackageDto packageDto);

  void delete(Integer id);

  List<PackageDto> findAll();

  PackageDto findById(Integer id);

  public void generate();

}
