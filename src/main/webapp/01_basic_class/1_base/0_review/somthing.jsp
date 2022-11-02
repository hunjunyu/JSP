<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ page import = "member.dao1.*" %> <!-- member.dao의 패키지안의 모든 클래스를 인포트한다 --> 
		
<%
		request.setCharacterEncoding("utf-8");//한국어가 깨지지 않게 설정
	//1. 이전폼의 입력값 얻어오기
		String name = request.getParameter("realname");//폼의 realname을 name에 담는다
		String nick = request.getParameter("nickname");//폼의 nickname을 nick에 담는다
		String email = request.getParameter("myemail");//폼의 myemail을 email에 담는다
		int age = Integer.parseInt(request.getParameter("myage"));//폼의 age를 age에 담는다
	//2. VO객체에 저장하기
	memberVO vo = new memberVO();//memberVO 클래스파일을 vo에 선언한다
	vo.setName(name); // vo의 name에 form의 name 입력값에 대입한다
	vo.setNick(nick); // vo의 nick에 form의 nick 입력값에 대입한다
	vo.setEmail(email); // vo의 email에 form의 email 입력값에 대입한다
	vo.setAge(age); // vo의 age에 form의 age 입력값에 대입한다
	
	//3. DB에 입력하기
	 memberDAO dao = memberDAO.getInstance();
	 dao.insert(vo);
	//4. 출력은 알아서
	
	
	
%>    


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
			<h2>정보</h2><br/>
			이름 : <%=name %><br/>
			닉네임 : <%=nick %><br/>
			이메일 : <%= email%><br/>
			나이 : <%=age  %><br/>

</body>
</html>