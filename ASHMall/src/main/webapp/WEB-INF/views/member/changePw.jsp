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

<script type="text/javascript" src="/js/member/changePw.js"></script>
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
      <div class="col-lg-9">
	
        <div class="row">		

			<section class="content container-fluid">
				<div style="background-color: white; width:70%; padding: 5% 5%;">
				<h2>
					P A S S W O R D
				</h2>
				<%-- realPath 주석 
				<%= application.getRealPath("/") %>
				--%>
				<ol class="breadcrumb">
					<li>
						<a href="/"><i class="fa fa-dashboard"></i> M A I N </a>
					</li>
					<li>P A S S W O R D</li>
				</ol>
				</div>
			</section>

			<%-- MAIN CONTENT --%> 
			<section class="content container-fluid">
				<div style="background-color: white; width:80%; padding: 5% 5%;">
					<form id="changePwForm" method="post" action="/member/changePw">
						<div class="form-group">
							<input type= "hidden" name="mb_id" value="${sessionScope.user.mb_id}" />
							
							<input type="password" class="form-control" id="mb_password" class="form-control"
								placeholder="ENTER YOUR CURRENT PASSWORD" style="max-width: 630px;">
							
							<input type="password" class="form-control" id="mb_password_change" name="mb_password" class="form-control"
								placeholder="ENTER NEW PASSWORD" style="max-width: 630px; margin: 7px 0px;">
							
							<input type="password" class="form-control" id="mb_password_check" class="form-control"
								placeholder="RE-ENTER YOUR NEW PASSWORD" style="max-width: 630px;">
						</div>
						<div class="form-group">
							<input type="button" id="btn_submit" class="btn btn-primary" value="확인">
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