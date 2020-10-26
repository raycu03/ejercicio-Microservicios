package com.ejercicio.planificacion.repository;

import com.ejercicio.planificacion.model.dao.RegionsDAO;
import com.ejercicio.planificacion.model.dao.VehiclesDAO;
import com.ejercicio.planificacion.model.dto.RegionsDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehiclesRepository extends JpaRepository<VehiclesDAO, Integer> {

  List<VehiclesDAO> findByRegions(RegionsDAO regionsDAO);

}
