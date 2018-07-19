<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<style>
	div{
		box-sizing: border-box;
	}
	
	#wrapper{
		border: 1px solid black;
		text-align:center;
		width:400px;
		height:150px;
		margin:0px auto;
	}
	
	#wrapper2{
		width:375px;
		margin:0px auto;
	}
</style>
<script>
	$(document).ready(function(){
		$("#join").click(function(){
			$(location).attr("href","Join.do");
		});
		
		$("#logout").click(function(){
			$(location).attr("href","logout.do");
		});
		
		$("#memberOut").click(function(){
			$(location).attr("href","memberOut.do");
		});
		
		$("#info").click(function(){
			$(location).attr("href","info.do");
		});
		
		$("#infoModify").click(function(){
			$(location).attr("href","modi.do");
		});
		
		$("#board").click(function(){
			$(location).attr("href","modi.do");
		});
		
	})
</script>
</head>
<body>
	<c:choose>
		<c:when test="${sessionScope.loginId eq null}">
			<div id="wrapper">
				<form action="login.do" method="post">
					<h3>LOGIN</h3>
					아이디 : <input type="text" placeholder="아이디를 입력하세요" name="id"><br>
					비밀번호 : <input type="password" placeholder="비밀번호를 입력하세요" name="pw"><br>
					<button id="login">로그인</button>
					<button id="join" type="button">회원가입</button>
				</form>
			</div>
		</c:when>
		<c:otherwise>
			<div id="wrapper2">
				<table border="1px">
					<tr>
					
						<td colspan="5">${sessionScope.loginId}님 환영합니다.
					</tr>
					<tr>
						<td><button id="logout">로그아웃</button>
						<td><button id="memberOut">회원탈퇴</button>
						<td><button id="info">정보보기</button>
						<td><button id="infoModify">정보수정</button>
						<td><button id="board">게시판</button>
					</tr>
				</table>
			</div>
		</c:otherwise>
	</c:choose>
</body>
</html>