<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
</head>
<body>
	<c:if test="${result eq true}">
		<script>
			alert("로그인 성공!");
			$(location).attr("href","loginPage.do");
		</script>
	</c:if>
	
	<c:if test="${result eq false}">
		<script>
			alert("로그인 실패!");
			$(location).attr("href","loginPage.do");
		</script>
	</c:if>
	
</body>
</html>