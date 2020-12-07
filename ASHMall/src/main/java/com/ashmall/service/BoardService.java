package com.ashmall.service;

import java.util.List;

import com.ashmall.domain.BoardVO;
import com.ashmall.util.Criteria;
import com.ashmall.util.SearchCriteria;

public interface BoardService {

	/* 게시판 쓰기 */
	public void regist(BoardVO vo) throws Exception;
	
	public BoardVO read(Integer bd_num) throws Exception;
	
	public void modify(BoardVO vo) throws Exception;
	
	public void remove(Integer bd_num) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	
	public int listCountCriteria(Criteria cri) throws Exception;
	
	public List<BoardVO> listSearchCriteria(SearchCriteria cri) throws Exception;
	
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
}
