package com.ejercicio.planificacion.shared;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import lombok.Getter;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
public enum Day {
  DOMINGO(Day.DOMINGO_CODE(), "Domingo"),
  LUNES(Day.LUNES_CODE(), "Lunes"),
  MARTES(Day.MARTES_CODE(), "Martes"),
  MIERCOLES(Day.MIERCOLES_CODE(), "Miercoles"),
  JUEVES(Day.JUEVES_CODE(), "Jueves"),
  VIERNES(Day.VIERNES_CODE(), "Viernes"),
  SABADO(Day.SABADO_CODE(), "Sabado");


  public static  Integer DOMINGO_CODE(){
    return 1;
  }
  public static  Integer LUNES_CODE (){
    return 2;
  }
  public static  Integer MARTES_CODE(){
    return 3;
  }
  public static  Integer MIERCOLES_CODE (){
    return 4;
  }
  public static  Integer JUEVES_CODE(){
    return 5;
  }
  public static  Integer VIERNES_CODE (){
    return 6;
  }
  public static  Integer SABADO_CODE(){
    return 7;
  }


  private final Integer value;

  private final String name;

  Day(Integer value, String name) {
    this.value = value;
    this.name = name;
  }
  private static final HashMap<Integer, Day> ENUM_MAP_BY_CODE = new HashMap<>();

  static {
    ENUM_MAP_BY_CODE.put(DOMINGO_CODE(), DOMINGO);
    ENUM_MAP_BY_CODE.put(LUNES_CODE(), LUNES);
    ENUM_MAP_BY_CODE.put(MARTES_CODE(), MARTES);
    ENUM_MAP_BY_CODE.put(MIERCOLES_CODE(), MIERCOLES);
    ENUM_MAP_BY_CODE.put(JUEVES_CODE(), JUEVES);
    ENUM_MAP_BY_CODE.put(VIERNES_CODE(), VIERNES);
    ENUM_MAP_BY_CODE.put(SABADO_CODE(), SABADO);

  }


  public static Day findById(Integer id){
    return ENUM_MAP_BY_CODE.get(id);

  }

  public static Collection<Day> getList (){
    return ENUM_MAP_BY_CODE.values();
  }

  public static List<Integer> getIdList (){
    return new ArrayList<>(ENUM_MAP_BY_CODE.keySet());
  }
}
