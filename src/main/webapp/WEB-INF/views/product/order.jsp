<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style type="text/css">
ul {
	list-style-type : none;
}

li {
	display : inline-block;
}

</style>
<meta charset="UTF-8">
<title>Order</title>
	<h1>List</h1>
	<%@ include file="../header.jspf" %> 	
	<h1>장바구니</h1>
	<button id="home"><a href="/calla/">홈</a></button>
</head>
<body>
	 <table>
        <thead>
            <tr>
                <th style="width: 60px">번호</th>
                <th style="width: 60px">이미지</th>
                <th style="width: 60px">이름</th>
                <th style="width: 60px">카테고리</th>
                <th style="width: 60px">가격</th>
            </tr>
        </thead>
        <c:choose>
            <c:when test="${empty list}">
                <tbody>
                    <tr>
                        <td colspan="5">등록된 상품이 없습니다.</td>
                    </tr>
                </tbody>
            </c:when>
            <c:otherwise>
                <tbody>
                    <c:forEach var="vo" items="${list}">
                        <tr>
                            <td>${vo.productOrderId}</td>
                            <td>
                                <div>
                                    <img src="display?fileName=${vo.productImagePath}" width="100px" height="100px">
                                </div>
                            </td>
                            <td>
                                <a href="detail?productId=${vo.productId}&memberId=${memberId}&page=${pageMaker.criteria.page}">${vo.productName}</a>
                            </td>
                            <td>${vo.productCategori}</td>
                            <td>${vo.productPrice}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </c:otherwise>
        </c:choose>
    </table>


</body>
</html>