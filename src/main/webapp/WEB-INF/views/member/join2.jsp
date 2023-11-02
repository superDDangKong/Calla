<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">

.member__form{
 	border: 1px solid rgba(0, 0, 0, 0.3);
	width: 20%;
    padding: 16px 0 12px;
    background: none transparent;
    font-family: dotum,sans-serif;
    font-size: 14px;
    line-height: 20px;
    font-weight: 700;
    text-indent: 10px;
}

#member_id{
	border-top-left-radius: 10px; /* 상단 좌측 모서리를 10px로 둥글게 만듭니다. */
    border-top-right-radius: 10px; /* 상단 우	측 모서리를 10px로 둥글게 만듭니다. */
}

#member_nickname{
	border-bottom-left-radius: 10px; /* 하단 좌측 모서리를 10px로 둥글게 만듭니다. */
    border-bottom-right-radius: 10px; /* 하단 우측 모서리를 10px로 둥글게 만듭니다. */
}

.error_msg{
	font-size: 14px;
}
</style>
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
<div style="text-align:center">
	<h1><a href="http://localhost:8080/calla/"><span>Calla</span></a></h1>
	 <form name="join" action="join" id="join"method="post"> 
	 	<div>
			<div id="f">
				<input type="text" id="member_id" class="member__form" name="memberId" placeholder="아이디" required>
				<br>
						
				<input type="password" id="member_pw" class="member__form" name="memberPw" placeholder="비밀번호" required>
				<br>
					
				<input type="password" id="member_pw_ck" class="member__form" name="member_pw_check" placeholder="비밀번호 확인" required>
				<br>
						
				<input type="text" id="member_name" class="member__form" name="memberName" placeholder="홍길동" required>
				<br>
		
			
				<input type="text" id="member_nickname" class="member__form" name="memberNickname" placeholder="calla" required><br>
				<span class="error_msg" id="error_id_msg"></span>
				<span class="error_msg" id="error_pw_msg"></span>
				<span class="error_msg" id="error_name_msg"></span>
				<span class="error_msg" id="error_nick_msg"></span>
			</div>
			<span class="error_msg" id="error_email_msg"></span>
			<span class="error_msg" id="error_phone_msg"></span>
			<span class="error_msg" id="error_interest_msg"></span>
			<span class="error_msg" id="error_address_msg"></span>
			<div>
				<input type="text" class="member__form" name="member_email1" id="email_id" placeholder="calla" required>@
				<input type="text" class="member__form" name="member_email2" id="email_domain" class="box" placeholder="naver.com" required>
				<select class="box" id="domain-list" name="emailSelection" onchange="select_change(this.value);">
					<option value="type">-직접입력-</option>
					<option value="naver.com">naver.com</option>
  					<option value="gmail.com">gmail.com</option>
  					<option value="hanmail.net">hanmail.net</option>
  					<option value="nate.com">nate.com</option>
  					<option value="kakao.com">kakao.com</option>
				</select>
				<span class="final_email_ck"></span><!-- 이메일 형식대로 입력하라는 문장출력 -->
				<input type="hidden" name="memberEmail" id="memberEmail">
			</div><br>
		
			<div>
        		<input type="tel" id="member_phone" class="member__form" name="memberPhone" placeholder="010-1234-5678" required><br>
        	</div><br>
        
        	<div>관심사<br>
        		만화<input type="checkbox" name="check" class="check" id="check1" value="만화">
        		굿즈<input type="checkbox" name="check" class="check" id="check2" value="굿즈">
        		캐릭터<input type="checkbox" name="check" class="check" id="check3" value="캐릭터">
        		<input type="hidden" name="memberInterest" id="memberInterest">
        		<span class="final_interest_ck"></span>
        	</div>
      	
      	
      		<br>
      		<div></div> <!-- 주소api -->
      		<input type="text" id="postcode" class="member__form" placeholder="우편번호" required>
			<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
			<input type="text" id="address" class="member__form" placeholder="주소" required>
			<input type="text" id="detailAddress" class="member__form" placeholder="상세주소"><br>
			<input type="text" id="extraAddress" class="member__form" placeholder="참고항목" required>
			<br>
			<input type="submit" name="join_button" id="join_button" value="가입하기">  
			  
			<span class="final_add_ck"></span><!-- 주소를 확인해주세요 -->
			<input type="hidden" name="memberAddress" id="memberAddress">
      	</div>
      
   	</form>
</div>
      <script>
      /* 유효성 검사 통과유무 변수 */
      var idckcorCheck = false;			// 아이디 유효성 검사
      var idckCheck = false;            // 아이디 중복 검사
      var pwckCheck = false;            // 비밀번호 유효성 검사
      var pwckcorCheck = false;        	// 비밀번호 일치 확인
      var nameCheck = false;            // 이름입력 유무 검사
      var nickNameCheck = false;		// 닉네임 중복 검사
      var mailCheck = false;            // 이메일
      var phoneCheck = false;			// 핸드폰
      var addressCheck = false;         	// 주소
      
      $('#join').submit(function(event){
    	  var memberId = $('#member_id').val();
    	  var memberPw = $('#member_pw').val();
    	  var memberName = $('#member_name').val(); 
    	  var memberNickname = $('#member_nickname').val();
    	  var memberPhone = $('#member_phone').val();
    	  var memberEmail = $('#memberEmail').val();
    	  var memberAddress = $('#memberAddress').val();
    	  var memberInterest = $('#memberInterest').val(); 
    	  event.preventDefault();
    	  console.log(idckcorCheck);
    	  console.log(idckCheck);
    	  console.log(pwckCheck);
    	  console.log(pwckcorCheck);
    	  console.log(nameCheck);
    	  console.log(nickNameCheck);
    	  console.log(mailCheck);
    	  console.log(phoneCheck);
    	  console.log(addressCheck);
    	  if(idckcorCheck && idckCheck && pwckCheck && pwckcorCheck && nameCheck && nickNameCheck && mailCheck && phoneCheck && addressCheck){
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
    		  console.log(obj);
    		  $.ajax({ // 회원가입 ajax
    			  type : 'POST',
    			  url : '/calla/member/join',
    			  headers : {
                      'Content-Type' : 'application/json'
                   },
    			  data : JSON.stringify(obj),
    			  success : function(result){
    				  if(result == 1) {
    					  alert('회원가입을 축하합니다!');
    					  location.href = 'http://localhost:8080/calla/';
    				  }
    			  }
    		  }) // end ajax
    	  } else {
    		  alert("안돼 돌아가");
    		  //location.reload();
    	  }// 유효성 검사 통과 조건문
      })// end submit 
    		           
      
    	        // 폼 제출 이벤트 발생 시 실행되는 코드
				// 아이디 유효성 + 중복검사 
      $('#member_id').blur(function() {
       var memberId = $('#member_id').val(); // 클라이언트가 입력한 아이디 변수에 저장
       console.log("입력한 아이디 : " + memberId); // 입력한 아이디 콘솔에 띄우기
    	    // 아이디 정규식
       var idEffectiveness = /^[A-Za-z0-9]{8,}$/;
       if(memberId !== ""){
    	   
        	// 아이디 유효성 확인
    	if (idEffectiveness.test(memberId)){
    		idckcorCheck = true; // 아이디 유효성 검사 통과 변수
    		
    		// 중복확인 ajax
    		$.ajax({ // JoinRestController의 checkId 송수신
        		 type : 'POST',
        		 url : '/calla/member/checkId',  
        		 data : {memberId : memberId},
        		 success : function(result){
        			 console.log(result + "중복"); 
        			 if (result == 1) {
        				 console.log("사용불가능한아이디")
        				 idckCheck = false; // 아이디 중복검사 통과 변수
        				 $('#error_id_msg').text('사용 불가능한 아이디 입니다.');
        				 $("#error_id_msg").append("<br>");
        		    	 $('#error_id_msg').css('color', 'red');
        				 
        			 } else {
        				 console.log("사용가능한아이디")
        				 idckCheck = true; // 아이디 중복검사 통과 변수
        				 $('#error_id_msg').text('');
        			 }
        		 } // end success
        	 }) // end ajax
        	 
    	} else {
    		console.log("아이디 유효성 검사 실패");
    		idckcorCheck = false; // 아이디 유효성 검사 실패 변수
    		$('#error_id_msg').text('아이디 유효성 검사 실패');
    		$('#error_id_msg').append("<br>");
    		$('#error_id_msg').css('color', 'red');
    	}
       
      } else {
    	  $('#error_id_msg').text('아이디를 입력해주세요.');
    	  $('#error_id_msg').append("<br>");
  		  $('#error_id_msg').css('color', 'red');
      }			 
    	}); // end 아이디 function(아이디 유효성 검사 + 중복체크)
    	
     	/* // 비밀번호 유효성 검사 
    	$('#member_pw').focus(function() {
    		var memberPw = $('#member_pw').val(); // 클라이언트가 입력한 비밀번호 변수에 저장
    		if(memberPw !== ''){
    			console.log("입력한 비밀번호 : " + memberPw); // 입력한 비밀번호 콘솔에 띄우기
    		// 비밀번호 정규식
    			var pwEffectiveness = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
    			if (pwEffectiveness.test(memberPw)){
    				console.log("비밀번호 유효성 검사 통과"); // 조건문 사용해서 css효과줘서 아이디 사용불가 가능 표현 만들기
    				pwckCheck = true;
    				$('#error_pw_msg').text('');
    			} else {
    				console.log("비밀번호 유효성 검사 실패");
    				pwckCheck = false;
    				$('#error_pw_msg').text('사용 불가능한 비밀번호 입니다.');
    				$('#error_pw_msg').append("<br>");
		    		$('#error_pw_msg').css('color', 'red');
    			}
    		} else {
    			 $('#error_pw_msg').text('비밀번호를 입력해주세요.');
    			 $('#error_pw_msg').append("<br>");
    	  		 $('#error_pw_msg').css('color', 'red');
    		}
    	}); // end 비밀번호 function(비밀번호 유효성 검사) */
		
    	//1.비번검사(key up)
    	//3
    	//2.비번재확인(key up)
    	//3
    	
    	//3.비번유형성 + 비번 재확인()
    	
    	 // 비밀번호 검사
    	$('#member_pw').keyup(function(event){
    		passwordCheck();
    	});
    	
      	// 비밀번호 재확인
      	$('#member_pw_ck').blur(function(event){
      		passwordCheck();
    	}); // end 재확인 function(비밀번호 재확인)
    	
    	function passwordCheck() { // 비밀번호 유효성 + 재확인 함수
    		var pwEffectiveness = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
    		var memberPw = $('#member_pw').val(); // 유효성 검사 통과한 비밀번호
      		var memberPwCk = $('#member_pw_ck').val(); // 비밀번호 재확인
    		// 비밀번호 공백인지 확인
      		if(memberPw !== ''){ // 이거는 실행됨
      			console.log(memberPw)
      			if (pwEffectiveness.test(memberPw)){ // 비밀번호 유효성 검사
      				console.log("입력한 비밀번호 : " + memberPw); // 입력한 비밀번호 콘솔에 띄우기
    				console.log("비밀번호 유효성 검사 통과"); // 조건문 사용해서 css효과줘서 아이디 사용불가 가능 표현 만들기
    				pwckCheck = true; // 유효성 검사를 통과 했다는 변수
    				$('#error_pw_msg').text(''); // 에러 메세지 없애기
      				if (memberPw === memberPwCk) { // 유효성 통과한 비밀번호와 재확인 검사
      					console.log("비밀번호 재확인 성공");
      					pwckcorCheck = true; // 비밀번호 재확인 변수
      				} else { // 틀리면 실행 할 오류 메시지지
      					console.log("비밀번호 재확인 실패");
      					$('#error_pw_msg').text('비밀번호가 일치하지 않습니다.');
      					$('#error_id_msg').append("<br>");
		    			$('#error_pw_msg').css('color', 'red');
      					pwckcorCheck = false;
      				}
    			} else {
    				console.log("비밀번호 유효성 검사 실패");
    				pwckCheck = false; 
    				$('#error_pw_msg').text('사용 불가능한 비밀번호 입니다.');
    				$('#error_pw_msg').append("<br>");
		    		$('#error_pw_msg').css('color', 'red');
    			}
      		} else {
      			$('#error_pw_msg').text('비밀번호를 입력해주세요.');
   			 	$('#error_pw_msg').append("<br>");
   	  		 	$('#error_pw_msg').css('color', 'red');
      		}
    	} // 비밀번호 유효성 + 재확인 
    	
    	        

      	// 이름 입력확인
		$('#member_name').blur(function(){
			var memberName = $('#member_name').val(); // 입력한 이름
			// 이름 정규식
			if(memberName !== ''){
				$('#error_name_msg').text('');
				var nameEffectiveness = /^[가-힣a-zA-Z0-9]{2,20}$/;

				if (nameEffectiveness.test(memberName)){
					console.log("이름 입력 성공");
					nameCheck = true;
					console.log(nameCheck);
				} else {
					console.log("이름 입력 실패");
					console.log(nameCheck);
					nameCheck = false;
				}
			} else {
				$('#error_name_msg').text('이름을 입력해주세요.');
				$('#error_name_msg').append("<br>");
		    	$('#error_name_msg').css('color', 'red');
			}
		}) // end 이름 입력확인
      	
		$('#member_nickname').blur(function(){
			var memberNickname = $('#member_nickname').val();
			// 닉네임 정규식
			var nicknameEffectiveness = /^[가-힣ㄱ-ㅎa-zA-Z0-9]{2,20}$/;
			if(memberNickname !== ''){
				$('#error_nick_msg').text('');
				if(nicknameEffectiveness.test(memberNickname)){
				
					// ajax를 이용한 닉네임 중복체크
					$.ajax({ // JoinRestController의 checkNick 송수신
        		 	type : 'POST',
        		 	url : '/calla/member/checkNick', 
        		 	data : {memberNickname : memberNickname},
        		 	success : function(result){
        				 console.log(result); // 조건문 사용해서 css효과줘서 아이디 사용불가 가능 표현 만들기
        			 	if (result == 1) {
        					 console.log("사용불가능한닉네임")
        				 	nickNameCheck = false;
        					$('#error_nick_msg').text('사용 불가능한 닉네임입니다.');
        					$('#error_nick_msg').append("<br>");
        				   	$('#error_nick_msg').css('color', 'red');
        			 	} else {
        					console.log("사용가능한닉네임")
        				 	nickNameCheck = true;
        					
        			 	}
        		 	} // end success
        	 	}) // end ajax
				} else {
					console.log("입력한 닉네임 : " + memberNickname);
				}
			} else {
				$('#error_nick_msg').text('닉네임을 입력해주세요.');
				$('#error_nick_msg').append("<br>");
		    	$('#error_nick_msg').css('color', 'red');
			}
		}) // end 닉네임 중복확인
		
		$(document).click(function(){
			var emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
			var emailId = $('#email_id').val();
  			var emailDomain = $('#email_domain').val();
  			var email = emailId + '@' + emailDomain;
  			$("#memberEmail").val(email);
  			var memberEmail = $('#memberEmail').val();
  			if(emailRegex.test(memberEmail)){
	  			mailCheck = true;
  				console.log(mailCheck);
  				$('#error_email_msg').text('');
  			} else {
  				$('#error_email_msg').text('사용 불가능한 이메일입니다.');
				$('#error_email_msg').append("<br>");
			   	$('#error_email_msg').css('color', 'red');
  			}
  			
		})// end 이메일 합치기	 
		
		
    	$('#member_phone').blur(function(){
    	 var memberPhone = $('#member_phone').val();
    	 
    	 var phoneEffectiveness = /01[0-9]-?[0-9]{3,4}-?[0-9]{4}$/;
		 
    	 
    	 // 연락처 입력 유무 검사
    	 if (phoneEffectiveness.test(memberPhone)){
    		 console.log(memberPhone);
    		 phoneCheck = true;
    		 $('#error_phone_msg').text('');
    	 } else {
    		 $('#error_phone_msg').text('사용 불가능한 이메일입니다.');
			 $('#error_phone_msg').append("<br>");
			 $('#error_phone_msg').css('color', 'red');
    	 }
    	 console.log(memberPhone)
     }) // end 입력한 핸드폰 번호
    	            
	

      
       	   
    	
      		$(document).ready(function() {
      			
    	    var interestValues = ''; // 체크된 value 값들 저장할 문자열 변수 선언
    	    
    	     $(".check").change(function() { // 클래스가 check인 모든 체크박스 요소를 선택하고, 상태가 변경될 때 마다 실행
    	    	 console.log("check.change")
    	    	 var selectedValues = $(".check:checked").map(function() { // $(".check:checked") ckeck인 요소중에 체크된 요소를 선택
    	    		// .map(function) .map은 결과들을 배열에 저장하는 메서드
    	    		//selectedValues 체크된 요소들을 저장할 배열
    	            return $(this).val();
    	        }).get(); // get();  selectedValues jquery객체를 javascript 객체로 바꾸어줌
				
    	    	interestValues = selectedValues.join(', '); // 선택된 값을 쉼표로 구분하여 문자열로 저장
    	        // join 배열을 하나의 문자열로 합쳐주는 메서드
    	        document.getElementById("memberInterest").value = interestValues; // 아이디가 memberInterest인 요소에 interestValues를 저장 
    	        var memberInterest = $('#memberInterest').val(); // 변수 값을 저장
    	        console.log(memberInterest); // 값이 제대로 나오는지 확인 
    	    	});
    		});
     
      
 	  
      var select_change = function(value){
    	  console.log("값 변경 테스트 : " + value);
    	  $("#email_domain").val(value);
      } // end function 셀렉트 옵션값이 도메인(input태그)에 넘겨주는
      
      $(function(){
    	  $('select[name=emailSelection]').change(function() {
				if($(this).val()=="1"){
					$('#email_domain').val("");
				} else {
					$('#email_domain').val($(this).val());
					$("#email_domain").attr("readonly", true);
				}
			});
       
    	}); // end function 
      
     

	
	</script>
      
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
                		$('#detailAddress').blur(function(){
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
                		});
					      
            		}
        		}).open();
    		}
    		
    		

</script>
<%@ include file="../footer.jspf" %> 
</html>