package com.ashmall.persistence;

import java.util.List;

import com.ashmall.domain.AdminVO;
import com.ashmall.domain.MemberVO;
import com.ashmall.domain.OrderListVO;
import com.ashmall.domain.OrderReadDetailVO;
import com.ashmall.domain.OrderVO;
import com.ashmall.dto.AdminDTO;
import com.ashmall.util.SearchCriteria;

public interface AdminDAO {

	// 로그인
	public AdminVO login(AdminDTO dto) throws Exception;
	
	// 로그인 시간 업데이트
	public void loginUpdate(String admin_id) throws Exception;
	
	
	/* 관리자 기능 */
	
	// 관리자 유저 목록 기능
	public List<MemberVO> userList() throws Exception;
	
	// 관리자 유저 목록 검색 기능 
	public List<MemberVO> userlistSearch(SearchCriteria cri) throws Exception;
	
	// 관리자 유저 목록 검색 카운트 기능
	public int userlistSearchCount(SearchCriteria cri) throws Exception;
	
	// 관리자 유저 삭제 
	public void userDelete(String mb_id) throws Exception;
	
	// 관리자 회원 정보
	public MemberVO userInfo(MemberVO vo) throws Exception;
	
	// 관리자 회원정보수정 
	public void userModify(MemberVO vo) throws Exception;
	
	// 주문 목록
	public List<OrderVO> orderList() throws Exception;
	
	// 특정 주문 목록
	public List<OrderListVO> readOrder(int od_num) throws Exception;
	
	// 주문 상세정보
	// public List<OrderReadDetailVO> readOrder(int od_num) throws Exception;
	
	// 주문자 정보
	public OrderVO getOrder(int od_num) throws Exception;
	
	// 주문 삭제
	public void deleteOrder(int od_num) throws Exception;
	
}