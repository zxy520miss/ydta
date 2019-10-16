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

	@Column(nullable = false,length = 10,name = "sender")
	private String sender;

	@Column(nullable = false,length = 10,name = "recipient")
	private String recipient;

	@Column(nullable = false,length = 10,name = "type")
	private String type;

}