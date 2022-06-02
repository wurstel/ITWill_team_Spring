<%@page import="dao.MemberDAO"%>
<%@page import="vo.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//POST 방식 한글 처리
request.setCharacterEncoding("UTF-8");

//mem_joinForm.jsp 페이지로부터 전달받은 폼 파라미터 데이터 가져오기
String id = request.getParameter("id");
String pass = request.getParameter("pass");
String name = request.getParameter("name");
String birth = request.getParameter("birth");
String gender = request.getParameter("gender");
String email = request.getParameter("email")+"@"+request.getParameter("domain");
String phone = request.getParameter("phone");
String postcode = request.getParameter("postcode");
String address = request.getParameter("address")+request.getParameter("add_detail");

//회원가입에 필요한 파라미터 데이터를 하나의 객체로 관리하기 위해 MemberBean 객체 활용
//MemberBean 객체 생성 후 Setter 를 호출하여 폼 파라미터 데이터 저장
MemberVO memberDTO = new MemberVO();
memberDTO.setMem_id(id);
memberDTO.setMem_password(pass);
memberDTO.setMem_name(name);
memberDTO.setMem_birth(birth);
memberDTO.setMem_gender(gender);
memberDTO.setMem_email(email);
memberDTO.setMem_phoneNum(phone);
memberDTO.setMem_postcode(postcode);
memberDTO.setMem_address(address);

//MemberDAO 클래스 객체 생성
MemberDAO memberDAO = MemberDAO.getInstance();

//MemberDAO 객체의 insertMember() 메서드를 호출하여 회원 가입 처리
//=> 파라미터 : MemberBean 객체,   리턴타입 : int(insertCount)
int insertCount = memberDAO.insertMember(memberDTO);

//insertMember() 작업 수행 후 리턴받은 값에 따른 처리
//=> 성공 시(insertCount > 0) "member 폴더의 joinSucess.jsp" 페이지로 포워딩
//=> 실패 시 자바스크립트를 사용하여 "회원 가입 실패!" 출력 후 이전페이지로 돌아가기
if(insertCount > 0) {
	response.sendRedirect("../member/mem_joinSuccess.jsp");
} else {
%>
	<script>
		alert("회원 가입 실패!");
		history.back();
	</script>
	<%
}

%>