<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="../header.jspf" %> 
<title>로그인</title>
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title text-center">로그인</h4>
                        <form action="login" method="POST">
                            <div class="form-group">
                                <label for="memberId">아이디:</label>
                                <input type="text" id="memberId" name="memberId" value="test" class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="memberPw">비밀번호:</label>
                                <input type="password" id="memberPw" name="memberPw" value="test" class="form-control">
                            </div>
                            <button type="submit" class="btn btn-primary btn-block">로그인</button>
                            <c:if test="${not empty param.targetURL}">
                                <input type="hidden" id="targetURL" name="targetURL" value="${param.targetURL}">
                            </c:if>
                        </form>
                        <div class="text-center mt-3">
                            <a href="searchId">아이디 찾기</a> | <a href="searchPw">비밀번호 찾기</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

	<input type="hidden" name="searchResult" id="searchResult" value="${searchResult }">
	<input type="hidden" name="searchId" id="searchId" value="${searchId }">
	<input type="hidden" name="searchPw" id="searchPw" value="${searchPw }">
	
	
	<script type="text/javascript">
		var searchResult = $('#searchResult').val();
		var searchId = $('#searchId').val();
		var searchPw = $('#searchPw').val();
		console.log('searchResult = ' + searchResult);
		console.log('searchId = ' + searchId);
		console.log('searchPw = ' + searchPw);
		if(searchResult == 'idSearch') {
			alert('회원님의 아이디는   ' + searchId + '    입니다');
		} else if(searchResult == 'pwSearch') {
			alert('회원님의 비밀번호는   ' + searchPw + '    입니다');
		}
		
	</script>
<%@ include file="../footer.jspf" %> 
</body>
</html>