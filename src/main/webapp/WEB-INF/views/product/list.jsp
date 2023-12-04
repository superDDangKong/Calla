<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<script src="https://code.jquery.com/jquery-3.7.1.slim.js"
	integrity="sha256-UgvvN8vBkgO0luPSUl2s8TIlOSYRoGFAX4jlCIm9Adc="
	crossorigin="anonymous"></script>
<head>
<style type="text/css">
	.rating {
        unicode-bidi: bidi-override;
        direction: rtl;
        text-align: center;
        position: relative;
    }

    .rating>span {
        display: inline-block;
        position: relative;
        width: 1.1em;
        color: #ffc107; /* 노란색 */
    }

    .rating>span:before {
        content: "\2605";
        position: absolute;
        color: #ffc107; /* 노란색 */
    }
table, th, td {
	border-style : solid;
	border-width : 1px;
	text-align : center;
}

ul {
	list-style-type : none;
	text-align: center;
}

li {
	display : inline-block;
	text-align: center;
}
</style>
<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>List</title>
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />
<%@ include file="../header.jspf"%> 	

</head>
<body>
	
	
	<h1>상품목록</h1>
	<form action="list" method="GET">
		<select id="option" name="option">
			<option value="searchTitleOrContent">제목&내용</option>
		</select> 
		
		<input type="text" name="keyword" value="${keyword }"> 
		<input type="submit" value="검색">
		<button id="home"><a href="/calla/">HOME</a></button>
	</form>
	<input type="hidden" id="selectedOption" value=${option }>
	<input type="hidden" id="sessionNickname" value=${memberNickname }>
	<input type="hidden" id="sessionLevel" value=${memberLevel }>
	
	
	<div id="register">
		<a href="register"><input type="button" value="상품등록"></a>
		<c:if test="${memberNickname != null}">
			<a href="orderList?memberId=${memberId}&productId=${vo.productId}"><input type="button" value="장바구니"></a>
			<a href="order?memberId=${memberId}"><input type="button" value="구매내역"></a>
		</c:if>
	</div>
	<hr>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="productSidebar.jspf"%>
			<main class="container col-md-8 ms-sm-auto col-lg-8 px-md-4" >
	
	<section class="py-5">
	<div class="container px-4 px-lg-5 mt-5">
		<div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
			<c:forEach var="vo" items="${list }">
				<div class="col mb-5">
					<div class="card h-100">
						<img class="card-img-top" src="display?fileName=${vo.productImagePath}" width="280px" height="150px" alt="..." />
							<div class="card-body p-4">
								<div class="text-center">
									<h5 class="fw-bolder">
										<a href="detail?productId=${vo.productId }&memberId=${memberId }&page=${pageMaker.criteria.page}">${vo.productName }</a>
									</h5>
										<div>										
											<h6>
												${vo.productCategori } 
											</h6>
										</div>
									
									<div>
										<p></p>
									</div>
									<div class="d-flex justify-content-center small text-warning mb-2">
										좋아요 : ${vo.productLikes } 조회수 : ${vo.productViews } <br> 
										댓글수 : ${vo.productCommentCount } 
									</div>
									<div>
										<c:set var="averageRated" value="${(vo.productCommentCount == 0) ? 0 : (1.0 * vo.productRatedCount) / vo.productCommentCount}"/>
										 만족도:
										    <c:forEach begin="1" end="${averageRated}" varStatus="loop">
										        ⭐
										    </c:forEach>
									</div>
									<br>
				                    <div>
										가격 : ${vo.productPrice } 원
				                    </div>
								</div>
							</div>
							<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
								<div class="text-center">
									<a class="btn btn-outline-dark mt-auto" href="detail?productId=${vo.productId }&memberId=${memberId }&page=${pageMaker.criteria.page}">상품 보기</a>
								</div>
							</div>
					</div>
				</div>
				
			</c:forEach>
		</div>
	</div>
	</section>
	</main>
			
		</div>
</div>
	
	<ul>
		<c:if test="${pageMaker.hasPrev }">
			<li><a href="list?page=${pageMaker.startPageNo - 1 }">이전</a></li>
		</c:if>
		<c:forEach begin="${pageMaker.startPageNo }"
			end="${pageMaker.endPageNo }" var="num">
			<li><a href="list?page=${num }">${num }</a></li>
		</c:forEach>
		<c:if test="${pageMaker.hasNext }">
			<li><a href="list?page=${pageMaker.endPageNo + 1 }">다음</a></li>
		</c:if>
	</ul>
</section>
	
	
	<script type="text/javascript">
		$(document).ready(function(){
			if($("#selectedOption").val() != ""){
				$("#option").val($("#selectedOption").val());
			}
			
			if(!($('#sessionLevel').val() >= 2)) {
				var list = 
					"상품등록은 관리자기능입니다."
					+ "&nbsp&nbsp"
				$("#register").html(list);
			}
			
		}) // end document.ready()
	</script>
	
	<input type="hidden" id="insertAlert" value="${insert_result }">
	
	<script type="text/javascript">
		var result = $('#insertAlert').val();
		if(result == 'success'){
			alert('새 상품 등록 완료');
		}
		
		
	</script>
</body>
</html>