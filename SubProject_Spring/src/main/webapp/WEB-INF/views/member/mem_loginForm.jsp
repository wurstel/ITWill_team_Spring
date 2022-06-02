<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="./css/login.css" rel="stylesheet" type="text/css">
<script src="./js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="./js/login.js"></script>
</head>
<body>
	<section class="login-form">
	<h1>LOGIN</h1>
	<form action="login_pro.me" method="post">
		<div class="int-area">
			<input type="text" name="mem_id" id="id" autocomplete="off" required="required">
			<label for="id">USER NAME</label>
		</div>
		<div class="int-area">
			<input type="password" name="mem_password" id="passwd" autocomplete="off" required="required">
			<label for="passwd">PASSWORD</label>
		</div>
		<div class="btn-area">
			<button type="submit" id="btn">LOGIN</button>
		</div>
	</form>
	<div class="caption">
			<a href="">Sign Up?</a>&nbsp;&nbsp;&nbsp;
			<a href="">Forgot Password?</a>
	</div>
<!-- 	<div class="caption"> -->
<!-- 			<a href="">회원</a> -->
<!-- 	</div> -->
	</section>
</body>
</html>
<!-- 		<table> -->
<!-- 			<tr> -->
<!-- 				<td>아이디</td> -->
<!-- 				<td><input type="text" name="id" required="required" size="20"></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>패스워드</td> -->
<!-- 				<td><input type="text" name="passwd" required="required" size="20"></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td colspan="2" align="center"> -->
<!-- 					<input type="submit" value="로그인"> -->
<!-- 					<input type="button" value="회원가입" onclick="location.href='MemberJoinForm.me'"> -->
<!-- 				</td> -->
<!-- 			</tr> -->
<!-- 		</table> -->