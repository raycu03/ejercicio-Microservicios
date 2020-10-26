package com.ejercicio.planificacion.model.dao;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Data
@Entity
@NoArgsConstructor
@Table(name = "diary")
public class DiaryDAO {

  @Id
  private Integer id;
  @OneToMany
  private List<DiaryRegionDAO> diaryRegion;
}
