<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "member.beans.MemberDao" %>
<%
	String id =	request.getParameter("id");
	MemberDao dao = MemberDao.getInstance();
	boolean result = dao.isDuplicatedId(id);
	out.print(result);
%>  
