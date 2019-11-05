package com.nuo.ydta.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "NUO_PLAY")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Play extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "stage")
    private int stage;

    @Lob
    @Column(columnDefinition="text",nullable = false,name = "content")
    private String content;

    @Column(name = "roleId")
    private int roleId;

    @Column(name = "status")
    private int status;
}
