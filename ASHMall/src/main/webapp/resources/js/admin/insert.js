$(document).ready(function() {

    var form = $("#registerForm");

    /* 상품 등록 버튼 클릭시 */ 
    $("#btn_submit").on("click", function() {
        var result = confirm("COMPLETE THE SUBMISSION?");

        if(result) {
            var mainCategory = $("#mainCategory option:selected");
            var subCategory = $("#subCategory option:selected");
            var pd_name = $("#pd_name");
            var pd_company = $("#pd_company");
            var pd_price = $("#pd_price");
            var pd_dc = $("#pd_dc");
            var ckeditor = CKEDITOR.instances['pd_detail'];
            var file1 = $("#file1");
            var pd_amount = $("#pd_amount");
            var pd_buy = $("#pd_buy");
            var fileSize = file1.size;
            var fileFormat = /(.*?)\.(jpg|jpeg|png|gif|bmp|pdf)$/;
            var maxSize = 5 * 1024 * 1024; // 5MB;

            if(mainCategory.val()==null || mainCategory.val()=="default"){
                alert("FIRST CATEGORY IS EMPTY");
                mainCategory.focus();
                return;
            } else if(subCategory.val()==null || mainCategory.val()=="default"){
                alert("SECOND CATEGORY IS EMPTY");
                subCategory.focus();
                return;
            } else if(pd_name.val()==null || pd_name.val()==""){
                alert("ENTER PRODUCT NAME");
                pd_name.focus();
                return;
            } else if(pd_company.val()==null || pd_company.val()==""){
                alert("ENTER BRAND HOUSE NAME");
                pd_company.focus();
                return;
            } else if(pd_price.val()==null || pd_price.val()==""){
                alert("ENTER PRICE");
                pd_price.focus();
                return;
            } else if(pd_dc.val()==null || pd_dc.val()==""){
                alert("ENTER DISCOUNT");
                pd_dc.focus();
            } else if(ckeditor.getData()==null || ckeditor.getData()==""){
                alert("ENTER PRODUCT DETAIL");
                ckeditor.focus();
            } else if(file1.val()==null || file1.val()==""){
                alert("ADD IMAGE FILE");
                file1.focus();
            } else if(!file1.val().match(fileFormat)){
                alert("ONLY IMAGE FILE CAN BE UPLOADED");
                file1.focus();
                return;
            } else if(fileSize > maxSize){
                alert("FILE SIZE LIMITED TO 5MB");
                file1.focus();
                return;
            } else if(pd_amount.val()==null || pd_amount.val()==""){
                alert("ENTER AMOUNT");
                pd_amount.focus();
                return;
            } else {
                form.submit();
            }
        } else {}

    });

});