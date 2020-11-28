package com.ashmall.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ashmall.domain.BoardVO;
import com.ashmall.util.Criteria;
import com.ashmall.util.SearchCriteria;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	SqlSession session;
	public final static String NS="com.ashmall.mappers.BoardMapper";
	
	@Override
	public void create(BoardVO vo) throws Exception {
		session.insert(NS + ".create", vo);

	}

	@Override
	public BoardVO read(Integer bd_num) throws Exception {
		return session.selectOne(NS + ".read", bd_num);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		session.update(NS + ".update", vo);
	}

	@Override
	public void delete(Integer bd_num) throws Exception {
		session.delete(NS + ".delete", bd_num);
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		return session.selectList(NS + ".listAll");
	}

	@Override
	public List<BoardVO> listPage(int page) throws Exception {
	
		if (page <= 0) {
			page = 1;
		}
		
		page = (page - 1) * 10;
		
		return session.selectList(NS + ".listPage", page);
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		return session.selectList(NS + ".listCriteria", cri);
	}

	@Override
	public int countPaging(Criteria cri) throws Exception {
		return session.selectOne(NS + ".countPaging", cri);
	}
	
	
	@Override
	public List<BoardVO> listSearch(SearchCriteria cri) throws Exception {
		return session.selectList(NS + ".listSearch", cri);
	}

	@Override
	public int listSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(NS + ".listSearchCount", cri);
	}

	@Override
	public void updateReplyCnt(Integer bd_num, int amount) throws Exception {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		paramMap.put("bd_num", bd_num);
		paramMap.put("amount", amount);
		
		session.update(NS + ".updateReplyCnt", paramMap);
	}

	@Override
	public void updateViewCnt(Integer bd_num) throws Exception {
		session.update(NS + ".updateViewCnt", bd_num);
	}

}
