$(document).ready(function(){
	
	var form = $("#changePwForm");
	
	var mb_password = $("#mb_password");
	var mb_password_change = $("#mb_password_change");
	var mb_password_check = $("#mb_password_check");
	
	/* 확인 버튼 클릭 시 */
	$("#btn_submit").on("click", function(){
		// 유효성 검사
		if(mb_password.val()==null || mb_password.val()==""){
			alert("현재 비밀번호를 입력해주세요.");
			mb_password.focus();
			return;
			
		} else if(mb_password_change.val() ==null || mb_password_change.val() ==""){
			alert("변경할 비밀번호를 입력해주세요.");
			mb_password_change.focus();
			return;
			
		} else if(mb_password_check.val() ==null || mb_password_check.val() ==""){
			alert("변경할 비밀번호 확인 란을 입력해주세요.");
			mb_password_check.focus();
			return;
			
		} else if(mb_password_change.val() != mb_password_check.val()){
			alert("변경할 비밀번호와 비밀번호 확인 란의 비밀번호가 다릅니다.");
			
			mb_password_change.val("");
			mb_password_check.val("");
			mb_password_change.focus();
			return;
		}
		
		// 현재 비밀번호 확인 검사
		var mb_password_val = mb_password.val();
		
		$.ajax({
			url: "/member/checkPwAjax",
			type: "post",
			datatype: "text",
			data: {mb_password : mb_password_val},
			success: function(data){
				if(data=='SUCCESS'){
					form.submit();
					
				} else{
					alert("현재 비밀번호가 다릅니다.");
					mb_password.val("");
					mb_password.focus();
				}
			} 
		});
	});
});