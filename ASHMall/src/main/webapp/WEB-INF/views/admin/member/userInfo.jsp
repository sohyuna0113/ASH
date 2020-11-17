<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>

<html>
<%@include file="/WEB-INF/views/admin/include/head.jsp" %>
<%@ include file="/WEB-INF/views/admin/include/plugins.jsp" %>
<head>
<%-- 버튼 클릭 이벤트 메소드 --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
input[type=text], select {
  width: 100%;
  padding: 8px 150px;
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
  padding: 8px 150px;
  margin: 8px 0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

input[type=submit]:hover {
  background-color: #45a049;
}

</style>
<script>
$(document).ready(function()
{
	$('#userList').on("click", function() 
	{					//get 방식에는 이런식으로 주소뒤에 붙는 값들을 붙여준다
		self.location = "/admin/member/userList";
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
				<div class="box-body">
				<form role="form" id="modifyform" action="/admin/member/userModify" method="get">
				<input 	type='hidden' 	name='mb_id' 		value="${memberVO.mb_id}"> 
				<input 	type='hidden' 	name='page' 		value="${cri.page}"> 
				<input	type='hidden' 	name='perPageNum' 	value="${cri.perPageNum}">
				<input 	type='hidden' 	name='searchType' 	value="${cri.searchType}">
				<input 	type='hidden' 	name='keyword' 		value="${cri.keyword}">
				</form>
				<div class="box-body">
				<div class="pull-left image" style="padding: 12px 100px">
				<img src="/dist/img/dia00.jpg">
				</div>
			   	 <table class="mypage" style="padding: 8px 150px">
			    	<tr>
			    		<th colspan="15" class="mypage"> &#8273; ${memberVO.mb_name} </th>
			    	</tr>

			    	<tr>
			            <td class="mypage"><label for="mb_name"> &#8270; NAME</label></td>
			         </tr>
			         <tr>
			            <td><input id="mb_name" type="text" value="${memberVO.mb_name}" readonly="readonly" class="mypage"/></td>
			        </tr>
			        
					<tr>
			            <td class="mypage"><label for="mb_id"> &#8270; ID</label></td>
			         </tr>
			         <tr>
			            <td><input id="mb_id" type="text" value="${memberVO.mb_id}" readonly="readonly" class="mypage"/></td>
			        </tr> 
			        
			        <tr>
			            <td class="mypage"><label for="mb_addr"> &#8270; ADDR1</label></td>
			        </tr>
			        <tr>
			            <td><input id="mb_addr" type="text" value="${memberVO.mb_addr}" readonly="readonly" class="mypage"/></td>
			        </tr>
			        
			        <tr>
			            <td class="mypage"><label for="mb_addr_d"> &#8270; ADDR2</label></td>
			        </tr>
			        <tr>
			            <td><input id="mb_addr_d" type="text" value="${memberVO.mb_addr_d}" readonly="readonly" class="mypage"/></td>
			        </tr>
			        
			        <tr>
			            <td class="mypage"><label for="mb_phone"> &#8270; PHONE</label></td>
			        </tr>
			        <tr>
			            <td><input id="mb_phone" type="text" value="${memberVO.mb_phone}" readonly="readonly" class="mypage"/></td>
			        </tr>
			        
			        <tr>
			        	<td colspan="10" style="height: 50px;" align="center">
			        		<button type="button" id="userList" class="mypage">Go to List</button>
			        	</td>
			        </tr>
			    </table>
			</div>
		</div>
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