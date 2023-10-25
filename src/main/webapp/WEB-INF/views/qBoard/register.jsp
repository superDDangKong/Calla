<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성 페이지</title>
</head>
<body>
	<h2>글 작성 페이지</h2>
	<form action="register" method="POST">
		<div>
			<p>제목 : </p>
			<input type="text" name="qBoardTitle" placeholder="제목 입력" required> <!-- 데이터를 입력할 땐 쿼리 기준 물음표 갯수로 -->
		</div>
		<div>
			<p>작성자 : </p>								<!-- value="${memberNickname }" readonly="readonly" -->
			<input type="text" name="memberNickname" value="${memberNickname }" readonly="readonly"> <!-- 태그네임과 vo 이름과 같아야 한다 안그럼 에러나 -->
		</div>
		<div>
			<p>내용 : </p>
			<textarea rows="20" cols="120" name="qBoardContent" placeholder="내용 입력" ></textarea>
		</div>
		<div>
			<input type="submit" value="등록">
		</div>
	</form>
</body>
</html>