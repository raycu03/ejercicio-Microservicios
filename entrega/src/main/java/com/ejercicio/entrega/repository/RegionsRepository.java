package com.ejercicio.entrega.repository;

import com.ejercicio.entrega.model.dao.RegionsDAO;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegionsRepository extends JpaRepository<RegionsDAO, Integer> {

  Optional<RegionsDAO> findByCode(String code);

}
