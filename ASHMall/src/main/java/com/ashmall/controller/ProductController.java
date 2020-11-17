package com.ashmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.ashmall.domain.CategoryVO;
import com.ashmall.domain.ProductVO;
import com.ashmall.service.ProductService;
import com.ashmall.service.ReviewService;
import com.ashmall.util.Criteria;
import com.ashmall.util.FileUtils;
import com.ashmall.util.PageMaker;
import com.ashmall.util.SearchCriteria;
import com.ashmall.controller.ProductController;

@Controller
@RequestMapping("/product/")
public class ProductController {
	
	
	@Inject
	ProductService service;
	
	@Inject
	ReviewService reviewService;
	
	// 웹 프로젝트 영역 외부에 파일을 저장할 때 사용할 경로
	/* 주의 : servlet-context.xml에 설정
	 * 	< PC에서 쓸때 / MAC환경에서 쓸 때 설정바꾸기 >
	 * */
	@Resource(name="uploadPath")
	private String uploadPath;
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	/*
	 * <1차 카테고리에 따른 2차 카테고리 출력>
	 * 
	 * @Params
	 * 	@PathVariable("cg_code")
	 * 		: path로 들어온 1차 카테고리
	 * 
	 * @return ResponseEntity<List<CategoryVO>> 
	 * 	:	REST 방식에 따른 HttpStatus를 같이 보내주기 위함
	 */
	
	@RequestMapping(value="list", method=RequestMethod.GET)
	public void subCGListPOST(@ModelAttribute("cri") Criteria cri,
			 				@ModelAttribute("categ_now") String categ_now,
			 				Model model) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categ_now", categ_now);
		// 페이징 정보 <mapper 파일의 between 1과 10>
		map.put("rowStart", cri.getRowStart());
		map.put("rowEnd", cri.getRowEnd());
		

		List<ProductVO> list = service.productListCG(map);
		model.addAttribute("productList", service.productListCG(map));
		model.addAttribute("categ_name", service.getCg_name(categ_now));
		
		
		// PageMake생성
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		int count = service.productCount(categ_now);
		pm.setTotalCount(count);

		model.addAttribute("pm", pm);
		
	}
		/*
		logger.info("=====subCGListGET() execute...");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categ_now", categ_now);
		// 페이징 정보 <mapper 파일의 between 1과 10>
		map.put("rowStart", cri.getRowStart());
		map.put("rowEnd", cri.getRowEnd());
		

		model.addAttribute("productList", service.productListCG(map));
		model.addAttribute("categ_name", service.getCg_name(categ_now));
		// 카테고리 이름
			
		// 카테고리에 해당하는 상품 리스트
		List<ProductVO> list = service.productListCG(map);
	    for(int i = 0; i < list.size(); i++)
	    logger.info(list.get(i).toString());
		
		// PageMake생성
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		int count = service.productCount(categ_now);
		pm.setTotalCount(count);

		model.addAttribute("pm", pm);
		/*
		// 2차 카테고리 리스트
		ResponseEntity<List<CategoryVO>> entity = null;
		try {
			logger.info("====="+service.subCGList(categ_now));
			entity = new ResponseEntity<List<CategoryVO>>(service.subCGList(categ_now), HttpStatus.OK);		
		} catch(Exception e) {	
			entity = new ResponseEntity<List<CategoryVO>>(HttpStatus.BAD_REQUEST);
		}
		
	}
		*/
	
	/* 
	// ProductController.java
	 * 검색리스트 -> 상품 상세정보 페이지 읽기
	 * SearchCriteria를 매개변수로 받는다
	 */
	
	/* 카테고리에 해당하는 상품 리스트 출력 
	@RequestMapping(value="list", method=RequestMethod.GET)
	public void list(@ModelAttribute("cri") Criteria cri,
					 @ModelAttribute("categ_now") String categ_now,
					 Model model) throws Exception {
		
		logger.info("=====list() executed...");
		logger.info(model.toString());
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("categ_now", categ_now);
		// 페이징 정보 <mapper 파일의 between 1과 10>
		map.put("rowStart", cri.getRowStart());
		map.put("rowEnd", cri.getRowEnd());
		

		model.addAttribute("productList", service.productListCG(map));
		model.addAttribute("categ_name", service.getCg_name(categ_now));
		// 카테고리 이름
			
		// 카테고리에 해당하는 상품 리스트
		List<ProductVO> list = service.productListCG(map);
	    for(int i = 0; i < list.size(); i++)
	    logger.info(list.get(i).toString());
		
		// PageMake생성
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		int count = service.productCount(categ_now);
		pm.setTotalCount(count);

		model.addAttribute("pm", pm);
		
	}
	*/
	/* 검색 조건에 해당하는 상품 리스트 출력 */
	@RequestMapping(value="listSearch", method=RequestMethod.GET)
	public void listSearch(@ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception{
		
		logger.info("=====listSearch() executed...");
		logger.info("=====cri:" + scri.toString());
		
		List<ProductVO> list = service.productListSearch(scri); 
		model.addAttribute("productList", list);
		
		// PageMaker생성
		PageMaker pm = new PageMaker();
		pm.setCri(scri);
		int count = service.productCountSearch(scri.getKeyword());
		pm.setTotalCount(count);

		model.addAttribute("pm", pm);
	}
	
	/* 파일 출력 (저장된 파일을 가져와 반환)  */ 
	@ResponseBody
	@RequestMapping(value="displayFile", method=RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		
		return FileUtils.getFile(uploadPath, fileName);
	}
	
	
	/* 카테고리 선택 -> 상품 상세정보 페이지 읽기 
	  Criteria를 매개변수로 받는다. 왜?  
	  	* 	 선택된 페이지에서 상세 페이지로 넘어갔다가 다시 리스트로 넘어갔을때,
	  	*	 이전 정보를 가지고 있어야하기 때문에 cri 변수에 담고 있는다.
	  	*/
	@RequestMapping(value="read", method=RequestMethod.GET)
	public void productReadGET(@ModelAttribute("cri") Criteria cri, @RequestParam("pd_num") int pd_num, Model model) throws Exception {
		
		logger.info("=====productReadGET() exectued...");
		
		// 선택한 상품 정보의 이미지를 썸네일에서 원본으로 변경 
		ProductVO vo = service.readProduct(pd_num);
		
		// 이미지 사용시 1) 리스트 : Thumb Nail 2) 상세페이지 : 원본 이미지 
		vo.setPd_img(FileUtils.thumbToOriginName(vo.getPd_img()));
		
		logger.info("=====Product Detail: "+vo.toString());
		model.addAttribute("vo", vo);
		
		// PageMaker생성 - 상세페이지에서 -> 상품목록으로 이동시 페이징정보를 제공하기 위하여 사용
		PageMaker pm = new PageMaker();
		pm.setCri(cri); // Criteria : page, perPageNum -> rowStart, rowsEnd 
		
		model.addAttribute("pm", pm); // 페이징정보 사용, 검색정보 사용한함. makeQuery()메소드 사용 

		/* 해당 상품 후기 */
		model.addAttribute("totalReview", reviewService.countReview(vo.getPd_num()));
	}
	
	/* 검색 리스트 -> 상품 상세정보 페이지 읽기 */ 
	/* SearchCriteria를 매개변수로 받는다 */
	@RequestMapping(value="readSearch", method=RequestMethod.GET)
	public void productReadSearch(@ModelAttribute("scri") SearchCriteria scri, 
									@RequestParam("pd_num") int pd_num, Model model) throws Exception{
		
		logger.info("=====productReadGET() execute...");
		
		// 선택한 상품 정보의 이미지를 썸네일에서 원본으로 변경
		ProductVO vo = service.readProduct(pd_num);
		vo.setPd_img(FileUtils.thumbToOriginName(vo.getPd_img()));
		
		logger.info("=====Product Detail: "+vo.toString());
		model.addAttribute("vo", vo);
		
		// PageMaker생성 - 상품목록으로 돌아가기 클릭시 이동하기 위해서
		PageMaker pm = new PageMaker();
		pm.setCri(scri);
		
		model.addAttribute("pm", pm);
		
		// 해당 상품에 달린 상품 후기 개수를 함께 보냄
		// 상품후기갯수만 존재하고, 상품후기구현은 AJAX방식으로 후기테이블을 가져오는 것을 확인
		model.addAttribute("totalReview", reviewService.countReview(vo.getPd_num()));
	}
	
	
}
