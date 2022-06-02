<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<link
href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
rel="stylesheet">
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<style>
html, body {
	height: 100%;
}
</style>
<script src="js/paymentAPI.js"></script>
<script type="text/javascript" src="https://code.jquery.com/jquery-1.12.4.min.js" ></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
<script type="text/javascript">
	IMP.init("imp05633369"); // https://admin.iamport.kr/settings#tab_profile 가맹점 식별코드
	
	function kakaoPay() {
	  
    // IMP.request_pay(param, callback) 결제창 호출
    IMP.request_pay({ // param
        pg: 'kakaopay', // 결제할 pg사 간편결제 카카오페이 테스트모드 ON 가맹점코드 일반결제용
        pay_method: 'card',
        merchant_uid: 'merchant_'+new Date().getTime(), // 주문번호
        name: '파댕이', // 상품명
        amount: 100, // 가격
        buyer_email: 'lhj6346@gmail.com',
        buyer_name: '이현진',
        buyer_tel: '010-5771-6061',
        buyer_addr: '부산시 강서구',
        buyer_postcode: '46723'
    }, function (rsp) { // callback
    	console.log(rsp);
        if (rsp.success) {
        	var msg = '결제가 완료되었습니다.';
	        msg += '고유ID : ' + rsp.imp_uid;
	        msg += '상점 거래ID : ' + rsp.merchant_uid;
	        msg += '결제 금액 : ' + rsp.paid_amount;
	        msg += '카드 승인번호 : ' + rsp.apply_num;
	        document.location="payment_result.jsp";
            // 결제 성공 시 로직,
            
        } else {
        	 var msg = '결제에 실패하였습니다.';
	         msg += '에러내용 : ' + rsp.error_msg;
            // 결제 실패 시 로직,
        }
        alert(msg);
    });
  }
	function tossPay() {
		  
	    // IMP.request_pay(param, callback) 결제창 호출
	    IMP.request_pay({ // param
	        pg: 'tosspay', // 결제할 pg사 간편결제 토스
	        // PG 상점 아이디 tosstest
	        // apikey sk_test_w5lNQylNqa5lNQe013Nq
	        pay_method: 'card',
	        merchant_uid: 'merchant_'+new Date().getTime(), // 주문번호
	        name: '파댕이', // 상품명
	        amount: 100, // 가격
	        buyer_email: 'lhj6346@gmail.com',
	        buyer_name: '이현진',
	        buyer_tel: '010-5771-6061',
	        buyer_addr: '부산시 강서구',
	        buyer_postcode: '46723'
	    }, function (rsp) { // callback
	    	console.log(rsp);
	        if (rsp.success) {
	        	var msg = '결제가 완료되었습니다.';
		        msg += '고유ID : ' + rsp.imp_uid;
		        msg += '상점 거래ID : ' + rsp.merchant_uid;
		        msg += '결제 금액 : ' + rsp.paid_amount;
		        msg += '카드 승인번호 : ' + rsp.apply_num;
		        document.location="payment_result.jsp";
	            // 결제 성공 시 로직,
	            
	        } else {
	        	 var msg = '결제에 실패하였습니다.';
		         msg += '에러내용 : ' + rsp.error_msg;
	            // 결제 실패 시 로직,
	        }
	        alert(msg);
	    });
	  }
	function kgPay() {
		  
	    // IMP.request_pay(param, callback) 결제창 호출
	    IMP.request_pay({ // param
	        pg: 'html5_inicis', // 결제할 pg사
	        pay_method: 'card',
	        merchant_uid: 'merchant_'+new Date().getTime(), // 주문번호
	        name: '파댕이', // 상품명
	        amount: 100, // 가격
	        buyer_email: 'lhj6346@gmail.com',
	        buyer_name: '이현진',
	        buyer_tel: '010-5771-6061',
	        buyer_addr: '부산시 강서구',
	        buyer_postcode: '46723'
	    }, function (rsp) { // callback
	    	console.log(rsp);
	        if (rsp.success) {
	        	var msg = '결제가 완료되었습니다.';
		        msg += '고유ID : ' + rsp.imp_uid;
		        msg += '상점 거래ID : ' + rsp.merchant_uid;
		        msg += '결제 금액 : ' + rsp.paid_amount;
		        msg += '카드 승인번호 : ' + rsp.apply_num;
		        document.location="payment_result.jsp";
	            // 결제 성공 시 로직,
	            
	        } else {
	        	 var msg = '결제에 실패하였습니다.';
		         msg += '에러내용 : ' + rsp.error_msg;
	            // 결제 실패 시 로직,
	        }
	        alert(msg);
	    });
	  }
</script>
</head>
<body>
<jsp:include page="../inc/header.jsp"/>  <!-- 헤더 들어가는 곳 -->
<div class="container h-100">
	<div class="row d-flex justify-content-center align-items-center h-100">
	        <div class="col-4 text-center">
				<div class="card">
					<img src="./assets/credit-card.png" alt="스탠다드패키지" class="card-img-top" onclick="kgPay()"/>
					<div class="card-body">
						<button class="btn btn-outline-success btn-block" onclick="kgPay()">카드결제</button><br>
					</div>
				</div>
	        </div>
	        <div class="col-4 text-center">
				<div class="card">
					<img src="./assets/payment_icon_yellow_large.png" alt="스탠다드패키지" class="card-img-top" onclick="kakaoPay()"/>
					<div class="card-body">
						<button class="btn btn-outline-warning btn-block" onclick="kakaoPay()">카카오페이</button><br>
					</div>
				</div>
	        </div>
	        <div class="col-4 text-center">
				<div class="card">
					<img src="./assets/logo-toss-blue.png" alt="스탠다드패키지" class="card-img-top" onclick="tossPay()"/>
					<div class="card-body">
						<button class="btn btn-outline-primary btn-block" onclick="tossPay()">토스페이</button>
					</div>
				</div>
	        </div>
	</div>
</div>
<!-- <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script> -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
</body>
</html>