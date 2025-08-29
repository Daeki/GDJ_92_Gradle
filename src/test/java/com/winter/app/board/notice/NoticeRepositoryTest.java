package com.winter.app.board.notice;

import static org.junit.jupiter.api.Assertions.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import com.winter.app.board.BoardVO;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
@Transactional
class NoticeRepositoryTest {

	@Autowired
	private NoticeRepository noticeRepository;
	
	@Autowired
	private NoticeFileRepository noticeFileRepository;
	
	
	@Test
	void test() throws Exception {
		NoticeVO noticeVO = new NoticeVO();
		noticeVO.setBoardTitle("titl21");
		noticeVO.setBoardContents("contents21");
		noticeVO.setBoardWriter("writer21");
		
//		List<NoticeFileVO> notiList = new ArrayList<>();
//		NoticeFileVO noticeFileVO = new NoticeFileVO();
//		
//		noticeFileVO.setNoticeVO(noticeVO);
//		noticeFileVO.setOriName("ori");
//		noticeFileVO.setSaveName("save");
//		
//		notiList.add(noticeFileVO);
//		
//		noticeVO.setList(notiList);
//		
		noticeVO=noticeRepository.save(noticeVO);
		
		assertNotNull(noticeVO);
		
		
	}
	
	@Test
	void test2() {
		Optional<NoticeVO> result =noticeRepository.findById(2L);
		NoticeVO noticeVO = result.get();
		log.info("{}", noticeVO.getBoardNum());
		log.info("{}", noticeVO.getList().getFirst().getSaveName());
		assertNotNull(noticeVO);
		
	}
	
	@Test
	void test3() {
		Pageable pageable = PageRequest.of(1, 10, Sort.by("boardNum").descending());
		
		List<NoticeVO> list = noticeRepository.findByBoardTitleLike("%%", pageable);
		
		list.forEach((e)->{
			log.info("{}", e.getBoardTitle());
		});
		
		log.info("{}", list.getFirst().getBoardTitle());
	}
	

}
