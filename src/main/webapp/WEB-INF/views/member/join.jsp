<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">
.final_id_ck1 {
	display : none;
}

.final_id_ck2 { 
	display : none;
}

.final_pw_ck1 {
	display : none;
}

.final_pw_ck2 {
	display : none;
}

.final_nick_ck1 {
	display : none;
}

.final_interest_ck {
	display : none;
}

.final_add_ck {
	display : none;
}
</style>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>

<div style="text-align:center">
	<form name="join" action="join" method="post">
		<div>아이디
		<input type="text" id="member_id" name="member_id" placeholder="아이디 입력"><br>
		<span class="final_id_ck1">사용할 수 없는 아이디입니다. 다른 아이디를 입력해 주세요.</span>
		<span class="final_id_ck2">아이디: 8자 이상의 영문, 숫자와 특수기호만 사용 가능합니다.</span><!-- 아이디 유효성 확인 -->
		</div><br>
		
		<div>비밀번호
		<input type="password" id="member_pw" name="member_pw"><br>
		<span class="final_pw_ck1">비밀번호: 8자 이상의 영문, 숫자, 특수문자를 사용해 주세요.</span><!-- 비밀번호 유효성 확인 -->
		</div><br>
		
		<div>비밀번호 재확인
		<input type="password" id="member_pw_ck" name="member_pw_check"><br>
		<span class="final_pw_ck2">비밀번호가 틀렸습니다.</span><!-- 입력한 비밀번호와 똑같은지 체크 -->
		</div><br>
		
		<div>이름
		<input type="text" id="member_name" name="member_name" placeholder="홍길동"><br>
		</div><br>
		
		<div>닉네임
		<input type="text" id="member_nickname" name="member_nickname" placeholder="calla"><br>
		<span class="final_nick_ck1">중복된 닉네임입니다.</span>
		</div><br>
		
		<div>이메일
				<input type="text" name="member_email1" id="email_id" placeholder="calla">@
				<input type="text" name="member_email2" id="email_domain" class="box" placeholder="naver.com">
				<select class="box" id="domain-list" name="emailSelection" onchange="select_change(this.value);">
					<option value="type">-직접입력-</option>
					<option value="naver.com">naver.com</option>
  					<option value="gmail.com">gmail.com</option>
  					<option value="hanmail.net">hanmail.net</option>
  					<option value="nate.com">nate.com</option>
  					<option value="kakao.com">kakao.com</option>
				</select>
				<span class="final_email_ck"></span><!-- 이메일 형식대로 입력하라는 문장출력 -->
		</div><br>
		
		<div>핸드폰
        <input type="tel" id="member_phone" name="member_phone" placeholder="010-1234-5678"><br>
        </div><br>
        
        <div>관심사
      	만화<input type="checkbox" class="check" value="만화">
      	굿즈<input type="checkbox" class="check" value="굿즈">
      	애니<input type="checkbox" class="check" value="애니"><br>
      	<span class="final_interest_ck">관심사를 선택해주세요.</span><!-- 관심사를 선택해주세요 -->
      	</div><br>
      	
      	<br>
      	<div>주소</div> <!-- 주소api -->
      	<input type="text" id="sample6_postcode" placeholder="우편번호">
		<input type="button" onclick="sample6_execDaumPostcode()" value="우편번호 찾기"><br>
		<input type="text" id="sample6_address" placeholder="주소">
		<input type="text" id="sample6_detailAddress" placeholder="상세주소"><br>
		<input type="text" id="sample6_extraAddress" placeholder="참고항목">
		<br>
		<input type="submit" name="join_button" id="join_button" value="가입완료">
		<span class="final_add_ck">주소를 확인해주세요.</span><!-- 주소를 확인해주세요 -->
      
      
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
      var addressCheck = false         	// 주소
      
      // 아이디 유효성 검사 + 중복체크
      $('#member_id').blur(function() {
       var memberId = $('#member_id').val(); // 클라이언트가 입력한 아이디 변수에 저장
       console.log("입력한 아이디 : " + memberId); // 입력한 아이디 콘솔에 띄우기
    	    // 아이디 정규식
       var idEffectiveness = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
    	// 아이디 유효성 확인
    	if (idEffectiveness.test(memberId)){
    		console.log("아이디 유효성 검사 통과");
    		idckcorCheck = true; // 아이디 유효성 검사 통과 변수
    		// 중복확인 ajax
    		$.ajax({ // JoinRestController의 checkId 송수신
        		 type : 'POST',
        		 url : '/calla/member/checkId', // 뭐라 할까나 ~ 
        		 data : {memberId : memberId},
        		 success : function(result){
        			 console.log(result); // 조건문 사용해서 css효과줘서 아이디 사용불가 가능 표현 만들기
        			 if (result == 1) { // result 1은 중복된 아이디가 존재, 0은 없다는 뜻
        				 console.log("사용불가능한아이디")
        				 idckCheck = false; // 아이디 중복검사 통과 변수
        			 } else {
        				 console.log("사용가능한아이디")
        				 idckCheck = true; // 아이디 중복검사 통과 변수
        			 }
        		 } // end success
        	 }) // end ajax
        	 
    	} else {
    		console.log("아이디 유효성 검사 실패");
    		idckcorCheck = false; // 아이디 유효성 검사 실패 변수
    		
    	}
       
       				 
    	}); // end 아이디 function(아이디 유효성 검사 + 중복체크)

    	// 비밀번호 유효성 검사 
    	$('#member_pw').blur(function() {
    		var memberPw = $('#member_pw').val(); // 클라이언트가 입력한 비밀번호 변수에 저장
    		console.log("입력한 비밀번호 : " + memberPw); // 입력한 비밀번호 콘솔에 띄우기
    		// 비밀번호 정규식
    		var pwEffectiveness = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
    		if (pwEffectiveness.test(memberPw)){
    			console.log("비밀번호 유효성 검사 통과"); // 조건문 사용해서 css효과줘서 아이디 사용불가 가능 표현 만들기
    			pwckCheck = true;
    		} else {
    			console.log("비밀번호 유효성 검사 실패");
    			pwckCheck = false;
    		}
    	}); // end 비밀번호 function(비밀번호 유효성 검사)
		
      	// 비밀번호 재확인
      	$('#member_pw_ck').blur(function(){
      		var memberPw = $('#member_pw').val(); // 유효성 검사 통과한 비밀번호
      		var memberPwCk = $('#member_pw_ck').val(); // 비밀번호 재확인
      		
      		if (memberPw === memberPwCk) {
      			console.log("비밀번호 재확인 성공");
      			pwckcorCheck = true; // 비밀번호 재확인 성공 변수 
      		} else {
      			console.log("비밀번호 재확인 실패");
      			pwckcorCheck = false; // 비밀번호 재확인 실패 변수
      		}
      	}); // end 재확인 function(비밀번호 재확인)
      	
      	// 이름 입력확인
		$('#member_name').blur(function(){
			var memberName = $('#member_name').val(); // 입력한 이름
			// 이름 정규식
			var nameEffectiveness = /[\p{Script=Hangul}\p{Script=Latin}]{1,}/gu;

			if (nameEffectiveness.test(memberName)){
				console.log("이름 입력 성공");
				nameCheck = true;
				console.log(nameCheck);
			} else {
				console.log("이름 입력 실패");
				console.log(nameCheck);
				nameCheck = false;
			}
		}) // end 이름 입력확인
      	
		$('#member_nickname').blur(function(){
			var memberNickname = $('#member_nickname').val();
			// 닉네임 정규식
			var nicknameEffectiveness = /[\p{Script=Hangul}\p{Script=Latin}]{1,}/gu;
			
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
        			 } else {
        				 console.log("사용가능한닉네임")
        				 nickNameCheck = true;
        			 }
        		 } // end success
        	 }) // end ajax
			} else {
				console.log("입력한 닉네임 : " + memberNickname);
			}
		}) // end 닉네임 중복확인
		
		
		
      
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
      
     $('#member_phone').blur(function(){
    	 var memberPhone = $('#member_phone').val();
    	 var phoneEffectiveness = /01[0-9]-?[0-9]{3,4}-?[0-9]{4}/;
		 
    	 // 연락처 입력 유무 검사
    	 if (phoneEffectiveness.test(memberPhone)){
    		 console.log(memberPhone);
    		 phoneCheck = true;
    	 } else {
    		 phoneCheck = false; 
    	 }
    	 console.log(memberPhone)
     }) // end 입력한 핸드폰 번호
     
      

       
       
      
    
	
	

    

	
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
                    		document.getElementById("sample6_extraAddress").value = extraAddr;
                
                		} else {
                    		document.getElementById("sample6_extraAddress").value = '';
                		}

                		// 우편번호와 주소 정보를 해당 필드에 넣는다.
                		document.getElementById('sample6_postcode').value = data.zonecode;
                		document.getElementById("sample6_address").value = addr;
                		// 커서를 상세주소 필드로 이동한다.
                		document.getElementById("sample6_detailAddress").focus();
            		}
        		}).open();
    		}
</script>
</html>