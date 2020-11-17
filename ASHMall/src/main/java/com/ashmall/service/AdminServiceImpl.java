package com.ashmall.service;

import java.util.List;

import javax.inject.Inject;

import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ashmall.domain.AdminVO;
import com.ashmall.domain.MemberVO;
import com.ashmall.domain.OrderListVO;
import com.ashmall.domain.OrderReadDetailVO;
import com.ashmall.domain.OrderVO;
import com.ashmall.dto.AdminDTO;
import com.ashmall.persistence.AdminDAO;
import com.ashmall.persistence.OrderDAO;
import com.ashmall.util.SearchCriteria;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO dao;
	
	@Autowired
	private OrderDAO dao2;
	
	@Autowired
	private CartService cartService;
	
	@Override
	public AdminVO login(AdminDTO dto) throws Exception {
		
		AdminVO vo = dao.login(dto);
		
		if(vo!=null) {
			
			dao.loginUpdate(dto.getAdmin_id());
		}
		
		return vo;
	}

	/* 관리자 기능 */
	
	// 관리자 유저 목록 기능
	@Override
	public List<MemberVO> userList() throws Exception {
		return dao.userList();
	}

	// 관리자 유저 목록 검색 기능 
	@Override
	public List<MemberVO> userlistSearch(SearchCriteria cri) throws Exception {
		return dao.userlistSearch(cri);
	}

	// 관리자 유저 목록 검색 카운트 기능
	@Override
	public int userlistSearchCount(SearchCriteria cri) throws Exception {
		return dao.userlistSearchCount(cri);
	}

	// 관리자 유저 삭제
	@Override
	public void userDelete(String mb_id) throws Exception {
		dao.userDelete(mb_id);
	}

	// 관리자 회원 정보
	@Override
	public MemberVO userInfo(MemberVO vo) throws Exception {
		return dao.userInfo(vo);
	}

	// 관리자 회원정보수정 
	@Override
	public void userModify(MemberVO vo) throws Exception {
		dao.userModify(vo);
	}

	/* 유저 주문정보 */
	// 주문 목록
	@Override
	public List<OrderVO> orderList() throws Exception {
		return dao.orderList();
	}
	
	@Override
	// 특정 주문 목록 
	public List<OrderListVO> readOrder(int od_num) throws Exception {
		return dao.readOrder(od_num);
	}

	// 주문 상세정보
	/* @Override
	public List<OrderReadDetailVO> readOrder(int od_num) throws Exception {
		return dao2.readOrder(od_num);
	} */

	// 주문자 정보
	@Override
	public OrderVO getOrder(int od_num) throws Exception {
		return dao2.getOrder(od_num);
	}

	// 주문 삭제
	@Override
	public void deleteOrder(int od_num) throws Exception {
		dao.deleteOrder(od_num);
	}
}
