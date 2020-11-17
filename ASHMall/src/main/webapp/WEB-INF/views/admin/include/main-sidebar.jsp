<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<aside class="main-sidebar">

<%-- Handlebar Template --%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script id="subCGListTemplate" type="text/x-handlebars-template">
	{{#each .}}
		<li><a href="/product/list?categ_now={{categ_now}}">{{categ_name}}</a></li>
	{{/each}}
</script>

<%-- 2차 카테고리 템플릿 적용함수 --%>
<script>
	$(document).ready(function(){
		/* 1차 카테고리에 따른 2차 카테고리 작업.   on()메서드: 매번진행 one()메서드: 단1회만 진행 */
		$(".mainCategory").one("click", function(){
			var mainCGCode= $(this).val();
			var url = "/product/subCGList/" + mainCGCode;
			
						
			// REST 방식으로 전송
			$.getJSON(url, function(data){
				// 받은 데이터로 subCategory에 템플릿 적용
				subCGList(data, $("#mainCategory_"+mainCGCode) ,$("#subCGListTemplate"));
				
			});

		});	
	});

	var subCGList = function(subCGStr, target, templateObject) {

		var template = Handlebars.compile(templateObject.html());
		var options = template(subCGStr);

		// 기존 option 제거(누적방지)
		//$("#subCategory option").remove();
		target.append(options);
	}
</script>
			<!-- sidebar: style can be found in sidebar.less -->
			<section class="sidebar">

				<!-- Sidebar user panel (optional) -->
				<div class="user-panel">
					<div class="pull-left image">
						<img src="/dist/img/user2-160x160.jpg" class="img-circle" alt="User Image">
					</div>
					<div class="pull-left info">
						<p>Sohyun Ahn</p>
						<!-- Status -->
						<a href="#"><i class="fa fa-circle text-success"></i> Online</a>
					</div>
				</div>

				<!-- search form -->
				<form action="/product/listSearch" method="get" class="sidebar-form">
					<div class="input-group">
						<input type="hidden" name="searchType" class="form-control" value="name_detail">
						<input type="text" name="keyword" class="form-control" placeholder="Search product"
							<c:if test="${!empty scri}">
								value="<c:out value='${scri.keyword}' />"						
							</c:if>
							style="background-color: #6e6e6e; color:#B8C7CE">
						<span class="input-group-btn">
							<button type="submit" name="search" id="search-btn" class="btn btn-flat">
								<i class="fa fa-search"></i>
							</button>
						</span>
					</div>
				</form>
				<!-- /.search form -->

<!-- Sidebar Menu -->
<!-- 카테고리 작업은 DB에서 수동으로 데이터를 입력한다. -->
			<c:choose>
				<c:when test="${sessionScope.admin != null}"> <!-- 로그인 O -->
				<ul class="sidebar-menu" data-widget="tree">
					<li class="header">ADMIN</li>
						<li class="treeview">
							<a href="#"><i class="fa fa-link"></i> <span>Product</span> <span class="pull-right-container">
								<i class="fa fa-angle-left pull-right"></i>
							</span> </a>
								<ul class="treeview-menu">
							<li>
								<a href="/admin/product/list">List</a>
							</li>
							<li>
								<a href="/admin/product/insert">Upload</a>
							</li>
					<!-- Optionally, you can add icons to the links -->
						</ul>
					<li class="treeview">
						<a href="#"><i class="fa fa-link"></i> <span>Order</span> <span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
							</span> </a>
								<ul class="treeview-menu">
							<li>
								<a href="/admin/member/userOrder">Orders</a>
							</li>
						</ul>
					<li class="treeview">
						<a href="#"><i class="fa fa-link"></i> <span>Member</span> <span class="pull-right-container">
						<i class="fa fa-angle-left pull-right"></i>
							</span> </a>
								<ul class="treeview-menu">
							<li>
								<a href="/admin/member/userList">Members</a>
							</li>
						</ul>
				</ul>
				<!-- /.sidebar-menu -->
				</c:when>
			</c:choose>
			</section>
			<!-- /.sidebar -->
		</aside>