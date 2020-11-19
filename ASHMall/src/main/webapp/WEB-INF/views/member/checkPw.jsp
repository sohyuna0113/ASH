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

<script>
if("${msg}" == "CHECK_PW_FAIL"){
	alert("비밀번호가 다릅니다.");}
</script>
<script>
$(document).ready(function(){
	$("#btn_submit").on("click", function(){
		if($("#mb_password").val()== null || $("#mb_password").val()==""){
			alert("비밀번호를 입력해주세요.");
		} else {
			$("#checkPwForm").submit();
		}
	});
});
</script>
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
      <div class="col-lg-9">
   		<a href="#">
    	<img src="/dist/img/moon.jpg"  style="padding:5px 60px; postion:fixed">
   		</a>
  
	
        <div class="row">		

			<%-- MAIN CONTENT --%> 
			<section class="content container-fluid">
				<div style="background-color: white; width:70%; padding: 5% 5%;">
					<form id="checkPwForm" method="post" action="checkPw">
						<div class="form-group">
							<!-- 1)회원정보 수정 url=modify,  2)비밀번호 변경 url=changePw, 3)회원 탈퇴  url=delete  -->
							<input type="hidden" name="url" value="${url}" />
							<input type="password" class="form-control" id="mb_password" name="mb_password" class="form-control"
								placeholder="ENTER YOUR PASSWORD" style="max-width: 630px;">
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
  <!-- Footer -->
  <%@ include file="/WEB-INF/views/common/bottom.jsp" %>
  <!-- Bootstrap core JavaScript -->
  <%@ include file="/WEB-INF/views/common/bootjs.jsp" %>

</body>

</html>
		