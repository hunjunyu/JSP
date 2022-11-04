<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="board_ex.service.*,  board_ex.model.*" %>
<%
   // 1. 수정할 레코드의 게시글번호를 넘거받기
   String seq = request.getParameter("seq");
   
   // 2. Service에 getArticleById()함수를 호출하여 그 게시글번호의 레코드를 검색
      //vo에 담아야 저장되어있던 녀석들을 vo로 사용할 수 있으니
      
      //기존에 있던 그 게시글 내용들을 확인하려고
         //getArticleById 를 불러온거다 어쩃든 여기서 수정자체가 이루어 지는게
         //아니 잖아??! 수정할 얘들을 일단 불러와서 수정하기가 눌렸을때 변하는거니
         //여기서는 getArticleById 를 불러와야함 (수정하기 위한 애들을 불러오는것)
         
         //섣불리 모디파이서비스 란에들어가지말자
   BoardVO vo = ViewArticleService.getInstance().getArticleById(seq); 
   
%>   
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 수정하기</title>
</head>
 <body>
   <h4> 게시판 글 수정하기 </h4><br/>
   <!-- 수정하기 누르면 그글번호에대한 제목과 내용은 일단떠야하니 vo를 사용해 지정해주고 각 인풋마다 일단 이름을 잘 지정해준다 -->
      <!--폼태그안에 action = 'BoardModify.jsp' 를 잘 지정해줘야 여기는 보여지는 칸이지 동작자체가 모디파이에가서 실행을 한다  -->
   <form name='frm' method='post' action = 'BoardModify.jsp'>
   제  목 : <input type='text' name ='title' value =<%=vo.getTitle() %>><br/><br/>
   패스워드(수정/삭제시 필요) :
         <input type='password' name ='pass'><br/><br/>
   내  용 : <textarea name='content' rows='10' cols='40' name ='content'><%=vo.getContent()%></textarea><br/><br/>
    <!-- 히든으로 파라미터 또 넘겨주깅 -->
   <input type = 'hidden' name = 'seq' value = '<%=seq%>'>

   <input type='submit' value='수정하기'>
   <input type='button' value='목록보기' onclick="window.location='BoardList.jsp'">
   </form>

</body>
</html>