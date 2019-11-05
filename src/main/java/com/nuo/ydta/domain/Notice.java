package com.nuo.ydta.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "NUO_NOTICE")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Notice extends BaseDomain {

	@Column(nullable = false,length = 500,name = "content")
	private String content;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "roleId")
	private Integer roleId;

	@Column(nullable = false,name = "stageId")
	private Integer stageId;

	@Column(nullable = false,name = "status")
	private int status;

}