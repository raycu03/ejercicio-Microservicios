package com.ejercicio.entrega.model.dao;



import java.util.Calendar;
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
@Table(name = "package")
@Builder
public class PackageDAO {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @OneToOne
  private RegionsDAO regions;
  @Column(unique = true, updatable = false)
  private String code;
  private Calendar day;

}
