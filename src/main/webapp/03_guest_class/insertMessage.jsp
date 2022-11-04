<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 방명록 </title>
<script type="text/javascript"
   src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script type="text/javascript">
$(function () {
	
	$('#submitbtn').click(function () {//메세지 남기기 버튼 클릭시 이벤트발생
		
		var id = $('input[name="guestName"]').val();//아이디 값
		var pass = $('input[name="password"]').val();//비밀번호 값
		var msg = $('textarea[name="message"]').val();//메세지 내용 값
		if(id =="" || pass==""||msg ==""){//id,비번,메세지 내용이 없으면 
			    alert('입력해주세요');   
			    return;
		}
		$('form[name="frm"]').submit();//모든 내용을 전송
	});
	
});


</script>

</head>

<body>

	<form action="saveMessage.jsp" name="frm" method="get">
		이름 : <input type="text" name="guestName" required /><br/><br/>
		암호 : <input type="password" name="password" required /><br/><br/>
		메세지 : <textarea name="message" rows="3" cols="30" required></textarea><br/><br/>
		<input id='submitbtn' type="button" value="메세지 남기기">
	</form>
</body>
</html>