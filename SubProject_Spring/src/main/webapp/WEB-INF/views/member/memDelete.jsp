<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="resources/js/joinFunc.js"></script>
<script type="text/javascript">
$(function() {
	$("#submit").on("click", function() {
		if ($("#mem_id").val() == "") {
			alert("아이디를 입력해주세요.");
			$("#mem_id").focus();
			return false;
		} else if ($("#mem_password").val() == "") {
			alert("비밀번호를 입력해주세요.");
			$("#userPass").focus();
			return false;
		}
		

	});
});

</script>
</head>
<body>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div id="page-wrapper">
<jsp:include page="../inc/sidebar_mypage.jsp"></jsp:include>
  <!-- 본문 -->
  <div id="page-content-wrapper">
    <div class="container-fluid">
     <form action="MemberDeletePro.me" method="post">
		<!-- 		<input type="button" value="네" onclick="location.href='MemberDeletePro.me'"> -->
		<!-- 		<input type="button" value="아니오"> -->
		<div class="card text-center"
			style="width: 40rem; float: none; margin: 100px auto">

			<div class="card-body">
				<h5 class="card-title">정말로 탈퇴하시겠습니까?</h5>
				<p class="card-text">회원탈퇴를 위해 비밀번호를 입력해주세요.</p>
				<table class="table table-borderless table-light">
<!-- 					<tr> -->
<!-- 						<th scope="row" class="col-4">아이디</th> -->
<!-- 						<td><input type="text" name="mem_id" id="mem_id" required="required"></td> -->

<!-- 					</tr> -->
					<tr>
						<th scope="row">비밀번호</th>
						<td><input type="password" name="mem_password" id="mem_password" required="required"></td>

					</tr>

				</table>

				<input type="submit" class="btn btn-danger" value="탈퇴하기" id="submit">
					<input type="reset" class="btn btn-secondary" value="취소하기" onclick="javascript:history.back()">
			</div>
		</div>
	</form>
    </div>
  </div>
  <!-- /본문 -->
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>