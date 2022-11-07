<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <!-- 1107일 설정중 taglib -->
    <%@ taglib prefix ='c' uri = "http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL 문법</title>
</head>
<body>

<!--EL  변수선언   --> <!-- 태그안에 if문 쓰는법 -->

<c:set var = 'gender' value='male'/>

<c:if test="${gender == 'male'}"> 당신은 남성입니다.</c:if>
<c:if test="${gender eq 'female'}"> 당신은 여성입니다.</c:if>


<hr/>


<!-- 괄호사이가 밸류값 -->
<c:set var='age'>10</c:set>

<!-- else if 대신 추스  추스태그안에는 주석금지--> 
<c:choose>
   <c:when test="${age lt 10}">어린이입니다</c:when>
   <c:when test="${age ge 10 and 19 lt 20}">청소년입니다</c:when>
   <c:otherwise>성인입니다.</c:otherwise>
</c:choose>
<hr/>
<c:set var = 'sum' value='0'/>
<c:forEach var = 'i' begin = '1' end = '100'>
<c:set var = 'sum' value = '${sum+i}'/>
</c:forEach>
1~100까지의 합 : ${sum} <hr/>

<!--  1부터 100까지의 짝수의 합 홀수의 합 구하기 -->
<c:forEach var = 'i' begin = '1' end = '100'>
	<c:choose>
		<c:when test = "${ i mod 2 eq 0 }">
		<c:set var = 'even' value = '${even+i}'/>
	</c:when>
	<c:otherwise>
		<c:set var = 'odd' value = '${odd+i}'/>
	</c:otherwise>
	</c:choose>
</c:forEach>
1~100까지의 짝수의 합 : ${even} <br/>
1~100까지의 홀수의 합 : ${odd} <br/>

</body>
</html>

<!-- 일단 jsp 기초문법 정리
 

   < %@ % > : 설정
      - 페이지 설정 import말고는 건들이지 않음
      - include : 정적,동적 이든뭐든 
      - taglib : jstl사용
      
      
   < %! % > : 선언 변수선언
   < % % > : 스크립트릿 (자바쓰는란)
   < % = % > : 표현식 결과 출력
   
   
   
   
   
   
   
    -->