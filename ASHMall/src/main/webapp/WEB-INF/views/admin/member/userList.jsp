<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/admin/include/head.jsp" %>
<%@ include file="/WEB-INF/views/admin/include/plugins.jsp" %>
<head>
<%-- 버튼 클릭 이벤트 메소드 --%>

<script>
$(document).ready(function() 
{
	$('#searchBTN').on("click",	function(event) 
	{
		location = "/admin/member/userList"
					+ '${pageMaker.makeQuery(1)}'
					+ "&searchType="
					+ $("select option:selected").val()
					+ "&keyword=" 
					+ $('#keywordInput').val();
	});
	
	$('.deldel').on("click", function(event) 
	{		
		if (confirm("Remove from the list?") == true)
		{
			//location = "/admin/member/userWithdraw/?mb_id="+ $(this).attr("data-mb_id");
			location = "/admin/member/userDelete/${pageMaker.makeSearch(pageMaker.cri.page)}&mb_id=" + $(this).attr("data-mb_id");
			alert("Completed");
		}
		else
		{
			return false;
		}
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
						<a href="#"><i class="fa fa-dashboard"></i>Member Edit</a>
					</li>
					<li class="active">Member List</li>
				</ol>
			</section>
			
		<!-- Main content -->
		<section class="content container-fluid">
			<div class="body">
			<div class="row">
			<div class="box" style="border: none;">
				<div class="box-body">
				<form role="form" action="modifyPage" method="post">
				<input 	type='hidden' 	name='mb_id' 		value="$(this).attr("data-mb_id")"> 
				<input 	type='hidden' 	name='page' 		value="${cri.page}"> 
				<input	type='hidden' 	name='perPageNum' 	value="${cri.perPageNum}">
				<input 	type='hidden' 	name='searchType' 	value="${cri.searchType}">
				<input 	type='hidden' 	name='keyword' 		value="${cri.keyword}">		
				</form>
				<div class="box-body">
					<table class="table table-striped text-center">

				<tr>
				<th><input type="checkbox" id="checkAll" /></th>
				<th>ID</th>
				<th>NAME</th>
				<th>PHONE</th>
				<th>DEL</th>
				</tr>

			<c:forEach items="${orderList}" var="memberVO">
				<tr>
					<td class="col-md-1"><input type="checkbox"></td>
					<td class="col-md-2">
					<a href='/admin/member/userInfo${pageMaker.makeSearch(pageMaker.cri.page) }&mb_id=${memberVO.mb_id}'>${memberVO.mb_id}</a></td>
					<td class="col-md-2">${memberVO.mb_name}</td>
					<td class="col-md-2">${memberVO.mb_phone}</td>			
					<td class="col-md-2" style="width: 100px" align="center">
					<input type="button" class="deldel" data-mb_id="${memberVO.mb_id}" value="DEL"></td>
				</tr>
			</c:forEach>
				
				<tr align="center">
					<td colspan="6" align="center" style="height: 80px; text-align: center; margin: 0 auto; background-color: #f2f2f2; border-radius: 10px;">
											<!-- 페이징 기능 -->
							<div class="box-footer">

								<div class="text-center">
									<ul class="pagination">
										<!-- 이전표시 여부  [이전] -->
										<c:if test="${pm.prev}">
											<li><a href="list${pm.makeSearch(pm.startPage-1)}">&laquo;</a>
											</li>
										</c:if>
										<!-- 페이지목록번호 :  1  2  3  4  5  -->
										<c:forEach begin="${pm.startPage}" end="${pm.endPage}"
											var="idx">
											<li <c:out value="${pm.cri.page == idx?'class =active':''}"/>>
												<a href="list${pm.makeSearch(idx)}">${idx}</a>
											</li>
										</c:forEach>
										<!-- 다음표시 여부  [다음]-->
										<c:if test="${pm.next && pm.endPage > 0}">
											<li><a href="list${pm.makeSearch(pm.endPage +1)}">&raquo;</a>
											</li>
										</c:if>
									</ul>
								</div>
							</div>
					</td>
			<%-- 검색조건 설정 및 페이지 이동에도 해당값 유지 --%>		
	<div class="row text-center">	
		<div class="row text-center">
			<div style="display: inline-block; float: none; margin-left:15px;">
				<select name="searchType" style="width:180px; height:26px;">
					<option value="all"
					<c:out value="${cri.searchType == null ? 'selected':''}"/>>-
					</option>
					<option value="n"
					<c:out value="${cri.searchType eq 'n'?'selected':''}"/>>N A M E
					</option>
					<option value="i"
					<c:out value="${cri.searchType eq 'i'?'selected':''}"/>>I D
					</option>
				</select> 
		      
				<input type="text" name='keyword' id="keywordInput" class="lliissttsrc"	value='${cri.keyword}'/>
				<button id='searchBTN' class="lliisstt">Search</button>
			</div>
		</div>
		</div>
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