<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border=2 align=center>
		<tr>
			<th colspan="5">B O A R D</th>
		</tr>
		<tr align=center>
			<td width=30></td>
			<td width=500 align=center>Title</td>
			<td>Writer</td>
			<td width=100>Date</td>
			<td>Hits</td>
		</tr>
		<tr align=center>
		<c:choose>
		<c:when test="${result!=null}">
			<c:forEach var="tmp" items="${result}">
				<tr align=center>
					<td width=30>${tmp.seq}</td>
					<td width=150 align=center><a href="article.do?seq=${tmp.seq}">${tmp.title}</td>
					<td>${tmp.writer}</td>
					<td>${tmp.writedate}</td>
					<td>${tmp.viewcount}</td>
				</tr>
			</c:forEach>
		</c:when>
		<c:when test="${result==null}"> 
			<tr>
			<td colspan="6" height=150 align=center>표시할 내용이 없습니다.</td>
			</tr>
		</c:when>
		</c:choose>
		</tr>
		<tr>
			<td colspan="6" align=center>${navi}</td>
		</tr>
		<tr>
			<th colspan='4'><input id="searchText" type=text placeholder="제목을 검색해주세요" name="searchTitle"></td>
			<td><button type="button" id="searchBt">search</button></td>
		</tr>
		<tr>
			<td colspan="6" align=right>
				<button type=button id=write
				onclick="location.href='write.do'">글쓰기</button>
			</td>
		</tr>

	</table>
</body>
</html>