<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  
<script>
	var cart_click = function(pd_num){
		$.ajax({
			url: "/cart/add",
			type: "post",
			dataType: "text",
			data: {pd_num: pd_num},
			success: function(data){
				var result = confirm("ADDED TO YOUR CART.\n");
				if(result){
					location.href="/cart/list";
				} else{}
			}
		});
		
	}
</script>
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

		<div class="content-wrapper">
			
			<section class="content-header">
				<h1>
					Product List 
				</h1>
				<%-- realPath 주석 
				<%= application.getRealPath("/") %>
				--%>
				<ol class="breadcrumb">
					<li>
						<a href="#"><i class="fa fa-dashboard"></i>Product List</a>
					</li>
				</ol>
			</section>
			<%-- MAIN CONTENT --%> 
			<section class="content container-fluid">
				<%-- 상품 목록 표시 --%>
				<div style="width:100%; min-width:1400px; background-color:white; padding: 50px 180px;" class="container text-center">
					<h3>Search : ${scri.keyword}</h3><br>
					<ul class="pdtList">
						<%-- 상품이 존재하지 않는 경우 --%>
						<c:if test="${empty productList}">
							<span>PRODUCT NOT EXIST</span>
						</c:if>
						
						<%-- 상품이 존재하는 경우 --%>
						<c:forEach items="${productList}" var="productVO" >
						<li class="product">
							${productVO.pd_num}
							<div class="thumnail">
								<a href="/product/readSearch${pm.makeQuery(pm.cri.page)}&pd_num=${productVO.pd_num}&categ_now=${categ_now}">
									<img src="/product/displayFile?fileName=${productVO.pd_img}" >
								</a>
							</div>
							<div class="description">
								<a href="/product/readSearch${pm.makeQuery(pm.cri.page)}&pd_num=${productVO.pd_num}&categ_now=${categ_now}" >${productVO.pd_name}</a>
								<p>PRICE: <fmt:formatNumber value="${productVO.pd_price}" pattern="###,###,###" /><br>
								 	DISCOUNT: <fmt:formatNumber value="${productVO.pd_dc}" pattern="###,###,###" /></p>
							</div>
							<div class="btnContainer">
								<button class="btn btn-primary" id="btn_buy" type="button" 
									onclick="location.href = '/order/buy?pd_num=${productVO.pd_num}&ord_amount=1';">BUY</button>
								<button class="btn btn-default" id="btn_cart" type="button" 
									onclick="cart_click(${productVO.pd_num})">CART</button>
							</div>
						</c:forEach>
					</ul>
				</div>
				<%-- 페이지 표시 --%>
				<div class="box-footer container" style="width:100%; min-width:1400px;">
					<div class="text-center">
						<ul class="pagination">
							<c:if test="${pm.prev}">
								<li><a href="listSearch${pm.makeQuery(pm.startPage-1)}">&laquo;</a>
								</li>
							</c:if>

							<c:forEach begin="${pm.startPage}" end="${pm.endPage}"
								var="idx">
								<li <c:out value="${pm.cri.page == idx?'class =active':''}"/>>
									<a href="list${pm.makeQuery(idx)}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pm.next && pm.endPage > 0}">
								<li><a href="listSearch${pm.makeQuery(pm.endPage +1)}&categ_now=${categ_now}">&raquo;</a>
								</li>
							</c:if>
						</ul>
					</div>
				</div>
				</section>
			
			</div>
		</div>
	</div>
<%@ include file="/WEB-INF/views/common/bottom.jsp" %>
</body>
</html>