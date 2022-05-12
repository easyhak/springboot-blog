let index = {
	init: function() {
		/*
		$("#btn-save").on("click",()=>{
			this.save();
		});
		*/
		$('#btn-login').on("click", () => {
			this.login();
		});
		$("#btn-update").on("click", () => {
			this.update();
		});
	},

	login: function() {
		const rememberId = document.getElementById('rememberId');
		const username = document.getElementById('username').value;
		if (rememberId.checked) {
			setCookie("username", username, 1)
		}
		else {
			setCookie("username",username,0);
		}
		console.log(rememberId.checked);
		console.log(username);
		
		setCookie("rememberId", rememberId.checked, 1);
		alert(document.cookie);
	},

	save: function() {
		$.ajax({
			type: "POST",
			url: "/auth/joinProc",
			// dataType 지정 안해도 됨
		}).done(function(resp) {
			console.log(resp)
			if (resp.status == 500) {
				alert("회원가입에 실패했습니다.");
			} else {
				alert("회원가입에 성공했습니다.");
			}
			location.href = "";
		}).fail(function(error) {
			alert(JSON.stringify(error))
		});
	},

	update: function() {
		let data = {
			id: $('#id').val(),
			username: $("#username").val(),
			password: $("#password").val(),
			email: $("#email").val(),
		}
		console.log(data)
		$.ajax({
			type: "PUT",
			url: "/user",
			data: JSON.stringify(data),
			contentType: "application/json; charset=utf-8",
			dataType: "json"
		}).done(function(resp) {
			console.log(resp)
			if (resp.data == "1") {
				alert("회원수정이 완료되었습니다.");
			}
			else {
				alert("회원수정에 실패했습니다.");
			}
			location.href = "/";
		}).fail(function(error) {
			alert("실패했습니다.");
			alert(JSON.stringify(error))
		});
	}
}
var setCookie = function(name, value, exp) {
	var date = new Date();
	date.setTime(date.getTime() + exp * 24 * 60 * 60 * 1000);
	document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
};
var getCookie = function(name) {
        var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
        return value? value[2] : null;
};
index.init();
$(function(){
	if(getCookie("rememberId") === "true"){
		$('#rememberId').prop('checked',true);
	}
	
})