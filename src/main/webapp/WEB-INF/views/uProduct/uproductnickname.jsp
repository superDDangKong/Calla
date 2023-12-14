<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table, th, td {
	border-style: solid;
	border-width: 1px;
	text-align: center;
}

ul {
	list-style-type: none;
}

li {
	display: inline-block;
}
</style>
<meta charset="UTF-8">
<title>자유게시판</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="../resources/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../resources/assets/css/LineIcons.3.0.css" />
    <link rel="stylesheet" href="../resources/assets/css/tiny-slider.css" />
    <link rel="stylesheet" href="../resources/assets/css/glightbox.min.css" />
    <link rel="stylesheet" href="../resources/assets/css/main.css" />

<script src="https://kit.fontawesome.com/ef717dbcd3.js" crossorigin="anonymous"></script>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
 <link rel="shortcut icon" type="image/x-icon" href="../resources/assets/images/favicon.svg" />



<%@ include file="../header.jspf"%>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="../sidebar2.jspf"%>
			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
				<div class="container">
					<h4>
						<br>
						<a href="uproductnickname?memberNickname=${vo.memberNickname }&page=${pageMaker.criteria.page}"> 판매물품 </a> &nbsp;&nbsp;
						<a href="reviewboard?sellerNickname=${vo.memberNickname }&page=${pageMaker.criteria.page}"> 구매후기 </a>
						
					</h4>
					<input type="hidden" id="selectedOption" value=${option }>
					<hr>
					
					 <section class="trending-product section" style="margin-top: 12px;">
        <div class="container">
            
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
        
       
        
    </section>
					
					
					<hr>
			
					<br>
				</div>
			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>
	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							if ($("#selectedOption").val() != "") {
								$("#option").val($("#selectedOption").val());
							}

							if ($('#sessionNickname').val() == "") {
								var list = "게시글 작성은 <a href='/calla/member/login?targetURL=/fBoard/register'>로그인</a>이 필요합니다."
								$("#register").html(list);
							}
						}) // end document.ready()
	</script>

	<!-- BoardController -> registerPOST()에서 보낸 데이터 저장 -->
	<input type="hidden" id="insertAlert" value="${insert_result }">
	<script type="text/javascript">
		var result = $('#insertAlert').val();
		if (result == 'success') {
			alert('새 글 작성 성공!');
		}
	</script>
	<%@ include file="../footer.jspf"%>
</body>
</html>