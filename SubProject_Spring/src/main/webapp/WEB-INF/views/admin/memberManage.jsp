<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Bootstrap 5 Simple Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
    <link href="css/styles_ad_pr.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/chartist.js/latest/chartist.min.css">
    <style>
        .sidebar {
            position: fixed;
            top: 0;
            bottom: 0;
            left: 0;
            z-index: 100;
            padding: 90px 0 0;
            box-shadow: inset -1px 0 0 rgba(0, 0, 0, .1);
            z-index: 99;
        }

        @media (max-width: 767.98px) {
            .sidebar {
                top: 11.5rem;
                padding: 0;
            }
        }
            
        .navbar {
            box-shadow: inset 0 -1px 0 rgba(0, 0, 0, .1);
        }

        @media (min-width: 767.98px) {
            .navbar {
                top: 0;
                position: sticky;
                z-index: 999;
            }
        }

        .sidebar .nav-link {
            color: #333;
        }

        .sidebar .nav-link.active {
            color: #0d6efd;
        }
    </style>
</head>
<body>
    <jsp:include page="../inc/header.jsp"></jsp:include>
    <div class="container-fluid">
        <div class="row">
			<jsp:include page="../inc/sidebar_adminpage.jsp"></jsp:include>
			<main class="col-md-9 ml-sm-auto col-lg-10 px-md-4 py-4">
				<div class="container-fluid px-4">
					<h1 class="mt-4">회원관리</h1>
				</div>
				<c:set var="pageNum" value="${pageInfo.getPageNum() }" />
				<c:set var="maxPage" value="${pageInfo.getMaxPage() }" />
				<c:set var="startPage" value="${pageInfo.getStartPage() }" />
				<c:set var="endPage" value="${pageInfo.getEndPage() }" />
				<c:set var="listCount" value="${pageInfo.getListCount() }" />

				<!-- 게시판 리스트 -->
				<section id="listForm">
					<h2>회원 목록</h2>
					<table>
						<tr id="tr_top">
							<td width="100px">아이디</td>
							<td width="100px">회원이름</td>
							<td width="150px">생년월일</td>
							<td width="100px">성별</td>
							<td width="200px">이메일</td>
							<td width="200px">전화번호</td>
							<td width="200px">주소</td>
							<td width="200px">비고</td>
						</tr>
						<!-- 단, 게시물 목록이 하나라도 존재할 경우에만 출력 c:if 태그 사용 -->
						<c:if test="${pageInfo.getListCount() > 0 && not empty memberList }">
							<c:forEach var="member" items="${memberList }">
								<tr class="text-center">
									<td>${member.mem_id }</td>
									<td>${member.mem_name }</td>
									<td>${member.mem_birth }</td>
									<td>${member.mem_gender }</td>
									<td>${member.mem_email }</td>
									<td>${member.mem_phoneNum }</td>
									<td>${member.mem_address }</td>
									<td><button type="button" class="btn btn-outline-danger btn-sm">삭제하기</button></td>
								</tr>
							</c:forEach>
						</c:if>
					</table>
				</section>
				<section id="pageList">
					<c:choose>
						<c:when test="${pageNum > 1}">
							<input type="button" value="이전"
								onclick="location.href='ProductList.ad?page=${pageNum - 1}'">
						</c:when>
						<c:otherwise>
							<input type="button" value="이전">
						</c:otherwise>
					</c:choose>

					<!-- 페이지 번호 목록은 시작 페이지(startPage)부터 끝 페이지(endPage) 까지 표시 -->
					<c:forEach var="i" begin="${startPage }" end="${endPage }">
						<!-- 단, 현재 페이지 번호는 링크 없이 표시 -->
						<c:choose>
							<c:when test="${pageNum eq i}">
									${i }
								</c:when>
							<c:otherwise>
								<a href="BoardList.bo?page=${i }">${i }</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>

					<!-- 현재 페이지 번호(pageNum)가 총 페이지 수보다 작을 때만 [다음] 링크 동작 -->
					<c:choose>
						<c:when test="${pageNum < maxPage}">
							<input type="button" value="다음"
								onclick="location.href='ProductList.ad?page=${pageNum + 1}'">
						</c:when>
						<c:otherwise>
							<input type="button" value="다음">
						</c:otherwise>
					</c:choose>
				</section>
			</main>
		</div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/chartist.js/latest/chartist.min.js"></script>
    <!-- Github buttons -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>
    <script>
        new Chartist.Line('#traffic-chart', {
            labels: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat','Sun'],
            series: [
                [23000, 25000, 19000, 34000, 56000, 64000,80000]
            ]
            }, {
            low: 0,
            showArea: true
        });        
    </script>
</body>
</html>