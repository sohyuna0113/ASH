package com.ashmall.service;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ashmall.domain.MemberVO;
import com.ashmall.dto.MemberDTO;
import com.ashmall.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO dao; // 다형성 
	
	@Inject	// 주입 어노테이션. spring-security.xml에 클래스의 bean객체 생성
	private BCryptPasswordEncoder passwdEncrypt;
	

	@Override
	public MemberVO readUserInfo(String mb_id) throws Exception {
		return dao.readUserInfo(mb_id);
	}

	// 로그인 
	@Transactional
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		MemberDTO memDTO = dao.login(dto);	// 아이디 파라미터만 사용 
		
		if(memDTO != null) {
			if(passwdEncrypt.matches(dto.getMb_password(), memDTO.getMb_password())) {
				dao.loginUpdate(memDTO.getMb_id());	// 로그인 시간저장 
			} else {
				// 비밀번호가 일치하지 않으면 null 반환 
				memDTO = null; 
			}
		}
		
		return memDTO;
	}

	// 회원가입
	@Override
	public void join(MemberVO vo) throws Exception {
		dao.join(vo);
	}

	// 아이디 중복체크
	@Override
	public int checkIdDuplicate(String mb_id) throws Exception {
		return dao.checkIdDuplicate(mb_id);
	}

	// 회원정보수정
	@Override
	public void modifyUserInfo(MemberVO vo) throws Exception {
		dao.modifyUserInfo(vo);
		
	}

	//	비밀번호 변경
	@Override
	public void ChangePw(MemberDTO dto) throws Exception {
		dao.ChangePw(dto);
	}
	
	// 회원탈퇴
	@Override
	public void deleteUser(String mb_id) throws Exception {
		dao.deleteUser(mb_id);
	}
	
	// 
	public MemberVO checkUserSession(String value) throws Exception {
		return null;
	}

	// 쿠키 저장
	@Override
	public void saveCookie(String sessionKey, Date sessionLimit, String mb_id) throws Exception {
		
	}
}
