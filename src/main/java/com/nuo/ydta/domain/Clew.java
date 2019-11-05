package com.nuo.ydta.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "NUO_CLEW")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Clew extends BaseDomain {

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