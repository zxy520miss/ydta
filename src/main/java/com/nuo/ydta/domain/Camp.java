package com.nuo.ydta.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "NUO_CAMP")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Camp extends BaseDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,name = "description")
    private String description;
    @Column(name = "status")
    private String status;

}
