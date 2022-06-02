<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="js/basketFunc.js"></script>
<script type="text/javascript">
$(function() {
	$(".qtyup").click(function() {
		var n = $('.qtyup').index(this);
	    var num = $(".qty:eq("+n+")").val();
	    num = $(".qty:eq("+n+")").val(num*1+1); 
	});
	
	$(".qtydown").click(function() {
		var n = $('.qtydown').index(this);
	    var num = $(".qty:eq("+n+")").val();
	    num = $(".qty:eq("+n+")").val(num*1-1);
	});
})
$(function() {
	$(".qtyupdate").click(function() {
		var n = $('.qtyupdate').index(this);
	    var num = $(".qty:eq("+n+")").val();
	    var order_num = $(".order_num:eq("+n+")").val();
		var mem_id = $(".bk_mem_id").val();
		var pd_code = $(".bk_pd_code:eq("+n+")").val();
	 	var isupdate = confirm("수량을 변경하시겠습니까?");
// 		alert(pd_code);
	 	if(isupdate) {
	 		location.href="basketUpdate.me?bk_qty=" + num + "&bk_mem_id=" + mem_id + "&bk_order_num=" + order_num + "&bk_pd_code=" + pd_code;
	 	}else {
			
	 	}
	});
})
</script>
</head>
<body>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div id="page-wrapper">
<jsp:include page="../inc/sidebar_mypage.jsp"></jsp:include>  
  <!-- 본문 -->
<div id="page-content-wrapper">
    <div class="container">
      <h1 class="display-5">장바구니</h1>
    </div>
    <div class="container">
		<table class="table table-striped" id="fr">  
			<thead>
				<tr class="text-center">
					<th scope="col">상품</th>
					<th scope="col">가격</th>
					<th scope="col">수량</th>
					<th scope="col">주문금액</th>
					<th scope="col">비고</th>
				</tr>
			</thead>
			<c:choose>
				<c:when test="${empty basketlist }">
					<tr><td colspan="5" class="text-center">장바구니가 비어있습니다</td></tr>
				</c:when>
				<c:otherwise>
					<tbody>
					<!-- 장바구니 목록  -->
						<c:forEach items="${basketlist }" var="basketlist">
					    <tr class="text-center">
					      <td>${basketlist.pd_name }</td>
					      <td>${basketlist.pd_price }</td>
					      <td>
						      <input type="text" name="qty" style="width: 30px;" class="qty text-center" value="${basketlist.bk_qty }" readonly>
						      <button type="button" class="qtyup btn btn-text-center btn-outline-dark" >▲</button>
						      <button type="button" class="qtydown btn btn-outline-dark text-center" >▼</button>
					      </td>
					      <td>${basketlist.totalprice }</td>
					      <td>
					      	<input type="hidden" class="bk_mem_id" value="${basketlist.bk_mem_id} ">
					      	<input type="hidden" class="bk_pd_code" value="${basketlist.bk_pd_code} ">
					      	<input type="hidden" class="order_num" value="${basketlist.bk_order_num} ">
					      	<button type="button" class="qtyupdate btn btn-success btn-sm" onclick="updateQty()">수량변경</button>
					      	<button type="button" class="btn btn-danger btn-sm">삭제하기</button></td>
					    </tr>
						</c:forEach>
					<!-- /장바구니 목록  -->    
					    <tr>
					      <td scope="row"></td>
					      <td></td>
					      <td></td>
					      <td>총 결제금액</td>
					      <td><!-- 코드로 합계를 나타내서 표현 --></td>
					    </tr>
					</tbody>
				</c:otherwise>
			</c:choose>
		</table>   	
    </div>
    <div class="container text-center">
    	<button type="button" class="btn btn-outline-success btn-lg col-6" onclick="location.href='payment.pm'">주문하기</button>
    </div>
</div>
  <!-- /본문 -->
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>