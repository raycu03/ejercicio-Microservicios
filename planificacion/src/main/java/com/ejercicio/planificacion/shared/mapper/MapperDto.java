package com.ejercicio.planificacion.shared.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface MapperDto<I, O>  {

  O write(I instance);

  I read(O instance);

  default List<O> write(List<I> instances) {
    return instances.stream().map(this::write).collect(Collectors.toList());
  }

  default List<I> read(List<O> instances) {
    return instances.stream().map(this::read).collect(Collectors.toList());
  }

}
