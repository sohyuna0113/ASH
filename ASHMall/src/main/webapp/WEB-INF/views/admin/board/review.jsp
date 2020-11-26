<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<!--
This is a starter template page. Use this page to start your new project from
scratch. This page gets rid of all links and provides the needed markup only.
-->
<html>
<%@include file="/WEB-INF/views/include/plugin_js.jsp"%>
<%@include file="/WEB-INF/views/admin/include/head.jsp" %>

<!--
BODY TAG OPTIONS:
=================
Apply one or more of the following classes to get the
desired effect
|---------------------------------------------------------|
| SKINS         | skin-blue                               |
|               | skin-black                              |
|               | skin-purple                             |
|               | skin-yellow                             |
|               | skin-red                                |
|               | skin-green                              |
|---------------------------------------------------------|
|LAYOUT OPTIONS | fixed                                   |
|               | layout-boxed                            |
|               | layout-top-nav                          |
|               | sidebar-collapse                        |
|               | sidebar-mini                            |
|---------------------------------------------------------|
-->
<script>
	var result = '${msg}';

	if (result == 'SUCCESS') {
		alert("처리가 완료되었습니다.");
	}
</script>

<script>
	$(document).ready(
			function() {

				$('#searchBtn').on(
						"click",
						function(event) {

							self.location = "list"
									+ '${pageMaker.makeQuery(1)}'
									+ "&searchType="
									+ $("select option:selected").val()
									+ "&keyword=" + $('#keywordInput').val();

						});

				$('#newBtn').on("click", function(evt) {

					self.location = "register";

				});

			});
</script>

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
					ADMINSTRATION <small>This is Admin Page for ASH Mall &#10084;</small>
				</h1>
				<ol class="breadcrumb">
					<li>
						<a href="#"><i class="fa fa-dashboard"></i> Level</a>
					</li>
					<li class="active">Here</li>
				</ol>
			</section>
		
		<!-- Main content -->
		<section class="content">
			<div class="row">
				<!-- left column -->
				<div class="col-md-12">
					<!-- general form elements -->
					<div class='box'>
						<div class="box-header with-border">
							<h3 class="box-title">Board List</h3>
						</div>
		
						<div class='box-body'>
							<select name="searchType">
								<option value="n"
									<c:out value="${cri.searchType == null?'selected':''}"/>>
									---</option>
								<option value="t"
									<c:out value="${cri.searchType eq 't'?'selected':''}"/>>
									Title</option>
								<option value="c"
									<c:out value="${cri.searchType eq 'c'?'selected':''}"/>>
									Content</option>
								<option value="w"
									<c:out value="${cri.searchType eq 'w'?'selected':''}"/>>
									Writer</option>
								<option value="tc"
									<c:out value="${cri.searchType eq 'tc'?'selected':''}"/>>
									Title OR Content</option>
								<option value="cw"
									<c:out value="${cri.searchType eq 'cw'?'selected':''}"/>>
									Content OR Writer</option>
								<option value="tcw"
									<c:out value="${cri.searchType eq 'tcw'?'selected':''}"/>>
									Title OR Content OR Writer</option>
							</select> <input type="text" name='keyword' id="keywordInput"
								value='${cri.keyword }'>
							<button id='searchBtn'>Search</button>
							<button id='newBtn'>New Board</button>
		
						</div>
					</div>
		
		
					<div class="box">
						<div class="box-header with-border">
							<h3 class="box-title">LIST PAGING</h3>
						</div>
						<div class="box-body">
							<table class="table table-bordered">
								<tr>
									<th style="width: 10px">BNO</th>
									<th>TITLE</th>
									<th>WRITER</th>
									<th>REGDATE</th>
									<th style="width: 40px">VIEWCNT</th>
								</tr>
		
								<c:forEach items="${list}" var="boardVO">
		
									<tr>
										<td>${boardVO.bno}</td>
										<td><a
											href='/sboard/readPage${pageMaker.makeSearch(pageMaker.cri.page) }&bno=${boardVO.bno}'>
												${boardVO.title} <strong>[ ${boardVO.replycnt} ]</strong>
										</a></td>
										<td>${boardVO.writer}</td>
										<td><fmt:formatDate pattern="yyyy-MM-dd HH:mm"
												value="${boardVO.regdate}" /></td>
										<td><span class="badge bg-red">${boardVO.viewcnt }</span></td>
									</tr>
		
								</c:forEach>
		
							</table>
						</div>
						<!-- /.box-body -->
		
		
						<div class="box-footer">
		
							<div class="text-center">
								<ul class="pagination">
		
									<c:if test="${pageMaker.prev}">
										<li><a
											href="list${pageMaker.makeSearch(pageMaker.startPage - 1) }">&laquo;</a></li>
									</c:if>
		
									<c:forEach begin="${pageMaker.startPage }"
										end="${pageMaker.endPage }" var="idx">
										<li
											<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
											<a href="list${pageMaker.makeSearch(idx)}">${idx}</a>
										</li>
									</c:forEach>
		
									<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
										<li><a
											href="list${pageMaker.makeSearch(pageMaker.endPage +1) }">&raquo;</a></li>
									</c:if>
		
								</ul>
							</div>
		
						</div>
						<!-- /.box-footer-->
					</div>
				</div>
				<!--/.col (left) -->
		
			</div>
			<!-- /.row -->
		</section>
		<!-- /.content -->

		</div>
		<!-- /.content-wrapper -->
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