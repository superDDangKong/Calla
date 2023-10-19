<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
<input type="hidden" id="memberId" value=${memberId }>
<a href="update?memberId=${memberId}">회원정보 수정</a>
<a href="order">거래내역</a>
<a href="likes">좋아요 목록</a>

</body>
</html>