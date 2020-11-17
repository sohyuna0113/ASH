$(document).ready(function() { 

	var form = $("#frmJoin");

/* 아이디 중복체크 사용여부확인 변수 */

	var isCheckId = "false";

/* 인증 확인 여부를 위한 변수 */

	var isCheckEmail = "false";

/* 아이디 중복체크 클릭시 (btn_checkId)*/

	$("#btn_checkId").on("click", function(){

		if($("#mb_id").val()=="" || $("#mb_id").val()== null){
			$("#id_availability").html("ENTER YOUR ID");
			return;
		}
		
		var mb_id = $("#mb_id").val();

		/*
		ajax방식 : 클라이언트 시작 -> 서버요청 -> 클라이언트 종료
			※ ajax방식은 서버요청에 실행되는 코드가 response.redirect,redirect
			주소이동 구문이 사용안하거나 동작되어서는 안된다. 
		*/
		$.ajax({
			url: "/member/checkIdDuplicate",
			type: "post",
			dataType: "text",		// /member/checkIdDuplicate 주소의 리턴값 형식
			data: {mb_id : mb_id},	// JS object표현구문 {key : value}
			success: function(data){
				if(data == 'SUCCESS'){
					$("#id_availability").css("color", "black");
					$("#id_availability").html("USER ID IS AVAILABLE.");

					/* 아이디 중복체크후 텍스트박스 -> 읽기모드/중복체크 비활성화 */
					$("#mb_id").attr("readonly", true);
					$("#btn_checkId").attr("disabled", true);
				
					isCheckId = "true";
				} else {
					$("#id_availability").html("USER ID IS ALREADY EXISTED.");
				}
			}
		});
	});

/* 이메일 인증 클릭시 */

	$("#btn_sendAuthCode").on("click", function(){
		var recieverEmail = $("#mb_email").val();

			if($("#mb_email").val()=="" || $("#mb_email").val()==null){
				$("#authcode_status").html("ENTER YOUR EMAIL");
				return;
			}

			$("#authcode_status").css("color", "grey");
			$("#authcode_status").html("PLEASE WAIT");

			$.ajax({
				url: '/email/send',
				type: 'post',
				dataType: 'text',
				data: {recieverEmail : recieverEmail},
				success: function(data){
					$("#mb_authcode").show(); 
					$("#authcode_status").css("color", "grey");
					$("#authcode_status").html("EMAIL SENT");
				}
			});
	});

/* 인증코드 입력후 확인 클릭시 */

	$("#btn_mb_authcode").on("click", function(){
		var code = $("#mb_authcode").val();

		$.ajax({
			url: '/member/checkAuthcode',
			type: 'post',
			dataType: 'text',
			data: {code : code},
			success: function(data){
				if(data == 'SUCCESS'){
					$("#mb_authcode").hide();	// 인증코드 화면숨김
					$("#authcode_status").css("color", "grey");
					$("#authcode_status").html('AUTHORIZED');
					$("#mb_email").attr("readonly",true);
					$("#btn_mb_authcode").attr("disabled", true);
					isCheckEmail = "true";
					return;
				} else {
					$("#mb_authcode").hide();
					$("#authcode_status").css("color", "grey");
					$("#authcode_status").html('NOT AUTHORIZED');
					return;
				}
			}

		});
	});

/* 회원가입 버튼 클릭시 (btnJoin) */

	$("#btnJoin").on("click", function(){

			var mb_id = $("#mb_id");
			var mb_name = $("#mb_name");
			var mb_password = $("#mb_password");
			var mb_password2 = $("#mb_password2");
			var mb_email = $("#mb_email");
			var mb_authcode = $("#mb_authcode");
			var mb_zipcode = $("input[name='mb_zipcode']");
			var mb_addr = $("input[name='mb_addr']");
			var mb_addr_d = $("input[name='mb_addr_d']");
			var mb_phone = $("#mb_phone");
			var mb_nickname = $("#mb_nickname");

			/* 유효성 검사 */

			if(mb_id.val()==null || mb_id.val()==""){
				alert("ENTER YOUR ID");
				mb_id.focus();
			} else if(mb_name.val()==null || mb_name.val()==""){
				alert("ENTER YOUR NAME");
				mb_name.focus();
			} else if(mb_password.val()==null || mb_password.val()==""){
				alert("ENTER YOUR PASSWORD");
				mb_password.focus();
			} else if(mb_password2.val()==null || mb_password2.val()==""){
				alert("RE-ENTER YOUR PASSWORD");	
				mb_password2.focus();
			} else if(mb_email.val()==null || mb_email.val()==""){
				alert("ENTER YOUR EMAIL");
				mb_email.focus();
			} else if(mb_authcode.val()==null || mb_authcode.val()==""){
				alert("ENTER 6-DIGIT CODE FROM YOUR EMAIL");
				mb_authcode.focus();
			} else if(mb_zipcode.val()==null || mb_zipcode.val()==""){
				alert("ENTER YOUR ZIPCODE");
				mb_zipcode.focus();
			} else if(mb_addr.val()==null || mb_addr.val()==""){
				alert("ENTER YOUR ADDRESS");
				mb_addr.focus();
			} else if(mb_addr_d.val()==null || mb_addr_d.val()==""){
				alert("ENTER YOUR ADDRESS DETAIL");
				mb_addr_d.focus();
			} else if(mb_phone.val()==null || mb_phone.val()==""){
				alert("ENTER YOUR PHONE NUMBER");
				mb_phone.focus();
			} else if(mb_nickname.val==null || mb_nickname.val()==""){
				alert("ENTER YOUR USER NAME");
				mb_nickname.focus();
			} else {
				form.submit(); 
			}
	});
	
	$("#btnCancel").on("click", function(){
			 
			var result = confirm("RETURN HOME?");
				if(result){
					location.href="/"; 
				} else{}
	});

});