<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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

</head>
<body>
  <!-- Navigation -->
  <%@ include file="/WEB-INF/views/common/top.jsp" %>

	  <!-- 카테고리 메뉴  --> 
      <div class="col-lg-3">
		
		<%@ include file="/WEB-INF/views/common/category.jsp" %>

      </div>
      <!-- /.col-lg-3 -->

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					<small>O r d e r d e t a i l</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> M A I N</a></li>
					<li class="active">O R D E R</li>
				</ol>
			</section>
			
					
			<%-- MAIN CONTENT --%> 
			<section class="content container-fluid">
			<div class="row">
				<!-- left column -->
				<div class="box" style="border: none;">
					<form id="orderForm" method="post" action="/order/buy">
						<div class="box-body" style="padding:30px 10px 100px 10px;">
							<%-- 주문내역 상단 버튼 --%>
							<div class="orderList" style="padding: 0px 40px;">
								<%-- 주문내역 테이블 --%>
								<table class="table  text-center" id="ordertb">
									<thead id="thead">
										<tr style="background-color: aliceBlue;" >
											<td colspan="7" style="text-align:left;">
												<b>주문날짜: <fmt:formatDate value="${order.od_date}" pattern="yyyy/MM/dd HH:mm:ss"/>
												(주문번호: ${order.od_num} )</b>
											</td>
										<tr>
										<tr style="background-color: whitesmoke;">
											<td>IMAGE</td>
											<td>NAME</td>
											<td>PRICE</td>
											<td>DISCOUNT</td>
											<td>AMOUNT</td>	
											<td>TOTAL</td> 
											<td>REVIEW</td>
										</tr>
										<%-- 상품이 존재하지 않는 경우 --%>
										<tr>
											<c:if test="${empty orderList}">
												<span>구매한 상품이 존재하지 않습니다.</span>
											</c:if>
										</tr>
									<thead>
									
									<%-- 상품이 존재하는 경우,  리스트 출력 --%>
									<tbody>
									<c:forEach items="${orderList}" var="product" varStatus="status">
									<c:set var="totalPrice" value="${totalPrice + orderList[status.index].pd_price * orderList[status.index].od_amount}"></c:set>
										<tr id="row">
											<td class="col-md-2">
												<a href="/product/read?pd_num=${product.pd_num}">
													<img src="/product/displayFile?fileName=${product.pd_img}" style="width:100px;">
												</a>
											</td>
											<td class="col-md-2">
												<a href="/product/read?pd_num=${product.pd_num}"
													style="color: black;"> ${product.pd_name} </a>
											</td>
											<td class="col-md-1">
												<p><fmt:formatNumber value="${product.pd_price}" pattern="###,###,###" /></p>
											</td>
											<td class="col-md-1">
												<p><fmt:formatNumber value="${product.od_price}" pattern="###,###,###" /></p>
											</td>
											<td class="col-md-1">
												<p>${product.od_amount}</p>
											</td>
											<td class="col-md-1">
												<p ><fmt:formatNumber value="${product.od_price * product.od_amount}"  pattern="###,###,###" /></p>
											</td>
											<td class="col-md-1">
												<button type="button" name="btn_review" class="btn btn-flat" 
												onclick="location.href='/product/read?pd_num=${product.pd_num}';" value="${product.pd_num}" >WRITE REVIEW</button>
											</td>
										</tr>
									</c:forEach>
									</tbody>
								</table>
								<br><br><br>
							</div>
							<hr><br>
							
							<%-- 주문 정보 --%>
							<div class="orderInfo" style="min-width:1000px;" > 
								<div class="userInfo" style="display:inline-block; float:left; width:60%; padding: 0% 5%;">
									<div class="container" style="width:100%;">
										<span>[주문 정보]</span>
										<div class="form-group">
											<label for="inputName">* 이름</label> <input type="text"
												class="form-control" value="${order.od_name}" readonly>
										</div>
										<div class="form-group">
											<label for="inputMobile">* 휴대폰 번호</label> <input type="tel"
												class="form-control" value="${order.od_phone}" readonly>
										</div>
										<div class="form-group">
											<label for="inputAddr">* 주소</label> <br />
											<input type="text" id="sample2_postcode" name="od_zipcode" class="form-control" 
												value = "${order.od_zipcode}" 
												style="width:calc(100% - 128px); margin-right: 5px; display: inline-block;" placeholder="우편번호" readonly>
											<input type="button" onclick="sample2_execDaumPostcode()" id="btn_postCode" class="btn btn-default" value="우편번호 찾기" disabled="disabled"><br>
											<input type="text" id="sample2_address" name="od_addr" class="form-control" 
												value = "${order.od_addr}" 
												placeholder="주소" style=" margin:3px 0px;" readonly>
											<input type="text" id="sample2_detailAddress" name="od_addr_d" class="form-control" 
												value = "${order.od_addr_d}"
												placeholder="상세주소" readonly >
											<input type="hidden" id="sample2_extraAddress" class="form-control" 
												placeholder="참고항목">
										</div>
									</div>
								</div>
								
								<%-- 주문 금액 확인 --%>
								<div class="orderConfirm" style="display:inline-block; width:20%; margin: 0px 5%;">
								<br>
									<%-- 주문 금액 --%>
									<div style="width: 400px;">
										<span>[결제 금액]</span>
										<table class="table text-center" style="margin-top:15px;" >
											<tr>
												<td class="col-md-1">Price</td>
												<td class="col-md-1" style="height:30px; text-align: center;">
													<fmt:formatNumber value="${totalPrice}" pattern="###,###,###" /></td>
											</tr>
											<tr>
												<td class="col-md-1">Discount(-)</td>
												<td class="col-md-1" style="height:30px; text-align: center;">
													<fmt:formatNumber value="${totalPrice-order.od_price}" pattern="###,###,###" /></td>
											</tr>
											<tr>
												<td class="col-md-1"><label>Total Price</label></td>
												<td class="col-md-1" style="height:30px; text-align: center;">
													<label><fmt:formatNumber value="${order.od_price}" pattern="###,###,###" /></label>
												</td>
											</tr>
										</table>
								
									</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
<%@ include file="/WEB-INF/views/common/bottom.jsp" %>

</body>
</html>