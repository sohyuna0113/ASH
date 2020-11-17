package com.ashmall.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ashmall.domain.MemberVO;
import com.ashmall.dto.MemberDTO;
import com.ashmall.service.MemberService;

@Controller
@RequestMapping("/member/*") /* member 공통경로 */ 
public class MemberController {
	
	
	/* 스프링 시스템이 시작하면서 패키지에 Bean으로 생성할 어노테이션을 가지고 있는 클래스를 검색한 후 생성 */
	@Autowired
	MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder passwdEncrypt;	
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	/* 	리턴타입이 void이면 @RequestMApping의 value가 jsp파일 
	 	리턴타입이 String이면 return type이 jsp파일
	*/
	
	/* 02. 로그인(GET) */ 
	/* 리턴타입이 void인 경우에는 JSP파일명은 요청주소(/member/login)가 된다.  */
	@RequestMapping(value="login", method=RequestMethod.GET)
	public void loginGET() {
		
	}
	
	/* 03. 로그인(POST) */
	/* RedirectAttributes 클래스 : 이동되는 주소에 데이터 (Return "redirect:/") */
	@RequestMapping(value="loginPost", method=RequestMethod.POST)
	public String loginPOST(MemberDTO dto, RedirectAttributes redirect, HttpSession session,
			Model model, HttpServletRequest response) throws Exception {
		logger.info("=====loginPost() execute...");
		
		String url = "";
		
		/* DB에서 암호화된 비밀번호가 저장 */
		MemberDTO memDTO = service.login(dto);	// service클래스에서 일반암호와 데이터베이스에서 가져온 암호문자열
		
		if(memDTO != null) {	// 로그인 성공 
		
			/* 세션작업 : 저장소 - 서버의 메모리 (현재 연결된 사용자 사용이 가능) */
			/* memDTO : 아이디, 비밀번호, 이름, 최근접속시간 */
			session.setAttribute("user", memDTO);
			session.getAttribute("dest");
			
			/* 쿠키를 사용할 경우 저장소 : 클라이언트(브라우저) */
			/* <input type="checkbox" name="useCookie" /> Remember me */
			if(memDTO.isUseCookie()) {
				
				int amount = 60*60*24*7;	// 쿠키저장 (7일) 
				
				/* System.currentTimeMillis() : 서버의 현재시간을 밀리세컨드 읽어온다. */
				Date sessionLimit = new Date(System.currentTimeMillis()+(1000*amount));
				
				/* 클라이언트 쿠키 설명 : Cookie 클래스 사용
				 * 쿠키는 클라이언트의 컴퓨터에 설정된 시간에 의하여 텍스트 파일로 저장이 된다.
				 * 브라우저에서 쿠키를 작업해준 사이트 접속을 할 때 브라우저가 쿠키를 메모리상에서 읽고
				 * 해당 사이트에 쿠키정보가전송이 이루어진다. 
				 * 
				 * 브라우저를 통하여 사이트에 접속할때에 서버에서 고유한 식별자에 해당하는 값을 쿠키형태로  
				 * 클라이언트에게 보내준다. 세션쿠키 ID session.getId() 
				 */
				
				service.saveCookie(session.getId(), sessionLimit, memDTO.getMb_id());
				
			}
			
			// 주소 
			redirect.addFlashAttribute("msg", "LOGIN_SUCCESS");	// 로그인 페이지에서 "msg" 키가 사용됨
			return "redirect:/";
		
		} else {
			logger.info("=====로그인 실패");
			
			redirect.addFlashAttribute("msg", "LOGIN_FAIL");
			
			return "redirect:/member/login";
			
		}
	}
	
	
	
	/* 로그아웃 */
	@RequestMapping(value="logout", method=RequestMethod.GET )
	public String logout(HttpSession session, RedirectAttributes redirect) {
		
		logger.info("=====logout execute()...");
		
		session.invalidate();		// 세션으로 처리한 정보를 서버 메모리에서 제거 (로그아웃)
		redirect.addFlashAttribute("msg", "LOGOUT_SUCCESS");
		
		return "redirect:/";
	}
	
	
	/* 회원가입(GET) */
	@RequestMapping(value="join", method=RequestMethod.GET)
	public void joinGET() {
		}
	
	/* 회원가입 전송(POST) */
	@RequestMapping(value="join", method=RequestMethod.POST)
	public String joinPOST(MemberVO vo, RedirectAttributes redirect) throws Exception {
		
		vo.setMb_password(passwdEncrypt.encode(vo.getMb_password()));
		
		service.join(vo);
		redirect.addFlashAttribute("msg", "REGISTER_SUCCESS");		
		
		return "redirect:/";
	} 

	/* 아이디 중복체크(ajax요청) */ 
	@ResponseBody
	@RequestMapping(value="checkIdDuplicate", method=RequestMethod.POST)
	public ResponseEntity<String> checkIdDuplicate(@RequestParam("mb_id") String mb_id) throws Exception {
		
		logger.info("=====checkIdDuplicate exectue()...");
		ResponseEntity<String> entity = null;
		try {
			int count = service.checkIdDuplicate(mb_id);
			
			if(count != 0) {
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
			} else {
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	/* 이메일 인증코드(/member/checkAuthcode) */
	/* 	- 입력된 인증 코드와 세션에 저장해 두었던 인증코드가 일치하는지 확인 */
	
	@ResponseBody
	@RequestMapping(value= "checkAuthcode", method=RequestMethod.POST)
	public ResponseEntity<String> checkAuthcode(@RequestParam("code") String code, HttpSession session){
		
		ResponseEntity<String> entity = null;
		
		try {
			if(code.equals(session.getAttribute("authcode"))) {
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			} else {
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
			}
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	/* 비밀번호 재확인 */
	// @ModelAttribute("url") String url : String url 파라미터로 제공한 값을 @ModelAttribute 이 Model에 url 이름으로 저장
	@RequestMapping(value="checkPw", method=RequestMethod.GET)
	public void checkPwGET(@ModelAttribute("url") String url) {
		
	}
	
	/* 
	 * 비밀번호 재확인(POST) - 회원 정보 수정을 위함
	 * 받은 url에 해당하는 jsp를 출력
	 * 
	 * @Params
	 * String url: 이동할 jsp페이지 이름
	 * 
	 * @return
	 * String : 받은 url에 일치하는 jsp호출
	 */
	
	@RequestMapping(value="checkPw", method=RequestMethod.POST)
	public String checkPwPOST(@RequestParam("url") String url,
							@RequestParam("mb_password") String mb_password,
							HttpSession session, Model model) throws Exception {
		
		logger.info("=====checkPw() execute...");
		logger.info("=====url: " + url + ", mb_password:" + mb_password);

		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		// logger.info("=====세션 저장 값: " + dto.toString());
		
		if(passwdEncrypt.matches(mb_password, dto.getMb_password())) {
			
			if(url.equals("modify")) {
				
				model.addAttribute("vo", service.readUserInfo(dto.getMb_id()));
				
				return "/member/modify";
				
			} else if(url.equals("changePw")) {
				
				return "/member/changePw";
				
			} else if(url.equals("delete")) {
				
				return "/member/delete";
			}		
		}
		
		model.addAttribute("url", url);
		model.addAttribute("msg", "CHECK_PW_FAIL");
		
		return "/member/checkPw";
	
	}

	
	/* 비밀번호 확인(ajax요청) */
	@ResponseBody
	@RequestMapping("checkPwAjax")
	public ResponseEntity<String> checkPwAjax(@RequestParam("mb_password") String mb_password, HttpSession session){
		
		logger.info("======checkPwAjax() execute...");
		ResponseEntity<String> entity = null;
		MemberDTO dto = (MemberDTO)session.getAttribute("user");
		logger.info("=====mb_password:" + mb_password);
		logger.info("=====dto:" + dto.toString());
		
		if(passwdEncrypt.matches(mb_password, dto.getMb_password())) {
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} else {
			entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
		}
		
		return entity;
	} 
	
	/* 회원수정 */
	@RequestMapping(value="modify", method=RequestMethod.POST)
	public String modifyPOST(MemberVO vo, RedirectAttributes redirect, HttpSession session) throws Exception {
	
		MemberDTO dto = new MemberDTO();
		dto.setMb_id(vo.getMb_id());
		dto.setMb_password(vo.getMb_password());
		
		vo.setMb_password(passwdEncrypt.encode(vo.getMb_password()));
		service.modifyUserInfo(vo);
		
		session.setAttribute("user", service.login(dto));
		redirect.addFlashAttribute("msg", "MODIFY_USER_SUCCESS");
		
		return "redirect/";
	}
	
	/* 비밀번호 변경 */
	@RequestMapping(value="changePw", method=RequestMethod.POST)
	public String changePWPOST(MemberDTO dto, RedirectAttributes redirect, HttpSession session) throws Exception {
		
		logger.info("=====changePWPOST() execute...");
		
		dto.setMb_password(passwdEncrypt.encode(dto.getMb_password()));
		service.ChangePw(dto);
		
		MemberDTO memDTO = (MemberDTO) session.getAttribute("user");
		memDTO.setMb_password(dto.getMb_password());
		session.setAttribute("user", memDTO);
		
		redirect.addFlashAttribute("msg", "CHANGE_PW_SUCCESS");
		return "redirect:/";
	}
	
	/* 회원 탈퇴 */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String deletePOST(String mb_id, HttpSession session, RedirectAttributes redirect) throws Exception {
		
		logger.info("=====deletePOST() execute..."); 
		
		service.deleteUser(mb_id);
		
		session.invalidate();
		redirect.addFlashAttribute("msg", "DELETE_USER_SUCCESS");
		
		return "redirect:/";
	}
	
	// 인증된 사용자만 접근하는 주소 
	@RequestMapping(value= "/authenticate", method=RequestMethod.GET)
	public String authenticate() {
		
		return "/member/authenticate";
	}
	
}
	
	
	
	

