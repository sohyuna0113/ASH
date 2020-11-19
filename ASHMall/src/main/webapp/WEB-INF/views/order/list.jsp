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
  <!-- Page Content -->
  <div class="container">

    <div class="row">

	  <!-- 카테고리 메뉴  --> 
      <div class="col-lg-3">
		
	<%@ include file="/WEB-INF/views/common/category.jsp" %>

      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">

		<div class="content-wrapper">
			
			<section class="content-header">
				<h1>
				<small>O r d e r l i s t</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> M A I N</a></li>
					<li class="active">O R D E R</li>
				</ol>
			</section>

			<%-- MAIN CONTENT --%>
			<!-- Main content -->
			<section class="content container-fluid">

				<div class="row">
					<!-- left column -->
					<div class="box" style="border: none; padding: 10px 30px;">
						<div class="box-body">
							<table class="table text-center">
								<%-- 상품이 존재하지 않는 경우 --%>
								<c:if test="${empty orderList}">
									<tr>
										<td colspan="10"> 
											<p style="padding:50px 0px; text-align: center;"></p>
										</td>
									<tr>
								</c:if>
								
								<%-- 상품이 존재하는 경우,  리스트 출력 --%>
								<c:forEach items="${orderList}" var="orderVO" varStatus="status">
									<c:if test="${status.index eq 0 || orderVO.od_num ne code}">
									<tr style="background-color: white;" >
										<td colspan="10" style="text-align:center;">
											<b>DATE OF ORDER: <fmt:formatDate value="${orderVO.od_date}" pattern="yyyy/MM/dd HH:mm:ss"/>
											(ORDER NUMBER: ${orderVO.od_num} ) </b>
										
											<button class="btn btn-primary" onclick="location.href='/order/read?od_num=${orderVO.od_num}';">
											ORDER DETAIL</button>
										</td>
									</tr>
									
									<tr style="background-color: whitesmoke;">
										<td></td>
										<td>Name</td>
										<td>Price</td>
										<td>Amount</td>
										<td>Review</td>
										<td>Status</td>
									</tr>
									</c:if>
									<c:set var="code" value="${orderVO.od_num}">	</c:set>
									<tr>
										<td class="col-md-2">
											<a href="/product/read?pd_num=${orderVO.pd_num}">
												<img src="/product/displayFile?fileName=${orderVO.pd_img}" style="width:100px;">
											</a>
										</td>
										<td class="col-md-2">
											<a href="/product/read?pd_num=${orderVO.pd_num}"
												style="color: black;"> ${orderVO.pd_name} </a>
										</td>
										<td class="col-md-2">
											<fmt:formatNumber value="${orderVO.od_price}" pattern="" />
											
										<td class="col-md-2">
											<fmt:formatNumber value="${orderVO.od_price * orderVO.od_amount}" pattern="" />
										</td>
										<td class="col-md-3">
											
											<button type="button" class="btn btn-flat" 
												onclick="location.href='/product/read?pd_num=${orderVO.pd_num}';" value="${orderVO.pd_num}" >Write Review</button>
										</td>
										<td class="col-md-3">
											<p>${orderVO.delivery}</p>
										</td>
									</tr>
								</c:forEach>
								
							</table>
						</div>
					</div>
				</div>
				<!--/.col (left) -->
			</section>
	 	 </div>
	  </div>
	</div>
</div>
 <!-- Footer -->
 <%@ include file="/WEB-INF/views/common/bottom.jsp" %>
</body>
</html>
