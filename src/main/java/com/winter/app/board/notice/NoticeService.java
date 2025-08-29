package com.winter.app.board.notice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class NoticeService {

	@Autowired
	private NoticeRepository noticeRepository;
	
	public NoticeVO detail(Long id)throws Exception{
		Optional<NoticeVO> result = noticeRepository.findById(id);
		return result.orElseThrow();
	}
	
	public Page<NoticeVO> list (Pageable pageable) throws Exception{
		
		
		
		Page<NoticeVO> pages = noticeRepository.findAll(pageable);
		
		return pages;
	}
}
