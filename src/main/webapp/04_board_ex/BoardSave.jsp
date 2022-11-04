<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ page import="board_ex.model.*,board_ex.service.*" %>

<%request.setCharacterEncoding("utf-8");%>

<!-- 아래Bean으로 인해서 인풋태그에 넣은 네임값과 VO랑 동일하게 이름지정한 4가지가 VO에 잘저장이된거다 -->
<!-- class="board_ex.model.BoardVO" 클래스는 VO를 가져오는것임 -->
<jsp:useBean id="rec" class="board_ex.model.BoardVO">
   <jsp:setProperty name="rec" property="*"/>
</jsp:useBean>

<% 
//<1 먼저 단순 디비저장>write 함수안에 게시판에 글을 입력시 DB에 저장하는 메소드 인 인설트가 존재한다!
    //WriteArticleService 는 역시 뭔가 중간다리역활이라 생각하자 getInstance는 싱글톤 패턴이니 기억하고
    //WriteArticleService.getInstance().write(); - 새로고침시 계속들어가는거 처리전1번!

    //<추후2번> 새로고침하면 계속 추가되는 구문을 인설트함수에서 수정하고왔다
   int seq = WriteArticleService.getInstance().write(rec); 
   //BoardSave.jsp에서 새로고침을 하게 되면 중복 입력되기에 변수안에다 rec받고 
   // response.sendRedirect("BoardView.jsp?seq="+seq); 해주자
   
%>

<!-- sendRedirect로 파라미터로 seq 넘겨줌-->
<%  response.sendRedirect("BoardView.jsp?seq="+seq);  %>