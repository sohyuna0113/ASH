package com.ashmall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/* /member/** 주소로 요청하는 부분을 인터넵터 설정을 하여 제어 */ 
public class TestInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		/*
		 	인터셉터 설정이 관리자 메뉴 주소 요청 발생 
			관리자 로그인 세션여부 체크
			1) 정상 로그인 상태 : return true;
			2) 로그인 상태 아님 : 관리자 로그인 주소 이동작업 : return false; 
		*/
		
		return false;
	}
	/* return true; 라면 그 전에 요청된 주소로 제어가 넘어간다. */

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}

	
	
}
