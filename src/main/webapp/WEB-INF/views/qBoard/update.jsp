<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${vo.qBoardTitle }</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="../header.jspf" %>
<style type="text/css">
.title {
      width: 100%;
      padding: 10px;
      margin: 8px 0;
      box-sizing: border-box;
      border: 1px solid #ccc;
      border-radius: 4px;
      outline: none;
      transition: border-color 0.3s;
    }

    /* 텍스트 입력란 호버 효과 */
    .title:hover {
      border-color: #007bff; /* 호버 시 테두리 색상 변경 */
    }

    /* 텍스트 입력란 포커스 효과 */
    .title:focus {
      border-color: #007bff; /* 포커스 시 테두리 색상 변경 */
      box-shadow: 0 0 5px rgba(0, 123, 255, 0.5); /* 포커스 시 그림자 효과 추가 */
    }
    
    .content {
      width: 100%;
      padding: 10px;
      margin: 8px 0;
      box-sizing: border-box;
      border: 1px solid #ccc;
      border-radius: 4px;
      outline: none;
      transition: border-color 0.3s;
      resize: vertical; /* 수직 리사이즈 허용 */
    }

    /* 텍스트 입력란 호버 효과 */
    .content:hover {
      border-color: #007bff; /* 호버 시 테두리 색상 변경 */
    }

    /* 텍스트 입력란 포커스 효과 */
    .content:focus {
      border-color: #007bff; /* 포커스 시 테두리 색상 변경 */
      box-shadow: 0 0 5px rgba(0, 123, 255, 0.5); /* 포커스 시 그림자 효과 추가 */
    }
    
    /* 버튼 스타일 */
    .insert {
      padding: 10px 20px;
      font-size: 16px;
      cursor: pointer;
      border: none;
      border-radius: 4px;
      color: #fff; /* 버튼 텍스트 색상 */
      background-color: #28a745; /* 버튼 배경색 */
      transition: background-color 0.3s, color 0.3s, border-color 0.3s;
    }

    /* 호버 효과 */
    .insert:hover {
      background-color: #218838; /* 호버 시 배경색 변경 */
    }
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="../sidebar2.jspf"%>
			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
			
				<div class="container">
				<h2>글 수정 페이지</h2>
				<form action="update" method="post">
					<input type="hidden" name="page" value= "${page }">
					<div>
						<input type="hidden" name="qBoardId" value="${vo.qBoardId }" readonly> 
					</div>
					<div>
						<p>제목 : <input type="text" class="title" name="qBoardTitle" value="${vo.qBoardTitle }"></p>
					</div>
					<div>
						<c:set var="qBoardCreatedDate" value="${vo.qBoardCreatedDate}" />
						<fmt:formatDate value="${qBoardCreatedDate}" pattern="yyyy-MM-dd HH:mm:ss" var="formattedDate" />		
						<p>작성자 : ${vo.memberNickname }</p>
						<p>작성일 : ${formattedDate}</p>
					</div>
					<div>
						<textarea class="content" rows="20" cols="120" name="qBoardContent">${vo.qBoardContent }</textarea>
					</div>
					<div>
						<input type="submit" class="insert" value="등록">
						<br>
						<br>
						<br>
					</div>
				</form>
				</div>
			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>
	<%@ include file="../footer.jspf"%>
</body>
</html>