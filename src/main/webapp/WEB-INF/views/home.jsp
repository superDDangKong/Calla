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
<link href="resources/css/styles.css" rel="stylesheet" />
<title>Calla</title>
</head>

<body>

<%@ include file="header.jspf" %> 	
	
	<input type="hidden" id="memberNickname" value=${memberNickname }>
	<input type="hidden" id="memberId" value=${memberId }>

	</div>
	<div id="navigator">
		<form action="product/list" method="get">
			<input type="submit" value="공용상품">
		</form>
		<form action="uProduct/list" method="get">
			<input type="submit" value="중고상품">
		</form>
		<form action="qBoard/list" method="get">
			<input type="submit" value="문의게시판">
		</form> 
		<form action="fBoard/list" method="get">
			<input type="submit" value="자유게시판">
		</form>
		
	</div>
<%@ include file="footer.jspf" %> 
</body>
</html>
