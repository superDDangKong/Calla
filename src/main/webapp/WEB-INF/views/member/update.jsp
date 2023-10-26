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
			<input type="hidden" id="memberId" value=${vo.memberId }>
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
							<td><input type="password" id="currentPw"></td>
						</tr>
						<tr>
							<th scope="row">새 비밀번호</th>
							<td><input type="password" id="newPw"></td>
							<td><div id="newPwEffect"></div></td>
						</tr>
						<tr>
							<th scope="row">비밀번호 다시입력</th>
							<td><input type="password" id="newPwCheck"></td>
							<td><div id="newPwCheckEffect"></div></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="button" id="btnUpdatePw" value="비밀번호 변경" disabled></button></td>
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

<script type="text/javascript">
	$(document).ready(function(){
		var newPwFlag = false;
		var newPwCheckFlag = false;
		
		$('#newPw').on('keyup blur', (function(){
			$('#btnUpdatePw').prop("disabled", true);
			newPwFlag = false;
			var newPw = $('#newPw').val();
			var pwEffectiveness = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
			if (pwEffectiveness.test(newPw)){
				$('#newPwEffect').html("비밀번호 사용이 가능합니다.")
				newPwFlag = true;
				if(newPwFlag==true && newPwCheckFlag==true){
					$('#btnUpdatePw').prop("disabled", false);
				}
			} else {
				$('#newPwEffect').html("영문, 숫자, 특수기호(!@#$%^&*) 모두 조합하여 8글자 이상.")
			}
		})) // end newPw.keyup

		$('#newPwCheck').on('keyup blur', (function(){
			$('#btnUpdatePw').prop("disabled", true);
			newPwCheckFlag = false;
			var newPw = $('#newPw').val();
			var newPwCheck = $('#newPwCheck').val();
			if (newPw == newPwCheck){
				$('#newPwCheckEffect').html("비밀번호가 일치합니다.")
				newPwCheckFlag = true;
				if(newPwFlag==true && newPwCheckFlag==true){
					$('#btnUpdatePw').prop("disabled", false);
				}
			} else {
				$('#newPwCheckEffect').html("비밀번호가 일치하지 않습니다.")
			}
		})) // end newPw.keyup
		
		$('#btnUpdatePw').click(function(){
			var currentPw = $('#currentPw').val();
			var newPw = $('#newPw').val();
			var newPwCheck = $('#newPwCheck').val();
			var memberId=$('#memberId').val();
			
			var obj = {
					'currentPw' : currentPw, 
					'newPw' : newPw,
					'newPwCheck' : newPwCheck
			};
			$.ajax({
				type : 'PUT',
				url : 'updatePw/' + memberId,
				headers : {
					'Content-Type' : 'application/json'
				},
				data : JSON.stringify(obj), // JSON으로 변환
				success : function(result) {
					console.log(result);
					if(result == 1) {
						alert('비밀번호 수정 성공');
					} else {
						alert('현재 비밀번호가 다릅니다.');
						$('#currentPw').val("");
					}
				}
				
			}); // end ajax()
		})// end btnUpdatePw.click
	}); // end document.ready
</script>
</body>
</html>