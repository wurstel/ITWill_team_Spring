<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="./resources/js/jquery-3.6.0.js"></script>
<script type="text/javascript" src="./resources/js/scripts.js"></script>
<link href="./resources/css/styles.css" rel="stylesheet" type="text/css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="//code.jquery.com/jquery.min.js"></script>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
</head>
<body>
<jsp:include page="../inc/header.jsp"></jsp:include>
<div id="page-wrapper">
<jsp:include page="../inc/sidebar_mypage.jsp"></jsp:include>
  <!-- 본문 -->
<div id="page-content-wrapper">
    <div class="container">
      <h1 class="display-5">주문조회</h1>
    </div>
    <div class="container">
		<table class="table table-striped">
			<c:choose>
			<c:when test="${empty comment }">
				<thead>
					<tr class="text-center">
						<th scope="col">상품</th>
						<th scope="col">가격</th>
						<th scope="col">수량</th>
						<th scope="col">주문금액</th>
						<th scope="col">비고</th>
					</tr>
				</thead>
				<tbody>
				<!--  주문목록  -->
					<c:forEach items="${list }" var="oc">
					<input type="hidden" class="oc_pd_code" name="oc_pd_code" value="${oc.pd_code } ">
					<input type="hidden" class="oc_pd_name" name="oc_pd_name" value="${oc.pd_name } ">
					<input type="hidden" class="oc_pd_img" name="oc_pd_img" value="${oc.pd_img } ">
				    <tr class="text-center">
				      <td>${oc.pd_name }</td>
				      <td>
				     	 <fmt:formatNumber value="${oc.pd_price }" pattern="#,###원" />
				      </td>
				      <td>${oc.order_qty }</td>
				      <td>
				      	<fmt:formatNumber value="${oc.totalprice }" pattern="#,###원" />
				      </td>
				      <td>
				      		<c:choose>
				      				<c:when test="${oc.order_status eq '2'}">
				      						결제 완료
				      				</c:when>
				      		</c:choose>
				      </td>
				       <td>
<%-- 				       		<c:choose> --%>
<%-- 				       			<c:when test="${re_check eq '1' }"> --%>
<!-- 				       				<button type="button" class="btn btn-sm btn-dark reviewWrite" data-bs-toggle="modal" data-bs-target="#exampleModal" disabled="disabled">리뷰작성완료</button> -->
<%-- 				       			</c:when> --%>
<%-- 				       			<c:otherwise> --%>
				       				<button type="button" class="btn btn-sm btn-dark reviewWrite" data-bs-toggle="modal" data-bs-target="#exampleModal">리뷰쓰기</button>
<%-- 				       			</c:otherwise> --%>
<%-- 				       		</c:choose> --%>
				      </td>
				    </tr>
					</c:forEach>
				<!-- /주문목록  -->    
				</tbody>
			</c:when>
			<c:otherwise>
				<thead>
					<tr>
						<th scope="col">상품</th>
						<th scope="col">가격</th>
						<th scope="col">수량</th>
						<th scope="col">주문금액</th>
						<th scope="col">비고</th>
					</tr>
				</thead>
				<tbody>
					<tr><td colspan="5" class="text-center">${comment }</td></tr>
				</tbody>
			</c:otherwise>
			</c:choose>
		</table>   	
    </div>
</div>
  <!-- /본문 -->
</div>

<form action="review.me" name="myform" id="myform" method="get">
	<input type="hidden" name="re_mem_id" value="${sessionScope.sId }">
	<input  type="hidden" name="re_pd_code" id="re_pd_code" value="">
	    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	      <div class="modal-dialog modal-80size modal-center">
	          <div class="modal-content">
	             <div class="modal-header">
	                   <h5 class="modal-title" id="exampleModalLabel">리뷰 작성</h5>
	                   <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close" onclick="cartChoice('close')"></button>
	               </div>
	         <div class="modal-body cen">
	               <div class="mb-3">
	                  <img id="imageR" >
	               </div>
	               <div class="mb-3">
	                  <div>
	                     <input  type="text" name="re_pd_name" id="re_pd_name">  
	                  </div>
	                  <fieldset>
	                     <input type="radio" name="re_score" value="5" id="rate1">
	                     <label for="rate1">★</label> 
	                     <input type="radio" name="re_score" value="4" id="rate2">
	                     <label for="rate2">★</label> 
	                     <input type="radio" name="re_score" value="3" id="rate3">
	                     <label for="rate3">★</label> 
	                     <input type="radio" name="re_score" value="2" id="rate4">
	                     <label for="rate4">★</label> 
	                     <input    type="radio" name="re_score" value="1" id="rate5">
	                     <label for="rate5">★</label>
	                  </fieldset>
	               </div>
	               <div class="mb-3">
	                     <input type="text" name="re_title" class="form-control" id="recipient-name" placeholder="제목을 입력해주세요. :)">
	                   </div>
	            <div class="mb-3">
	<!--                            <label for="message-text" class="col-form-label">Message:</label> -->
	               <textarea class="form-control" name="re_comment" id="message-text" placeholder="리뷰를 적어주세요. :)" style="height: 200px"></textarea>
	            </div>
	         </div>
	         <div class="modal-footer">
	                     <input type="button" value="나중에 하기" class="btn btn-outline-dark" data-bs-dismiss="modal">
	                 <input type="submit" value="등록" class="btn btn-outline-dark">
	               </div>
	            </div>
	        </div>
	   </div>
	</form>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>