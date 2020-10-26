package com.ejercicio.planificacion.model.dao;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@Entity
@AllArgsConstructor
@ToString(exclude = "invoice")
@EqualsAndHashCode(exclude = "invoice")
@NoArgsConstructor
@Table(name = "details")
public class DiaryRegionDetailsDAO {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @OneToOne
  private VehiclesDAO vehicles;
  @OneToOne
  private PackageDAO packages;
  private Calendar day;

}
