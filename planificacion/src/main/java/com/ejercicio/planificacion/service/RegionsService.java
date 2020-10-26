package com.ejercicio.planificacion.service;


import com.ejercicio.planificacion.model.dto.RegionsDto;
import java.util.List;

public interface RegionsService {

    RegionsDto save(RegionsDto regionsDto);

    RegionsDto update(RegionsDto regionsDto);

    RegionsDto findById(Integer id);

    void delete(Integer id);

    List<RegionsDto> findAll();
}
