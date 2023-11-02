<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<style type="text/css">
table, th, td {
	border-style: solid;
	border-width: 1px;
	text-align: center;
}

ul {
	list-style-type: none;
	text-align: center;
}

li {
	display: inline-block;
	text-align: center;
}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"
	integrity="sha256-eKhayi8LEQwp4NKxN+CfCh+3qOVUtJn3QNZ0TciWLP4="
	crossorigin="anonymous">
</script>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>구트 쇼핑몰</title>
<!-- Favicon-->
<link rel="icon" type="image/x-icon" href="photos/favicon.ico" />
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<script type="text/javascript"
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />
<!-- jQuery library -->
<!-- Popper Js -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>

</head>
<body>
	<!-- Navigation-->
	<!-- header1 -->
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<a class="navbar-brand" href="/calla/uProduct/list">Goot mall</a>
			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
					<li class="nav-item"></li>
					<li class="nav-item"><a class="nav-link" href="#!">About</a></li>

					<li class="nav-item dropdown"><a
						class="nav-link dropdown-toggle" id="navbarDropdown" href="#"
						role="button" data-bs-toggle="dropdown" aria-expanded="false">Shop</a>
						<ul class="dropdown-menu" aria-labelledby="navbarDropdown">
							<li><a class="dropdown-item" href="#!">All Products</a></li>
							<li><hr class="dropdown-divider" /></li>
							<li><a class="dropdown-item" href="#!">Popular Items</a></li>
							<li><a class="dropdown-item" href="#!">New Arrivals</a></li>
						</ul></li>
					<input type="text" id="keyword" class="head_input"
						value="${keyword }" placeholder="동네 이름, 물품명 등을 검색해보세요 !" />
					<button class="btn btn-outline-secondary" type="button" value="확인1"
						id="button-addon2">
						<i class="fa-solid fa-magnifying-glass"></i>
					</button>

				</ul>
				<!-- <form class="d-flex"> -->


				<!-- 로그인 전 -->
				<c:if test="${SessionScope.id==null}">
					<!-- Button trigger modal -->
					<c:if test="${empty memberNickname }">
						<a href="/calla/member/login">로그인</a>
					</c:if>
					<c:if test="${not empty memberNickname }">
						<a href="/calla/member/login">로그아웃</a>&nbsp;&nbsp;
							<a href="/calla/uProduct/register">물건등록</a>
					</c:if>

				</c:if>
				<!-- 		</form> -->
			</div>
		</div>
	</nav>

	<form action="map" method="GET">
		<select id="Address" name="Address">
			<option value="searchAddress" selected>주소</option>
		</select> <input type="text" name="keyword" value="${keyword }"> <input
			type="submit" value="검색">

	</form>


	<section class="py-5" style="width: 50%; height: 750px; float: left;">
		<div class="container px-4 px-lg-5 mt-5">
			<div
				class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">

				<c:forEach var="vo" items="${list }">
					<input type="hidden" name="memberAddress" value="${vo.memberAddress }">
					<div class="col mb-5">
						<div class="card h-100">
							<!-- Product image-->
							<img class="card-img-top"
								src="display?fileName=${vo.uProductImagePath}" alt="..." />
							<!-- Product details-->
							<div class="card-body p-4">
								<div class="text-center">
									<!-- Product name-->
									<h5 class="fw-bolder">

										<a
											href="detail?uProductId=${vo.uProductId }&page=${pageMaker.criteria.page}">${vo.uProductName }</a>

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
									${vo.uProductPrice }

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

		<ul>
			<c:if test="${pageMaker.hasPrev }">
				<li><a href="map?page=${pageMaker.startPageNo - 1 }">이전</a></li>
			</c:if>

			<c:forEach begin="${pageMaker.startPageNo }"
				end="${pageMaker.endPageNo }" var="num">
				<li><a
					href="map?page=${num }&Address=${Address}&keyword=${keyword}">${num }</a></li>
			</c:forEach>

			<c:if test="${pageMaker.hasNext }">
				<li><a href="map?page=${pageMaker.endPageNo + 1 }">다음</a></li>
			</c:if>
		</ul>

	</section>



	<div id="map" style="width: 50%; height: 750px; float: right;"></div>

	<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=d1bf5eface4b1d2d29fa03fe32944641&libraries=services"></script>
	<script>
// 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
var infowindow = new kakao.maps.InfoWindow({zIndex:1});

var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
        level: 5 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다



var geocoder = new kakao.maps.services.Geocoder();

var memberAddresses = [];
var places = [];

<c:forEach var="vo" items="${list}">
  var address = "${vo.memberAddress}";
  var place = "${vo.uProductName}"; 
  places.push(place); 
  memberAddresses.push(address);
</c:forEach>


console.log(memberAddresses);


for (var i = 0; i < memberAddresses.length; i++) {
    geocoder.addressSearch(memberAddresses[i], createMarkerCallback(i)); // {vo.memberAddress}에 담겨있는 주소로 search
}

function createMarkerCallback(index) { // 검색시 
    return function (result, status) {
        if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);  //결과값으로 위치를 마커로 표시합니다 
            var marker = new kakao.maps.Marker({
                map: map,
                position: coords,
            });

            var infowindow = new kakao.maps.InfoWindow({
                content: places[index], // infowwindow 이름
            });

            kakao.maps.event.addListener(marker, 'click', function () { // 마커를 클릭하면 정보창 열기 
                infowindow.open(map, marker);
            });

            map.setCenter(coords);  // 마커를 지도 중심으로 설정
        }
    };
}



</script>
</body>
</html>