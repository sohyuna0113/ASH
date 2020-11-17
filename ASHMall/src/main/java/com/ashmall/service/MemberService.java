package com.ashmall.service;

import java.util.Date;

import com.ashmall.domain.MemberVO;
import com.ashmall.dto.MemberDTO;

public interface MemberService {

	// MemberVO가져오기
	public MemberVO readUserInfo(String mb_id) throws Exception;
	
	// 로그인
	public MemberDTO login(MemberDTO dto) throws Exception;
	
	// 회원가입
	public void join(MemberVO vo) throws Exception;
	
	// 아이디 중복체크
	public int checkIdDuplicate(String mb_id) throws Exception;
	
	// 회원정보 수정
	public void modifyUserInfo(MemberVO vo) throws Exception;
	
	// 비밀번호 변경
	public void ChangePw(MemberDTO dto) throws Exception;
	
	// 회원 탈퇴
	public void deleteUser(String mb_id) throws Exception;
	
	// 로그인 정보 쿠키 저장
	public void saveCookie(String sessionKey, Date sessionLimit, String mb_id) throws Exception;

	// 쿠키에 저장된 세션값으로 로그인 정보를 가져옴
	public MemberVO checkUserSession(String value) throws Exception;
}
