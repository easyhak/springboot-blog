let index = {
	init: function(){
		$("#btn-save").on("click",()=>{
			this.save();
		});
	},
	
	save : function(){
		// alert("user의 save함수 호출")
		let data = {
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		}
		// console.log(data);
		// ajax호출시 default 비동기 호출
		// ajax통신을 이용해서 3개의 데이터를 json으로 변경하여 insert요청을 한다.
		$.ajax({
			//회원가입수행요청		
			type: "POST",
			url: "auth/joinProc",
			data: JSON.stringify(data), // json 문자열 변경 // http body데이터
			contentType : "application/json; charset=utf-8", // body데이터가 어떤 타입인지 (mime)
			dataType: "json" // 요청을 서버로해서 응답이 왔을 때 기본적으로 모든 것이 문자열 (생긴게 json이라면) => javascript 오브젝트로 변경해줌 
			// dataType 지정 안해도 됨
		}).done(function(resp){
			alert(resp);
			location.href = "/";
		}).fail(function(error){
			alert(JSON.stringify(error))
		});
	}
}

index.init();