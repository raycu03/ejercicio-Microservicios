package com.ejercicio.entrega.shared;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum  Action {
  GUARDAR( "save"),
  BORRAR("delete"),
  ACTUALIZAR( "update");

  private final String value;

  Action(String value) {

    this.value = value;
  }


}
