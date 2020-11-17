package com.ashmall.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.ashmall.interceptor.AuthInterceptor;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	private static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);
	private static final String LOGIN = "login";

	
	// 원래 요청한 주소정보를 저장하는 구문 
	private void saveDest(HttpServletRequest req) {
		String uri = req.getRequestURI();
		String query = req.getQueryString();
		
		if(query == null || query.equals("null")) {
			query = "";
		}else {
			query = "?" + query;
		}

		// 사용자가 요청한 방식이 GET방식이면
		if(req.getMethod().equals("GET")) {
			logger.info("dest: " + (uri + query));
			// 원래 요청한 주소를 저장 
			req.getSession().setAttribute("dest", uri+query);
		}
	}
	
	// 인증 유무 체크 : 로그인 성공의미를 가리키는 세션정보가 생성되어있나 확인 
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse resposnse, Object handler)
		throws Exception {
		
		HttpSession session = request.getSession();
		
		// 인증된 사용자가 아닌 경우 로그인 페이지로 이동 
		if(session.getAttribute(LOGIN) == null) {
			logger.info("=====USER IS NOT SIGNED IT");
			
			saveDest(request); // 원래 요청된 주소를 세션형태로 저장하는 메소드 
			
			resposnse.sendRedirect("/member/login");
			return false;
		}
		return true; // 요청된 주소의 해당 컨트롤러 메소드로 진행이 이루어진다. 
	}
	
}
