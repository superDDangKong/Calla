<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<title>아이디 찾기</title>
</head>
<body>
	<form action="searchId" method="post">
		이름 : <input type="text" name="memberName" required>
		이메일 : <input type="text" name="memberEmail" required>
		<input type="submit" value="아이디 찾기">
	</form>
	
	
	<input type="hidden" name="searchResult" id="searchResult" value="${searchResult }">
	<input type="hidden" name="searchId" id="searchId" value="${searchId }">
	<script type="text/javascript">
		var searchResult = $('#searchResult').val();
		var searchId = $('#searchId').val();
		if (searchResult == 'fail'){
			alert('일치하는 회원 정보가 없습니다.')
		}

	</script>
</body>
</html>