<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- 하위 카테고리 목록 -->
<ul style="list-style: none; text-align: center;">
	<c:forEach items="${userSubCategoryList}" var="subCategory">
		<li style="disply: inline-block;"> 
			<a href="/?categ_prtcode=${subCategory.categ_prtcode}&categ_now=${subCategory.categ_now}" style="color: black;">
				<c:choose>
					<c:when test="${productVO == null}">
						<!-- 리스트 -->
							<c:if test="${subCategory.categ_now eq categ_now}"><b>${subCategory.categ_name}</b></c:if>
							<c:if test="${subCategory.categ_now ne categ_now}"></c:if>
					</c:when>
					<c:otherwise>
						<!-- 상세 페이지 -->
						<c:if test="${subCategory.categ_now eq productVO.categ_now}"><b>${subCategory.categ_name}</b></c:if>
						<c:if test="${subCategory.categ_now ne productVO.categ_now}">${subCategory.categ_name}</c:if>
					</c:otherwise>
				</c:choose>					
				<%-- <c:if test="${subCategory.categ_now eq productVO.categ_now}"><b>${subCategory.categ_name}</b></c:if>
				<c:if test="${subCategory.categ_now ne productVO.categ_now}">${subCategory.categ_name}</c:if> --%>
			</a>
		
		</li>	
	
	</c:forEach>

</ul>
<br>