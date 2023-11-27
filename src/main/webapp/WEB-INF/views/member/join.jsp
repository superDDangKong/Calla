<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<meta charset="UTF-8">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />
<%@ include file="../header.jspf" %> 
<title>회원가입</title>
</head>
<body>
	<div class="container mt-5">
        <form name="join" action="join" id="join" method="post">
            <h2 class="mb-4">회원가입</h2>
            <div class="mb-3">
                <input type="text" id="member_id" class="form-control" name="memberId" placeholder="아이디" required>
            	<div id="error_member_id" class="text-danger"></div>
            </div>
            <div class="mb-3">
                <input type="password" id="member_pw" class="form-control" name="memberPw" placeholder="비밀번호" required>
            	<div id="error_member_pw" class="text-danger"></div>
            </div>
            <div class="mb-3">
                <input type="password" id="member_pw_ck" class="form-control" name="member_pw_check" placeholder="비밀번호 확인" required>
            	<div id="error_member_pw_check" class="text-danger"></div>
            </div>
            <div class="mb-3">
                <input type="text" id="member_name" class="form-control" name="memberName" placeholder="홍길동" required>
            	<div id="error_member_name" class="text-danger"></div>
            </div>
            <div class="mb-3">
                <input type="text" id="member_nickname" class="form-control" name="memberNickname" placeholder="calla" required>
            	<div id="error_member_nickname" class="text-danger"></div>
            </div>
            <div class="mb-3">
                <div class="input-group">
                    <input type="text" class="form-control" name="member_email1" id="email_id" placeholder="calla" required>
                    <span class="input-group-text">@</span>
                    <input type="text" class="form-control" name="member_email2" id="email_domain" placeholder="naver.com" required>
                </div>
                <div id="error_member_email" class="text-danger"></div>
            </div>
            <div class="mb-3">
                <select class="form-select" id="domain-list">
                    <option value=" ">-직접입력-</option>
                    <option value="naver.com">naver.com</option>
                    <option value="gmail.com">gmail.com</option>
                    <option value="hanmail.net">hanmail.net</option>
                    <option value="nate.com">nate.com</option>
                    <option value="kakao.com">kakao.com</option>
                </select>
            </div>
            <input type="hidden" name="memberEmail" id="memberEmail">
            <div class="mb-3">
                <input type="tel" id="member_phone" class="form-control" name="memberPhone" placeholder="010-1234-5678" required>
            	<div id="error_member_phone" class="text-danger"></div>
            </div>
            <div class="mb-3">
                <div>관심사<br>
                    <div class="form-check">
                        <input type="checkbox" name="check" class="form-check-input" id="check1" value="만화" checked>
                        <label class="form-check-label" for="check1">만화</label>
                    </div>
                    <div class="form-check">
                        <input type="checkbox" name="check" class="form-check-input" id="check2" value="굿즈">
                        <label class="form-check-label" for="check2">굿즈</label>
                    </div>
                    <div class="form-check">
                        <input type="checkbox" name="check" class="form-check-input" id="check3" value="캐릭터">
                        <label class="form-check-label" for="check3">캐릭터</label>
                    </div>
                    <input type="hidden" name="memberInterest" id="memberInterest">
                    <div id="error_member_interest" class="text-danger"></div>
                </div>
            </div>
            <div class="mb-3">
                <div>
                    <input type="text" id="postcode" class="form-control" placeholder="우편번호" required>
                    <button type="button" class="btn btn-secondary mt-2"
                        onclick="sample6_execDaumPostcode()">우편번호 찾기</button>
                </div>
                <input type="text" id="address" class="form-control mt-2" placeholder="주소" required>
                <input type="text" id="detailAddress" class="form-control mt-2" placeholder="상세주소">
                <input type="text" id="extraAddress" class="form-control mt-2" placeholder="참고항목" required>
				<div id="error_member_address" class="text-danger"></div>
                <input type="submit" name="join_button" id="join_button" class="btn btn-primary mt-2" value="가입하기">
                <!-- 주소를 확인해주세요 -->
                <input type="hidden" name="memberAddress" id="memberAddress">
            </div>
        </form>
    </div>
</body>
<script type="text/javascript">
	/* 유효성 검사 통과유무 변수 */
	var idCheck = false;
	// 아이디 중복 검사
	var pwckCheck = false; 
	// 비밀번호 유효성 검사
	var pwckcorCheck = false; 
	// 비밀번호 일치 확인
	var nameCheck = false;   
	// 이름입력 유무 검사
	var nickNameCheck = false;	
	// 닉네임 중복 검사
	var mailCheck = false;     
	// 이메일
	var phoneCheck = false;		
	// 핸드폰
	var addressCheck = false;  
	// 주소
	
	
	
	
	function idDuplicationCheck() { // 아이디 공백 + 중복 + 유효성 검사 함수
		console.log("아이디검사 호출");
		var memberIdInput = $('#member_id');
		var memberId = $('#member_id').val();
		var idEffectiveness = /^[A-Za-z0-9]{8,}$/; // 아이디: 최소 8자 이상의 영문 대소문자, 숫자만 사용 가능합니다.
		var memberIdError = $('#error_member_id'); // 에러메세지를 출력한 태그의 아이디를 변수로 선언
		
		if (memberId.trim() === '') { // 아이디 입력란이 공백인지 확인하는 
			memberIdError.text('아이디를 입력해주세요.');
			memberIdError.css('color', 'red');
			console.log("아이디가 공백임")
			idCheck = false;
			return;
		}
		
		if (!idEffectiveness.test(memberId)) { // 아이디 유효성 검사(false가 나올 경우 참으로 실행)
			memberIdError.text('아이디: 최소 8자 이상의 영문 대소문자, 숫자만 사용 가능합니다.');
			memberIdError.css('color', 'red');
			console.log("아이디 유효성검사 통과못함")
			idCheck = false;
			return;
		}
		
		$.ajax({
			type : 'POST',
			url : '/calla/member/checkId',
			data : {memberId : memberId},
			success : function(result){
				console.log(result + "개 중복"); // result가 1이면 1개 중복 0이면 0중복
				if (result == 0) { // 중복 없을 시
					memberIdError.text('');
					memberIdInput.css('border', '1px solid limegreen');
					idCheck = true;
				} else { // 중복이 있을 시
					memberIdError.text('사용 불가능한 아이디입니다.');
					memberIdError.css('color', 'red');
					memberIdInput.css('border', '1px solid red');
					idCheck = false;
				}
			} // end success
		}) // end ajax
	}; // end idDuplicationCheck()
	
	function pwEffectivenessCheck() { // 비밀번호 공백 + 유효성 검사 함수
		console.log("비밀번호검사 호출");
		var memberPw = $('#member_pw').val();
		var memberPwError = $('#error_member_pw');
		var pwEffectiveness = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/; 
		
		if (memberPw.trim() === '') { // 비밀번호 입력란이 공백인지 확인하는 
			memberPwError.text('비밀번호를 입력해주세요.');
			memberPwError.css('color', 'red');
			console.log("비밀번호 공백임")
			return false;
		}
		
		if (!pwEffectiveness.test(memberPw)) { // 비밀번호 유효성 검사(false가 나올 경우 참으로 실행)
			memberPwError.text('비밀번호: 최소 8자 이상의 영문 대/소문자, 숫자, 특수문자를 사용해 주세요.');
			memberPwError.css('color', 'red');
			console.log("비밀번호 유효성 검사 실패")
			return false;
		}
		memberPwError.text('');
		console.log("비밀번호검사 유효성 검사 성공")
	    return true;
	    
	}; // end pwEffectivenessCheck()
	
	function pwAccordanceCheck() { // 비밀번호 일치 확인 함수
		console.log("비밀번호 재검사 호출");
		var memberPw = $('#member_pw').val(); 
		var memberPwCheck = $('#member_pw_ck').val(); 
		var memberPwCheckError = $('#error_member_pw_check');  
		
		if (memberPw !== memberPwCheck) { // 처음에 입력한 비밀번호와 다를 때 참
			memberPwCheckError.text('비밀번호가 일치하지 않습니다.');
			memberPwCheckError.css('color', 'red');
			console.log("비밀번호가 불일치")
			return false;
		}
		
		memberPwCheckError.text('');
		return true;
	}; // end pwAccordaneCheck()
	
	function nameConfirmCheck() { // 이름 입력 유무 확인 함수
		console.log("이름입력검사 호출");
		var memberName = $('#member_name').val();
		var nameEffectiveness = /^[가-힣a-zA-Z]{2,}$/; 
		var memberNameError = $('#error_member_name');
		
		if (memberName.trim() === '') { // 이름이 공백일 시 참
			memberNameError.text('이름을 입력해주세요.');
			memberNameError.css('color', 'red');
			console.log("이름이 공백임")
			return false;
		}
		
		if (!nameEffectiveness.test(memberName)) { // 이름 제대로 입력한건지 확인 false가 참
			memberNameError.text('이름: 한글, 영문 대/소문자 중 2자 이상이어야 합니다. (특수기호, 공백 사용 불가)');
			memberNameError.css('color', 'red');
			console.log("이름 제대로 입력")
			return false;
		}
		
		// 이름을 입력 성공시
		memberNameError.text('');
		return true;
	};
	
	function nicknameDuplicationCheck() { // 닉넴인 중복 검사 확인 함수
		console.log("닉네임검사 호출");
		var memberNickname = $('#member_nickname').val();
		var nicknameEffectiveness = /^[가-힣ㄱ-ㅎa-zA-Z0-9]{2,20}$/;
		var memberNicknameError = $('#error_member_nickname');
		
		if (memberNickname.trim() === '') {
			memberNicknameError.text('닉네임을 입력해주세요.');
			memberNicknameError.css('color', 'red');
			console.log("닉네임이 공백임")
			nickNameCheck = false;
			return 
		}
		
		if (!nicknameEffectiveness.test(memberNickname)) {
			memberNicknameError.text('닉네임: 한글, 영문 대/소문자, 숫자로 2자 이상이어야 합니다.');
			memberNicknameError.css('color', 'red');
			console.log("닉네임 제대로 입력")
			nickNameCheck = false;
			return false;
		}
		
		$.ajax({
			type : 'POST',
			url : '/calla/member/checkNick',
			data : {memberNickname : memberNickname},
			success : function(result){
				console.log(result + '개 중복');
				if (result == 0) { // 중복된게 없을 시
					memberNicknameError.text('');
					/* memberNicknameError.text('사용 가능한 닉네임입니다.');
					memberNicknameError.css('color', 'limegreen'); */
					nickNameCheck = true;
				} else {
					console.log("닉네임 중복됨")
					memberNicknameError.text('사용 불가능한 닉네임입니다.');
					memberNicknameError.css('color', 'red');
					nickNameCheck = false;
				}
			} // end success
		}) // end ajax
	}; // end nicknameDuplicationCheck()
	
	function mailConfirmCheck(memberEmail) { // 메일 입력 유무 확인 함수
		console.log("메일검사 호출");
		var memberEmailId = $('#email_id').val();
		var memberEmailDomain = $('#email_domain').val();
		var email = memberEmailId + '@' + memberEmailDomain;
		$('#memberEmail').val(email);
		var memberEmail = $('#memberEmail').val();
		console.log(memberEmail);
		var memberEmailError = $('#error_member_email');
		var emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
		
		if (!emailRegex.test(memberEmail)) {
			memberEmailError.text('사용 불가능한 이메일입니다.'); // 나중에 이메일 인증 만들어서 여기도 바꿔보자
			memberEmailError.css('color', 'red');
			return false;
		}
		
		memberEmailError.text(''); // 나중에 이메일 인증 만들어서 여기도 바꿔보자
		return true;
	};
	
	function phoneConfirmCheck() { // 연락처 입력 유무 확인 함수
		console.log("연락처검사 호출");
		var memberPhone = $('#member_phone').val();
		var phoneEffectiveness = /01[0-9]-?[0-9]{3,4}-?[0-9]{4}$/;
		var memberPhoneError = $('#error_member_phone');
		
		if (!phoneEffectiveness.test(memberPhone)) {
			memberPhoneError.text('연락처: 필수정보입니다.');
			memberPhoneError.css('color', 'red');
			return false;
		}
		
		memberPhoneError.text('');
		return true;
	}; // end phoneConfirmCheck()
	
	
	function interestValues() { 
		console.log("관심사검사 호출");
	    var selectedValues = $('.form-check-input:checked').map(function(){ 
	        return $(this).val();
	    }).get();

	    var interest = selectedValues.join(', '); 
	    $('#memberInterest').val(interest);

	    var memberInterestValue = $('#memberInterest').val(); // 값 가져오기
	    console.log(memberInterestValue);
	    return true;
	};
	
	
 	function addressConfirmCheck() { // 주소 입력 유무 확인 함수
 		console.log("주소검사호출");
 		var add1 = $('#postcode').val(); // 우편번호
		var add2 = $('#address').val(); // 주소
		var add3 = $('#detailAddress').val(); // 상세주소
		var add4 = $('#extraAddress').val(); // 참고항목
		var Address = add1 + add2 + add3 + add4;
		$("#memberAddress").val(Address);
		var memberAddress = $('#memberAddress').val();
		console.log(memberAddress);

    	if (add3.trim() === '') {
    	    $('#error_member_address').text('상세주소를 입력해주세요.');
    	    $('#error_member_address').css('color', 'red');
    	    console.log("주소 검사 실패");
    	    return false;
    	} 
    	
    	// 성공 시에 할 작업
    	$('#error_member_address').text('');
    	return true;
    	
	};
	
 	
   	$(function () {
   	    $('#domain-list').change(function () {
   	        var selectedValue = $(this).val();
   	        var emailDomainInput = $('#email_domain');

   	        if (selectedValue === " ") {
   	            emailDomainInput.prop('disabled', false);
   	            emailDomainInput.prop('readonly', false);
   	        } else {
   	            emailDomainInput.prop('disabled', true);
   	            emailDomainInput.prop('readonly', true);
   	        }

   	        if (selectedValue === "1") {
   	            emailDomainInput.val("");
   	            emailDomainInput.prop("readonly", false);
   	        } else {
   	            emailDomainInput.val(selectedValue);
   	        }
   	    });
   	});




   	
	$(document).ready(function(){
		$('#member_id').blur(function(){
			idDuplicationCheck();
		})
		
		$('#member_pw').blur(function(){
			pwEffectivenessCheck();
		})
		
		$('#member_pw_ck').blur(function(){
			pwAccordanceCheck();
		})
		
		$('#member_name').blur(function(){
			nameConfirmCheck();
		})
		
		$('#member_nickname').blur(function(){
			nicknameDuplicationCheck();
		})
		
		$('#member_phone').blur(function(){
			phoneConfirmCheck();
		})
		
		$('.form-check-input').change(function(){
			interestValues();
		})
		
		$('#join').submit(function(e){
			e.preventDefault();
			
			if (!idCheck || !nickNameCheck){ // 아이디 또는 닉넴임 검사 false가 나오면 form 제출막기
				console.log("아이디 또는 닉네임");
				return;
			} 

			// 비밀번호 검사
			if (!pwEffectivenessCheck()){ // pwEffectivenessCheck()가 false를 리턴할 때 실행
				console.log("비번");
				return;
			} 

			// 비밀번호 재확인
			if (!pwAccordanceCheck()) {
				console.log("비번재확인");
				return;
			}
			
			// 이름 확인
			if (!nameConfirmCheck()) {
				console.log("이름");
				return;
			}
			
			// 이메일 확인
			if (!mailConfirmCheck()) {
				console.log("이메일");
				return;
			}
			
			// 연락처 확인
			if (!phoneConfirmCheck()) {
				console.log("연락처");
				return;
			}
			
			// 관심사 확인
			if (!interestValues()) {
				console.log("관심사");
				return;
			}
			
			// 주소 확인
			if (!addressConfirmCheck()) {
				console.log('여기까지 왔다 드뎌');
				return;
			} 
			
			var memberId = $('#member_id').val();
			var memberPw = $('#member_pw').val();
			var memberName = $('#member_name').val();
			var memberNickname = $('#member_nickname').val();
			var memberEmail = $('#memberEmail').val();
			var memberPhone = $('#member_phone').val();
			var memberInterest = $('#memberInterest').val();
			var memberAddress = $('#memberAddress').val();
			console.log(memberId);
			console.log(memberPw);
			console.log(memberName);
			console.log(memberNickname);
			console.log(memberEmail); // 값이 없다고 나옴
			console.log(memberPhone);
			console.log(memberInterest); // change가 안되면 값이 없음
			console.log(memberAddress); // 값이 없다고 나옴
			
 			 var obj = {
				'memberId' : memberId,
				'memberPw' : memberPw,
				'memberName' : memberName,
				'memberNickname' : memberNickname,
				'memberEmail' : memberEmail,
				'memberPhone' : memberPhone,
				'memberInterest' : memberInterest,
				'memberAddress' : memberAddress
			}
			
			$.ajax({
				type : 'POST',
				url : '/calla/member/join',
				headers : {
                    'Content-Type' : 'application/json'
                 },
                data : JSON.stringify(obj),
				success : function(result){
					if(result === 1){
						location.href = 'http://localhost:8080/calla/member/login?';
						alert('회원가입을 축하합니다.');
					}
				}/* , // success
				error: function(result){
					console.log(result); // objec Objec
					alert(result);
				} */
			}) // ajax   
		}) // join.submit
		
	}) // end document.ready
	
		
	
</script> 
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
    function sample6_execDaumPostcode() {
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
                	/* $('#detailAddress').blur(function(){
                	var add1 = $('#postcode').val(); // 우편번호
	   				var add2 = $('#address').val(); // 주소
	    			var add3 = $('#detailAddress').val(); // 상세주소
	    			var add4 = $('#extraAddress').val(); // 참고항목
	    			var memberAddress = add1 + add2 + add3 + add4;
					// add1,2,3,4를 memberAddress에 넣는다
					document.getElementById("memberAddress").value = memberAddress;
					console.log(memberAddress);
					addressCheck = true;
                	if(memberAddress !== ''){
                		addressCheck = true;
                		$('#error_address_msg').text('');
                	} else {
                		$('#error_address_msg').text('사용 불가능한 이메일입니다.');
               			$('#error_address_msg').append("<br>");
               			$('#error_address_msg').css('color', 'red');
                	}
                	}); */
           		}
       		}).open();
   		}
</script>
<%@ include file="../footer.jspf" %> 
</html>