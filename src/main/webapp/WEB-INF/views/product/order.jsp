<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<title>주문내역</title>
<%@ include file="../header.jspf" %> 	
</head>
<h1>주문내역</h1>
<body>
	<c:set var="memberId" value="${memberId}" />
	<input type="hidden" id="memberId" value="${memberId}" />	
	<div id="productOrder">
	
	 <table>
        <thead>
            <tr>
                <th style="width: 100px">주문 날짜</th>
                <th style="width: 150px">상품 이름</th>
                <th style="width: 60px">이미지</th>
                <th style="width: 80px">수량</th>
                <th style="width: 80px">가격</th>
                <th style="width: 80px">총 가격</th>
                <th style="width: 150px">배송지</th>
                <th style="width: 80px">수령인</th>
                               
            </tr>
        </thead>
        <c:choose>
            <c:when test="${empty productOrderList}">
                <tbody>
                    <tr>
                        <td colspan="5">등록된 상품이 없습니다.</td>
                    </tr>
                </tbody>
            </c:when>
            <c:otherwise>
                <tbody>
                    <c:forEach var="vo" items="${productOrderList}">
                        <tr>
                            <td>
	                        	<fmt:formatDate value="${vo.productOrderCreatedDate }"
								pattern="yyyy.MM.dd" var="productOrderCreatedDate" />                            
                            	${productOrderCreatedDate }
                            </td>
                            <td><a href="detail?productId=${vo.productId }&memberId=${vo.memberId }&page=${pageMaker.criteria.page}">${vo.productName }</a></td>
                            <td>
                            	<div>
									<img src="display?fileName=${vo.productImagePath}" width="100px" height="100px">
								</div>
                            </td>
                            <td>${vo.productAmount }</td>
                            <td>${vo.productPrice }</td>
                            <td>
                            	<c:set var="totalPrice" value="${vo.productAmount * vo.productPrice}" />
                            	${totalPrice }
                            </td>
                            <td>${vo.memberAddress }</td>
                            <td>${vo.recipientName }</td>
                            <td>
                            	<input type="hidden" name="productId" value="${vo.productId}">
                            </td>
                        </tr>
                    </c:forEach>
                  
                </tbody>
            </c:otherwise>
        </c:choose>
    </table>
	</div>

	
</body>
</html>