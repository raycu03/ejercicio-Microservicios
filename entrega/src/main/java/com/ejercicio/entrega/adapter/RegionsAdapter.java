package com.ejercicio.entrega.adapter;

import com.ejercicio.entrega.mapper.RegionsMapper;
import com.ejercicio.entrega.model.dto.RegionsDto;
import com.ejercicio.entrega.repository.RegionsRepository;
import com.ejercicio.entrega.service.RegionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionsAdapter implements RegionsService {

  @Autowired
  private RegionsRepository regionsRepository;

  @Override
  public RegionsDto findById(Integer id) {
    return RegionsMapper.INSTANCE.write(regionsRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Region no encontrada")));
  }
}
