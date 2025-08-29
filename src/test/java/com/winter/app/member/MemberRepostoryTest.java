package com.winter.app.member;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class MemberRepostoryTest {

	@Autowired
	private MemberRepostory memberRepostory;
	
	@Test
	void test() {
		MemberVO memberVO= new MemberVO();
		memberVO.setUsername("user2");
		memberVO.setPassword("pw2");
		memberVO.setName("name2");
		memberVO.setEmail("email2@email.com");
		memberVO.setBirth(LocalDate.now());
		
		List<MemberRoleVO> list = new ArrayList<>();
		RoleVO roleVO = new RoleVO();
		roleVO.setRoleNum(2L);
		
		MemberRoleVO memberRoleVO = new MemberRoleVO();
		memberRoleVO.setMemberVO(memberVO);
		memberRoleVO.setRoleVO(roleVO);
		
		list.add(memberRoleVO);
		
		memberVO.setList(list);
		
		memberRepostory.save(memberVO);
		
	}

}
