<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/include/plugin_js.jsp"%>
<%@include file="/WEB-INF/views/admin/include/head.jsp" %>

<head>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

<script>
$(document).ready(function(){
	$("#orderListtb .orderdt").on("click", function(){
		
		var od_num = $(this).text();
		var curtr = $(this).parent();
	
		getorderList("/admin/member/readOrder/"+od_num, curtr);
	});
	
	/* 상품 리스트 - 삭제 버튼 클릭 시 */
	$("button[name=btn_delete]").on("click", function() {
		
		var od_num = $(this).val();
			$.ajax({
				url : "/admin/member/delete",
				type : "post",
				data : {
					od_num : od_num
				},
				dataType : "text",
				success : function(data) {
					location.href = "/admin/member/userOrder";
				}
			});
	
	});

});	

function getorderList(pageInfo, curtr) {
	$.getJSON(pageInfo, function(data) {
		
		// 상품후기가 존재
		if(data.length>0){
			// 댓글 리스트 템플릿 적용
			printData(data, curtr, $('#template'));
		}
	});
}

/*
 * printData()
 * : 상품후기 리스트를 보여주는 템플릿 적용
 */
var printData = function(replyArr, target, templateObject) {
	var template = Handlebars.compile(templateObject.html());

	var html = template(replyArr);
	$("tr.orderdt").remove();
	target.after(html);
}
</script>

<%-- 템플릿: 상품목록 --%>
<script id="template" type="text/x-handlebars-template">
	{{#each .}}
		<tr class="orderdt" colspan="10">
			<td></td>
			<td colspan="3">
			<img src="/admin/product/displayFile?fileName={{pd_img}}" style="width:150px"></td>
			<td colspan="3">
			<dl>
			<dt>Member</dt><dd>{{mb_id}}</dd><br>
			<dt>Addr1</dt><dd>{{od_addr}}</dd><br>
			<dt>Addr2</dt><dd>{{od_addr_d}}</dd><br>
			<dt>Zipcode</dt><dd>{{od_zipcode}}</dd><br>
			<dt>Phone</dt><dd>{{od_phone}}</dd><br>
			</dl>					
			</td>
			<td coldpan="4"></td>
		</tr>
		</div>
	{{/each}}
</script>
 
<script>
   $(".delivery1_btn").click(function(){
    $(".delivery").val("SHIPPED");
    run();
   });
   
   $(".delivery2_btn").click(function(){
    $(".delivery").val("DELIVERED");
    run();
    
   });
   
   function run(){
    $(".deliveryForm").submit();
   }
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
					Admin Page <small>Order Detail</small>
				</h1>
				<ol class="breadcrumb">
					<li>
						<a href="#"><i class="fa fa-dashboard"></i> Order Edit </a>
					</li>
					<li class="active">Order List</li>
				</ol>
			</section>

	<!-- Main content -->
	<section id="content container-fluid">
	<div class="body" style="padding: 20px">
	<div class="row">
			<div class="box" style="border: none;">
				<div class="box-body">
					<table id="orderListtb" class="table table-striped text-center">
						<tr>
							<th></th>
							<th> ITEM # </th>
							<th> MEMBER ID </th>
							<th> NAME </th>
							<th> PRICE </th>
							<th> DATE </th>
							<th> STATUS </th>
							<th> DELETE </th>
						</tr>
					
					<%-- 상품리스트출력 --%>
					<c:if test="${empty orderList}">
						<tr>
							<td colspan="10"> 
								<p style="padding:50px 0px; text-align: center;">ORDER EMPTY</p>
							</td> 
						<tr>
					</c:if>
								<%-- 상품이 존재하는 경우,  리스트 출력 --%>
					<c:forEach items="${orderList}" var="orderList">
						<tr>
							<td class="col-md-1"><input type="checkbox" name="check" class="check" value="${orderList.od_num}"></td>
											<td class="col-md-1 orderdt" style="cursor:pointer">${orderList.od_num}</td>
											<td class="col-md-2">${orderList.mb_id}</td>
											<td class="col-md-2">${orderList.od_name}</td>
											<td class="col-md-2">${orderList.od_price}</td>
											<!-- 상품 전시(보임/숨김)기능 -->
											<td class="col-md-2">${orderList.od_date}</td>
											
											<td class="col-md-3 deliveryChange">
												<button type="button" class="delivery1_btn">SHIPPED</button>
												<button type="button" class="delivery2_btn">DELIVERED</button>
											</td>
											<td class="col-md-2">
												<form>
													<!-- 상품 코드 -->
													<!--  <input type="hidden" name="od_num"
														value="${orderList.od_num}">  -->
													<!-- 삭제기능  -->
													<button type="button" name="btn_delete" class="btn btn-default" value="${orderList.od_num}">CANCEL</button>
												</form>
											</td>
										</tr>
							</c:forEach>
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	</section>
			
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
						</div>
		
			<!-- /.content -->
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

	<!-- ./wrapper -->

	<%@ include file="/WEB-INF/views/admin/include/plugins.jsp" %>
</body>
</html>