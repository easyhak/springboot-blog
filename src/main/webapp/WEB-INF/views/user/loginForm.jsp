<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>
<div class="container">
	<form action="/auth/loginProc" method="post">
		<div class="form-group">
			<label for="username">Username:</label> <input type="text" name="username" class="form-control" placeholder="Enter Username" id="username">
		</div>
		<div class="form-group">
			<label for="password">Password:</label> <input type="password" name="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		<button id="btn-login" class="btn btn-primary">로그인 완료</button>
		<a
			href="https://kauth.kakao.com/oauth/authorize?client_id=a9b6d7b0798909ecdd9d8164e48915ec&redirect_uri=http://localhost:8000/auth/kakao/callback&response_type=code">
			<img height="38px" src="/image/kakao_login_btn.png">
		</a>
		<div class="form-group form-check">
			<label class="form-check-label"> 
				<input class="form-check-input" type="checkbox"> Remember me
			</label>
		</div>
	</form>

</div>
<%@ include file="../layout/footer.jsp"%>



