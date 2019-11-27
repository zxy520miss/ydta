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

	@Column(name = "roleId")
	private int roleId;

	@Column(length = 20,name = "title")
	private String title;

	@Column(length = 20,name = "recipient")
	private String recipient;

	@Column(length = 20,name = "sender")
	private String sender;

	@Column(length = 10,name = "type")
	private String type;
}