<%@ page contentType="text/html;charset=utf-8" %>
<%@ page import="board_ex.model.*, board_ex.service.*" %>
<%@ page import="java.util.List" %>

<%  //웹브라우저가 게시글 목록을 캐싱할 경우 새로운 글이 추가되더라도 새글이 목록에 안 보일 수 있기 때문에 설정
   response.setHeader("Pragma","No-cache");      // HTTP 1.0 version
   response.setHeader("Cache-Control","no-cache");   // HTTP 1.1 version
   response.setHeader("Cache-Control","no-store"); // 일부 파이어폭스 버스 관련
   response.setDateHeader("Expires", 1L);         // 현재 시간 이전으로 만료일을 지정함으로써 응답결과가 캐쉬되지 않도록 설정
   //목록보기를 불러올때 다시 서버에있는걸 가져오기 위해서 목록보기란에는 위에 이걸 항상그냥 복붙해넣는다

%>

<%
		//3.



		String pNum =request.getParameter("page");   //파라미터이름받기
		// 1.전체 메세지 레코드 검색
		//getArticleList() 함수안에 - 매개변수없는
		//selectList가 있음 저장된 레코드들불러오는 함수임
		ListArticleService service = ListArticleService.getInstance();
		//List <BoardVO> mList =  service.getArticleList(); - 처음 페이지 아래 1 2 3 만들기 전에는 매개변수없는 함수를 썻었다
		List <BoardVO> mList =  service.getArticleList(pNum);
		
		//2.
		int totalPageCount = service.getTotalPage();
%>

<HTML>
<head><title> 게시글 목록 </title>
</head>

<BODY>

   <h3> 게시판 목록 </h3>
   
   <table border="1" bordercolor="darkblue">
   <tr>
      <td> 글번호 </td>
      <td> 제 목 </td>
      <td> 작성자 </td>
      <td> 작성일 </td>
      <td> 조회수 </td>
   </tr>
   
   
   <% if( mList.isEmpty() ) { %>
      <tr><td colspan="5"> 등록된 게시물이 없습니다. </td></tr>
   <% } else { %>
   <!--  여기에 목록 출력하기  서비스단에서 mList가 배열로 있겠네? 반복문이겠지
   집합 배열이 오른쪽에 항상둔다 왼쪽엔 자료형-->
   <!-- 왼쪽에 vo겟세터 생성완 -->
   <%for(BoardVO vo :mList){ %>
   <!-- tr을 반복한다 라는건 리스트들을 한줄한줄 반복한다는거겠군 -->
      <tr>
          <td> <%=vo.getSeq() %> </td>
          <!-- 하이퍼링크와 파라미터로 pk를 넘겨준다 
             즉 해당하는 글번호를 넣어주면되겠네? 글번호가 pk이임
             그럼 url에 해당하는 글번호가 넘어감-->
         <td><a href = 'BoardView.jsp?seq=<%=vo.getSeq() %>'><%=vo.getTitle() %></a></td>
         <td> <%=vo.getWriter() %> </td>
         <td> <%=vo.getRegdate()%>  </td>
         <td> <%=vo.getCnt()%> </td>
      </tr>
      <%} //end of for%>
   <% }//end of if - else  %>
      <tr>
         <td colspan="5">
            <a href="BoardInputForm.jsp">글쓰기</a>
            <hr/>
            
            <!-- 페이지 아래뜨게 하기위해 나중에만드는 -->
            <%for(int i = 1 ; i<=totalPageCount; i++){ %>
      <a href = 'BoardList.jsp?page=<%= i%>'>[<%= i  %>]</a>
   <%} //end for %>
         </td>
      </tr>
   </table>
</BODY>
</HTML>