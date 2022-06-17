<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function logout() {
	var logoutData = confirm("로그아웃 하시겠습니까?");
	if(logoutData) {
		location.href="logout.me";
	}
}
</script>
</head>
<body id="headerBody">
	<nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom navbar-fixed-top">
    <a class="navbar-brand" href="./" style="margin-left: 10px">subscribeProject</a>
    <button class="navbar-toggler ml-auto" type="button" data-toggle="collapse"
            data-target="#navbarNav">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-between" id="navbarNav">
        <ul class="navbar-nav">
            <li class="nav-item ">                                                                
                <a class="nav-link" href="./subscribePage.sub">구독서비스</a>                
            </li>
            <li class="nav-item ">                                                                
                <a class="nav-link" href="./storeMain.st">스토어</a>                
            </li>
            <li class="nav-item ">                                                                
                <a class="nav-link" href="customerCenter_list.cu">고객센터</a>                
            </li>
      	</ul>
      	<ul class="navbar-nav">
      		<c:choose>
            	<c:when test="${empty userId }">
			            <li class="nav-item ">                                                                
			                <a class="nav-link" href="login_form.me">로그인</a>                
			            </li>
			            <li class="nav-item ">                                                                
			                <a class="nav-link" href="join_before.me">회원가입</a>                
			            </li>
            	</c:when>
           		<c:when test="${userId eq 'almeal'}">
            		<li class="nav-item ">                                                                
			                <a class="nav-link">관리자</a>                
			            </li>
	            		<li class="nav-item ">                                                                
			                <a class="nav-link" href="adminpage.me">관리자페이지</a>                
			            </li>
			            <li class="nav-item ">                                                                
			                <a class="nav-link" onclick="logout()">로그아웃</a>                
			            </li>
            	</c:when>
            	<c:otherwise>
            		<c:choose>
            			<c:when test="${access_token ne null }">
            				<li class="nav-item ">                                                                
			                <a class="nav-link">${userId}님</a>                
				            </li>
		            		<li class="nav-item ">                                                                
				                <a class="nav-link" href="mypage.me">마이페이지</a>                
				            </li>
		            		<li class="nav-item ">                                                                
				                <a class="nav-link" href="https://kauth.kakao.com/oauth/logout?client_id=25b9b94777a0d2b56646129bea603613&logout_redirect_uri=http://localhost:8080/subProject/auth/kakao/logout">로그아웃</a>                
				            </li>
            			</c:when>
            			<c:when test="${access_token eq null }">
            				<li class="nav-item ">                                                                
			                <a class="nav-link">${userId}님</a>                
				            </li>
		            		<li class="nav-item ">                                                                
				                <a class="nav-link" href="mypage.me">마이페이지</a>                
				            </li>
		            		<li class="nav-item ">                                                                
				                <a class="nav-link" href="logout.me">로그아웃</a>                
				            </li>
            			</c:when>
            		</c:choose>
            	</c:otherwise>
            </c:choose>
      	</ul>
    </div>
</nav>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>