package com.nuo.ydta.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "NUO_MESSAGEPUSH")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessagePush extends BaseDomain{

	@Column(nullable = false,length = 200,name = "content")
	private String content;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(length = 10,name = "roleId")
	private int roleId;

	@Column(length = 10,name = "stage")
	private int  stage;

}