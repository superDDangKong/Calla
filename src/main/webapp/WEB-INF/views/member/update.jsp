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
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />
<%@ include file="../header.jspf" %> 	
<title>회원 정보 수정</title>

</head>
<body>

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
			<td>
			<input type="text" id="memberNickname" value=${vo.memberNickname } readonly>
			<input type="text" id="newNickname" required>
			<input type="button" id="btnUpdateNickname" value="닉네임 변경" disabled>
			<div id="newNicknameEffect" style="color:red"></div>
			</td>
		</tr>

		<tr>
			<th scope="row">비밀번호</th>
			<td>
				<table style="text-align: left;">
					<tbody>
						<tr>
							<th scope="row">현재 비밀번호</th>
							<td><input type="password" id="currentPw" required></td>
						</tr>
						<tr>
							<th scope="row">새 비밀번호</th>
							<td><input type="password" id="newPw" required></td>
						</tr>
						<tr>
							<td><div id="newPwEffect" style="color:red"></div></td>
						</tr>
						<tr>
							<th scope="row">비밀번호 다시입력</th>
							<td><input type="password" id="newPwCheck" required></td>
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
			<td><input type="text" id="memberPhone" value=${vo.memberPhone } readonly>
			<input type="text" id="newPhone" required>
			<input type="button" id="btnUpdatePhone" value="번호 변경" disabled>
			<div id="newPhoneEffect" style="color:red"></div></td>
		</tr>
		
		<tr>
			<th scope="row">이메일</th>
			<td><input type="text" id="memberEmail" value=${vo.memberEmail } readonly>
			<input type="text" id="email_id" placeholder="calla" required>@
			<input type="text" id="email_domain" placeholder="naver.com" required>
				<select id="domain_list">
					<option value="-직접입력-">-직접입력-</option>
					<option value="naver.com">naver.com</option>
  					<option value="gmail.com">gmail.com</option>
  					<option value="hanmail.net">hanmail.net</option>
  					<option value="nate.com">nate.com</option>
  					<option value="kakao.com">kakao.com</option>
				</select>
			<input type="hidden" id="newMemberEmail">
			<input type="button" id="btnUpdateEmail" value="이메일 변경" disabled>
			<div id="newEmailEffect" style="color:red"></div></td>
		</tr>
		
		<tr>
			<th scope="row">관심사</th>
			<td><input type="text" id="memberInterest" value=${vo.memberInterest } readonly>
			    만화<input type="checkbox" id="interest_cartoon" value="만화">
        		굿즈<input type="checkbox" id="interest_goods" value="굿즈">
        		캐릭터<input type="checkbox" id="interest_character" value="캐릭터">
			<input type="button" id="btnUpdateInterest" value="관심사 변경">
			<input type="hidden" id="newMemberInterest"></td>
		</tr>

		<tr>
			<th scope="row">주소</th>
			<td><input type="text" id="memberAddress" value=${vo.memberAddress } readonly>
			<input type="hidden" id="newMemberAddress"></td>
			<td>
			<input type="text" id="postcode" placeholder="우편번호" required readonly>
			<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br>
			<input type="text" id="address" placeholder="주소" required readonly>
			<input type="text" id="detailAddress" placeholder="상세주소"><br>
			<input type="text" id="extraAddress" placeholder="참고항목" required readonly>
			<input type="button" id="btnUpdateAddress" value="주소 변경" disabled>
			</td>
		</tr>
	</tbody>
</table>

<script type="text/javascript">
	$(document).ready(function(){
		$('#newPw').on('keyup', function() {
		    validatePasswords();
		    $('#newPwEffect').html(newPwValid ? "비밀번호 사용이 가능합니다." : "영문, 숫자, 특수기호(!@#$%^&*) 모두 조합하여 8글자 이상.");
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
		    if (newPwCheck != "") {
		    	$('#newPwCheckEffect').html(newPwCheckValid ? "비밀번호가 일치합니다." : "비밀번호가 일치하지 않습니다.");
		    }

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
						$('#newPwCheckEffect').html("");
						$('#newPwEffect').html("");
					} else {
						alert('현재 비밀번호가 다릅니다.');
						$('#currentPw').val("");
					}
				}
				
			}); // end ajax()
		})// end btnUpdatePw.click
		// ---------------------- end 비밀번호 수정--------------------------------------------------- 
		
		
		$('#newNickname').on('keyup', function() {
			$('#btnUpdateNickname').prop("disabled", true);
			var memberNickname=$('#newNickname').val();
			var nicknameEffectiveness = /^[가-힣ㄱ-ㅎa-zA-Z0-9]{2,20}$/;
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
				data : newNickname, // JSON으로 변환
				success : function(result) {
					console.log(result);
					if(result == 1) {
						alert('닉네임 수정 성공');
						$('#memberNickname').val(newNickname);
						$('#newNicknameEffect').html("");
					} 
				}
			}); // end ajax()
		}) // end btnUpdateNickname
		// ---------------------- end 닉네임 수정--------------------------------------------------- 
		
		
		$('#newPhone').on('keyup', function() {
			$('#btnUpdatePhone').prop("disabled", true);
			var newPhone=$('#newPhone').val();
			var phoneEffectiveness = /^01[0-9]-?[0-9]{3,4}-?[0-9]{4}$/;
			var newPhoneValid = phoneEffectiveness.test(newPhone);
			
			$('#newPhoneEffect').html(newPhoneValid ? '사용가능한 전화번호 입니다' : ' 01로시작하는 8~9자리 숫자를 입력해 주세요');
			if(newPhoneValid) {
				$('#btnUpdatePhone').prop("disabled", false);
			}
		})// end newPhone.keyup
		
		$('#btnUpdatePhone').click(function(){
			var newPhone=$('#newPhone').val();
			var memberId=$('#memberId').val();
			
			$.ajax({
				type : 'PUT',
				url : 'updatePhone/' + memberId,
				data : newPhone, // JSON으로 변환
				success : function(result) {
					console.log(result);
					if(result == 1) {
						alert('연락처 수정 성공');
						$('#memberPhone').val(newPhone);
						$('#newPhoneEffect').html("");
					} 
				}
			}); // end ajax()
		}) // end btnUpdatePhone
		// ---------------------- end 번호 수정--------------------------------------------------- 
		
		
		$('#email_id').on('keyup', function(){
			validateEmail();
		}) // end email_id.keyup
		$('#email_domain').on('keyup', function(){
			validateEmail();
		}) // end emain_domain.keyup 
		
		$('#domain_list').change(function(){
			if($(this).val()=="-직접입력-"){
				$('#email_domain').val("");
				$("#email_domain").attr("readonly", false);
			} else {
				$("#email_domain").val($(this).val());
				$("#email_domain").attr("readonly", true);
			}
			validateEmail();
		}) // domain_list.change
		
		$('#btnUpdateEmail').click(function(){
			var newMemberEmail=$('#newMemberEmail').val();
			console.log(newMemberEmail);
			var memberId=$('#memberId').val();
			$.ajax({
				type : 'PUT',
				url : 'updateEmail/' + memberId,
				data : newMemberEmail,
				success : function(result) {
					console.log(result);
					if(result == 1) {
						alert('이메일 수정 성공');
						$('#memberEmail').val(newMemberEmail);
						$('#newEmailEffect').html("");
					} 
				}
			}); // end ajax()
		}) // end btnUpdateEmail
		
		function validateEmail() { 
			var emailEffectiveness = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
			var emailId = $('#email_id').val();
			var emailDomain = $('#email_domain').val();
			var newEmail = emailId + '@' + emailDomain;
			$('#newMemberEmail').val(newEmail);
			console.log(newEmail);
			if(emailEffectiveness.test(newEmail)){
				$('#newEmailEffect').text('사용가능한 이메일입니다.');
				$('#btnUpdateEmail').attr('disabled', false);
			} else {
				$('#newEmailEffect').text('잘못된 이메일 형식입니다.');
				$('#btnUpdateEmail').attr('disabled', true);
			}
		} // end validateEmail
		// ---------------------- end 이메일 수정--------------------------------------------------- 
		
		$('#btnUpdateInterest').click(function(){
			var newMemberInterest = "";
			newMemberInterest += $('#interest_goods').prop('checked') ? $('#interest_goods').val() + " " : ""; 
			newMemberInterest += $('#interest_character').prop('checked') ? $('#interest_character').val() + " " : ""; 
			newMemberInterest += $('#interest_cartoon').prop('checked') ? $('#interest_cartoon').val() + " " : ""; 
			var memberId=$('#memberId').val();
			
			$.ajax({
				type : 'PUT',
				url : 'updateInterest/' + memberId,
				data : newMemberInterest, // JSON으로 변환
				success : function(result) {
					console.log(result);
					if(result == 1) {
						alert('관심사 수정 성공');
						$('#memberInterest').val(newMemberInterest);
					} 
				}
			}); // end ajax()
		}) // end btnUpdateAddress
		// ---------------------- end 관심사 수정--------------------------------------------------- 
		
		
		$('#btnUpdateAddress').click(function(){
			var newMemberAddress=$('#newMemberAddress').val();
			var memberId=$('#memberId').val();
			$.ajax({
				type : 'PUT',
				url : 'updateAddress/' + memberId,
				data : newMemberAddress, // JSON으로 변환
				success : function(result) {
					console.log(result);
					if(result == 1) {
						alert('주소 수정 성공');
						$('#memberAddress').val(newMemberAddress);
					} 
				}
			}); // end ajax()
		}) // end btnUpdateAddress
		// ---------------------- end 주소 수정--------------------------------------------------- 
	}); // end document.ready
</script>
<%@ include file="../footer.jspf" %> 
</body>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script> 
		<script>
		new daum.Postcode({
    		onclose: function(state) {
       		 //state는 우편번호 찾기 화면이 어떻게 닫혔는지에 대한 상태 변수 이며, 상세 설명은 아래 목록에서 확인하실 수 있습니다.
       		 if(state === 'FORCE_CLOSE'){
           		 //사용자가 브라우저 닫기 버튼을 통해 팝업창을 닫았을 경우, 실행될 코드를 작성하는 부분입니다.

        		} else if(state === 'COMPLETE_CLOSE'){
            		//사용자가 검색결과를 선택하여 팝업창이 닫혔을 경우, 실행될 코드를 작성하는 부분입니다.
            		//oncomplete 콜백 함수가 실행 완료된 후에 실행됩니다.
        		}
    		}
		});
		</script>
		<script>
    		function execDaumPostcode() {
        		new daum.Postcode({
            		oncomplete: function(data) {
               		 // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                		// 각 주소의 노출 규칙에 따라 주소를 조합한다.
                		// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
               		var addr = ''; // 주소 변수
                	var extraAddr = ''; // 참고항목 변수

                	//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                	if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    	addr = data.roadAddress;
                	} else { // 사용자가 지번 주소를 선택했을 경우(J)
                    	addr = data.jibunAddress;
                	}

                	// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                	if(data.userSelectedType === 'R'){
                    	// 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    	// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    	if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        	extraAddr += data.bname;
                    	}
                    	// 건물명이 있고, 공동주택일 경우 추가한다.
                    		if(data.buildingName !== '' && data.apartment === 'Y'){
                        		extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    		}
                    		// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    		if(extraAddr !== ''){
                        		extraAddr = ' (' + extraAddr + ')';
                    		}
                    		// 조합된 참고항목을 해당 필드에 넣는다.
                    		document.getElementById("extraAddress").value = extraAddr;
                
                		} else {
                    		document.getElementById("extraAddress").value = '';
                		}
	
                		// 우편번호와 주소 정보를 해당 필드에 넣는다.
                		document.getElementById('postcode').value = data.zonecode;
                		document.getElementById("address").value = addr;
                		// 커서를 상세주소 필드로 이동한다.
                		document.getElementById("detailAddress").focus();
                		
                		var postcode = $('#postcode').val(); // 우편번호
	   					var address = $('#address').val(); // 주소
	    				var detailAddress = $('#detailAddress').val(); // 상세주소
	    				var extraAddress = $('#extraAddress').val(); // 참고항목
	    				var newMemberAddress = postcode + " " + address + " " + detailAddress + extraAddress;

                		$('#btnUpdateAddress').prop("disabled", false);
	    				document.getElementById("newMemberAddress").value = newMemberAddress;
					      
            		}
        		}).open();
    		}
    		</script>
</html>