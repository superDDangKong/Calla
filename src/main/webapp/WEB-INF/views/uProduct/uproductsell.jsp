<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<style type="text/css">
body {
	margin: 0;
}

* {
	box-sizing: border-box;
}

p, span {
	margin: 0;
}

a {
	color: black;
}

img {
	display: block;
	width: 80%;
	height: 80px;
	margin: auto;
}

.cart {
	width: 80%;
	margin: auto;
	padding: 30px;
}

.cart ul {
	background-color: whitesmoke;
	padding: 30px;
	margin-bottom: 50px;
	border: whitesmoke solid 1px;
	border-radius: 5px;
	font-size: 13px;
	font-weight: 300;
}

.cart ul :first-child {
	color: limegreen;
}

table {
	border-top: solid 1.5px black;
	border-collapse: collapse;
	width: 100%;
	font-size: 14px;
	border-width: 1px;
	border-style: solid;
	text-align: center;
}

thead {
	text-align: center;
	font-weight: bold;
}

tbody {
	font-size: 15px;
}

td {
	padding: 15px 0px;
	border-bottom: 1px solid lightgrey;
}

.cart__list__detail :nth-child(3) a {
	font-size: 12px;
}

.cart__list__detail :nth-child(3) p {
	margin-top: 6px;
	font-weight: bold;
}

.cart__list__smartstore {
	font-size: 12px;
	color: gray;
}

.cart__list__option p {
	margin-bottom: 25px;
	position: relative;
}

.cart__list__option p::after {
	content: "";
	width: 90%;
	height: 1px;
	background-color: lightgrey;
	left: 0px;
	top: 25px;
	position: absolute;
}

.cart__list__optionbtn {
	background-color: white;
	font-size: 15px;
	border: lightgrey solid 1px;
}

.cart__list__detail :nth-child(4), .cart__list__detail :nth-child(5),
	.cart__list__detail :nth-child(6) {
	border-left: 2px solid whitesmoke;
}

.cart__list__detail :nth-child(5), .cart__list__detail :nth-child(6) {
	text-align: center;
	font-size: 15px;
}

.cart__list__detail :nth-child(5) button {
	background-color: limegreen;
	color: white;
	border: none;
	border-radius: 5px;
	padding: 4px 8px;
	font-size: 12px;
	margin-top: 5px;
}

.price {
	font-weight: bold;
}

.cart__mainbtns {
	width: 420px;
	height: 200px;
	padding-top: 40px;
	display: block;
	margin: auto;
}

.cart__bigorderbtn {
	width: 200px;
	height: 50px;
	font-size: 16px;
	margin: auto;
	border-radius: 5px;
}

.cart__bigorderbtn.left {
	background-color: white;
	border: 1px lightgray solid;
}

.cart__bigorderbtn.right {
	background-color: lightgrey;
	color: white;
	border: none;
}
</style>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://kit.fontawesome.com/ef717dbcd3.js" crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />
<head>
<meta charset="UTF-8">
<title>calla</title>


</head>

<body>
	<%@ include file="../header.jspf"%>
	<section class="cart">
		<div class="cart__information">
			<ul>
				<li>예약 버튼을 누르면 상품목록이 거래가능에서 예약중으로 바뀝니다.</li>
				<li>거래가 완료 되었으면 거래완료 버튼을 눌러서 거래자의 닉네임을 골라주세요.</li>
				<li>실수로 예약 버튼을 눌렀거나 거래자의 닉네임을 잘못 선태했을시 1대1 문의를 해주시거나
					수정버튼을 이용해 고쳐주시기 바랍니다.</li>
			</ul>
		</div>
		<table class="cart__list">

			<thead>
				<tr>
					<td></td>
					<td colspan="2">상품정보</td>
					<td>등록날짜</td>
					<td>상품금액</td>
					<td>삭제</td>
				</tr>
			</thead>

			<tbody>
				<c:forEach var="vo" items="${list }">
				<input type="hidden" id="uProductSellId" name="uProductSellId" value="${vo.uProductSellId }">
					<tr class="cart__list__detail">
						<td></td>
						<td><img class="card-img-top"
							src="display?fileName=${vo.uProductImagePath}" width="200px"
							height="150px" alt="..." /></td>
						<td>${vo.uProductName }</td>
						<fmt:formatDate value="${vo.uProductCreatedDate }"
							pattern="yyyy-MM-dd HH:mm:ss" var="uProductCreatedDate" />
						<td>${uProductCreatedDate }</td>
						<td><span class="price">${vo.uProductPrice }원</span><br>
						<td>			
							 <button type="button" id="btn_delete_${vo.uProductSellId}" style="border: none; background: none;">
							<i class="fa-solid fa-trash fa-xl"></i>
						</button>
						
						<script type="text/javascript">
					    $(document).ready(function() {
					        var deleteBtn = $('#btn_delete_${vo.uProductSellId}');
					
					        deleteBtn.click(function(){
					            var uProductSellId = '${vo.uProductSellId}'; // 버튼의 ID에서 uProductId 추출
					            console.log(uProductSellId);
					
					           
					                $.ajax({
					                    type: 'DELETE',
					                    url: 'sell/' + uProductSellId,
					                    headers: {
					                        'Content-Type': 'application/json'
					                    },
					                    success: function(result) {
					                        console.log(result);
					                        if (result == 1) {
					                            alert('삭제 성공');
					                            location.reload();
					                        }
					                    }
					                }); // end ajax()
					           
					        }); // end btn_update click event
					    }); // end document ready
					</script>
						</td>
					</tr>
				</c:forEach>
			</tbody>

		</table>

	


		<div class="cart__mainbtns">

			<button class="cart__bigorderbtn left"
				onclick="location.href='http://localhost:8080/calla/'">쇼핑
				계속하기</button>
			<button class="cart__bigorderbtn right"
				onclick="location.href='http://localhost:8080/calla/uProduct/register'">
				물건 등록하기</button>


		</div>
		
		
		<ul>
			<c:if test="${pageMaker.hasPrev }">
				<li><a href="uproductsell?page=${pageMaker.startPageNo - 1 }">이전</a></li>
			</c:if>
			<c:forEach begin="${pageMaker.startPageNo }"
				end="${pageMaker.endPageNo }" var="num">
				<li><a href="uproductsell?page=${num }">${num }</a></li>
			</c:forEach>
			<c:if test="${pageMaker.hasNext }">
				<li><a href="uproductsell?page=${pageMaker.endPageNo + 1 }">다음</a></li>
			</c:if>

		</ul>
		
		
	</section>

	<%@ include file="../footer.jspf"%>
</body>


</html>