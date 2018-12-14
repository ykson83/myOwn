<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<%@ include file="/WEB-INF/views/inc/meta.jsp"%>
	<title>Insert title here</title>
	</head>
	<body>
		<div class="login-box">
			<div class="login-logo">
				<a href="../../index2.html">KSIGN</a>
			</div>
			<!-- /.login-logo -->
			<div class="login-box-body">
				<p class="login-box-msg">Sign in to start your session</p>
	
				<form action="../../index2.html" method="post" id="loginForm">
					<div class="form-group has-feedback">
						<input type="text" class="form-control" placeholder="ID" name="userId" id="userId">
						<span class="glyphicon glyphicon-envelope form-control-feedback"></span>
					</div>
					<div class="form-group has-feedback">
						<input type="password" class="form-control" placeholder="Password" name="password" id="password">
						<span class="glyphicon glyphicon-lock form-control-feedback"></span>
					</div>
					<div class="row">
						<div class="col-xs-8">
							<div class="checkbox icheck">
								<label> <input name="remember-me" type="checkbox"> Remember Me	</label>
							</div>
						</div>
						<!-- /.col -->
						<div class="col-xs-4">
							<button type="button" class="btn btn-primary btn-block btn-flat" id="loginBtn">Sign In</button>
						</div>
						<!-- /.col -->
					</div>
				</form>
				
				<div class="social-auth-links text-center">
			      <p>- OR -</p>
			      <a href="https://accounts.google.com/o/oauth2/v2/auth?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fcalendar.readonly&access_type=offline&state=state_parameter_passthrough_value&redirect_uri=http%3A%2F%2Flocalhost%3A9080%2Fprod%2Foauth&response_type=code&client_id=225613869215-3c9ar7d1srjmjp9jk2eihoq6jvmdkgsv.apps.googleusercontent.com" 
			      class="btn btn-block btn-social btn-google btn-flat"><i class="fa fa-google-plus"></i> Sign in using Google+</a>
			     
			    </div>
	
	
<!-- 				<a href="#">I forgot my password</a><br> <a href="register.html" -->
<!-- 					class="text-center">Register a new membership</a> -->
	
			</div>
			<!-- /.login-box-body -->
	
	
		</div>
		<!-- /.login-box -->
		<%@ include file="/WEB-INF/views/inc/base_js.jsp"%>
		<script type="text/javascript">
	$(document).ready(function() {
		//password에서 엔터 || sign in 버튼 클릭하면 로그인
		//ajax로 컨트롤러 가
		$('#loginBtn').on('click', function() {
			if($('#userId').val() == '' || $('#userId').val() == null) {
				alert("ID를 입력하세요");
			} else if($('#password').val() == '' || $('#password').val() == null) {
				alert("password를 입력하세요");
			} else {
				login();
			}
		});
		
		$('#password').on("keyup", function(e) {
			if(e.keyCode == 13) {
				if($('#userId').val() == '' || $('#userId').val() == null) {
					alert("ID를 입력하세요");
				} else if($('#password').val() == '' || $('#password').val() == null) {
					alert("password를 입력하세요");
				} else {
					login();
				}
			}
		});
		
		function login() {
			$.ajax({
				url : "${ctxPath}/login.ajax",
				type : "post",
				data : $('#loginForm').serialize(),
				
				success : function(data) {
					var returnUrl = data.returnUrl;
					var result = data.result;
					if (result == "success") {
						top.location.href = returnUrl;
					} else {
						alert(data.message);
					}
				},error : function(jpXHR, textStatus, errorThrown) {
					alert(textStatus);
					alert(errorThrown);
				}
			});
		}
		
	});
	</script>
	</body>
</html>