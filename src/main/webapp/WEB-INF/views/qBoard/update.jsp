<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${vo.qBoardTitle }</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>

	<%@ include file="../header.jspf" %>
	<div class="col-md-3">
<!-- 사이드 바 메뉴-->
  <!-- 패널 타이틀1 -->
<div class="panel panel-info">
    <div class="panel-heading">
      <h3 class="panel-title">Panel Title</h3>
    </div>
    <!-- 사이드바 메뉴목록1 -->
    <ul class="list-group">
      <li class="list-group-item"><a href="#">HTML</a></li>
      <li class="list-group-item"><a href="#">CSS</a></li>
      <li class="list-group-item"><a href="#">ECMAScript5</a></li>
    </ul>
</div>
  <!-- 패널 타이틀2 -->
<div class="panel panel-default">
  <div class="panel-heading">
    <h3 class="panel-title">Panel Title</h3>
  </div>
    <!-- 사이드바 메뉴목록2 -->
      <ul class="list-group">
        <li class="list-group-item"><a href="#">jQuery</a></li>
        <li class="list-group-item"><a href="#">BootStrap</a></li>
      </ul>
</div>      
  <!-- 패널 타이틀3 -->
<div class="panel panel-info">
  <div class="panel-heading">
    <h3 class="panel-title">Panel Title</h3>
  </div>
      <!-- 사이드바 메뉴목록3 -->
      <ul class="list-group">
        <li class="list-group-item"><a href="#">About</a></li>
        <li class="list-group-item"><a href="#">Help</a></li>
      </ul>
    </div>
</div> 

	<div class="container">
	<h2>글 수정 페이지</h2>
	<form action="update" method="post">
		<input type="hidden" name="page" value= "${page }">
		<div>
			<p>글 번호 : ${vo.qBoardId }</p>
			<input type="hidden" name="qBoardId" value="${vo.qBoardId }" readonly> 
		</div>
		<div>
			<p>제목 : </p>
			<input type="text" name="qBoardTitle" value="${vo.qBoardTitle }">
		</div>
		<div>
			<p>작성자 : ${vo.memberNickname }</p>
			<p>작성일 : ${vo.qBoardCreatedDate }</p>
		</div>
		<div>
			<textarea rows="20" cols="120" name="qBoardContent">${vo.qBoardContent }</textarea>
		</div>
		<div>
			<input type="submit" value="등록">
		</div>
	</form>
	</div>
	<%@ include file="../footer.jspf"%>
</body>
</html>