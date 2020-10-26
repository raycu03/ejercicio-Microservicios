package com.ejercicio.planificacion.model.dao;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "vehicles")
public class VehiclesDAO {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column(unique = true)
  private String code;
  private Integer maxNumberDeliveries;
  @OneToOne
  private RegionsDAO regions;

}
