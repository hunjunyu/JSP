<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String mid = request.getParameter("messageId");
%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 방명록 삭제 </title>
</head>
<body>
	<%=mid %>번 메세지를 삭제하려면 암호를 입력하세요. <br/><br/>
	<form action="deleteConfirm.jsp" method="get">
	<input type = 'hidden' name = 'messageId' value='<%=mid %>'>
		암호 : <input type="password"	 name="password" />
		<input type="submit" value="메세지 삭제"/>
	</form>
</body>
</html>