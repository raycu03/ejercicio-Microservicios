package com.ejercicio.planificacion.controller;

import com.ejercicio.planificacion.model.dao.DiaryDAO;
import com.ejercicio.planificacion.model.dto.RegionsDto;
import com.ejercicio.planificacion.service.DiaryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/diary")
public class DiaryController {

  @Autowired
  private DiaryService diaryService;

  @GetMapping
  public List<DiaryDAO> findAll() {
    return diaryService.findAll();
  }

}
