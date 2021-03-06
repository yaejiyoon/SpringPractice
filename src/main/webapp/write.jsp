<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시글 작성</title>
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
</style>
<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script>
	$(document)
			.ready(
					function() {
						var cnt = 0;
						$('#add')
								.click(
										function() {
											cnt++;
											console.log(cnt);
											$('#appendFile')
													.after(
															"<tr id='appendFile' align=center><td width=20% height=30><label>첨부파일</label></td><td><input type='file' name='file"+cnt+"'></td></tr>");
										})

						$("#cancel").click(function() {
							$(location).attr('href', 'index.do');
						})
					})
</script>
</head>
<body>
	<form action="writeProc.do" method="post" enctype="multipart/form-data">
		<table border=1>
			<tr>
				<th colspan=5>자유게시판</th>
			</tr>
			<tr align=center>
				<td width=20% height=30><label>제목</label></td>
				<td><input type="text" name="title" placeholder="제목을 입력하세요."></td>
			</tr>
			<tr>
				<td align=center><label>내용</label></td>
				<td colspan=4 height=300 align=center><textarea name="contents"
						placeholder="내용을 입력하세요."></textarea></td>
			</tr>
			<tr id="appendFile">
				<td colspan=5><button type="button" id="add"
						style="width: 160px">파일 첨부</button></td>
			</tr>
			<tr align=center>
				<td width=20% height=30><label>첨부파일</label></td>
				<td><input type="file" name="file"></td>
			</tr>

			<tr>
				<td colspan=5 align=right>
					<button>글 등록</button>
					<button type="button" id="cancel">취소</button>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>