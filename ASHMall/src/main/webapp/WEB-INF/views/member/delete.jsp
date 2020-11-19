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
  <!-- Bootstrap core CSS -->
  <!-- Custom styles for this template -->
<%@ include file="/WEB-INF/views/common/bootcss.jsp" %>
<%@include file="/WEB-INF/views/include/plugin_js.jsp" %>

<script type="text/javascript" src="/js/member/delete.js"></script>
</head>

<body>
<!-- Navigation -->
<%@ include file="/WEB-INF/views/common/top.jsp" %>
  <!-- Page Content -->
	<div class="container">

	  <!-- 카테고리 메뉴  --> 
      <div class="col-lg-3">
		
		<%@ include file="/WEB-INF/views/common/category.jsp" %>

      </div>
      <!-- /.col-lg-3 -->
      <div class="col-lg-9">
	
        <div class="row">		

			<section class="content container-fluid">
				<div style="background-color: white; width:70%; padding: 5% 5%;">
				<h1>
					
				</h1>
				<%-- realPath 주석 
				<%= application.getRealPath("/") %>
				--%>
				</div>
			</section>

			
			<%-- MAIN CONTENT --%> 
			<section class="content container-fluid">
				<div style="background-color: white; width:80%; padding: 5% 5%;">
					<form id="deleteForm" method="post" action="/member/delete">
						<div class="form-group">
							<input type="hidden" name="mb_id" value="${sessionScope.user.mb_id}" />
							PLEASE CONFIRM TO UNSUBSCRIBE OUR MEMBMERSHIP
						</div>
						<div class="form-group">
							<input type="submit" id="btn_submit" class="btn btn-danger" value="CONFIRM">
							<input type="button" id="btn_cancle" class="btn btn-default" value="CANCEL" onclick="location.href='/';">
						</div>
					</form>
				</div>
			</section>
        </div>
        <!-- /.row -->
      </div>
      <!-- /.col-lg-9 -->
    </div>
    <!-- /.row -->

  <!-- /.container -->
  <!-- Footer -->
  <%@ include file="/WEB-INF/views/common/bottom.jsp" %>
  <!-- Bootstrap core JavaScript -->
  <%@ include file="/WEB-INF/views/common/bootjs.jsp" %>

</body>

</html>