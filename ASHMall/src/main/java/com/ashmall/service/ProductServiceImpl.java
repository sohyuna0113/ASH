package com.ashmall.service;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ashmall.domain.CategoryVO;
import com.ashmall.domain.ProductVO;
import com.ashmall.persistence.ProductDAO;
import com.ashmall.util.SearchCriteria;

@Service
public class ProductServiceImpl implements ProductService {

	@Inject
	private ProductDAO dao;
	
	// 1차 카테고리 출력
	@Override
	public List<CategoryVO> mainCGList() throws Exception {
		return dao.mainCGList();
	}

	// 2차 카테고리 출력 
	@Override
	public List<CategoryVO> subCGList(String categ_now) throws Exception {
		return dao.subCGList(categ_now);
	}

	// 카테고리 코드에 해당하는 카테고리명
	@Override
	public String getCg_name(String categ_now) throws Exception {
		return dao.getCGName(categ_now);
	}

	// 해당 카테고리에 해당하는 상품리스트(페이지 맞춤) 
	@Override
	public List<ProductVO> productListCG(Map map) throws Exception {
		return dao.productListCG(map);
	}

	// 해당 카테고리에 해당하는 상품 개수 
	@Override
	public int productCount(String categ_now) throws Exception {
		return dao.productCount(categ_now);
	}

	// 해당 검색조건에 해당하는 상품리스트(페이지 맞춤)
	@Override
	public List<ProductVO> productListSearch(SearchCriteria cri) throws Exception {
		return dao.productListSearch(cri);
	}

	// 해당 검색조건에 해당하는 상품 개수
	@Override
	public int productCountSearch(String keyword) throws Exception { 
		return dao.productCountSearch(keyword);
	}
	
	// 상품 상세정보 읽기 
	@Override
	public ProductVO readProduct(int pd_num) throws Exception {
		return dao.readProduct(pd_num);
	}

}
