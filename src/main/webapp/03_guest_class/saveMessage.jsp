<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="guest.service.WriteMessageService"%>    

<!-- 
	2. Service 클래스의 함수 호출
 -->     
<%	
	//0. 넘겨받는 데이타의 한글처리
	request.setCharacterEncoding("utf-8");
%>
<jsp:useBean id="m" class="guest.model.Message">
<jsp:setProperty name='m' property='*'/>
</jsp:useBean>

<%
	//1. 화면의 입력값을 Message 클래스로 전달
	WriteMessageService service = WriteMessageService.getInstance();
    service.write(m);
%>
    
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 방명록 남김 </title>
</head>
<body>
	<font size="3" color="#bb44cc">
		<%= m.getGuestName() %>방명록에 메세지를 남겼습니다. 
	</font><br/><br/><br/>
	<a href='listMessage.jsp'>[ 목록보기 ]</a>
</body>
</html>