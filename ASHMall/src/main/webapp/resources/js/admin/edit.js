$(document).ready(function() {
	
	var form = $("#editForm");
	
	/* 상품 수정 버튼 클릭 시 */
	$("#btn_submit").on("click", function(){
		var result = confirm("수정된 정보를 저장하시겠습니까?");
		
		if(result){
			var mainCategory = $("#mainCategory option:selected");
			var subCategory = $("#subCategory option:selected");
			var pd_name = $("#pd_name");
			var pd_company = $("#pd_company");
			var pd_price = $("#pd_price");
			var pd_dc = $("#pd_dc");
			var ckeditor = CKEDITOR.instances['pd_detail'];
			var pd_detail = $("#pd_detail");
			var pd_amount = $("#pd_amount");
			var pd_buy = $("#pd_buy");
			
			
			if(mainCategory.val()==null || mainCategory.val()=="default"){
				alert("1차 카테고리를 선택해주세요.");
				mainCategory.focus();
				return;
				
			} else if(subCategory.val()==null || subCategory.val()=="default"){
				alert("2차 카테고리를 선택해주세요.");
				subCategory.focus();
				return;
				
			} else if(pd_name.val()==null || pd_name.val()==""){
				alert("상품명을 입력해주세요.");
				pd_name.focus();
				return;
				
			} else if(pd_company.val()==null || pd_company.val()==""){
				alert("제조사를 입력해주세요.");
				pd_company.focus();
				return;
				
			}else if(pd_price.val()==null || pd_price.val()==""){
				alert("상품 가격을 입력해주세요.");
				pd_price.focus();
				return;
				
			}else if(pd_dc.val()==null || pd_dc.val()==""){
				alert("할인 가격을 입력해주세요.");
				pd_dc.focus();
				return;
				
			}else if(ckeditor.getData()==null || ckeditor.getData()==""){
				alert("상품 상세 내용을 입력해주세요.");
				ckeditor.focus();	
				return;
				
			}else if(pd_amount.val()==null || pd_amount.val()==""){
				alert("상품 수량을 입력해주세요.");
				pd_amount.focus();
				return;
				
			}else {
				form.submit();
			}
		} else {}
		
	});
	
});

