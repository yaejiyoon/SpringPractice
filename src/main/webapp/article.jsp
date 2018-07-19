<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title }</title>
<style>
table {
	width: 800px;
	height: 450px;
	margin: 0 auto;
	box-sizing: border-box;
}

input, textarea {
	box-sizing: border-box;
	width: 100%;
	height: 100%;
}

textarea {
	resize: none;
}

a {
	color: black;
	text-decoration: none;
}

a:visited {
	color: red;
	text-decoration: none;
}

a:hover {
	color: blue;
}
</style>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#commentBtn").click(function() {
			var comment = $('#commentArea').val();
			if (comment == "") {
				alert("댓글을 입력하세요.");
			} else {
				$("#commentPost").submit();
			}

		})
	})

	var delFunc = function() {
		var con = confirm("삭제하시겠습니까?")
		if (con) {
			location.href = "delete.do?seq=${dto.seq}";
		} else {
			location.reload();
		}
	}
</script>
</head>
<body>
	<form action="comment.bo" method="post" id="commentPost">
		<table border=1>
			<tr>
				<th colspan=5>자유게시판</th>
			</tr>
			<tr align=center>
				<td width=20% height=30><label>작성자:</label></td>
				<td align=left colspan=4>${dto.getWriter() }</td>
			</tr>
			<tr align=center>
				<td width=20% height=30><label>제목:</label></td>
				<td align=left colspan=4>${dto.getTitle() }</td>
			</tr>
			<%-- 			<c:if test="${not empty flist }"> --%>
			<c:forEach var="flist" items="">
				<tr align=center>
					<td width=20% height=30><label>첨부된 파일</label></td>
					<td align=left colspan=4><a href="../Download?fileName=">
					</a></td>
				</tr>
			</c:forEach>
			<%-- 			</c:if> --%>

			<tr>
				<td align=center><label>내용:</label></td>
				<td height=270 align=left colspan=4>${dto.getContents() }</td>
			</tr>

			<tr>
				<td colspan=5 align=right>
				<c:if test="${sessionScope.loginId eq dto.getWriter() }">
					<button type="button"
						onclick="location.href='modify.do?seq=${dto.getSeq()}'">수정</button>
					<button type="button" onclick="delFunc()">삭제</button>
				</c:if>
					<button type="button" onclick="location.href='boardList.do'">돌아가기</button></td>
			</tr>
			<tr align=center>
				<td colspan=4 width=70%><textarea id="commentArea"
						name="commentArea" placeholder="댓글쓰기"></textarea></td>
				<td><button type="button" id="commentBtn">댓글 등록</button></td>
			</tr>

			<%-- 			<c:forEach var="clist" items="${clist }"> --%>
			<tr>
				<td id="changetd" colspan=4 width=70%><span>&nbsp;&nbsp;</span>
					<br></td>

				<td id="changebtntd" align=center><a href="#" id="change">수정</a>&nbsp;&nbsp;
					<a href="commentdel.bo?seq=&comment_seq=&title=">삭제</a></td>
			</tr>
			<%-- 			</c:forEach> --%>
		</table>
	</form>

</body>
</html>