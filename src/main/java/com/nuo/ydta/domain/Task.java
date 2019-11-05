package com.nuo.ydta.domain;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "NUO_TASK")
public class Task extends BaseDomain {

    @Column(nullable = false,name = "description")
    private String description;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,name = "roleId")
    private Integer roleId;

    @Column(nullable = false,name = "stageId")
    private Integer stageId;

    @Column(nullable = false,name = "status")
    private Integer status;
}
