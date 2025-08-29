package com.winter.app.member;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="member_role")
@Setter
@Getter
@ToString
//@IdClass(MemberRolePK.class)
public class MemberRoleVO {
	
//	@Id
//	private String username;
//	@Id
//	private Long roleNum;
	
//	@EmbeddedId
//	private MemberRolePK memberRolePK;
	
	@ManyToOne
	@JoinColumn(name="username", insertable = false, updatable = false)
	@Id
	private MemberVO memberVO;
	
	@ManyToOne
	@JoinColumn(name="roleNum",insertable = false, updatable = false)	
	@Id
	private RoleVO roleVO;

}
