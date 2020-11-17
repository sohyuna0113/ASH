package com.ashmall.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.proxy.InterfaceMaker;
import org.springframework.stereotype.Repository;

import com.ashmall.domain.OrderDetailVO;
import com.ashmall.domain.OrderListVO;
import com.ashmall.domain.OrderReadDetailVO;
import com.ashmall.domain.OrderVO;

@Repository
public class OrderDAOImpl extends InterfaceMaker implements OrderDAO {

	@Autowired
	private SqlSession session;
	public final static String NS="com.ashmall.mappers.OrderMapper";
	
	
	@Override
	public int readOrderCode() throws Exception {
		return session.selectOne(NS+ ".readOrderCode");
	}

	@Override
	public void addOrderDetail(OrderDetailVO vo) throws Exception {
		session.insert(NS+ ".addOrderDetail", vo);

	}

	@Override
	public void addOrder(OrderVO vo) throws Exception {
		session.insert(NS+ ".addOrder", vo);

	}

	@Override
	public List<OrderListVO> orderList(String mb_id) throws Exception {
		return session.selectList(NS+ ".orderList", mb_id);
	}

	@Override
	public List<OrderReadDetailVO> readOrder(int od_num) throws Exception {
		return session.selectList(NS + ".readOrder", od_num);
	}

	@Override
	public OrderVO getOrder(int od_num) throws Exception {
		return session.selectOne(NS + ".getOrder", od_num);
	}

}
