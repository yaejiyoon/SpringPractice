<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<style>
	#wrapper{
		text-align: center;
		border:1px dotted black;
		width:400px;
		height:200px;
		margin:0px auto;
	}
	
	button{
		margin-top: 10px;
	}
</style>
<script>
	$(document).ready(function(){
		$("#back").click(function(){
			$(location).attr("href","loginPage.do");
		});
	})
</script>
</head>
<body>
	<div id="wrapper">
		<h3>회원 정보</h3>
		아이디 : ${result.id }<br>
		이름 : ${result.name }<br>
		이메일 : ${result.email }<br>
		<button id="back">뒤로가기</button>
	</div>
</body>
</html>