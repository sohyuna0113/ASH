package com.ashmall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;


public class LoginInterceptor extends HandlerInterceptorAdapter {

	private static final String LOGIN = "login";
	
	private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);
	
	/* 
	 * preHandle : 지정된 컨트롤러의 동작 이전에 수행할 동작 (사전제어)
	 * 
	 * 
	 * 
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.info("=====login PreHandler() execute...");
		HttpSession session = request.getSession();
		
		// 현재 사용자가 로그인된 경우
		if(session.getAttribute(LOGIN) != null) {
		
			session.removeAttribute(LOGIN);
		}
		
		return true;
		
	}
	
	/*
	 * postHandle : 지정된 컨트롤러의 동작 이후에 처리할 동작 
	 * 
	 * 
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, 
			ModelAndView modelAndView) throws Exception {
		
		logger.info("postHandle");
	
		HttpSession session = request.getSession();
		Object loginDTO = modelAndView.getModel().get("loginDTO");
		
		if(loginDTO != null) {
			logger.info("=====Login process");
			session.setAttribute(LOGIN, loginDTO);
			
			/* 
			 * 원래 요청한 주소 유무 확인
			 * 존재 -> 주소 이동
			 * 존재 x -> login/authenticate 
			 */
			Object dest = session.getAttribute("dest");
			
			 if(dest != null) {
				 response.sendRedirect((String) dest);
			 } else {
				 response.sendRedirect("/login/loginMain");
			 }	 
		
			response.sendRedirect(dest != null ? (String) dest: "/member/login");
		}
		
		
	}
}
