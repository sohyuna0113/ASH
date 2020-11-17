$(document).ready(function() {
	// 처음에 가격 업데이트
	updatePrice();

	/* 전체 선택 체크박스 클릭 시 */
	$("#checkAll").on("click", function() {
		// 전체선택 클릭 여부로 다른 체크박스 체크
		$(".check").prop('checked', this.checked);
		updatePrice();
	});

	/* 체크박스 중 선택안된 체크박스 존재 시 전체선택 해제 */
	$(".check").on("click", function() {
		$("#checkAll").prop('checked', false);
		updatePrice();
	});


	/* 선택 상품 삭제 버튼 클릭 시 */
	$("#btn_delete_check").on("click", function() {

		// 체크여부 유효성 검사
		if ($("input[name='check']:checked").length == 0) {
			alert("SELECT PRODUCT TO REMOVE");
			return;
		}

		// 체크 된 상품이 존재할 경우 진행
		var result = confirm("REMOVE ITEMS FROM THE LIST");

		if (result) {
			var checkArr = [];

			// 체크 된 상품의 value(cart_code)를 가져옴
			$("input[name='check']:checked").each(function(i) {
				var cart_code = $(this).val();
				checkArr.push(cart_code);
			});

			$.ajax({
				url : '/cart/deleteChecked',
				type : 'post',
				dataType : 'text',
				data : {
					checkArr : checkArr
				},
				success : function(data) {
					location.href = "/cart/list";
				}
			});
		} else {
		}
	});

	/* 상품 리스트 - 상품 수량 변경 버튼 클릭 시 */
	$("button[name=btn_modify]").on(
			"click",
			function() {
				var cart_code = $(this).val(); // 장바구니 코드
				var buy_amount = $(
						"input[name='buy_amount_" + cart_code + "']")
						.val();					// 변경된 수량 

				$.ajax({
					url : "/cart/modify",
					type : "post",
					dataType : "text",
					data : {
						cart_code : cart_code,
						buy_amount : buy_amount
					},
					success : function(data) {
						alert("수량이 변경되었습니다.");
						location.href = "/cart/list";
					}
				});

			});

	/* 상품 리스트 - 삭제 버튼 클릭 시 */
	$("button[name=btn_delete]").on("click", function() {
		var cart_code = $(this).val();
		$.ajax({
			url : "/cart/delete",
			type : "post",
			data : {
				cart_code : cart_code
			},
			dataType : "text",
			success : function(data) {
				location.href = "/cart/list";
			}
		});

	});
});

/* 총 가격, 할인 이후 가격 변경 */ 
var updatePrice = function(){

	var size = $("input[name='check']:checked").length;
	var totalPrice= 0;
	var totalDiscount = 0;

	$("input[name='check']:checked").each(function(i){
		var cart_code = $(this).val();
		var amount = $("input[name='buy_amount_"+cart_code+"']").val();
		// var discount_sum = $("input[name='pd_dc_"+cart_code+"']").val();
		
		totalPrice += parseInt($("input[name='price_"+cart_code+"']").val())*amount;
		totalDiscount += parseInt(($("input[name='discount_"+cart_code+"']").val())*amount);
	});

	$("#totalPrice").html(numberFormat(totalPrice) + "");
	$("#totalDiscount").html(numberFormat(totalDiscount) + "");
};

/* 숫자 콤마 설정 */
function numberFormat(inputNumber) {
   return inputNumber.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

/* 상품-구매 버튼 클릭 */
var clickBuyBtn = function(pd_num, cart_code){

	var buy_amount = $("input[name='buy_amount_"+cart_code+"']").val();
	var url = "/order/buyFromCart?pd_num="+pd_num+"&od_amount="+buy_amount;
	location.href = url;
}