<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보 수정</title>
</head>
<body>
<h2>글 수정 페이지</h2>
	<form action="update" method="POST">
			<p>회원 ID : ${vo.memberId }</p>
			<p>회원 등급 : ${vo.memberLevel } </p>
			<input type="hidden" name="memberId" value="${vo.memberId }">
			${vo.memberPw }
			<input type="password" name="memberPw"> <br>
			${vo.memberName }
			<input type="text" name="memberName"> <br>
			${vo.memberEmail }
			<input type="text" name="memberEmail"> <br>
			${vo.memberPhone }
			<input type="text" name="memberPhone"> <br>
			${vo.memberInterest }
			<input type="text" name="memberInterest"> <br>
			${vo.memberAddress }
			<input type="text" name="memberAddress"> <br>
			${vo.memberNickname }
			<input type="text" name="memberNickname"> <br>
	
			<input type="submit" value="수정">
	</form>

</body>
</html>