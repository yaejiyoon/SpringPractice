<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${result > 0 }">
				<script>
					location.href = "article.do?seq="+${nseq}+"";
				</script>
		</c:when>
		<c:otherwise>
		</c:otherwise>
	</c:choose>
</body>
</html>