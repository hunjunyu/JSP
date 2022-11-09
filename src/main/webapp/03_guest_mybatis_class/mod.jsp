<%@page import="mybatis.guest.session.CommentRepository"%>
<%@ page language="java" contentType="text/html; charset=utf-8"%>
 <%@ page import="mybatis.guest.model.Comment" %>   
 <%@ page import="mybatis.guest.service.CommentService" %>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	int id = Integer.parseInt(request.getParameter("cId"));
	Comment comment = CommentService.getInstance().selectCommentByPrimaryKey(id);
	

%>
</head>
<body>

</body>
</html>