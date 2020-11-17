package com.ashmall.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.ashmall.domain.CategoryVO;
import com.ashmall.domain.ProductVO;
import com.ashmall.service.ProductService;
import com.ashmall.util.SearchCriteria;

@Repository
public class ProductDAOImpl implements ProductDAO{

	@Inject
	private SqlSession session;
	public final static String NS = "com.ashmall.mappers.ProductMapper"; 
	
	// 1차 카테고리 출력
	@Override
	public List<CategoryVO> mainCGList() throws Exception {
		return session.selectList(NS+ ".mainCGList");
	}

	// 2차 카테고리 출력 
	@Override
	public List<CategoryVO> subCGList(String categ_now) throws Exception {
		return session.selectList(NS+ ".subCGList", categ_now);
	}

	// 카테고리 코드에 해당하는 카테고리명
	@Override
	public String getCGName(String categ_now) throws Exception {
		return session.selectOne(NS+ ".getCGName", categ_now);
	}

	// 해당 카테고리에 해당하는 상품리스트(페이지 맞춤)
	@Override
	public List<ProductVO> productListCG(Map map) throws Exception {
		return session.selectList(NS+ ".productListCG", map);
	}

	// 카테고리에 해당하는 상품개수
	@Override
	public int productCount(String categ_now) throws Exception {
		return session.selectOne(NS+ ".productCount", categ_now);
	}

	// 해당 검색조건에 해당하는 상품리스트(페이지 맞춤)
	@Override
	public List<ProductVO> productListSearch(SearchCriteria cri) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	// 해당 검색조건에 해당하는 상품개수
	@Override
	public int productCountSearch(String keyword) throws Exception {
		return session.selectOne(NS+ ".productSearchCount", keyword);
	}
	
	// 상품 상세정보 읽기 
	@Override
	public ProductVO readProduct(int pd_num) throws Exception {
		return session.selectOne(NS +".readProduct", pd_num);
	}

}
