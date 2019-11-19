package com.nuo.ydta.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
@Table(name = "NUO_RELATIONSHIPS")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Relationships {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private int roleId;
    @Column
    private String roleName;
    @Column
    private String relationshipseName;
    @Column
    private String desc;

}
