<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style type="text/css">
table, th, td {
	border-style : solid;
	border-width : 1px;
	text-align : center;
}
</style>
<title>회원 정보 수정</title>

</head>
<body>
<%@ include file="../header.jspf" %> 	
<h2>회원 정보 수정</h2>
<table style="text-align: left;">
	<tbody>
		<tr>
			<th scope="row">아이디</th>
			<td>${vo.memberId }</td>
		</tr>
		<tr>
			<th scope="row">회원 등급</th>
			<td>${vo.memberLevel }</td>
		</tr>
		<tr>
			<th scope="row">이름</th>
			<td>${vo.memberName }</td>
		</tr>
		<tr>
			<th scope="row">닉네임</th>
			<td>${vo.memberNickname }</td>
			<td><input type="text"></td>
			<td><input type="button" value="닉네임 변경"></td>
			
		</tr>
		<tr>
			<th scope="row">비밀번호</th>
			<td>
				<table style="text-align: left;">
					<tbody>
						<tr>
							<th scope="row">현재 비밀번호</th>
							<td><input type="password"></td>
						</tr>
						<tr>
							<th scope="row">새 비밀번호</th>
							<td><input type="password"></td>
						</tr>
						<tr>
							<th scope="row">비밀번호 다시입력</th>
							<td><input type="password"></td>
						</tr>												
					</tbody>
				</table>
			</td>
		</tr>
		<tr>
			<th scope="row">휴대폰 번호</th>
			<td>${vo.memberPhone }</td>
			<td><input type="text"></td>
			<td><input type="button" value="휴대폰 번호 변경"></td>
		</tr>
		<tr>
			<th scope="row">이메일</th>
			<td>${vo.memberEmail }</td>
			<td><input type="text"></td>
			<td><input type="button" value="이메일 변경"></td>
		</tr>
		<tr>
			<th scope="row">관심사</th>
			<td>${vo.memberInterest }</td>
			<td><input type="checkbox">굿즈</td>
			<td><input type="button" value="관심사 변경"></td>
		</tr>

		<tr>
			<th scope="row">주소</th>
			<td>${vo.memberAddress }</td>
			<td><input type="text"></td>
			<td><input type="button" value="주소 변경"></td>
		</tr>
	</tbody>
</table>

</body>
</html>