<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	$(document).ready(function() {
		$("#boardList").click(function() {
			$(location).attr("href","boardList.do");
		})
	})
</script>
<body>
	<div align="center">
		<button id="boardList">Board List</button>
	</div>
	<a href="article.do">게시글</a>          
	<a href="write.do">게시글 작성</a>                      
</body>
</html>