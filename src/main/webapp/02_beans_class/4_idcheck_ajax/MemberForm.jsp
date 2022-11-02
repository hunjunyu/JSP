<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="member.beans.*" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 회원가입  </title>
<script type="text/javascript" src = "http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript" >
$(function () {
 	$(':button').click(function () {
 		
 		
		var id = $('#id').val();
 		if(id == null || id ==''){
 			$('#result').text('아이디를 입력해 주세요.');
 			$('#result').show();
 			return;
 		}//end of if
 		
 		var param = {id : $('#id').val()};
		$.ajax({
			url : 'MemberDao.jsp',
			data : param,
			success : function (data) {
				if(data.trim() == "true" ) {
					$('#result').text('사용중인 아이디가 있습니다. 다시 입력하여 주십시오.');
					$('#result').show();
			 }else if(data.trim() == 'false') { 
				 $('#result').text('사용할 수 있는 아이디입니다.');
				 $('#result').show();
			 }//end of else if
			}//end of success
		})//end of ajax
 	
	})//end of button click
	
	
	
	
	
	
})//end of function
</script>
</head>
<body>

<h1>회원가입서 작성하기</h1>
 
	<form action='ris.jsp' method="post" name="frm">
		<table>
			<tr>
				<td width="100">
				<font color="blue">아이디</font>
				</td>
				<td width="100">
				<input type="text" name="id" id = 'id' required="required">
				<input type="button" value="중복확인" >
				
				<div id = 'result'></div>
				<br/>
				</td>
			</tr>
			<tr>
				<td width="100">
				<font color="blue">비밀번호</font>
				</td>
				<td width="100">
				<input type="password" name="password" id ="password" required="required" /><br/>
				</td>
			</tr>
			<tr>
				<td width="100">
				<font color="blue">비밀번호학인</font>
				</td>
				<td width="100">
				<input type="password" name="repassword"  required="required"/><br/>
				</td>
			</tr>
			<tr>
				<td width="100">
				<font color="blue">이름</font>
				</td>
				<td width="100">
				<input type="text" name="name" id ="name" required="required" /><br/>
				</td>
			</tr>
			<tr>
				<td width="100">
				<font color="blue">전화번호</font>
				</td>
				<td>
				<input type="text" size="15" name="tel" id ="tel" required="required" />
				<br/>
				</td>
			</tr>
			<tr>
				<td width="100">
				<font color="blue">주소</font>
				</td>
				<td>
				<input type="text" size="50" name="addr" id ="addr"/><br/>
				</td>
			</tr>
			<tr>
				<td width="100">
				 <!--로그인 버튼-->
				 <input type="submit" value="회원가입" id = "su">
				</td>
				<td width="100">
				<input type="reset" name="cancel" value="취소"><br/>
				</td>
			</tr>
		</table>
	</form>



 </body>
</html>
    