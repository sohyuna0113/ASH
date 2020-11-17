<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>
<%@include file="/WEB-INF/views/admin/include/head.jsp" %>
<%@ include file="/WEB-INF/views/admin/include/plugins.jsp" %>
<head>
<style>
input[type=text], select {
  width: 100%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

input[type=submit] {
  width: 100%;
  background-color: #4CAF50;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

</style>
<%-- 버튼 클릭 이벤트 메소드 --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- 숫자만 입력 가능한 기능 -->
<script>
function numkeyCheck(e) 
{ 
	var keyValue = event.keyCode; 
	
	if( ((keyValue >= 48) && (keyValue <= 57)) ) 
	{	
		return true; 
	}
	
	else return false; 
} 

$(document).on("keyup", "input:text[numberonly]", function() 
{
	$(this).val( $(this).val().replace(/[^0-9]/gi,""));	// 숫자 이외의 값을 입력하면 ""으로 변환하도록 함
});
</script>
<!-- 수정 후 다시 데이터를 합치는 기능 -->
<script>
$(document).ready(function()
{
	$('#agoMypage').on("click", function() 
	{					//get 방식에는 이런식으로 주소뒤에 붙는 값들을 붙여준다
		self.location = "/admin/member/userInfo?page=${cri.page}&perPageNum=${cri.perPageNum}"
				 	+ "&searchType=${cri.searchType}&keyword=${cri.keyword}&mb_id=${userVO.mb_id}";
	});
	
	$('#formf').on('submit', function()
	{
		var mb_phone = $('#mb_phone1').val() + "-" + $('#mb_phone2').val() + "-" + $('#mb_phone3').val();
		var mb_addr = $('#mb_addr').val() + "/" + $('#mb_addr_d').val();
		
		$('#mb_phone').val(mb_phone);
		$('#mb_addr').val(mb_addr);
		
		return true;
	});
});
</script>
</head>

<body class="hold-transition skin-blue sidebar-mini">
	<div class="wrapper">
		<!-- Main Header -->
		<%@include file="/WEB-INF/views/admin/include/main-header.jsp" %>
		<!-- Left side column. contains the logo and sidebar -->
		<%@include file="/WEB-INF/views/admin/include/main-sidebar.jsp" %>
		
		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>
					Admin<small>Members</small>
				</h1>
				<ol class="breadcrumb">
					<li>
						<a href="#"><i class="fa fa-dashboard"></i>Product Edit</a>
					</li>
					<li class="active"></li>
				</ol>
			</section>
		
		<!-- Main content -->
		<section class="content container-fluid">
			<div class="body">
			<div class="row">
			<div class="box" style="border: none;">
				<div class="box-body"></div>
					<form name="formf" method="post" id="formf" action="">
 
						<label for="mb_name">NAME</label>
				        <input id="mb_name" name="mb_name" type="text" value="${memberVO.mb_name}" readonly="readonly" class="mypage"/>
	
						
						<label for="mb_id">ID</label>
						<input id="mb_id" type="email" value="${memberVO.mb_id}" readonly="readonly" maxlength="30" class="mypage"/>
						
						<label for="mb_addr">ADDRESS</label>
				            	<c:set var="address_array" value="${fn:split(memberVO.mb_addr, '/')}"/>
				            	<input id="mb_addr" name="mb_addr" type="text" value="${address_array[0]}" readonly="readonly" class="mypage" style="width: 350px"/>&nbsp;/&nbsp;
				            	<input id="mb_addr_d" name="mb_addr_d" type="text" value="${address_array[1]}" readonly="readonly" class="mypage" style="width: 220px"/>
				            	<input type="hidden" name="mb_addr" id="mb_addr"/>

						<label for="mb_phone">PHONE</label>
				            	<c:set var="phone_array" value="${fn:split(memberVO.mb_phonenum, '-')}"/>
				            	<input id="mb_phonenum1" name="mb_phonenum1" type="text" maxlength="3" value="${phone_array[0]}" readonly="readonly" class="mypagep" style="width: 120px"/>&nbsp;-&nbsp;
				            	<input id="mb_phonenum2" name="mb_phonenum2" type="text" maxlength="4" value="${phone_array[1]}" readonly="readonly" class="mypagep" style="width: 150px"/>&nbsp;-&nbsp;
				            	<input id="mb_phonenum3" name="mb_phonenum3" type="text" maxlength="4" value="${phone_array[2]}" readonly="readonly" class="mypagep" style="width: 150px"/>
				            	<input type="hidden" name="mb_phonenum" id="mb_phonenum"/>

						<input type="submit" id="amodifyFit" value="Modify" class="mypages"/>
						    	&nbsp; &nbsp; &nbsp;
						 <input type="button" id="agoMypage" value="Cancle" class="mypages"/>
				
				</form>
		</div>
		</div>
		</div>
		</section>
		</div>

		<!-- Main Footer -->
		<%@ include file="/WEB-INF/views/admin/include/main-footer.jsp" %>
		<!-- Control Sidebar -->
		<aside class="control-sidebar control-sidebar-dark">
			<!-- Create the tabs -->
			<ul class="nav nav-tabs nav-justified control-sidebar-tabs">
				<li class="active">
					<a href="#control-sidebar-home-tab" data-toggle="tab"><i class="fa fa-home"></i></a>
				</li>
				<li>
					<a href="#control-sidebar-settings-tab" data-toggle="tab"><i class="fa fa-gears"></i></a>
				</li>
			</ul>
			<!-- Tab panes -->
			<div class="tab-content">
				<!-- Home tab content -->
				<div class="tab-pane active" id="control-sidebar-home-tab">
					<h3 class="control-sidebar-heading">Recent Activity</h3>
					<ul class="control-sidebar-menu">
						<li>
							<a href="javascript:;"> <i class="menu-icon fa fa-birthday-cake bg-red"></i>

								<div class="menu-info">
									<h4 class="control-sidebar-subheading">Langdon's Birthday</h4>

									<p>Will be 23 on April 24th</p>
								</div>
							</a>
						</li>
					</ul>
					<!-- /.control-sidebar-menu -->

					<h3 class="control-sidebar-heading">Tasks Progress</h3>
					<ul class="control-sidebar-menu">
						<li>
							<a href="javascript:;">
								<h4 class="control-sidebar-subheading">
									Custom Template Design
									<span class="pull-right-container">
										<span class="label label-danger pull-right">70%</span>
									</span>
								</h4>

								<div class="progress progress-xxs">
									<div class="progress-bar progress-bar-danger" style="width: 70%"></div>
								</div>
							</a>
						</li>
					</ul>
					<!-- /.control-sidebar-menu -->

				</div>
				<!-- /.tab-pane -->
				<!-- Stats tab content -->
				<div class="tab-pane" id="control-sidebar-stats-tab">Stats Tab Content</div>
				<!-- /.tab-pane -->
				<!-- Settings tab content -->
				<div class="tab-pane" id="control-sidebar-settings-tab">
					<form method="post">
						<h3 class="control-sidebar-heading">General Settings</h3>

						<div class="form-group">
							<label class="control-sidebar-subheading">
								Report panel usage
								<input type="checkbox" class="pull-right" checked>
							</label>

							<p>Some information about this general settings option</p>
						</div>
						<!-- /.form-group -->
					</form>
				</div>
				<!-- /.tab-pane -->
			</div>
		</aside>
		<!-- /.control-sidebar -->
		<!-- Add the sidebar's background. This div must be placed
  immediately after the control sidebar -->
		<div class="control-sidebar-bg"></div>
	</div>
	<!-- ./wrapper -->

</body>
</html>