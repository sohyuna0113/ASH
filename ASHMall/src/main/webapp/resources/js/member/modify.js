$(document).ready(function() {
	
	var form = $("#modifyForm");
	// 인증 확인 여부를 위한 변수
	var isCheckEmail = "true";
	
	/* 이메일 변경 시 이메일 인증 활성화 */
	$("#mb_email").on("change", function(){
		$("#btn_sendAuthCode").show();
		isCheckEmail = "false";
	});
	
	/* 이메일 인증 클릭 시 */
	$("#btn_sendAuthCode").on("click", function(){
		var recieverEmail = $("#mb_email").val();
		
		if($("#mb_email").val()=="" || $("#mb_email").val()== null){
			$("#authcode_status").html("ENTER YOUR EMAIL");
			return;
		}
		
		$("#authcode_status").css("color", "grey");
		$("#authcode_status").html('PLEASE WAIT');
		
		$.ajax({
			url: '/email/send',
			type: 'post',
			dataType: 'text',
			data: {receiverMail : receiverMail},
			success: function(data){
				$("#mb_authcode").show();
				$("#authcode_status").css("color", "grey");
				$("#authcode_status").html('EMAIL SENT');
			}
		});
	});
	
	/* 인증코드 입력 후 확인 클릭 시 */
	$("#btn_mb_authcode").on("click", function(){
		var code = $("#mb_authcode").val();
		
		$.ajax({
			url: '/member/checkAuthcode',
			type: 'post',
			dataType: 'text',
			data: {code : code},
			success: function(data){
				if(data == 'SUCCESS'){
					$("#mb_authcode").hide();
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
	
	
	/* 회원수정 버튼 클릭 시 */ 
	$("#btnJoin").on("click", function(){
		
        var mb_id = $("#mb_id");
        var mb_name = $("#mb_name");
		var mb_email = $("#mb_email");
		var mb_authcode = $("#mb_authcode");
		var mb_zipcode = $("input[name='mb_zipcode']");
		var mb_addr = $("input[name='mb_addr']");
		var mb_addr_d = $("input[name='mb_addr_d']");
		var mb_phone = $("#mb_phone");
		var mb_nickname = $("#mb_nickname");
		
		/* 유효성 검사 */
		 if(mb_name.val()==null || mb_name.val()==""){
            alert("ENTER YOUR NAME");
            mb_name.focus();
			return;
		
		} else if(mb_nickname.val==null || mb_nickname.val()==""){
            alert("ENTER YOUR USER NAME");
            mb_nickname.focus();
			return;
		
		} else if(mb_email.val()==null || mb_email.val()==""){
			alert("ENTER YOUR EMAIL");
			mb_email.focus();
			return;

		} else if(isCheckEmail=="false" &&
					(mb_authcode.val()==null || mb_authcode.val()=="")){
			alert("ENTER 6-DIGIT CODE FROM YOUR EMAIL");
			mb_authcode.focus();
			return;

		} else if(isCheckEmail=="false"){
			alert("ENTER 6-DIGIT CODE FROM YOUR EMAIL");
			$("#btn_sendAuthCode").focus();
			return;
		
		} else if(mb_phone.val()==null || mb_phone.val()==""){
			alert("ENTER YOUR PHONE NUMBER");
			mb_phone.focus();
			return;

		} else if(mb_zipcode.val()==null || mb_zipcode.val()==""){
			alert("ENTER YOUR ZIPCODE");
			$("#btn_postCode").focus();
			return;
			
		} else if(mb_addr.val()==null || mb_addr.val()==""){
			alert("ENTER YOUR ADDRESS");
			$("#btn_postCode").focus();
			return;
			
		} else if(mb_addr_d.val()==null || mb_addr_d.val()==""){
			alert("ENTER YOUR ADDRESS DETAIL");
			mb_addr_d.focus();
			return;
		} 
		 
	});
	
	/* 취소 버튼 클릭 시 */
	$("#btnCancel").on("click", function(){
		
		var result = confirm("회원 정보 수정을 취소하시겠습니까?");
		if(result){
			location.href="/"; 
		} else{}
	});
	
});
