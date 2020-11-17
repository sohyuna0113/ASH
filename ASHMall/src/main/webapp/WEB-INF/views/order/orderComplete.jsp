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
<%-- 버튼 클릭 이벤트 --%>
<script type="text/javascript" src="/js/order/orderComplete.js"></script>
</head>

<body>
  <!-- Navigation -->
  <%@ include file="/WEB-INF/views/common/top.jsp" %>

	  <!-- 카테고리 메뉴  --> 
      <div style="margin-top:90px" class="col-lg-3">
		
		<%@ include file="/WEB-INF/views/common/category.jsp" %>

      </div>
      <!-- /.col-lg-3 -->

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->

      	<div class="col-lg-9">
		<a href="#"><img src="/dist/img/dia00.jpg" style="padding:50px 180px; postion:fixed"></a>
		</div>
			
			<%-- MAIN CONTENT --%> 
			<section class="content container-fluid">
				<div class="box" style="border: none; padding:200px 50px; text-align: center;">
					<div class="box-body"  >
						<h3>Thank you for your order.</h3><br>
						<button type="button" id="btn_orderList" class="btn btn-primary">Check Orderlist</button>
						<button type="button" id="btn_main" class="btn btn-default">Go Shopping</button>
					</div>
				</div>
			</section>
		</div>			



<%@ include file="/WEB-INF/views/common/bottom.jsp" %>

</body>
</html>