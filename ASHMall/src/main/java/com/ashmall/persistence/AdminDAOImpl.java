package com.ashmall.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ashmall.domain.AdminVO;
import com.ashmall.domain.MemberVO;
import com.ashmall.domain.OrderListVO;
import com.ashmall.domain.OrderReadDetailVO;
import com.ashmall.domain.OrderVO;
import com.ashmall.dto.AdminDTO;
import com.ashmall.util.SearchCriteria;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	SqlSession session;
	public final static String NS="com.ashmall.mappers.AdminMapper";
	
	// 로그인 
	@Override
	public AdminVO login(AdminDTO dto) throws Exception {
		return session.selectOne(NS+ ".login", dto);
	}

	// 로그인 시간 업데이트 
	@Override
	public void loginUpdate(String admin_id) throws Exception {
		session.update(NS+ ".loginUpdate", admin_id);
		
	}
	
	// 관리자 유저 목록 기능
	@Override
	public List<MemberVO> userList() throws Exception {
		return session.selectList(NS + ".userList");
	}

	// 관리자 유저 목록 검색 기능 
	@Override
	public List<MemberVO> userlistSearch(SearchCriteria cri) throws Exception {
		return session.selectList(NS + ".userlistSearch", cri);
	}

	// 관리자 유저 목록 검색 카운트 기능
	@Override
	public int userlistSearchCount(SearchCriteria cri) throws Exception {
		return session.selectOne(NS + ".userlistSearchCount", cri);
	}

	// 관리자 유저 삭제 
	@Override
	public void userDelete(String mb_id) throws Exception {
		session.delete(NS + ".userDelete", mb_id);
		
	}

	// 관리자 회원 정보
	@Override
	public MemberVO userInfo(MemberVO vo) throws Exception {
		return session.selectOne(NS + ".userInfo", vo);
	}

	// 관리자 회원정보수정 
	@Override
	public void userModify(MemberVO vo) throws Exception {
		session.update(NS + ".userModify", vo);
	}

	/* 유저 주문정보 */
	// 주문 목록
	@Override
	public List<OrderVO> orderList() throws Exception {
		return session.selectList(NS + ".orderList");
	}
	
	// 특정 주문 목록 
	@Override
	public List<OrderListVO> readOrder(int od_num) throws Exception {
		return session.selectList(NS + ".readOrder", od_num);
	}
	

	// 주문 상세정보
	/* @Override
	public List<OrderReadDetailVO> readOrder(int od_num) throws Exception {
		return session.selectList(NS + ".readOrder", od_num);
	} */

	// 주문자 정보
	@Override
	public OrderVO getOrder(int od_num) throws Exception {
		return session.selectOne(NS + ".getOrder", od_num);
	}

	@Override
	public void deleteOrder(int od_num) throws Exception {
		session.delete(NS + ".deleteOrder", od_num);
	}
	
}
