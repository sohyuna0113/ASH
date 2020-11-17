$(document).ready(function() {

    var form = $("#loginForm");

    // 로그인 버튼 클릭시
    $("#btn_login").on("click", function(){

        var mb_id = $("#mb_id");
        var mb_password = $("#mb_password");

        if(mb_id.val()==null || mb_id.val()==""){
            alert("PLEASE ENTER YOUR ID");  
            mb_id.focus();
        } else if(mb_password.val()==null || mb_password.val()==""){
            alert("PLEASE ENTER YOUR PASSWORD");
            mb_password.focus();
        } else {
            form.submit();
        }
    });

});