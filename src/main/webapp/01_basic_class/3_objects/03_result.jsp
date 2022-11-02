<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String irum = request.getParameter("irum");
	String pet[] = request.getParameterValues("pet");
	String choosePet = " ";
	
	for (int i = 0; pet !=null&& i < pet.length;i++){
		choosePet +=pet[i]+" ";
	}
	
%>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>03_result.html</title>
</head>
<body>

<!-- 이전화며네서 사용자 입력값을 얻어와서 
	-request.getParmeter(''')
	-request.getParmeterValues("")
	화면 출력 -->
이름 : <%=irum %>
좋아하는 동물 : <%=choosePet %>




</body>
</html>