package com.nuo.ydta.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "NUO_NPC")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Npc extends BaseDomain{

	@Column(nullable = false,length = 100,name = "description")
	private String description;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(nullable = false,length = 100,name = "name")
	private String name;
	@Column(nullable = false,name = "address")
	private String address;
	@Column(nullable = false,name = "status")
	private Integer status;

}