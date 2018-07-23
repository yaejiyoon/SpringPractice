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
		<form action="modiProc.do" method="post">
		<input type=hidden name=seq value="${result.seq }">
		아이디 : ${result.id }<br>
		비밀번호 : <input type="text" name="pw"><br>
		이름 : <input type="text" name="name" value="${result.name }"><br>
		이메일 : <input type="text" name="email" value="${result.email }"><br>
		<button id="update">수정하기</button>
		<button id="back" type=button>뒤로가기</button>
		</form>
	</div>
</body>
</html>