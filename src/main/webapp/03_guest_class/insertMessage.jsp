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
		
		var id = $('input[name="guestName"]').val();
		var pass = $('input[name="password"]').val();
		var msg = $('textarea[name="message"]').val();
		if(id =="" || pass==""||msg ==""){
			    alert('입력해주세요');   
			    return;
		}
		$('form[name="frm"]').submit();
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