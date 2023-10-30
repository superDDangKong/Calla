<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />
<title>세션 만료</title>
</head>
<body style="text-align:center">
<%@ include file="../header.jspf" %> 
<h1>세션이 만료되었습니다.</h1>
<h2>다시 로그인 해주세요.</h2>
<br>
<form action="login" method="get">
	<input type="hidden" id="targetURL" name="targetURL" value="${param.targetURL }">
	<input type="submit" value="로그인 하러 가기">
</form>
	<%@ include file="../footer.jspf"%>
</body>
<script>
	console.log($('#targetURL').val());
</script>
</html>