package com.ashmall.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ashmall.domain.CategoryVO;
import com.ashmall.domain.OrderVO;
import com.ashmall.domain.ProductVO;
import com.ashmall.service.AdProductService;
import com.ashmall.service.AdminService;
import com.ashmall.util.FileUtils;
import com.ashmall.util.PageMaker;
import com.ashmall.util.SearchCriteria;

@Controller
@RequestMapping("/admin/product/*")
public class AdProductController {

	 @Autowired
	 private AdProductService service;
	
	/* 웹 프로젝트 영역 외부에 파일을 저장할 때 사용하는 경로 */
	@Resource(name="uploadPath")
	private String uploadPath; // servlet-context.xml에 설정
	
	private static final Logger logger = LoggerFactory.getLogger(AdProductController.class);
	
	/*
		1차 카테고리에 의한 2차 카테고리 출력
		
		@Params
		@PathVariable("categ_now")
		: path로 들어온 1차 카테고리
	
		@return ResponseEntity<List<CategoryVO>>
		: REST 방식에 따른 HttpStatus를 같이 보내주기 위함
		
		ARC : Advanced Rest Client 프로그램 테스트 확인 
	*/
	
	@ResponseBody
	@RequestMapping(value="subCGList/{categ_now}", method=RequestMethod.GET)
	public ResponseEntity<List<CategoryVO>> subCGListPOST(@PathVariable("categ_now") String categ_now){
		
		logger.info("===== subCGList() execute...");
		
		ResponseEntity<List<CategoryVO>> entity = null;
		try {
			entity = new ResponseEntity<List<CategoryVO>>(service.subCGList(categ_now), HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<List<CategoryVO>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	/* 파일 출력 */	
	@ResponseBody
	@RequestMapping(value="displayFile", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		
		return FileUtils.getFile(uploadPath, fileName);
	}

	/* 이미지 파일 삭제 */
	public void deleteFile(String fileName) {
		logger.info("delete FileName : " + fileName);
		
		FileUtils.deleteFile(uploadPath, fileName);
	}

	/* 상품 등록(GET) */
	@RequestMapping(value="insert", method=RequestMethod.GET)
	public void productInsertGET(Model model) throws Exception{
		/* 1차 카테고리 리스트 전송 */
		model.addAttribute("cateList", service.mainCGList());
	}

	/* 상품 등록(POST) */

	/*
		1. 파일 업로드 : 일반적인 방식 <input type="file" /> + Ajax방식 (Drag & Drop) 
		2. DB작업
	*/
	@RequestMapping(value="insert", method=RequestMethod.POST)
	public String productInsertPOST(ProductVO vo, RedirectAttributes redirect) throws Exception{
	
		logger.info("======insertPOST() executed...");
		logger.info(vo.toString());

		vo.setPd_img(FileUtils.uploadFile(uploadPath, vo.getFile1().getOriginalFilename(), vo.getFile1().getBytes()));
		
		service.insertProduct(vo);
		redirect.addFlashAttribute("msg", "INSERT_SUCCESS");
			
		return "redirect:/admin/product/list";
	}

	/*
		상품 상세(ckEditor)-파일 업로드 
		웹 프로젝트 영역 상의 폴더안에 업로드

		@Params
		MultipartFile upload : 이미 지정된 이름 / 바꿀수x 
	*/
	@RequestMapping(value="imgUpload", method=RequestMethod.POST)
	public void imgUpload(HttpServletRequest req, HttpServletResponse res, MultipartFile upload) {
	
		logger.info("=====imgUpoad() execute...");
		
		OutputStream out = null;
		PrintWriter printWriter = null;
		
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		
		try {
			// 전송할 파일 정보를 가져옴
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
			
			String uploadPath = req.getSession().getServletContext().getRealPath("/");
			uploadPath = uploadPath + "resources\\upload\\" + fileName;
			
			logger.info("=====uploadPath:" + uploadPath);
			
			// 출력 스트림 생성
			out = new FileOutputStream(new File(uploadPath));
			// 파일 쓰기 
			out.write(bytes);
			
			printWriter = res.getWriter();
			String fileUrl = "/upload" + fileName;
			
			// ckeditor 4에서 제공하는 형식
			printWriter.println("{\"filename\":\"" + fileName + "\", \"uploaded\":1,\"url\":\"" + fileUrl + "\"}");
			printWriter.flush(); 
			// 전송 (return과 같은 역할: 클라이언트로 보냄)
		
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(out!= null) {
				try {out.close(); }catch (Exception e) {e.printStackTrace();}
				}
			if(printWriter!= null) {
				try {printWriter.close(); }catch(Exception e) {e.printStackTrace();}
				}
			}
		}
	
	/*
		상품 리스트 (페이징, 검색 조건 포함)
	
		# JSP로 전달
		1. 검색 조건에 해당하는 상품리스트
		2. pageMaker
	
	*/
	
	/* 
	 * url이 처음 요청을 받았을 경우 SearchCriteria cri의 기본값을 갖게됨.
	 * this.page = 1; // 현재 페이지 번호 
	 * this.perPageNum = 10; // 페이지 출력 게시물 개수
	 * searchType = null, keyword = null
	 * 
	 */
	@RequestMapping(value="list", method=RequestMethod.GET)
	public void productList(@ModelAttribute("cri") SearchCriteria cri,
							@ModelAttribute("categ_now") String categ_now,
											Model model) throws Exception {
	
		logger.info("=====productList() execute...");
		logger.info("=====cri : " + cri.toString());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categ_now", categ_now);
		map.put("rowStart", cri.getRowStart());
		map.put("rowEnd", cri.getRowEnd());
		
		List<ProductVO> list = service.searchListProduct(cri);
		// 페이징 기능이 적용된 상품 데이터 (검색기능 선택적 포함)
		model.addAttribute("productList", list);
		model.addAttribute("categ_name", service.mainCGList());
		
		// PageMaker생성
		PageMaker pm = new PageMaker();
		pm.setCri(cri);	// 페이징정보 + 검색정보 
		/* 페이징정보 (page=1, perPageNum=10), 검색정보 (searchType=null, keyword=null) */
		
		// 테이블 전체 데이터개수
		int count = service.searchListCount(cri);
		
		logger.info("=====일치하는 상품개수 :" + count);
		pm.setTotalCount(count);

		model.addAttribute("pm", pm);
	}
	
	/* 상품 상세정보 페이지 읽기 */
	@RequestMapping(value="read", method=RequestMethod.GET)
	public void productReadGET(@ModelAttribute("cri") SearchCriteria cri,
								@RequestParam("pd_num") int pd_num, Model model) throws Exception {
		
		logger.info("=====productReadGET() execute...");
		
		/* 선택한 상품 정보의 날짜 변환 */
		ProductVO vo = service.readProduct(pd_num);
		
		logger.info("=====Product Detail: " + vo.toString());

		/* 썸네일 파일 이름 수정 */
		vo.setPd_img(vo.getPd_img().substring(vo.getPd_img().lastIndexOf("_")+1));
		
		logger.info("=====changed Product Detail: " + vo.toString());
		model.addAttribute("vo", vo);
		
		/* PageMaker생성 : 상품목록으로 돌아가기 클릭시 이동하기 위해서  */
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		
		model.addAttribute("pm", pm);
		
		}
	
	/* 
	 	상품 수정(GET)
	 
	 	# JSP로 전달
	 	1. 선택한 상품정보
	 	2. 1차 카테고리 리스트
	 	3. 현재 선택된 2차 카테고리 리스트
	 	4. PageMaker
	 	5. 원래 저장되어 있던 파일명
	 
	 */
	@RequestMapping(value="edit", method=RequestMethod.GET)
	public void productEditGET(@ModelAttribute("cri") SearchCriteria cri,
								@RequestParam("pd_num") int pd_num, Model model) throws Exception {
		
		logger.info("=====productEditGET() executed...");
		
		ProductVO vo = service.readProduct(pd_num);		
		
		logger.info("=====Proddduct Detail: "+vo.toString());
			
		List<CategoryVO> subCateList = service.subCGList(vo.getCateg_prt());
		String originFile = vo.getPd_img().substring(vo.getPd_img().lastIndexOf("_")+1);
		
		model.addAttribute("vo", vo);
		model.addAttribute("originFile", originFile);
		model.addAttribute("cateList", service.mainCGList());
		model.addAttribute("subCateList", service.subCGList(vo.getCateg_prt()));
	
		// PageMaker생성 - 상품목록으로 돌아가기 클릭시 이동하기 위해서
		PageMaker pm = new PageMaker();
		pm.setCri(cri);

		model.addAttribute("pm", pm);
	}	
	
	/* 상품 수정(POST) */
	@RequestMapping(value="edit", method=RequestMethod.POST)
	public String productEditPOST(ProductVO vo, SearchCriteria cri, RedirectAttributes redirect) throws Exception {
		
		logger.info("=====productEditPOST() executed...");
		logger.info("=====edit info: "+vo.toString());
		logger.info("=====cri info: "+cri.toString());
		
		// 파일 사이즈로 새로운 파일 등록 여부 확인
		// 파일을 새로 등록하지 않은 경우 NULL이 아닌 비어있는 파일이 넘어옴.
		if(vo.getFile1().getSize() > 0) {
			logger.info("=====file not zero size");
			vo.setPd_img(FileUtils.uploadFile(uploadPath, vo.getFile1().getOriginalFilename(), vo.getFile1().getBytes()));
		}
		logger.info("=====changed info: "+vo.toString());
		
		service.editProduct(vo);
		
		redirect.addAttribute("cri", cri);
		redirect.addAttribute("msg", "EDIT_SUCCESS");
		
		return "redirect:/admin/product/list";
	}
	
	
	/* 상품 삭제 (POST) */
	@RequestMapping(value="delete", method=RequestMethod.POST)
	public String productDeletePOST(SearchCriteria cri,
									@RequestParam("pd_num") int pd_num,
									@RequestParam("pd_img") String pd_img,
									RedirectAttributes redirect) throws Exception {
	
		logger.info("=====delete(POST) executed...");
		
		// 1) 이미지 삭제
		deleteFile(pd_img);
		
		// 2) 상품 삭제 
		service.deleteProduct(pd_num);
		redirect.addFlashAttribute("cri", cri);
		redirect.addFlashAttribute("msg", "DELETE_SUCCESS");
		
		// 3) 처음 리스트로 돌아옴 
		return "redirect:/admin/product/list";
	
	}
	
	/* 선택된 상품 수정 */
	@ResponseBody
	@RequestMapping(value="editChecked", method=RequestMethod.POST)
	public ResponseEntity<String> editChecked(@RequestParam("checkArr[]") List<Integer> checkArr,
												@RequestParam("amountArr[]") List<Integer> amountArr,
												@RequestParam("buyArr[]") List<String> buyArr){
		logger.info("======editChecked() executed...");
		
		ResponseEntity<String> entity = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			for(int i=0; i<checkArr.size(); i++) {
				map.put("pd_num", checkArr.get(i));
				map.put("pd_amount", amountArr.get(i));
				map.put("pd_buy", buyArr.get(i));
				
				service.editChecked(map);
			}
			
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		} catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		entity = new ResponseEntity<String>(HttpStatus.OK);
		return entity;
	}
													
	/* 선택된 상품 삭제 */
	@ResponseBody
	@RequestMapping(value="deleteChecked", method=RequestMethod.POST)
	public ResponseEntity<String> deleteChecked(@RequestParam("checkArr[]") List<Integer> checkArr,
												@RequestParam("imgArr[]") List<String> imgArr) {

		logger.info("=====deleteChecked() execute...");
		
		ResponseEntity<String> entity = null;
		
		try { 
			// 체크된 상품의 이미지와 상품을 삭제 
			for(int i=0; i<checkArr.size(); i++) {
				deleteFile(imgArr.get(i));
				service.deleteProduct(checkArr.get(i));
			} 
			
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		} catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;

	}
	
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public String delivery(OrderVO vo) throws Exception {
		
		service.delivery(vo);
		
		return "redirect:/admin/product/list";
		
	}
}

