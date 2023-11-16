<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<%@ include file="header.jspf"%>
<title>Calla</title>
</head>
<body>

<!-- 소켓 -->
<ul id="alarmUL">ul</ul>
<i id="alarmI">i</i>
<div id="alarmDiv">div</div>
<input type="hidden" id="socketuserId" value=${memberId }>
<script type="text/javascript">
	const alarmUL = document.querySelector("#alarmUL");
	const alarmI = document.querySelector("#alarmI");
	const alarmDiv = document.querySelector("#alarmDiv");
	var sock = null;
	
	$(document).ready(function(){
		connectWs();
	
	});
	
	//소켓
	function connectWs(){
		var ws = new SockJS("http://localhost:8080/calla/echo");
		sock = ws;
	
		ws.onopen = function() {
			console.log("연결완료");
	 		ws.send($('#socketuserID').val());
		};
	
		ws.onmessage = function(event) {
			/* 받을 알람이 있을 때 */
			console.log(event.data);
			if(event.data.length>0){
				let newAlarm = '';
				newAlarm += '<li scope="col">' + event.data + "</li>"
				$('#alarmUL').append(newAlarm);
				alarmDiv.style.visibility = "visible";
			}
		};
	
		ws.onclose = function(event) {
		    console.log('WebSocket 연결이 닫혔습니다.');
		    console.log('코드: ' + event.code + ', 이유: ' + event.reason);
		};
	
	};
	
	/* 알람창 추가 */
	
	alarmI.addEventListener('click', function(){
		alarmUL.classList.toggle('visible');
		$(this).stop(false, false);
	});
	
	alarmUL.addEventListener('click', function(e){
		var endIdx = e.target.textContent.indexOf(")");
		var idx = e.target.textContent.substr(1, endIdx-1);
	
		$.ajax({
			url : '/alarmDel',
			data : {"idx" : idx},
			type : 'post',
			success : function(data){
				console.log(data);
				alert("성공");
			}
		})
		
		$(e.target).remove();
		if(alarmUL.children.length == 0){
			alarmDiv.style.visibility = "hidden";
		}
		
	})

</script>

<!-- 소켓 -->














	<div class="container-fluid">
		<div class="row">
			<%@ include file="sidebar2.jspf"%>
			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
				<c:if test="${not empty memberNickname }">
					<h5>${memberNickname }님 환영합니다!</h5>
					<hr>
					회원님을 위한 추천 상품 리스트 ↓ ↓ ↓
				</c:if>
				<div class="container">
					<h1 class="display-4">공용상품</h1>
				</div>
				<section class="py-5">
					<div class="container px-4 px-lg-5 mt-5">
						<div
							class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
							<input type="hidden" id="test" value='${lists}'>
							<c:forEach var="ProductVO" items="${lists.productList }">
								<div class="col mb-5">
									<div class="card h-100">
										<%-- <img class="card-img-top" src="fBoard/display?fileName=${ProductVO.productImagePath}" width="200px" height="150px" alt="..." /> --%>
										<div class="card-body p-4">
											<div class="text-center">
												<h5 class="fw-bolder">
													<a href="product/detail?productId=${ProductVO.productId }&memberId=${memberId}">${ProductVO.productName }</a>
												</h5>
												<div>
													<h6>${ProductVO.productCategori }</h6>
												</div>
												<div
													class="d-flex justify-content-center small text-warning mb-2">
													좋아요 : ${ProductVO.productLikes } 조회수 :
													${ProductVO.productViews }</div>
												가격 : ${ProductVO.productPrice }
											</div>
										</div>
										<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
											<div class="text-center">
												<a class="btn btn-outline-dark mt-auto"
													href="product/detail?productId=${ProductVO.productId }&memberId=${memberId}">상품 보기</a>
											</div>
										</div>
									</div>
								</div>

							</c:forEach>
						</div>
					</div>
				</section>

				<div class="container">
					<h1 class="display-4">중고상품</h1>
				</div>

				<section class="py-5">
					<div class="container px-4 px-lg-5 mt-5">
						<div
							class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

							<c:forEach var="uProductVO" items="${lists.uProductList }">

								<div class="col mb-5">
									<div class="card h-100">
										<!-- Product image-->
										<img class="card-img-top"
											<%-- src="fBoard/display?fileName=${uProductVO.uProductImagePath}" --%>
											alt="..." />
										<!-- Product details-->
										<div class="card-body p-4">
											<div class="text-center">
												<!-- Product name-->
												<h5 class="fw-bolder">

													<a href="uProduct/detail?uProductId=${uProductVO.uProductId }">${uProductVO.uProductName }</a>

												</h5>
												<!-- Product reviews-->
												<div
													class="d-flex justify-content-center small text-warning mb-2">
													<div class="bi-star-fill"></div>
													<div class="bi-star-fill"></div>
													<div class="bi-star-fill"></div>
													<div class="bi-star-fill"></div>
													<div class="bi-star-fill"></div>
												</div>
												<!-- Product price-->
												${uProductVO.uProductPrice }

											</div>
										</div>
										<!-- Product actions-->
										<div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
											<div class="text-center">
												<a class="btn btn-outline-dark mt-auto" href="#">Add to
													cart</a>
											</div>
										</div>
									</div>
								</div>


							</c:forEach>

						</div>
					</div>
				</section>
			</main>
			<%-- <%@ include file="sidebarRight.jspf"%> --%>
		</div>
	</div>
	<script type="text/javascript">
		// end document.ready
	</script>
	<%@ include file="footer.jspf"%>
</body>
</html>
