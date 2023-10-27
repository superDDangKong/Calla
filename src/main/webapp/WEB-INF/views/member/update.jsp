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
<input type="hidden" id="memberId" value=${vo.memberId }>
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
			<td><input type="text" id="memberNickname" value=${vo.memberNickname } readonly></td>
			<td><input type="text" id="newNickname"></td>
			<td><input type="button" id="btnUpdateNickname" value="닉네임 변경" disabled></td>
			<td><div id="newNicknameEffect" style="color:red"></div></td>
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
						</tr>
						<tr>
							<td><div id="newPwEffect" style="color:red"></div></td>
						</tr>
						<tr>
							<th scope="row">비밀번호 다시입력</th>
							<td><input type="password" id="newPwCheck"></td>
						</tr>
						<tr>
							<td><div id="newPwCheckEffect" style="color:red"></div></td>
						</tr>
						<tr>
							<td></td>
							<td><input type="button" id="btnUpdatePw" value="비밀번호 변경" disabled></td>
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
		$('#newPw').on('keyup', function() {
		    validatePasswords();
		});

		$('#newPwCheck').on('keyup', function() {
		    validatePasswords();
		});

		function validatePasswords() { 
		    var newPw = $('#newPw').val(); // 새 비밀번호 값 
		    var newPwCheck = $('#newPwCheck').val(); // 확인 비밀번호 값
		    var pwEffectiveness = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
			// 비밀번호 정규식
		    var newPwValid = pwEffectiveness.test(newPw); // 새로 입력한 비번 정규식 테스트
		    var newPwCheckValid = newPw === newPwCheck; // 

		    $('#newPwEffect').html(newPwValid ? "비밀번호 사용이 가능합니다." : "영문, 숫자, 특수기호(!@#$%^&*) 모두 조합하여 8글자 이상.");
		    $('#newPwCheckEffect').html(newPwCheckValid ? "비밀번호가 일치합니다." : "비밀번호가 일치하지 않습니다.");

		    if (newPwValid && newPwCheckValid) {
		        $('#btnUpdatePw').prop("disabled", false);
		    } else {
		        $('#btnUpdatePw').prop("disabled", true);
		    }
		}
		
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

		$('#newNickname').on('keyup', function() {
			$('#btnUpdateNickname').prop("disabled", true);
			var memberNickname=$('#newNickname').val();
			var nicknameEffectiveness = /^[가-힣a-zA-Z0-9]{2,20}$/;
			var newNicknameValid = nicknameEffectiveness.test(memberNickname)
			var newNicknameCheck = false;
				$.ajax({ // JoinRestController의 checkNick 송수신
	        		 type : 'POST',
	        		 url : 'checkNick', 
					 data : {memberNickname : memberNickname},
	        		 success : function(result){
	        			 console.log(result); // 조건문 사용해서 css효과줘서 아이디 사용불가 가능 표현 만들기
	        			 if (result == 1) {
	        				 newNicknameCheck = false;
	        				 if (newNicknameValid) {
	        				 	$('#newNicknameEffect').html('이미 존재하는 닉네임입니다..');
	        				 }
	        			 } else {
	        				 newNicknameCheck = true;
	        				 if (newNicknameValid) {
	        				        $('#btnUpdateNickname').prop("disabled", false);
	        				        $('#newNicknameEffect').html('사용가능한 닉네임입니다.');
	        				    }
	        			 }
	        		 } // end success
	        	 }) // end ajax
			if(!newNicknameValid) {
				$('#newNicknameEffect').html('영문/한글/숫자 2글자 이상으로 입력해주세요');
			}
			

			
		})// end newNickname.keyup
		
		$('#btnUpdateNickname').click(function(){
			var newNickname=$('#newNickname').val();
			var memberId=$('#memberId').val();
			
			$.ajax({
				type : 'PUT',
				url : 'updateNickname/' + memberId,
				headers : {
					'Content-Type' : 'application/json'
				},
				data : JSON.stringify(newNickname), // JSON으로 변환
				success : function(result) {
					console.log(result);
					if(result == 1) {
						alert('닉네임 수정 성공');
						$('#memberNickname')
					} 
				}
			}); // end ajax()
		}) // end btnUpdateNickname
	}); // end document.ready
</script>
</body>
</html>