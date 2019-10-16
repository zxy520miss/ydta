package com.nuo.ydta.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "NUO_STATISTICS")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Statistics extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
    @Column(nullable = false,name = "poll")
  private int poll;
    @Column(nullable = false,name = "roleName")
  private String roleName;
    @Column(nullable = false,name = "round")
  private int round;
    @Column(nullable = false,name = "serialNo")
  private String serialNo;

}
