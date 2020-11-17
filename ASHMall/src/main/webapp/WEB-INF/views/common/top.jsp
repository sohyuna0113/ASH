<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

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

<nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
    <div class="container">
      <div> <a class="navbar-brand" href="/"><img src="/dist/img/ash.png" style="marging-top:0px"></a> </div>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item active">
            <a style="margin-left:150px" class="nav-link" href="/"># H O M E
              <span class="sr-only">(current)</span>
            </a>
          </li>
          <%-- 로그인x --%>
          <c:if test="${sessionScope.user == null}">
          <li class="nav-item"> <a style="margin-left:190px" class="nav-link" href="/member/login"># L O G I N</a><span></span></li> 
         	
          <li class="nav-item"> <a style="margin-left:190px" class="nav-link" href="/member/join"># J O I N</a> </li>
           </c:if>
          
          <%-- 로그인o --%>	
          <c:if test="${sessionScope.user != null }">
          <li class="nav-item"> <a style="margin-left:40px" class="nav-link" href="/order/list"># O R D E R&nbsp&nbspL I S T</a> </li>

          <li class="dropdown user user-menu" id="modify"> <a style="margin-left:40px" class="nav-link" href="#"># M Y&nbsp&nbspP A G E</a> 
						<ul class="dropdown-menu" id="modify_dropdown">
							<li><a href="/member/checkPw?url=modify"># E D I T</a></li>
							<li><a href="/member/checkPw?url=changePw"># C H A N G E&nbsp&nbspP W</a></li>
							<li><a href="/member/checkPw?url=delete"># L E A V E</a></li>
						</ul></li>
    
          <li class="nav-item"> <a style="margin-left:40px" class="nav-link" href="/cart/list"># S H O P P I N G&nbsp&nbspB A G</a> </li>
          
          <li class="nav-item"> <a style="margin-left:40px" class="nav-link" href="/member/logout"># S I G N&nbsp&nbspO U T</a> </li>
          </c:if>
        </ul>
      </div>
    </div>
  </nav>