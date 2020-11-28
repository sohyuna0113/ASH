package com.ashmall.persistence;

import java.util.List;

import com.ashmall.domain.BoardVO;
import com.ashmall.util.Criteria;
import com.ashmall.util.SearchCriteria;

public interface BoardDAO {

	public void create(BoardVO vo) throws Exception;
	
	public BoardVO read(Integer bd_num) throws Exception;
	
	public void update(BoardVO vo) throws Exception;
	
	public void delete(Integer bd_num) throws Exception;
	
	public List<BoardVO> listAll() throws Exception;
	
	public List<BoardVO> listPage(int page) throws Exception;
	
	public List<BoardVO> listCriteria(Criteria cri) throws Exception;
	
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception;
	
	public int listSearchCount(SearchCriteria cri) throws Exception;
	
	public void updateReplyCnt(Integer bd_num, int amount) throws Exception;
	
	public void updateViewCnt(Integer bd_num) throws Exception;
	
}


