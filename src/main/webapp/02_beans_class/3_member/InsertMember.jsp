<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "member.beans.*" %>

<!-- 하나씩 천천히 도전합시다 -->
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="m" class="member.beans.Member">
   <jsp:setProperty name='m' property='*'/>
</jsp:useBean>

<%
   String name = m.getName();
   String id = m.getId();
   String pass = m.getPassword();
   if(name!=null && id!=null&&pass!=null){
      MemberDao dao = MemberDao.getInstance();
      dao.insertMember(m);
   }else%>
   <jsp:forward page="MemberForm.jsp"/>
   

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 회원가입 확인  </title>
</head>
<body>
   아이디 : <%=m.getId() %><br>
   패스워드 : <%=m.getPassword() %><br>
   이름 : <%=m.getName() %><br>
   전화 : <%=m.getTel() %><br>
   주소 : <%=m.getAddr() %><br>
</body>
</html>