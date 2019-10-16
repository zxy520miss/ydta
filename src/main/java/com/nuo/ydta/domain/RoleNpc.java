package com.nuo.ydta.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "NUO_ROLENPC")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RoleNpc extends BaseDomain{

	@Column(nullable = false,length = 10,name = "favorability")
	private Integer favorability;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false,length = 10,name = "npcId")
	private Integer npcId;
	@Column(nullable = false,length = 10,name = "roleId")
	private Integer roleId;
	@Column(nullable = false,length = 10,name = "npcName")
	private String npcName;
	@Column(nullable = false,length = 10,name = "roleName")
	private String roleName;
}