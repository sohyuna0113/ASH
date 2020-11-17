package com.ashmall.persistence;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ashmall.domain.MemberVO;
import com.ashmall.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired /* ibatis SqlSession어노테이션 */
	private SqlSession session;
	// MemberMapper을 가르키는 상수 NS를 생성.
	private final static String NS = "com.ashmall.mappers.MemberMapper";	

	// MemberVO 
	@Override
	public MemberVO readUserInfo(String mb_id) throws Exception {
		return session.selectOne(NS+ ".readUserInfo", mb_id);
	}

	// 로그인
	@Transactional
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		return session.selectOne(NS+ ".login", dto);
	}

	// 로그인 업데이트
	@Override
	public void loginUpdate(String mb_id) throws Exception {
		session.update(NS+".loginUpdate", mb_id);
	}

	// 회원가입
	@Override /* mapper의 id명과 메소드명이 같게끔 작업 */
	public void join(MemberVO vo) throws Exception {	
		session.insert(NS+".join", vo); // MemberMapper의 회원가입으로 데이터삽입 
	}

	// 아이디 중복체크
	@Override
	public int checkIdDuplicate(String mb_id) throws Exception {
		return session.selectOne(NS+ ".checkIdDuplicate", mb_id);
	}

	// 회원정보 수정
	@Override
	public void modifyUserInfo(MemberVO vo) throws Exception {
		session.update(NS+ ".modifyUserInfo", vo);		
	}
	
	// 비밀번호 변경
	@Override
	public void ChangePw(MemberDTO dto) throws Exception {
		session.update(NS+ ".changePw", dto);
	}
	
	// 회원 탈퇴 
	@Override
	public void deleteUser(String mb_id) throws Exception {
		session.delete(NS+ ".deleteUser", mb_id);
	}
	
	// 로그인 정보 쿠키 저장
	@Override
	public void saveCookie(Map<String, Object> map) throws Exception {
		session.update(NS+ ".saveCookie", map);
	}
	
	// 쿠키에 저장된 세션값으로 로그인 정보 가져옴
	@Override
	public MemberVO checkUserSession(String value) throws Exception {
		return session.selectOne(NS+ ".checkUserSession", value);
	}

	@Override
	public String getTime() throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(NS+ ".getTime");
	}

}
