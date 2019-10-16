package com.nuo.ydta.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.persistence.*;

@Entity
@Table(name = "NUO_STAGE")
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Stage extends BaseDomain {

	@Column(nullable = false,length = 50,name = "description")
	private String description;

	@Column(nullable = false,length = 10,name = "name")
	private String name;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false,name = "status")
	private Integer status;

}