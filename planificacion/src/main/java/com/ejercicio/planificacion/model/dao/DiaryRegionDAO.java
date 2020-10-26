package com.ejercicio.planificacion.model.dao;

import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "diaryRegion")
public class DiaryRegionDAO {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @OneToOne
  private RegionsDAO regions;
  private Calendar day;
  @OneToMany(fetch = FetchType.EAGER)
  private List<DiaryRegionDetailsDAO> details;

}
