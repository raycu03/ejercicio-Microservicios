package com.ejercicio.planificacion.model.dao;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "package")
public class PackageDAO {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  private String regionsCode;
  @Column(unique = true)
  private String code;
  private Calendar day;

}
