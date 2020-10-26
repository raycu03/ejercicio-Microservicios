package com.ejercicio.planificacion.repository;

import com.ejercicio.planificacion.model.dao.DiaryRegionDetailsDAO;
import com.ejercicio.planificacion.model.dao.VehiclesDAO;
import java.util.Calendar;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRegionDetailsRepository extends JpaRepository<DiaryRegionDetailsDAO, Integer> {

  List<DiaryRegionDetailsDAO> findByVehicles(VehiclesDAO vehiclesDAO);

  List<DiaryRegionDetailsDAO> findByVehiclesAndDay(VehiclesDAO vehiclesDAO, Calendar day);

}
