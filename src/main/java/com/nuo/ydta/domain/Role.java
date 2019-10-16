package com.nuo.ydta.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "NUO_ROLE")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Role extends BaseDomain{

	@Column(nullable = false,name = "camp")
	private int camp;
	@Column(nullable = false,name = "gender")
	private Integer gender;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "modifyCamp")
	private Boolean modifyCamp;
	@Column(nullable = false,length = 20,name = "name")
	private String name;
	@Lob
	@Column(columnDefinition="text",nullable = false,name = "play")
	private String play;
	@Column(name = "poll")
	private Integer poll;
	@Column(nullable = false,length = 50,name = "serialNo")
	private String serialNo;
	@Column(name = "status")
	private Integer status;
	@Column(name = "suspicion")
	private Integer suspicion;
	@Column(name = "halo")
	private Integer halo;
	@Column(length = 50,name = "roleDesc")
	private String roleDesc;

}