package com.ashmall.domain;

import java.util.Date;

public class MemberVO {

	/*
	1)테이블의 컬럼명과 VO클래스의 필드명(변수)를 같게하는 경우
	  mapper파일에서 parameterType,returnType 속성에 클래스를 사용 가능 -- 기준

	2)테이블의 컬럼명과 VO클래스의 필드명(변수)를 다르게 하는 경우
	  mapper파일에 resultMap - 데이터베이스 결과데이터를 객체에 로드하는 방법을 정의하는 엘리먼트)
	*/
	
	/*
	 <resultMap id="userResultMap" type="User">
  		<id property="id" column="user_id" />
  		<result property="username" column="username"/>
  		<result property="password" column="password"/>
	</resultMap> 
	 
	 <select id="selectUsers" resultMap="userResultMap">
  		select user_id, user_name, hashed_password
  		from some_table
  		where id = #{id}
	</select>
	 
	 */
	
	/*
    MB_ID               VARCHAR2(15)            PRIMARY KEY,
    MB_NAME             VARCHAR2(30)            NOT NULL,
    MB_PASSWORD         VARCHAR2(60)            NOT NULL,               -- 비밀번호 암호화작업으로 60byte
    MB_EMAIL            VARCHAR2(50)            NOT NULL,
    MB_ZIPCODE          CHAR(5)                 NOT NULL,
    MB_ADDR             VARCHAR2(50)            NOT NULL,
    MB_ADDR_D           VARCHAR2(50)            NOT NULL,
    MB_PHONE            VARCHAR2(15)            NOT NULL,
    MB_NICKNAME         VARCHAR2(20)            NOT NULL UNIQUE,        -- EMAIL 수신여부
    MB_ACCEPT_E         CHAR(1) DEFAULT 'Y'     NOT NULL,
    MB_POINT            NUMBER  DEFAULT 0       NOT NULL,
    MB_JOIN             DATE DEFAULT SYSDATE    NOT NULL,
    MB_UPDATE           DATE DEFAULT SYSDATE    NOT NULL,
    MB_LOGIN            DATE                    NOT NULL,
    MB_SESSION_KEY      VARCHAR2(50),           -- 세션ID 쿠키정보 저장
    MB_SESSION_LIMIT    TIMESTAMP               -- 7일 시간
	 */

	/* SQL문 수정필요 : mb_authcode (String) */
	private String 	mb_id;
	private String 	mb_name;
	private String 	mb_password;
	private String 	mb_email;
	private String	mb_authcode;
	private String 	mb_zipcode;
	private String 	mb_addr;
	private String 	mb_addr_d;
	private String 	mb_phone;
	private String 	mb_nickname;
	private String 	mb_accept_e;
	private int		mb_point;
	private Date 	mb_join;
	private Date 	mb_update;
	private Date	mb_login;
	private String 	mb_session_key;
	private Date	mb_session_limit;
	
	/* Getter and Setter */
	
	public String getMb_id() {
		return mb_id;
	}
	public void setMb_id(String mb_id) {
		this.mb_id = mb_id;
	}
	public String getMb_name() {
		return mb_name;
	}
	public void setMb_name(String mb_name) {
		this.mb_name = mb_name;
	}
	public String getMb_password() {
		return mb_password;
	}
	public void setMb_password(String mb_password) {
		this.mb_password = mb_password;
	}
	public String getMb_email() {
		return mb_email;
	}
	public void setMb_email(String mb_email) {
		this.mb_email = mb_email;
	}
	public String getmb_authcode() {
		return mb_authcode;
	}
	public void setmb_authcode(String mb_authcode) {
		this.mb_authcode = mb_authcode;
	}
	public String getMb_zipcode() {
		return mb_zipcode;
	}
	public void setMb_zipcode(String mb_zipcode) {
		this.mb_zipcode = mb_zipcode;
	}
	public String getMb_addr() {
		return mb_addr;
	}
	public void setMb_addr(String mb_addr) {
		this.mb_addr = mb_addr;
	}
	public String getMb_addr_d() {
		return mb_addr_d;
	}
	public void setMb_addr_d(String mb_addr_d) {
		this.mb_addr_d = mb_addr_d;
	}
	public String getMb_phone() {
		return mb_phone;
	}
	public void setMb_phone(String mb_phone) {
		this.mb_phone = mb_phone;
	}
	public String getMb_nickname() {
		return mb_nickname;
	}
	public void setMb_nickname(String mb_nickname) {
		this.mb_nickname = mb_nickname;
	}
	public String getMb_accept_e() {
		return mb_accept_e;
	}
	public void setMb_accept_e(String mb_accept_e) {
		this.mb_accept_e = mb_accept_e;
	}
	public int getMb_point() {
		return mb_point;
	}
	public void setMb_point(int mb_point) {
		this.mb_point = mb_point;
	}
	public Date getMb_join() {
		return mb_join;
	}
	public void setMb_join(Date mb_join) {
		this.mb_join = mb_join;
	}
	public Date getMb_update() {
		return mb_update;
	}
	public void setMb_update(Date mb_update) {
		this.mb_update = mb_update;
	}
	public Date getMb_login() {
		return mb_login;
	}
	public void setMb_login(Date mb_login) {
		this.mb_login = mb_login;
	}
	public String getMb_session_key() {
		return mb_session_key;
	}
	public void setMb_session_key(String mb_session_key) {
		this.mb_session_key = mb_session_key;
	}
	public Date getMb_session_limit() {
		return mb_session_limit;
	}
	public void setMb_session_limit(Date mb_session_limit) {
		this.mb_session_limit = mb_session_limit;
	}
	
	/* toString() */
	@Override
	public String toString() {
		return "MemberVO [mb_id=" + mb_id + ", mb_name=" + mb_name + ", mb_password=" + mb_password + ", mb_email="
				+ mb_email + ", mb_authcode=" + mb_authcode + ", mb_zipcode=" + mb_zipcode + ", mb_addr=" + mb_addr
				+ ", mb_addr_d=" + mb_addr_d + ", mb_phone=" + mb_phone + ", mb_nickname=" + mb_nickname
				+ ", mb_accept_e=" + mb_accept_e + ", mb_point=" + mb_point + ", mb_join=" + mb_join + ", mb_update="
				+ mb_update + ", mb_login=" + mb_login + ", mb_session_key=" + mb_session_key + ", mb_session_limit="
				+ mb_session_limit + "]";
	}
	
}
