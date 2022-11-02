<%@ page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%! String msg; %>
<%
	msg= "안녕하세요~~";
%> 

<!-- html주석 //제일 마지막에 남음-->
<%-- jsp주석 --%>
<% //자바주석 %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
메세지 : <%= msg %>
</body>
</html>