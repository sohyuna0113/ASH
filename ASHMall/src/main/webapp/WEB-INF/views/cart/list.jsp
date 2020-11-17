<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
    
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
   <!-- Bootstrap core JavaScript -->
<%@ include file="/WEB-INF/views/common/bootjs.jsp" %>
  <!-- Bootstrap core CSS -->
  <!-- Custom styles for this template -->
<%@ include file="/WEB-INF/views/common/bootcss.jsp" %>
<%-- 버튼 클릭 이벤트 메소드 --%>
<script type="text/javascript" src="/js/cart/list.js"></script>
</head>

<body>
  <!-- Navigation -->
  <%@ include file="/WEB-INF/views/common/top.jsp" %>
  <!-- Page Content -->
  <div class="container">
    
	  <!-- 카테고리 메뉴  --> 
      <div style="margin-top:90px" class="col-lg-3">
		
		<%@ include file="/WEB-INF/views/common/category.jsp" %>

      </div>
      <!-- /.col-lg-3 -->

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
				 	<small>C a r t l i s t</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i>M A I N</a></li>
					<li class="active">C A R T</li>
				</ol>
			</section>

			<%-- MAIN CONTENT --%>
			<!-- Main content -->
			<section class="content container-fluid">

				<div class="row">
					<!-- left column -->
					<div class="box" style="border: none;">
						<form method="post" action="/order/buyFromCart">
						<div class="btn-container" style="display: inline-block; float: right; margin:20px 10px 5px 5px;">
							<button id="btn_buy_check"  class="btn btn-primary" type="submit" >Buy</button>
							<button id="btn_delete_check"  class="btn btn-default" >Remove</button>
						</div>
						<div class="box-body">
							<table class="table table-striped text-center">
								<tr>
									<th><input type="checkbox" id="checkAll" checked="checked"/></th>
									<th>Item</th>
									<th>Image</th>
									<th>Product</th>
									<th>Price</th>
									<th>Discount</th>
									<th>Amount</th>
									<th>Buy/Remove</th>
								</tr>
								
								<%-- 상품이 존재하지 않는 경우 --%>
								<c:if test="${empty cartProductList}">
									<tr>
										<td colspan="10"> 
											<p style="padding:50px 0px; text-align: center;">장바구니에 담긴 상품이 없습니다.</p>
										</td>
									<tr>
								</c:if>
								
								<%-- 상품이 존재하는 경우,  리스트 출력 --%>
								<c:set var="i" value="${fn:length(cartProductList)}" ></c:set>
								<c:forEach items="${cartProductList}" var="cartProductVO">
									<tr>
										<td class="col-md-1">
											<input type="checkbox" name="check" class="check" value="${cartProductVO.cart_code}" checked="checked" >
											<input type="hidden" id="pd_num_${cartProductVO.cart_code}" name="pd_num" value="${cartProductVO.pd_num}" >
											<input type="hidden" name="buy_amount" value="${cartProductVO.buy_amount}" >
											<input type="hidden" name="cart_code" value="${cartProductVO.cart_code}" >
										</td>
										<td class="col-md-1">${i}</td>
										<td class="col-md-2">
											<a href="/product/read?pd_num=${cartProductVO.pd_num}&categ_now=${categ_now}">
												<img src="/product/displayFile?fileName=${cartProductVO.pd_img}" style="width:100px;">
											</a>
										</td>
										<td class="col-md-2">
											<a href="/product/read?pd_num=${cartProductVO.pd_num}&categ_now=${categ_now}"
												style="color: black;"> ${cartProductVO.pd_name} </a>
										</td>
										<td class="col-md-1">
											<p>${cartProductVO.pd_price}</p>
											<input type="hidden" name="price_${cartProductVO.cart_code}" value="${cartProductVO.pd_price}" /></td>
										<td class="col-md-1">
											<p>${cartProductVO.pd_dc}</p>
											<input type="hidden" name="discount_${cartProductVO.cart_code}" value="${cartProductVO.pd_dc}" /></td>
										<td class="col-md-2">
											<input type="number" name="buy_amount_${cartProductVO.cart_code}"
												style="width:60px; height:34px; padding-left:5px;" value="${cartProductVO.buy_amount}" />
											<button type="button" name="btn_modify" class="btn btn-default" value="${cartProductVO.cart_code}" >Count</button>
										</td>
										<td class="col-md-2">
											<button type="button" name="btn_buy" class="btn btn-primary" value="${cartProductVO.cart_code}"
												onclick="clickBuyBtn(${cartProductVO.pd_num}, ${cartProductVO.cart_code});">Buy</button>
											<button type="button" name="btn_delete" class="btn btn-default" value="${cartProductVO.cart_code}" >Remove</button>
										</td>
										<c:set var="i" value="${i-1}" ></c:set>
									</tr>

								</c:forEach>
							</table>
						</div>
						</form>
						<div class="box-body" style="margin: 7% 10%; padding-bottom:10%; min-width: 600px;">
							<table class="table table-striped text-center" >
								<tr>
									<td class="col-md-1">Total Discount</td>
									<td class="col-md-1">Total Price</td>
								</tr>
								<tr >
									<td class="col-md-1" style="height:50px; text-align: center;"><p id="totalDiscount">0</p></td>
									<td class="col-md-1" style="height:50px; text-align: center;"><p id="totalPrice">0</p></td>
								</tr>
					</table>
				  </div>
				</div>
			  </div>
			 <!--/.col (left) -->
			</section>
		  </div>
		<!-- /.content -->
		<!-- /.content-wrapper -->
	  </div>
   <!-- Footer -->
  <%@ include file="/WEB-INF/views/common/bottom.jsp" %>
</body>
</html>