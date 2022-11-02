<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
		//1. 이전 화면에서 사용자 입력값들을 얻어오기
		//-request.getParmetar('')
		
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
        String occupation = request.getParameter("occupation");
		String hobby[] = request.getParameterValues("hobby");
		String hobbyt = "";
			for(int i = 0;hobby!=null&& i<hobby.length;i++){
				hobbyt += hobby[i]+'/';
			};
			
		//2.
%>    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 2 얻어온 입력값들을 화면에 출력하기 -->
<h2>정보</h2>
이름 : <%=name %><br/>
성별 : <%=gender %><br/>
직업 : <%=occupation %><br/>
취미 : <%=hobbyt %><br/>


</body>
</html>