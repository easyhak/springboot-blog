let index = {
	init: function(){
		/*
		$("#btn-save").on("click",()=>{
			this.save();
		});
		*/
		
		$("#btn-update").on("click",()=>{
			this.update();
		});
	},
	
	save : function(){
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			// dataType 지정 안해도 됨
		}).done(function(resp){
			console.log(resp)
			if(resp.status == 500){
				alert("회원가입에 실패했습니다.");
			}else{
				alert("회원가입에 성공했습니다.");
			}
			location.href = "";
		}).fail(function(error){
			alert(JSON.stringify(error))
		});
	},
	
	update : function(){
		let data = {
			id: $('#id').val(),
			username:$("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		}
		console.log(data)
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data), 
			contentType : "application/json; charset=utf-8", 
			dataType: "json" 
		}).done(function(resp){
			console.log(resp)
			if (resp.data == "1"){
				alert("회원수정이 완료되었습니다.");
			}
			else {
				alert("회원수정에 실패했습니다.");
			}
			location.href = "/";
		}).fail(function(error){
			alert("실패했습니다.");
			alert(JSON.stringify(error))
		});
	}
}

index.init();
$(function(){
	
})