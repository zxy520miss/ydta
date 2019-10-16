package com.nuo.ydta.domain;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import javax.persistence.*;


@Entity
@Data
@Table(name = "NUO_MEMO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Memo extends BaseDomain{

	@Column(nullable = false,length = 500,name = "content")
	private String content;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false,name = "roleId")
	private Integer roleId;


}