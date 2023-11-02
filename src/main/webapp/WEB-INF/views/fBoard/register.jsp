<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="../header.jspf" %> 	
<title>글 작성 페이지</title>
</head>
<body>

	<form action="register" method="POST">
		<div>
			<p>제목 : </p>
			<input type="text" name="fBoardTitle" placeholder="제목 입력" required>
		</div>
		<div>
			<p>내용 : </p>
			<textarea rows="20" cols="120" name="fBoardContent" placeholder="내용 입력"></textarea>
		</div>
		<div>
			<input type="submit" value="등록">
		</div>
	</form>
	
</body>
</html>