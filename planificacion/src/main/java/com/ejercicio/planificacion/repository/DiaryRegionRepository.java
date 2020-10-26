package com.ejercicio.planificacion.repository;

import com.ejercicio.planificacion.model.dao.DiaryRegionDAO;
import com.ejercicio.planificacion.model.dao.RegionsDAO;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaryRegionRepository extends JpaRepository<DiaryRegionDAO,Integer> {

  Optional<DiaryRegionDAO> findByRegions(RegionsDAO regionsDAO);

  @Query("select dia from DiaryRegionDAO dia where dia.regions.id = ?1")
  Optional<DiaryRegionDAO> findByRegionsId(Integer id);
}
