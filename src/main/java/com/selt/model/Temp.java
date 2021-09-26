package com.selt.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Temp {

  private Long radio;
  private String start;
  private String end;
  private String tempString;
  private Long id;


  public void setId(Long id) {
    this.id = id;
  }

  @Id
  public Long getId() {
    return id;
  }
}
