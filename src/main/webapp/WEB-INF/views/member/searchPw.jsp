<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>비밀번호 찾기</title>
</head>
<body>
	<form action="searchPw" method="post">
	아이디 : <input type="text" name="memberId" required>
	휴대번호 : <input type="text" name="memberPhone" required>
	<input type="submit" value="비밀번호 찾기">
	
	</form>
	
	<input type="hidden" name="searchResult" id="searchResult" value="${searchResult }">
	<input type="hidden" name="searchPw" id="searchPw" value="${searchPw }">
	<script type="text/javascript">
		var searchResult = $('#searchResult').val();
		var searchPw = $('#searchPw').val();
		console.log('searchResult = ' + searchResult);
		console.log('searchPw = ' + searchPw);
		if (searchResult == 'fail'){
			alert('일치하는 회원 정보가 없습니다.')
		}
	</script>
<%@ include file="../footer.jspf" %> 
</body>
</html>