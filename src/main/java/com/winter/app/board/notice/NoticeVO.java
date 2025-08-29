package com.winter.app.board.notice;

import java.util.List;

import com.winter.app.board.BoardVO;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity  //해당 객체가 JPA에서 관리하고 있다라는것을 정의, 필수
@Table(name ="notice") //DB에 존재하는 테이블 이름을 매핑, 클래스명이 테이블명이 됨
@Getter
@Setter
@ToString
public class NoticeVO extends BoardVO {

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "noticeVO", cascade = CascadeType.ALL)
	private List<NoticeFileVO> list;
}
