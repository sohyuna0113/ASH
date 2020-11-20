package com.ashmall.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ashmall.domain.CategoryVO;
import com.ashmall.domain.ProductVO;
import com.ashmall.util.SearchCriteria;

@Repository
public class AdProductImpl implements AdProductDAO {

	@Autowired
	SqlSession session;
	public final static String NS="com.ashmall.mappers.AdProductMapper";
	
	// 1차 카테고리
	@Override
	public List<CategoryVO> mainCGList() throws Exception {
		return session.selectList(NS+ ".mainCGList");
	}

	// 1차 카테고리에 의한 2차 카테고리
	@Override
	public List<CategoryVO> subCGList(String categ_now) throws Exception {
		return session.selectList(NS+ ".subCGList", categ_now);
	}
	
	// 상품 등록
	@Override
	public void insertProduct(ProductVO vo) throws Exception {
		session.insert(NS+ ".insertProduct", vo);

	}

	// 상품 리스트
	@Override
	public List<ProductVO> searchListProduct(SearchCriteria cri) throws Exception {
		return session.selectList(NS+ ".searchListProduct", cri);
	}

	// 검색 조건에 맞는 상품 개수
	@Override
	public int searchListCount(SearchCriteria cri) throws Exception {
		return session.selectOne(NS+ ".searchListCount");
	}

	// 상품 정보
	@Override
	public ProductVO readProduct(int pd_num) throws Exception {
		return session.selectOne(NS+ ".readProduct", pd_num);
	}

	// 상품 수정 
	@Override
	public void editProduct(ProductVO vo) throws Exception {
		session.update(NS+ ".editProduct", vo);
	}

	// 상품 삭제
	@Override
	public void deleteProduct(int pd_num) throws Exception {
		session.delete(NS+ ".deleteProduct", pd_num);
	}

	// 선택한 상품 수정
	@Override
	public void editChecked(Map<String, Object> map) throws Exception {
		session.update(NS+ ".editChecked", map);
	}

	// 상품 수량 조절 
	@Override
	public void changeStock(ProductVO vo) throws Exception {
		session.update(NS+ ".changeStock", vo);
		
	}


}
