package com.ejercicio.planificacion.repository;

import com.ejercicio.planificacion.model.dao.PackageDAO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<PackageDAO, Integer> {

  Optional<PackageDAO> findByCode(String code);

  List<PackageDAO> findByRegionsCode(String code);

}
