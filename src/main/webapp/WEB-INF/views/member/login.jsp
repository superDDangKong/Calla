<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
<form action="login" method="POST">
    <label for="memberId">아이디:</label>
    <input type="text" id="memberId" name="memberId"> 
    <label for="memberPw">비밀번호:</label> 
    <input type="password" id="memberPw" name="memberPw"> 
    <input type="submit" value="로그인">
    <c:if test="${not empty param.targetURL}">
    <input type="hidden" id="targetURL" name="targetURL" value="${param.targetURL}">
    </c:if>
    <script>
    console.log($('#targetURL').val())
    </script>
</form>
</body>
</html>