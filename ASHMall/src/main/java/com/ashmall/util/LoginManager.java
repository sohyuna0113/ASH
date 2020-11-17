package com.ashmall.util;

import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class LoginManager implements HttpSessionBindingListener {

	/* 해당 클래스를 싱글톤 패턴으로 구성 */
	/* 왜? 개발자가 메소드를 호출해서 쓸 때 실수를 방지 */
	private static LoginManager loginMng = null;
	
	private LoginManager() {}
	
	/* 싱글톤은 관습적으로 getInstance의 이름을 쓴다. */
	
	/* CPU의 기능이 빠르기때문에 동시에 여러 스레드가 접근하는 것을 방지하기 위하여
	 * synchronized 동기화 기능 메소드로 순차적으로 메소드를 호출하게끔 한다. */
	public static synchronized LoginManager getInstance() {
		if(loginMng == null) {
			loginMng = new LoginManager();
		}
		return loginMng;
	}
	
	/* 로그인시 사용자아이디 저장 */
	private static Hashtable<HttpSession, String> loginUser = new Hashtable<HttpSession, String>();
	
	@Override
	public void valueBound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void valueUnbound(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub

	}

}
