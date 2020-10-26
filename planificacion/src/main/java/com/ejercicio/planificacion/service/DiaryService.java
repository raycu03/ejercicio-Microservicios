package com.ejercicio.planificacion.service;

import com.ejercicio.planificacion.model.dao.DiaryDAO;
import java.util.List;

public interface DiaryService {

  List<DiaryDAO> findAll();

}
