<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form:form modelAttribute="joinCommand"  action="/auth/joinProc" class="join-form">
		<div class="form-group">
			<label for="username">Username:</label> 
			<input type="text" class="form-control" placeholder="Enter Username" id="username"  name="username" required>
			<div class="valid-feedback">Valid.</div>
      		<div class="invalid-feedback">Please fill out this field.</div>
		</div>
		<div class="form-group">
			<label for="password">Password:</label> 
			<input type="password" class="form-control" placeholder="Enter password" id="password"  name="password" required>
		</div>
		<div class="form-group">
			<label for="password-check">PasswordCheck:</label> <input type="password" class="form-control" placeholder="Enter password Again"
				id="password-check" required>
		</div>
		<div class="form-group">
			<label for="email">Email: </label> <input type="email" class="form-control" placeholder="Enter email" id="email" name="email"
				pattern="(\w\.?)+@[\w\.-]+\.\w{2,4}"  required title="이메일 형식을 입력하세요." />
		</div>
		<button type="submit" id="btn-save" class="btn btn-primary">회원가입 완료</button>
		<input type="hidden"" id="validation-check" value="valid" />
	</form:form>
</div>
<script>
// Disable form submissions if there are invalid fields
(function() {
  'use strict';
  window.addEventListener('load', function() {
    // Get the forms we want to add validation styles to
    var forms = document.getElementsByClassName('join-form');
    // Loop over them and prevent submission
    var validation = Array.prototype.filter.call(forms, function(form) {
      form.addEventListener('submit', function(event) {
        if (form.checkValidity() === false) {
          event.preventDefault();
          event.stopPropagation();
        }
        form.classList.add('was-validated');
      }, false);
    });
  }, false);
})();
</script>
<script src="/js//user.js" type="application/javascript"></script>
<%@ include file="../layout/footer.jsp"%>