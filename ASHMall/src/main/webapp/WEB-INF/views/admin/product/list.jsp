<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>

<html>
<%@include file="/WEB-INF/views/admin/include/head.jsp" %>
<%@include file="/WEB-INF/views/include/plugin_js.jsp"%>
<head>
<%-- 버튼 클릭 이벤트 메소드 --%>
<script>
	$(document).ready(function(){
		/* 검색 버튼 클릭 */
		$('#btn_search').on("click", function(){
			self.location = "list"
				+ '${pm.makeQuery(1)}'
				+ "&searchType="
				+ $("select option:selected").val()
				+ "&keyword=" + $('#keyword').val();
		});
		
		/* 전체 선택 체크박스 클릭시 */
		$("#checkAll").on("click", function(){
			// 전체선택 클릭 여부로 다른 체크박스 체크
			$(".check").prop('checked', this.checked);
		});
		
		/* 체크박스 중 선택되지 않은 체크박스 존재시 전체선택 해제 */
		$('.check').on("click", function(){
			$("#checkAll").prop('checked', false);	
		});
		
		/* 선택 상품 수정 버튼 클릭시 */
		$("#btn_edit_check").on("click", function(){
		
			if($("input[name='check']:checked").length==0){
				alert("CHECK YOUR PRODUCT TO EDIT");
				return;
			}
			var checkArr = [];
			var amountArr = [];
			var buyArr = [];
			
			var pd_amount = $("#pd_amount").val();
			var pd_buy = $("#pd_buy:selected").val();
			
			/* 체크된 상품의 value를 가져옴 */
			
			$("input[name='check']:checked").each(function(i){
				var pd_num = $(pd_num).val();
				var pd_amount = $("input[name='amount_"+pd_num+"']").val();
				var pd_buy = $("select[name='buy_"+pd_num+"']").val();
				
				checkArr.push(pd_num);
				amountArr.push(pd_amount);
				buyArr.push(pd_buy);
				
			});
			
			$.ajax({
				url: '/admin/product/editChecked',
				type: 'post',
				dataType: 'text',
				data: {
						checkArr : checkArr,
						amountArr : amountArr,
						buyArr : buyArr
				},
				success : function(data) {
					alert("PROCESS COMPLETED");
					location.href="/admin/product/list${pm.makeSearch(pm.cri.page)}";
				}
			});
		});
	
	/* 선택상품 삭제버튼 클릭시 */
	$("#btn_delete_check").on("click", function(){

		// 체크여부 유효성 검사
		if($("input[name='check']:checked").length==0) {
			alert("PLEASE CHECK THE PRODUCT TO DELETE");
			return;
		}
		
		// 체크된 상품이 존재할 경우 진행
		var result = confirm("DELETE THE PRODUCT?");
		if(result){
			
			var checkArr = [];
			var imgArr = [];
			
			// 체크된 상품의 값을 가져옴
			$("input[name='check']:checked").each(function(i){
				var pd_num = $(this).val();
				var pd_img = $("input[name='img_"+pd_num+"']").val();
				
				checkArr.push(pd_num);
				imgArr.push(pd_img);
			});
		
			$.ajax({
				url: '/admin/product/deleteChecked',
				type: 'post',
				dataType: 'text',
				data: {
					checkArr : checkArr,
					imgArr : imgArr
					},
				success : function(data) {
					alert("PROCESS COMPLETED");
					location.href = "/admin/product/list${pm.makeSearch(pm.cri.page)}";
				}
			});
		} else{}
		
	});
		
	/* 상품 리스트 - 삭제 버튼 클릭 시
	코드수정됨 $(this).parent().submit(); 
	
	forEach문이기 때문에 선택자가 다수 -> DOM기능 메소드의  
	*/
	$("button[name=btn_delete]").on("click", function(){
		var result = confirm("이 상품을 삭제하시겠습니까?");
		if(result){
			$(this).parent().submit();
				/* $(".deleteForm").submit(); */ 
				// 문법에러 : 다른 데이터가 삭제가 된다. 
			} else{}
		});
	});
</script>

<script>
	/* 상품 수정버튼 클릭시 */
	var clickEdit = function(pd_num){
		var url = '/admin/product/edit${pm.makeSearch(pm.cri.page)}&pd_num=' + pd_num;
		location.href = url;
	};
</script>

<%-- 메세지 처리 --%>
<script>
	if ("${msg}" == "INSERT_SUCCESS") {
		alert("REGISTER COMPLETED");
	} else if ("${msg}" == "EDIT_SUCCESS") {
		alert("MODIFY COMPLETED");
	} else if ("${msg}" == "DELETE_SUCCESS") {
		alert("DELETE COMPLETED");
	};
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
					Admin<small>Product List</small>
				</h1>
				<ol class="breadcrumb">
					<li>
						<a href="#"><i class="fa fa-dashboard"></i>Product Edit</a>
					</li>
					<li class="active">Product List</li>
				</ol>
			</section>

	<!-- Main content -->
	<section class="content container-fluid">
	
	<div class="row">
	<%-- 검색조건 설정 및 페이지 이동에도 해당값 유지 --%>		
	<div class="row text-center">	
		<div class="row text-center">
			<div style="display: inline-block; float: none; margin-left:15px;">
				<select name="searchType" style="width:180px; height:26px;">
					<option value="null"
						<c:out value="${cri.searchType == null?'selected':''}" />>SEARCH CRITERIA </option>
					<option value="name"
						<c:out value="${cri.searchType eq 'name'?'selected':''}" />>PRODUCT NAME </option>
					<option value="detail"
						<c:out value="${cri.searchType eq 'detail'?'selected':''}" />>CONTENT </option>
					<option value="company"
						<c:out value="${cri.searchType eq 'company'?'selected':''}" />>BRAND HOUSE </option>
					<option value="name_detail"
						<c:out value="${cri.searchType eq 'name_detail'?'selected':''}" />>PRODUCT NAME + CONTENT </option>
					<option value="name_company"
						<c:out value="${cri.searchType eq 'name_company'?'selected':''}" />>PROUCT NAME + BRAND HOUSE </option>
					<option value="all"
						<c:out value="${cri.searchType eq 'all'?'selected':''}" />>PROUCT NAME + CONTENT + BRAND HOUSE </option>
				</select>
			<input type="text" name='keyword' id="keyword" style="width:250px; padding-left:5px" value='${cri.keyword}' />
			<button id="btn_search" class="btn btn-default">SEARCH</button>
			</div>
			<div style="display: inline-block; float: none; margin-right:15px;">
			<button id="btn_edit_check"  class="btn btn-default" >EDIT</button>
			<button id="btn_delete_check"  class="btn btn-default" >DELETE</button>
			<button class="btn btn-primary"
					onClick="location.href='/admin/product/insert'">REGISTER</button>
			</div>	
			</div>
			<br>
			
			<div class="box" style="border: none;">
				<div class="box-body">
					<table class="table table-striped text-center">
						<tr>
							<th><input type="checkbox" id="checkAll" /></th>
							<th> ITEM NUMBER </th>
							<th> ITEM IMAGE </th>
							<th> ITEM NAME </th>
							<th> PRICE </th>
							<th> DISCOUNT </th>
							<th> BRAND HOUSE </th>
							<th> AMOUNT </th>
							<th> AVAILABILITY </th>
							<th> EDIT/DELETE </th>
						</tr>
					
					<%-- 상품리스트출력 --%>
					<c:if test="${empty productList}">
						<tr>
							<td colspan="10"> 
								<p style="padding:50px 0px; text-align: center;"><td colspan="10">
								<p style="padding:50px 0px; text-align: center;">SELECTED PRODUCT NOT EXIST</p>
							</td> 
						<tr>
					</c:if>
					<c:forEach items="${productList}" var="productVO">
						<tr>
							<td><input type="checkbox" name="check" class="check" value="${productVO.pd_num}"></td>
											<td class="col-md-1">${productVO.pd_num}</td>
											<td class="col-md-2">
												<img src="/admin/product/displayFile?fileName=${productVO.pd_img}" style="width:80px;">		
												<!--  용도?-->
												<input type="hidden" name="img_${productVO.pd_num}" value="${productVO.pd_img}" />
											</td>
											<td class="col-md-2"><a
												href="/admin/product/read${pm.makeSearch(pm.cri.page)}&pd_num=${productVO.pd_num}"
												style="color: black;"> ${productVO.pd_name} </a></td>
											<td class="col-md-1">${productVO.pd_price}</td>
											<td class="col-md-1">${productVO.pd_dc}</td>
											<td class="col-md-2">${productVO.pd_company}</td>
											
											<!-- 상품 전시(보임/숨김)기능 -->
											<td><input name="amount_${productVO.pd_num}" type="number" style="width:80px; height:34px; padding-left:5px;" value="${productVO.pd_amount}" /></td>
											<td>
												<select class="form-control" name="buy_${productVO.pd_num}" style="width: 60px; display: inline-block;">
												  <option <c:out value="${productVO.pd_buy == 'Y'?'selected':''}" />>Y</option>
												  <option <c:out value="${productVO.pd_buy == 'N'?'selected':''}" />>N</option>
												</select>
											</td>
											<td class="col-md-2">
												<form class="deleteForm" method="post"
													action="/admin/product/delete${pm.makeSearch(pm.cri.page)}">
													<!-- 상품 코드 -->
													<input type="hidden" name="pd_num"
														value="${productVO.pd_num}">
													<!-- 파일 이미지명 -->
													<input type="hidden" name="pd_img"
														value="${productVO.pd_img}">
													<!-- 수정기능 -->
													<button type="button" name="btn_edit" class="btn btn-default" onclick="clickEdit(${productVO.pd_num});">EDIT</button>
													
													<!-- 삭제기능 -->
													<button type="button" name="btn_delete" class="btn btn-danger">DELETE</button>
												</form>
											</td>
										</tr>

							</c:forEach>
							
						</table>
					</div>
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
					</div>
				</div>
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

<%@ include file="/WEB-INF/views/admin/include/plugins.jsp" %>
</body>
</html>