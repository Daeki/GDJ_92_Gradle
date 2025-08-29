package com.winter.app.board.notice;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NoticeRepository extends JpaRepository<NoticeVO, Long> {
	
	public List<NoticeVO> findByBoardTitleLike(String search, Pageable pageable);
	
}
