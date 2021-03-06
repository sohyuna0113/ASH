<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<html>
<head>
  <!-- Bootstrap core JavaScript -->
<%@ include file="/WEB-INF/views/common/bootjs.jsp" %>
  <!-- Bootstrap core CSS -->
  <!-- Custom styles for this template -->
<%@ include file="/WEB-INF/views/common/bootcss.jsp" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script type="text/javascript" src="/js/product/read.js"></script>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
<%-- 템플릿: 상품목록 --%>
<script id="template" type="text/x-handlebars-template">
	{{#each .}}
		<li class="replyLi" data-rv_num={{rv_num}}>
        	<i class="fa fa-comments bg-blue"></i>
            <div class="timeline-item" >
                <span class="time">
                	<i class="fa fa-clock-o"></i>{{prettifyDate rv_date}}
                </span>
                <h3 class="timeline-header">
					<strong>{{checkRating rv_score}} <p class='rv_score' style="display:inline-block;">{{rv_score}}</p></strong> 
					</h3>
                <div class="timeline-body">
					Review #  {{rv_num}} <p style="float:right;">by {{mb_id}}</p> <br>
					<p id='rv_content'>{{rv_content}}</p> </div>
				<div class="timeline-footer" style="float:right;">
					{{eqReplyer mb_id rv_num}}
				</div>
	         </div>			
         </li>
	{{/each}}
</script>
<%-- 버튼 클릭 이벤트 메소드 /   --%>
<script>
	$(document).ready(function(){
		/* 상품 목록 버튼 클릭시 */
		$('#btn_list').on("click", function(){
			location.href="/product/listSearch${pm.makeQuery(pm.cri.page)}"
		});
	
		/*
		사용자 정의 헬퍼(prettifyDate)
		 : 매개변수로 받은 timeValue를 원하는 날짜 형태로 바꿔준다.
		*/
		Handlebars.registerHelper("prettifyDate", function(timeValue) {
			var dateObj = new Date(timeValue);
			var year = dateObj.getFullYear();
			var month = dateObj.getMonth() + 1;
			var date = dateObj.getDate();
			return year + "/" + month + "/" + date;
		});

		/*
		사용자 정의 헬퍼(checkRating)
		 : 매개변수로 받은 후기 평점을 별표로 출력 
		*/
		Handlebars.registerHelper("checkRating", function(rating) {
			var stars = "";
			switch(rating){
				case 1:
					 stars="★☆☆☆☆";
					 break;
				case 2:
					 stars="★★☆☆☆";
					 break;
				case 3:
					 stars="★★★☆☆";
					 break;
				case 4:
					 stars="★★★★☆";
					 break;
				case 5:
					 stars="★★★★★";
					 break;
				default:
					stars="☆☆☆☆☆";
			}
			return stars;
		});
		
		/*
		사용자 정의 헬퍼(eqReplyer)
		 : 로그인한 아이디와 리뷰의 아이디 확인후 수정/삭제 버튼 활성화
		*/
		Handlebars.registerHelper("eqReplyer", function(replyer, rv_num) {
			var btnHtml = '';
			var mb_id = "${sessionScope.user.mb_id}";
			if (replyer == "${user.mb_id}") {
				btnHtml = "<a class='btn btn-primary btn-xs' data-toggle='modal' data-target='#modifyModal'>"
					  + "MODIFY</a>"
					  + "<button class='btn btn-danger btn-xs' style='margin-left:5px;'" 
					  + "onclick='deleteReview("+rv_num+");'"
					  + "type='button' >DELETE</button>"; 
			}
			return new Handlebars.SafeString(btnHtml);
			

		});
				
	});
</script>

<%-- 스타일 --%>
<style>
     #star_grade a{
     	font-size:22px;
        text-decoration: none;
        color: lightgray;
    }
    #star_grade a.on{
        color: black;
    }
    
    #star_grade_modal a{
     	font-size:22px;
        text-decoration: none;
        color: lightgray;
    }
    #star_grade_modal a.on{
        color: black;
    }
    
    .popup {position: absolute;}
    .back { background-color: gray; opacity:0.5; width: 100%; height: 300%; overflow:hidden;  z-index:1101;}
    .front { 
       z-index:1110; opacity:1; boarder:1px; margin: auto; 
      }
     .show{
       position:relative;
       max-width: 1200px; 
       max-height: 800px; 
       overflow: auto;       
     } 
</style>

</head>

<body>
  <!-- Navigation -->
  <%@ include file="/WEB-INF/views/common/top.jsp" %>
  <!-- Page Content -->
  <div class="container">

    <div class="row">

		<div class="content-wrapper">
			
			<section class="content-header">
				<h1>
					Product Detail 
				</h1>
				<%-- realPath 주석 
				<%= application.getRealPath("/") %>
				--%>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i>Back to List</a></li>
					<li class="active">Product Detail</li>
				</ol>
			</section>
			
			<%-- MAIN CONTENT --%>
			<section class="content container-fluid">

				<!-- 상품등록 폼 -->
				<div class="row">
					<!-- left column -->
					<div class="col-md-12">
						<!-- general form elements -->
						<div class="box box-primary">
							<div class="box-header">
								<h3 class="box-title">Product Detail</h3>
							</div>
							<!-- /.box-header -->
							
							<%-- 상품 상세 정보 출력 --%>
							<div class="box-body">
								<div class="form-group container" style="margin:30px 0px; height:350px;" >
									<div style="float:left; width:30%; height:100%;">
										<img src="/product/displayFile?fileName=${vo.pd_img}" style="display: inline; width:90%;">
									</div>
									<div style="display: inline-block; margin-left:20px;">
										<label for="exampleInputEmail1">Product Name</label><br>
										<span>${vo.pd_name}</span><br><br>
										
										<label for="exampleInputEmail1">Company</label><br>
										<span>${vo.pd_company}</span><br><br>
										
										<div>
											<label for="exampleInputEmail1" style="width:100px; margin-right:10px;">Price</label> 
											<label for="exampleInputEmail1" style="width:100px;">Discount</label> <br>
											<span style="width:100px; margin-right:10px; display:inline-block;">
												<fmt:formatNumber value="${vo.pd_price}" pattern="###,###,###" />
											</span>
											<span style="width:100px; display:inline-block;">
												<fmt:formatNumber value="${vo.pd_dc}" pattern="###,###,###" />
											</span>
										</div>
										<br>
										
										<div>
											<form method="get" action="/order/buy" >
												<label for="exampleInputEmail1">Amount</label><br>
												<input type="number" id="od_amount" name="od_amount" value="1" /><br><br>
												<input type="hidden" id="pd_num" name="pd_num" value="${vo.pd_num}" />
												<button type="submit" id="btn_buy" class="btn btn-primary">Buy</button>
												<button type="button" id="btn_cart" class="btn btn-default">Cart</button>
											</form>
										</div>
										
									</div>
								</div>
								<!-- 상품 상세 -->
								<label for="detail">Detail</label><br>
								<div contenteditable="false" style="border: 1px solid grey; padding: 20px;">
									${vo.pd_detail}
								</div>
								<br>
								
								<%-- 상품 후기 --%>
								<div class='popup back' style="display:none;"></div>
							    <div id="popup_front" class='popup front' style="display:none;">
							     	<img id="popup_img">
							    </div>
						    	<form role="form" action="modifyPage" method="post">
									<input type='hidden' name='bno' value="${boardVO.bno}">
									<input type='hidden' name='page' value="${cri.page}"> 
									<input type='hidden' name='perPageNum' value="${cri.perPageNum}">
									<input type='hidden' name='searchType' value="${cri.searchType}">
									<input type='hidden' name='keyword' value="${cri.keyword}">	 
								</form>
								
								<div>
									<!-- 상품후기쓰기 부분 -->
									 <div>
										<label for="review">Review</label><br>
										<div class="rating">
											<p id="star_grade">
										        <a href="#">★</a>
										        <a href="#">★</a>
										        <a href="#">★</a>
										        <a href="#">★</a>
										        <a href="#">★</a>
											</p>
										</div>
										<textarea id="reviewContent" rows="3" style="width:100%;"></textarea><br>
									
										<!-- 상품 후기 리스트 -->
									 	<ul class="timeline" style="list-style:none;">
				 							 <!-- timeline time label -->
											<li class="time-label" id="repliesDiv">
												<span class="btn btn-default">
											    	Product Review <small id='replycntSmall'> [ ${totalReview} ] </small>
											    </span>
											    <button class="btn btn-primary" id="btn_write_review" type="button">Write Review</button>
											</li>
											<li class="noReview" style="display:none;">
												<i class="fa fa-comments bg-blue"></i>
												<div class="timeline-item" >
													 <h3 class="timeline-header">
														<br>
													</h3>
												</div>
											</li>
										</ul>
										<!-- 상품 후기 리스트 페이지부분 -->  
										<div class='text-center'>
											<ul id="pagination" class="pagination pagination-sm no-margin "></ul>
									 	</div>
									 </div>
									 
									 
									 <%-- Modal : 상품후기 수정/삭제 팝업 --%>
									<div id="modifyModal" class="modal modal-primary fade" role="dialog" style="position:fixed">
									  <div class="modal-dialog">
									    <!-- Modal content-->
									    <div class="modal-content">
									      <div class="modal-header" >
									      
									        <div class="modal-title">
												<p id="star_grade_modal">
											        <a href="#">★</a>
											        <a href="#">★</a>
											        <a href="#">★</a>
											        <a href="#">★</a>
											        <a href="#">★</a>
												</p>
									        </div>
									      </div>
									      <div class="modal-body" data-rv_num>
									        <p><input type="text" id="replytext" class="form-control"></p>
									      </div>
									      <div class="modal-footer">
									        <button type="button" class="btn btn-info" id="btn_modal_modify">MODIFY</button>
									        <button type="button" class="btn btn-default" data-dismiss="modal">CLOSE</button>
									      </div>
									    </div>
									  </div>
									</div>      
							</div>
							
							
							<!-- /.box-body -->

							<div class="box-footer">
								<div>
									<hr>
								</div>

								<ul class="mailbox-attachments clearfix uploadedList">
								</ul>

								<button id="btn_list" type="button" class="btn btn-primary" >Go List</button>
							</div>

						</div>

					</div>

				</div>

			</div>

		</section>
	
		</div>
		
	</div>
	
</div>

<%@ include file="/WEB-INF/views/common/bottom.jsp" %>

</body>
</html>