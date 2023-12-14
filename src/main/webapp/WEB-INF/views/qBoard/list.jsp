<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
table, th, td {
	border-style : solid;
	border-width : 1px;
	text-align : center;
}

/* 추가된 CSS 스타일링 */


/* .container {
	display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.table {
	border-collapse: collapse;
    width: 50%;
}

.th, .td {
    border: 1px solid #ddd;
    padding: 8px;
    text-align: left;
} */
ul {
	list-style-type : none;
}

li {
	display : inline-block;
}

body {
	margin : 0;
	padding : 0;
}

/* 기본 스타일 */
    .write-button {
      text-decoration: none;
    }

    /* 버튼 스타일 */
    .write-button input[type="button"] {
      display: inline-block;
      padding: 10px 20px;
      font-size: 16px;
      font-weight: bold;
      text-align: center;
      text-decoration: none;
      cursor: pointer;
      border: 2px solid #3498db; /* 테두리 색상 */
      color: #3498db; /* 텍스트 색상 */
      background-color: #ffffff; /* 배경색 */
      border-radius: 5px; /* 테두리 둥글기 */
      transition: background-color 0.3s, color 0.3s, border-color 0.3s; /* 전환 효과 */
    }

    /* 호버 효과 */
    .write-button input[type="button"]:hover {
      background-color: #3498db; /* 호버 시 배경색 */
      color: #ffffff; /* 호버 시 텍스트 색상 */
      border-color: #ffffff; /* 호버 시 테두리 색상 */
    }
</style>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<title>게시판 메인 페이지</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<%@ include file="../header.jspf" %>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="../sidebar2.jspf"%>
			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
				<div class="container">
					<br>
					<input type="hidden" id="sessionNickname" value=${memberNickname }>
					<h1 class="text-center">
						<a href="list" style="text-decoration: none; color: #007BFF; font-size: 36px; font-weight: bold;">문의게시판 </a>
					</h1>
					
					<a href="register" class="write-button"><input type="button" value="글 작성"></a>
					<hr>
					<%-- <div class="container mt-4">
					    <form action="list" method="GET">
					        <div class="row">
					            <div class="col-md-4">
					                <div class="form-group">
					                    <label for="option"></label>
					                    <select id="option" name="option" class="form-control">
					                        <option value="searchMemberNickname" selected>작성자</option>
					                        <option value="searchTitle">제목</option>
					                    </select>
					                </div>
					            </div> 
					            <div class="col-md-4">
					                <div class="form-group">
					                    <label for="keyword"></label>
					                    <input type="text" id="keyword" name="keyword" class="form-control" value="${keyword}" placeholder="검색어를 입력해주세요">
					                </div>
					            </div>
					            <div class="col-md-4">
					                <div class="form-group">
					                    <label>&nbsp;</label>
					                    <button type="submit" class="btn btn-primary btn-block">검색</button>
					                </div>
					            </div>
					        </div>
					    </form>
					</div> --%>
					<div>
						<table class="table">
							<thead>
								<tr>
									<th class="th" style="width : 80px">번호</th>
									<th class="th" style="width : 700px">제목</th>
									<th class="th" style="width : 120px">작성자</th>
									<th class="th" style="width : 150px">작성일</th>
									<th class="th" style="width : 100px">댓글수</th>
									<th class="th" style="width : 100px">조회수</th>
								</tr>
							</thead>
							<tbody>
								<c:set var="memberNickname" value="${memberNickname }"/> 
								<c:forEach var="vo" items="${list }">
									<tr>
										<td class="td">${vo.qBoardId }</td>
										<td class="td">
											<a class="title" href="detail?qBoardId=${vo.qBoardId }&page=${pageMaker.criteria.page}">${vo.qBoardTitle }</a>
										</td>
										<td class="td">${vo.memberNickname }</td>
										<fmt:formatDate value="${vo.qBoardCreatedDate }"
										pattern="yyyy-MM-dd" var="qBoardCreatedDate"/>
										<td class="td">${qBoardCreatedDate }</td>
										<td class="td">${vo.qBoardCommentCount }</td>
										<td class="td">${vo.qBoardViews }</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						
						<ul style="text-align:center">
							<c:if test="${pageMaker.hasPrev }"><!-- 현재 페이지보다 이전 페이지가 있으면  -->
								<li><a href="list?page=${pageMaker.startPageNo - 1 }">이전</a></li> <!-- 이전 버튼이 생성 -->
							</c:if>
							<c:forEach begin="${pageMaker.startPageNo }" end="${pageMaker.endPageNo }" var="num"> <!-- startPageNo 시작페이지 ~ endPageNo 끝페이지 각-->
								<li><a href="list?page=${num }">${num }</a></li>	
							</c:forEach>
							<c:if test="${pageMaker.hasNext }">
								<li><a href="list?page=${pageMaker.endPageNo + 1 }">다음</a></li>
							</c:if>
						</ul>
						<!-- BoardController -> registerPOST()에서 보낸 데이터 저장 -->
						<input type="hidden" id="insertAlert" value="${insert_result }">
						<input type="hidden" id="statusAlert" value="${status_result }">
						<input type="hidden" id="updateResult" value="${update_result }">
						<input type="hidden" id="deleteResult" value="${delete_result }">
					</div>
				</div>
			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>

	
<script type="text/javascript">
	$(document).ready(function(){
		
		if ($('#sessionNickname').val() == "") {
			var list = "게시글 작성은 <a href='/calla/member/login?targetURL=/fBoard/register'>로그인</a>이 필요합니다."
			$("#register").html(list);
		}
		var result = $('#insertAlert').val();
		if(result == 'success') {
			alert('새 글 작성 성공!');
		}

		var result2 = $('#statusAlert').val();
		if(result2 == 'secret') {
			alert('비공개 게시글입니다.');
		}
		
		var result3 = $('#updateResult').val();
		if(result3 == 'success') {
			alert('게시글이 수정되었습니다.');
		}
		
		var result4 = $('#deleteResult').val();
		if(result4 == 'success'){
			alert('게시글이 삭제되었습니다.');
		}
				
	})
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
	<%@ include file="../footer.jspf"%>	
</body>
</html>















