<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.beans.*" %>

<!-- 하나씩 천천히 도전합시다 -->

<% request.setCharacterEncoding("utf-8"); %>


<jsp:useBean id="m" class="member.beans.Member" >
	<jsp:setProperty name ="m" property = "*"/>
</jsp:useBean>

<%
	MemberDao dao = MemberDao.getInstance();
	dao.insertMember(m);

%>

<jsp:forward page="MemberForm.jsp" />