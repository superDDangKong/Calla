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
<title>좋아요 목록</title>
</head>
<body>
<%@ include file="../header.jspf" %> 
	<div id="product_likes_container">
		
		<div id="product">
			<button>공용상품</button>
			<div id="product_likes_list">
			</div>
		</div>
		
		<div id="u_product">
			<button>중고상품</button>
			<div id="u_product_likes_list">
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			
			
			
		}) // end document.ready()
		<%@ include file="../footer.jspf" %> 
	</script>
</body>
</html>