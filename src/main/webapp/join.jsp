<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Join</title>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<style>
	div{
		border: 1px dotted black;
		box-sizing: border-box;
	}
	
	#wrapper{
		text-align:center;
		width:500px;
		height:300px;
		margin:0px auto;
		padding-top: 10px;
	}
	
	input{
		padding-bottom: 5px;
		margin-bottom: 10px;
	}
</style>
<script>
	$(document).ready(function(){
		$("#back").click(function(){
			$(location).attr("href","login.jsp");
		});
		
		$("#join").click(function(){
			var id = $("#id").val();
			var pw = $("#pw").val();
			var name = $("#name").val();
			
			if(id=="" || pw=="" || name==""){
				alert("빈칸을 모두 입력하세요.");
			}else{
				joinForm.submit();
			}
			
			
		})
		
	})
</script>
</head>
<body>
	<div id="wrapper">
		<form action="joinProc.do" method="post" id="joinForm">
		<h3>회원가입</h3>
		아이디 : <input type="text" name="id" id="id"><br>
		비밀번호 : <input type="text" name="pw" id="pw"><br>
		이름 : <input type="text" name="name" id="name"><br>
		이메일 : <input type="text" name="email" id="email"><br>
		<button id="join" type="button">가입완료</button>
		<button id="back" type="button">뒤로가기</button>
		</form>
	</div>
</body>
</html>