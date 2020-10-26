package com.ejercicio.planificacion.repository;

import com.ejercicio.planificacion.model.dao.DiaryDAO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRepository extends JpaRepository<DiaryDAO, Integer> {

}
