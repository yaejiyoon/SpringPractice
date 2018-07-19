<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
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
			location.href = "deleteWriting.bo?seq=${bdto.seq}";
		} else {
			location.reload();
		}
	}
</script>
</head>
<body>
	<form action="comment.do" method="post" id="commentPost">
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
						<td align=left colspan=4><a
							href="../Download?fileName=">
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
<%-- 				<c:if test="${sessionScope.loginId eq dto.getWriter() }"> --%>
						<button type="button"
							onclick="location.href='modify.do?seq=${dto.getSeq()}'">수정</button>
						<button type="button" onclick="delFunc()">삭제</button>

<%-- 					</c:if> --%>
					<button type="button"
						onclick="location.href='boardList.do'">돌아가기</button></td>
			</tr>
			
		<%int count=0; %>

	<c:if test="${fn:length(commentList)>0}">
		<c:forEach var="commentList" items="${commentList }">
			<tr>
				<%count++; %>
				<td>${commentList.writer }
				<td style='width:300px;' id="text<%=count %>">${commentList.comment_text }
				<td>${commentList.writeDate }
				
				<c:if test="${session eq writer}">
					<td id=buttonTD<%=count %>>
					<button id=commentRemove<%=count %> type=button>삭제</button>
					
					<script>
					
						document.getElementById("commentRemove<%=count %>").onclick = function(){
							location.href="remove.com?articleNo=${item2.articleNo}&commentNo=${item2.comment_seq}";
						}
					</script>
					
				</c:if>
			</tr>
		</c:forEach>
	</c:if>
			
			
			
			<tr align=center>
				<td colspan=4 width=70%>
				<input type=hidden value="${dto.seq }" name="articleNo">
				<input type=hidden value="${dto.seq }" name="writer">
				<textarea id="commentArea" name="comment_text" placeholder="댓글쓰기"></textarea></td>
				<td><button type="button" id="commentBtn">댓글 등록</button></td>
			</tr>

<%-- 			<c:forEach var="clist" items="${clist }"> --%>
				<tr>
					<td id="changetd" colspan=4 width=70%><span>&nbsp;&nbsp;</span>
						<br></td>

					<td id="changebtntd" align=center><a href="#" id="change">수정</a>&nbsp;&nbsp;
						<a
						href="commentdel.bo?seq=&comment_seq=&title=">삭제</a></td>
				</tr>
<%-- 			</c:forEach> --%>
		</table>
		<input type="hidden" id="seq" name="seq" value="">
		<input type="hidden" id="title" name="title" value="">
	</form>

</body>
</html>