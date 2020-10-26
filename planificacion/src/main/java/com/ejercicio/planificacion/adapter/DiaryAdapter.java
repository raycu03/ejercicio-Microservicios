package com.ejercicio.planificacion.adapter;

import com.ejercicio.planificacion.model.dao.DiaryDAO;
import com.ejercicio.planificacion.repository.DiaryRepository;
import com.ejercicio.planificacion.service.DiaryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryAdapter implements DiaryService {

  @Autowired
  private DiaryRepository diaryRepository;

  @Override
  public List<DiaryDAO> findAll() {
    return diaryRepository.findAll();
  }
}
