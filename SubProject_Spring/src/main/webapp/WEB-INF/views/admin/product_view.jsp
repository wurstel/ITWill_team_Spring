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
					<h1 class="mt-4">상품관리</h1>
				</div>
				<div>
					<h2>상품 정보/삭제</h2>
					<form id="form1" name="form1" enctype="multipart/form-data" method="post">
						<table class="table">
							<tr>
								<td>상품 이미지</td>
								<td><img src="${path}/images/${vo.productUrl}"
									height="300px" width="310px"> <br> <input
									type="file" id="productPhoto" name="productPhoto"></td>
							</tr>
							<tr>
								<td>상품명</td>
								<td><input type="text" id="productName" name="productName"
									value="${vo.productName}"></td>
							</tr>
							<tr>
								<td>가격</td>
								<td><input type="number" id="productPrice"
									name="productPrice" value="${vo.productPrice}"></td>
							</tr>
							<tr>
								<td>상품소개</td>
								<td><textarea id="productDesc" name="productDesc" rows="5"
										cols="60">${vo.productDesc}</textarea></td>
							</tr>
							<tr>
								<td colspan="2" align="center"><input type="hidden"
									name="productId" value="${vo.productId}"> <input
									type="button" id="editBtn" value="수정"> <input
									type="button" id="deleteBtn" value="삭제"> <input
									type="button" id="listBtn" value="상품목록"></td>
							</tr>
						</table>
					</form>
				</div>
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