<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://code.jquery.com/jquery-3.7.1.slim.js"
	integrity="sha256-UgvvN8vBkgO0luPSUl2s8TIlOSYRoGFAX4jlCIm9Adc="
	crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
ul {
	list-style-type: none;
}

li {
	display: inline-block;
}
</style>
<meta charset="UTF-8">
<title>List</title>
<link rel="stylesheet" href="../resources/css/main.css">
</head>
<body>
	<div id="login">
		<input type="hidden" id="memberNickname" value=${memberNickname }>
	</div>

	<div id="product"></div>
	<div class="wrapper">
		<div class="wrap">
			<div class="top_gnb_area">

				<h1></h1>

				<a href="register"><input type="button" value="내 물건 등록"></a>
				<a href="list"><input type="button" value="상품 목록"></a>

			</div>
			<div class="top_area">
				<div class="logo_area">
					<h1>logo area</h1>
				</div>
				<div class="search_area">
					<h1>Search area</h1>
				</div>
				<div class="login_area">
					<div class="login_button">
					<c:if test="${empty memberNickname }">
						<a href="/calla/member/login">로그인</a>
					</c:if>
					<c:if test="${not empty memberNickname }">
						<a href="/calla/member/login">로그아웃</a>
					</c:if>
					</div>
					<span><a href="/calla/member/join">회원가입</a></span>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="navi_bar_area">
				<div class="dropdown">
			    <button class="dropbtn">공용구매 
			      <i class="fa fa-caret-down"></i>
			    </button>
			    <div class="dropdown-content">
			    	<a href="/calla/product/list">전체품목</a>
			  	  	<a href="#">테스트1</a>
			    	<a href="#">테스트2</a>
			    	<a href="#">테스트3</a>
			    	<a href="#">테스트4</a>
			    	<a href="#">테스트5</a>
			    	<a href="#">테스트6</a>	      		      		      
			    </div>			
			</div>
			<div class="dropdown">
			    <button class="dropbtn">중고구매 
			      <i class="fa fa-caret-down"></i>
			    </button>
			    <div class="dropdown-content">
			    	<a href="#">테스트1</a>
			    	<a href="#">테스트2</a>
			    	<a href="#">테스트3</a>
			    	<a href="#">테스트4</a>
			    	<a href="#">테스트5</a>
			    	<a href="#">테스트6</a>	      		      		      
			    </div>			
			</div>
			</div>
			<div class="content_area">
				<hr>
				<table>
					<thead>
						<tr>
							<th>번호</th>
							<th>이미지</th>
							<th>이름</th>
							<th>가격</th>
							<th>카테고리</th>
							<th>작성일</th>
							<th>리뷰수</th>
							<th>좋아요수</th>
							<th>조회수</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="vo" items="${list }">
							<tr>
								<td>${vo.uProductId }</td>
								<td>
									<div>
										<img src="display?fileName=${vo.uProductImagePath}"
											width="100px" height="100px">
									</div>
								</td>
								<td><a
									href="detail?uProductId=${vo.uProductId }&page=${pageMaker.criteria.page}">${vo.uProductName }</a></td>
								<td>${vo.uProductPrice }</td>
								<td>${vo.uProductCategori }</td>
								<fmt:formatDate value="${vo.uProductCreatedDate }"
									pattern="yyyy-MM-dd HH:mm:ss" var="uProductCreatedDate" />
								<td>${uProductCreatedDate }</td>
								<td>${vo.uProductContent }</td>
								<td>${vo.uProductLikes }</td>
								<td>${vo.uProductViews }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
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

				<input type="hidden" id="insertAlert" value="${insert_result }">
			</div>
		</div>
	</div>
</html>