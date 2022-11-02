<%@page import="info.infobin"%>
<%@ page contentType="text/html; charset=UTF-8" 
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<body>

	<% request.setCharacterEncoding("utf-8"); %>
	<jsp:useBean id="bean" class="info.infobin">
		<jsp:setProperty name="bean" property = "*"/>
	</jsp:useBean>

	<h2>  당신의 신상명세서 확인 </h2>
	이   름  : <br/> <%= bean.getName() %><br/>
	주민번호 : <br/> <%= bean.getId()%><br/>
	성  별   : <br/>  <%= bean.getGender() %><br/>
	맞습니까????
</body>
</html>