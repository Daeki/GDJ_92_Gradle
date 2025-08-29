package com.winter.app.member;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *  Serializable 구현
 *  equals, hashcode 구현,
 *  기본생성자 필수
 *  클래스는 public이어야 함
 * */

@Getter
@Setter
@EqualsAndHashCode
@Embeddable
public class MemberRolePK implements Serializable {
	
	private String username;
	private Long roleNum;

}
