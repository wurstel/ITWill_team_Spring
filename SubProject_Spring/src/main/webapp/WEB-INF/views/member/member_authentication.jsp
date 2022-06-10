<%@page import="jsp11_board.MemberDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
// URL 을 통해 전달받은 아이디, 인증코드 가져오기
String id = request.getParameter("id");
String code = request.getParameter("code");

// MemberDAO 객체의 selectAuthInfo() 메서드를 호출하여 인증코드 확인
// => 파라미터 : 아이디, 인증코드    리턴타입 : int(result)
MemberDAO dao = new MemberDAO();
int result = dao.selectAuthInfo(id, code);

// 리턴받은 결과값에 대한 판별
// 0 : 인증 실패(잘못된 인증코드)
// -1 : 인증 실패(인증 정보가 존재하지 않음)
// 1 : 인증 성공
String msg = "";
if(result == 0) {
	msg = "인증 실패(잘못된 인증코드)";
} else if(result == -1) {
	msg = "인증 실패(인증 정보가 존재하지 않음)";
} else {
	msg = "인증 성공";
	
	// memberDAO 객체의 changeAuthStatus메서드를 호출하여
	// 인증 성공시 member 테이블의 auth_status 컬럼값을 Y로 변경하고
	// member_auth_info 테이블의 아이디와 인증코드 레코드 삭제하기
	dao.changeAuthStatus(id);
}
%>
<script>
	alert("<%=msg%>");
	location.href="../main.jsp";
</script>










