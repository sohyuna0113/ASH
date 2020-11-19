<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
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
<%-- 스타일 처리 --%>
<style>
	ul{
   		list-style:none;
   		padding-left:0px;
    }
    .product{
    	display: inline-block;
    	margin: 10px;
    	padding:22px 40px;
    }
    .description{
    	margin: 10px;
    }
</style>
<script>
	/* 장바구니 버튼 클릭 이벤트 */ 
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
</head>

<body class="hold-transition skin-blue sidebar-mini">
  <!-- Navigation -->
  <%@ include file="/WEB-INF/views/common/top.jsp" %>
  <!-- Page Content -->
	<div class="wrapper">

	  <!-- 카테고리 메뉴  --> 
      <div class="col-lg-3">
		
		<%@ include file="/WEB-INF/views/common/category.jsp" %>

      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">
		<a href="#"><img src="/dist/img/dia00.jpg" style="padding:50px 200px; postion:fixed"></a>
		</div>
		
			<div class="content-wrapper">
			
			<%-- MAIN CONTENT --%> 
			<section class="content container-fluid">
				<%-- 상품 목록 표시 --%>
				<div style="width:100%; min-width:1400px; background-color:white; padding: 50px 180px;" class="container text-center">
					<h3>${categ_name}</h3>
					<ul class="pdList">
						<%-- 상품이 존재하지 않는 경우 --%>
						<c:if test="${empty productList}">
							<span>E M P T Y&nbsp&nbspL I S T</span>
						</c:if>
						
						<%-- 상품이 존재하는 경우 --%>
						<c:forEach items="${productList}" var="productVO">
						<li class="product">
							${productVO.pd_num}
							<div class="thumnail">
								<a href="/product/read${pm.makeQuery(pm.cri.page)}&pd_num=${productVO.pd_num}&categ_now=${categ_now}">
									<img src="/product/displayFile?fileName=${productVO.pd_img}" >
								</a>
							</div>
							<div class="description">
								<a href="/product/read${pm.makeQuery(pm.cri.page)}&pd_num=${productVO.pd_num}&categ_now=${categ_now}" >${productVO.pd_name}</a>
								<p>Price $ <fmt:formatNumber value="${productVO.pd_price}" pattern="###,###,###" /><br>
								 	Discount <fmt:formatNumber value="${productVO.pd_dc}" pattern="###,###,###" />%</p>
							</div>
							<c:if test="${sessionScope.user != null }">
							<div class="btnContainer">
								<button class="btn btn-primary" id="btn_buy" type="button" 
									onclick="location.href='/order/buy?pd_num=${productVO.pd_num}&od_amount=1';">BUY</button>
								<button class="btn btn-default" id="btn_cart" type="button" 
									onclick="cart_click(${productVO.pd_num})">CART</button>
							</div>
							</c:if>
						</c:forEach>
					</ul>
				</div>
				<%-- 페이지 표시 --%>
				<div class="box-footer container" style="width:100%; min-width:1400px;">
					<div class="text-center">
						<ul class="pagination">
							<c:if test="${pm.prev}">
								<li><a href="list${pm.makeQuery(pm.startPage-1)}&categ_now=${categ_now}">&laquo;</a>
								</li>
							</c:if>

							<c:forEach begin="${pm.startPage}" end="${pm.endPage}"
								var="idx">
								<li <c:out value="${pm.cri.page == idx?'class =active':''}"/>>
									<a href="list${pm.makeQuery(idx)}&categ_now=${categ_now}">${idx}</a>
								</li>
							</c:forEach>

							<c:if test="${pm.next && pm.endPage > 0}">
								<li><a href="list${pm.makeQuery(pm.endPage +1)}&categ_now=${categ_now}">&raquo;</a>
								</li>
							</c:if>
						</ul>
					</div>
				</div>
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
      </div>
      <!-- /.col-lg-9 -->

    <!-- /.row -->

  <!-- /.container -->

  <!-- Footer -->
<%@ include file="/WEB-INF/views/common/bottom.jsp" %>
</body>

</html>