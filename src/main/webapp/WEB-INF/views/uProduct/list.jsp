<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
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

img {
 image-rendering: -webkit-optimize-contrast;
 transform: translateZ(0);
 backface-visibility: hidden;
}



</style>


<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<script src="https://kit.fontawesome.com/ef717dbcd3.js" crossorigin="anonymous"></script>
   <link rel="stylesheet" href="../resources/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../resources/assets/css/LineIcons.3.0.css" />
    <link rel="stylesheet" href="../resources/assets/css/tiny-slider.css" />
    <link rel="stylesheet" href="../resources/assets/css/glightbox.min.css" />
    <link rel="stylesheet" href="../resources/assets/css/main.css" />
<title>메인페이지</title>
<!-- Bootstrap icons-->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
 <link rel="shortcut icon" type="image/x-icon" href="../resources/assets/images/favicon.svg" />
<link href="../resources/css/styles.css" rel="stylesheet" />



<%@ include file="../header.jspf" %> 
</head>
<body>

	<input type="hidden" id="selectedName" value=${name }>
	<input type="hidden" id="sessionCategori" value=${uProductCategori }>

	<form action="list" method="GET">
		<select id="name" name="name">
			<option value="searchName" selected>전체</option>
			<option value="searchDate">신상품</option>
			<option value="searchlike">좋아요순</option>
		</select>
			
			 <input type="text" name="keyword" value="${keyword }"> <input
			type="submit" value="검색">
			
			<c:if test="${not empty sessionScope.memberNickname}">
			<a href="register"><input type="button" value="상품등록"></a>
			</c:if>
			
	</form>
	
		
	
	
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container px-4 px-lg-5">
			<div class="collapse navbar-collapse" id="navbarSupportedContent">

				<div>

					<ul class="navbar-nav me-auto mb-2 mb-lg-0 ms-lg-4">
						<li class="nav-item"></li>
						<li class="nav-item"><a class="nav-link" 
							href="/calla/uProduct/list">전체상품</a></li>
						<li class="nav-item"><a class="nav-link" 
							href="/calla/uProduct/list?name=searchDate&keyword=">신상품</a></li>
						<li class="nav-item"><a class="nav-link" 
							href="/calla/uProduct/list?name=searchName&keyword=지브리">지브리</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/calla/uProduct/list?name=searchName&keyword=디즈니">디즈니 </a></li>
						<li class="nav-item"><a class="nav-link" 
							href="/calla/uProduct/list?name=searchName&keyword=마블">마블</a></li>
						<li class="nav-item"><a class="nav-link" 
							href="/calla/uProduct/map">매물지도</a></li>

					</ul>
					
				</div>

			</div>
		</div>
	</nav>
	<!-- Section-->
	 <section class="trending-product section" style="margin-top: 12px;">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12">
                    <div class="section-title">
                        <h2>중고 상품</h2>
                        <h3 style ="font-family: Century Gothic">Welcome Gootmall!</h3>
                        
                    </div>
                </div>
            </div>
            
            <div class="row" >
              
              
              <c:forEach var="vo" items="${list }">
                <div class="col-lg-3 col-md-6 col-12" >
                    <!-- Start Single Product -->
                    <div class="single-product" >
                        <div class="product-image">
                            <img src="display?fileName=${vo.uProductImagePath}" width="250px" height="250px" alt="#">
                            <div class="button">
                                <a href="detail?uProductId=${vo.uProductId }&page=${pageMaker.criteria.page}" class="btn"><i class="lni lni-cart"></i> 상품보기</a>
                            </div>
                        </div>
                        <div class="product-info">
                            <span class="category">${vo.uProductCategori}</span>
                            
                            
                            <c:if test="${(vo.uProductStatement) eq '거래가능'}">
   		 					<span class="category" style="float:right; color:lightgreen;" >${vo.uProductStatement }</span>
   		 					
							</c:if>
							
							<c:if test="${(vo.uProductStatement) eq '예약중'}">
   		 					<span class="category" style="float:right;" >${vo.uProductStatement }</span>
   		 					
							</c:if>
                            
                            <h4 class="title">
                                <a href="detail?uProductId=${vo.uProductId }&page=${pageMaker.criteria.page}">${vo.uProductName }</a>
                            </h4>
                            
                            <br>
                            
                               <span style="color:red;">
									<i class="fa-solid fa-heart fa-sm"></i>
									</span>
									
									<span>
									${vo.uProductLikes } &nbsp;
									</span>
									
						
									<span >
									<i class="fa-regular fa-comment"></i>
									</span>
									
									<span>
									${vo.uProductCommentCount }
									</span>
                            
                            <div class="price">
                                <span>$${vo.uProductPrice }</span>
                            </div>
                            
                            
                        </div>
                    </div>
                    <!-- End Single Product -->
                </div>
               </c:forEach>
               
            </div>
        </div>
        
        <br>
        <br>
        <br>
        
        <ul>
			<c:if test="${pageMaker.hasPrev }">
				<li><a href="list?page=${pageMaker.startPageNo - 1 }">이전</a></li>
			</c:if>

			 <c:if test="${keyword == ''}">
			 
				<c:forEach begin="${pageMaker.startPageNo }"
				end="${pageMaker.endPageNo }" var="num">
				<li><a href="list?page=${num }&name=${name}&keyword=">${num }</a></li>
				</c:forEach>

			</c:if>
			
			 <c:if test="${keyword != ''}">
			 
			<c:forEach begin="${pageMaker.startPageNo }"
				end="${pageMaker.endPageNo }" var="num">
				<li><a href="list?page=${num }&name=${name}&keyword=${keyword}">${num }</a></li>
			</c:forEach>
			
			</c:if>
			
			<c:if test="${pageMaker.hasNext }">
				<li><a href="list?page=${pageMaker.endPageNo + 1 }">다음</a></li>
			</c:if>
		</ul>
        
    </section>
	<!-- Footer-->
	<%@ include file="footer.jsp"%>
	<!-- Bootstrap core JS-->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
	<!-- Core theme JS-->
	<script src="../resources/js/scripts.js"></script>
</body>
</html>