package com.ashmall.persistence;

import java.util.List;
import java.util.Map;

import com.ashmall.domain.CategoryVO;
import com.ashmall.domain.ProductVO;
import com.ashmall.util.SearchCriteria;

public interface ProductDAO {

	// 1차 카테고리 출력
	public List<CategoryVO> mainCGList() throws Exception;
	
	// 2차 카테고리 출력
	public List<CategoryVO> subCGList(String categ_now) throws Exception;

	// 카테고리 코드에 해당하는 카테고리명
	public String getCGName(String categ_now) throws Exception;

	// 해당 카테고리에 해당하는 상품리스트(페이지 맞춤)
	public List<ProductVO> productListCG(Map map) throws Exception;
	
	// 해당 카테고리 상품 개수
	public int productCount(String categ_now) throws Exception;
	
	// 해당 검색조건에 해당하는 상품리스트 (페이지 맞춤)
	public List<ProductVO> productListSearch(SearchCriteria cri) throws Exception;
	
	// 해당 검색조건에 해당하는 상품개수
	public int productCountSearch(String keyword) throws Exception;
	
	// 상품 상세 정보 읽기
	public ProductVO readProduct(int pd_num) throws Exception;
}

