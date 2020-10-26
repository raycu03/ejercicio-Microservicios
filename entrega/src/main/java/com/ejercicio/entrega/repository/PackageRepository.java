package com.ejercicio.entrega.repository;

import com.ejercicio.entrega.model.dao.PackageDAO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PackageRepository extends JpaRepository<PackageDAO, Integer> {

  @Query("select pac from PackageDAO pac where pac.day is not null and pac.id = ?1")
  Optional<PackageDAO> validateDelivery(Integer id);

  @Query("select pac from PackageDAO pac where pac.day is null ")
  List<PackageDAO> delivery();

  Optional<PackageDAO> findByCode(String code);

}
