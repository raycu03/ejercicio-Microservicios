package com.ejercicio.entrega.service;

import com.ejercicio.entrega.model.dto.RegionsDto;
import org.springframework.web.bind.annotation.RequestBody;

public interface RegionsService {

  RegionsDto findById(@RequestBody Integer integer);

}
