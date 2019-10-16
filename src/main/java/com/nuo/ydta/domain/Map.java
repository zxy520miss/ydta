package com.nuo.ydta.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "NUO_MAP")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Map extends BaseDomain{

	@Column(nullable = false,length = 10,name = "description")
	private String description;
	@Column(nullable = false,name = "floor")
	private Integer floor;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false,name = "name")
	private String name;
	@Column(nullable = false,name = "parentId")
	private Integer parentId;
	@Column(nullable = false,name = "status")
	private Integer status;
	@Column(nullable = false,name = "url")
	private String url;


}