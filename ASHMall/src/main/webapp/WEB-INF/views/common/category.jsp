<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>
.bgGradient{
	background: linear-gradient(90deg, #2BC0E4,  #EAECC6) fixed;
}
#modify_dropdown {  
	display:none; /* 평상시에는 서브메뉴가 안보이게 하기 */ 
	height:auto; 
	padding:10px 0px; 
	margin:0px; 
	border:0px; 
	position:absolute; 
	width:130px; 
	z-index:200; 
}

#modify:hover #modify_dropdown{
	display:block;   /* 마우스 커서 올리면 서브메뉴 보이게 하기 */
}

</style>

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
		/* 1차 카테고리에 따른 2차 카테고리 작업. on()메서드: 매번진행 one()메서드: 단1회만 진행 */
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
		$("#subCategory option").remove();
		target.append(options);
	}
</script>
		
<!-- 카테고리 -->
<li class="header" style="background-color: #464646; color: #B8C7CE">C A T E G O R Y</li>

<div class="list-group">  
	<c:forEach items="${userCategoryList}" var="list">
   
          <a href="/product/list?categ_now=${list.categ_now}" class="list-group-item">
          <span>${list.categ_name}</span></a>
			<i class="fa fa-link"></i>
				<span>${list.categ_prt}</span>
				<span class="pull-right-container">
				<i class="fa fa-angle-left pull-right"></i>
				</span>
       
     <ul class="treeview-menu" id="mainCategory_${list.categ_now}"></ul>
	</c:forEach>
</div>
	