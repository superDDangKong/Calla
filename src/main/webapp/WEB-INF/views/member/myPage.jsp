<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
<%@ include file="../header.jspf" %> 	
<input type="hidden" id="memberId" value=${memberId }>
<h2>마이페이지</h2>

	<form action="/calla/" method="get">
		<input type="submit" value="홈">
	</form>
	
	<form action="update" method="get">
		<input type="submit" value="회원정보">
	</form>
	
	<form action="order" method="get">
		<input type="submit" value="거래내역">
	</form>
	
	<form action="likes" method="get">
		<input type="submit" value="좋아요 목록">
	</form>

</body>
</html>