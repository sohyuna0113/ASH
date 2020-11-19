<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

<script>
	if("${msg}"=="LOGIN_SUCCESS"){
		alert("WELCOME");
	} else if("${msg}"=="LOGOUT_SUCCESS"){
		alert("GOOD BYE");
	} else if("${msg}"=="REGISTER_SUCCESS"){
		alert("SUCCESS");
	} else if("${msg}"=="MODIFY_USER_SUCCESS"){
		alert("UPDATED");
	} else if("{msg}"=="CHANGE_PW_SUCCESS"){
		alert("UPDATED");
	} else if("{msg}"=="DELETE_USER_SUCCESS"){
		alert("SORRY TO SEE YOU GO!");
	}
</script>

  <title>Shop Homepage - Start Bootstrap Template</title>

  <!-- Bootstrap core JavaScript -->
<%@ include file="/WEB-INF/views/common/bootjs.jsp" %>

  <!-- Bootstrap core CSS -->
  <!-- Custom styles for this template -->
<%@ include file="/WEB-INF/views/common/bootcss.jsp" %>
</head>

<body>

  <!-- Navigation -->
  <%@ include file="/WEB-INF/views/common/top.jsp" %>

  <!-- Page Content -->
  <div class="container">

    <div class="row">
    
    
	  <!-- 카테고리 메뉴  --> 
      <div class="col-lg-3">
		
		<%@ include file="/WEB-INF/views/common/category.jsp" %>

      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">
		<!-- main-carousel begin  -->
        <%@ include file="/WEB-INF/views/common/carousel.jsp" %>
		<!-- main-carousel end -->
				

			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->
		
        <div class="row">

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="product/list?categ_now=1000"><img class="card-img-top" src="/dist/img/intense-bluish-green.jpg" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="product/list?categ_now=1000">Fancy Intense Bluish Green Diamond Ring</a>
                </h4>
                <h5>$2,000,0000</h5>
                <p class="card-text">Rectangular modified brilliant Fancy Intense Bluish Green diamond, carat weight 3.15; round brilliant white diamonds, carat total weight .52; baguette white diamonds, carat total weight .16.</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="http://localhost:8080/product/list?categ_now=3000"><img class="card-img-top" src="/dist/img/morganite-necklace.jpg" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="http://localhost:8080/product/list?categ_now=3000">Tiffany Anniversary Morganite Necklace</a>
                </h4>
                <h5>TBD</h5>
                <p class="card-text">This one-of-a-kind creation features a 175-carat morganite “tied” with a diamond bow, centered with a Tiffany Novo® diamond in platinum. Carat weight: cushion-cut morganite, 175.72; Tiffany Novo® diamond, 2.00, colour grade F. Carat total weight: round brilliant diamonds, 17.09.</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="http://localhost:8080/product/list?categ_now=2000"><img class="card-img-top" src="/dist/img/tanzanite-earrings.jpg" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="http://localhost:8080/product/list?categ_now=2000">Diamond and Tanzanite Flower Drop Earrings</a>
                </h4>
                <h5>$9000</h5>
                <p class="card-text">Inspired by the idea of abstract flower petals, the Tiffany Paper Flowers collection is a balance of refined femininity and industrial modernity. Glowing tanzanites are the centerpieces of these modern earrings.</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>
<!--  
          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">Item Four</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">Item Five</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur! Lorem ipsum dolor sit amet.</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>

          <div class="col-lg-4 col-md-6 mb-4">
            <div class="card h-100">
              <a href="#"><img class="card-img-top" src="http://placehold.it/700x400" alt=""></a>
              <div class="card-body">
                <h4 class="card-title">
                  <a href="#">Item Six</a>
                </h4>
                <h5>$24.99</h5>
                <p class="card-text">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Amet numquam aspernatur!</p>
              </div>
              <div class="card-footer">
                <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
              </div>
            </div>
          </div>

        </div>
         -->

      </div>
      <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->

  <!-- Footer -->
  <%@ include file="/WEB-INF/views/common/bottom.jsp" %>

</body>

</html>

