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
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="../header.jspf"%>

<style type="text/css">
table, th, td {
	border-style: solid;
	border-width: 1px;
	text-align: center;
}
</style>
<title>회원 정보 수정</title>

</head>
<body>

	<div class="container-fluid">
		<div class="row">
			<%@ include file="../sidebar2.jspf"%>

			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
				<div class="container mt-4">
					<h2>회원 정보 수정</h2>
					<input type="hidden" id="memberId" value='${vo.memberId}'>

					<table class="table table-bordered">
						<tbody>
							<tr>
								<th scope="row">아이디</th>
								<td>${vo.memberId}</td>
							</tr>
							<tr>
								<th scope="row">회원 등급</th>
								<td>${vo.memberLevel}</td>
							</tr>
							<tr>
								<th scope="row">이름</th>
								<td>${vo.memberName}</td>
							</tr>
							<tr>
								<th scope="row">닉네임</th>
								<td>
									<div class="form-group">
										<input type="text" class="form-control" id="memberNickname" value='${vo.memberNickname}' readonly>
									</div>
									<div class="form-group">
										<input type="text" class="form-control" id="newNickname" required>
									</div>
									<div class="form-group">
										<button class="btn btn-primary" id="btnUpdateNickname" disabled>닉네임 변경</button>
									</div>
									<div id="newNicknameEffect" style="color: red;"></div>
								</td>
							</tr>
							<tr>
								<th scope="row">비밀번호</th>
								<td>
									<table>
										<tbody>
											<tr>
												<th scope="row">현재 비밀번호</th>
												<td><input type="password" class="form-control"	id="currentPw" required></td>
											</tr>
											<tr>
												<th scope="row">새 비밀번호</th>
												<td><input type="password" class="form-control"	id="newPw" required></td>
											</tr>
											<tr>
												<td><div id="newPwEffect" style="color: red"></div></td>
											</tr>
											<tr>
												<th scope="row">비밀번호 다시 입력</th>
												<td><input type="password" class="form-control"
													id="newPwCheck" required></td>
											</tr>
											<tr>
												<td><div id="newPwCheckEffect" style="color: red"></div></td>
											</tr>
											<tr>
												<td></td>
												<td><button class="btn btn-primary" id="btnUpdatePw" disabled>비밀번호 변경</button></td>
											</tr>
										</tbody>
									</table>
								</td>
							</tr>
							<tr>
								<th scope="row">휴대폰 번호</th>
								<td>
									<div class="form-group">
										<input type="text" class="form-control" id="memberPhone" value='${vo.memberPhone}' readonly>
									</div>
									<div class="form-group">
										<input type="text" class="form-control" id="newPhone" required>
									</div>
									<div class="form-group">
										<button class="btn btn-primary" id="btnUpdatePhone" disabled>번호 변경</button>
									</div>
									<div id="newPhoneEffect" style="color: red;"></div>
								</td>
							</tr>
							<tr>
								<th scope="row">이메일</th>
								<td>
									<div class="form-group">
										<input type="text" class="form-control" id="memberEmail" value='${vo.memberEmail}' readonly>
									</div>
									<div class="input-group form-group">
										<input type="text" class="form-control" id="email_id" placeholder="calla" required>
										<div class="input-group-prepend">
											<span class="input-group-text">@</span>
										</div>
										<input type="text" class="form-control" id="email_domain" placeholder="naver.com" required>
									</div> 
									<input type="hidden" id="newMemberEmail">
									<button class="btn btn-primary" id="btnUpdateEmail" disabled>이메일 변경</button>
									<div id="newEmailEffect" style="color: red;"></div>
								</td>
							</tr>
							<tr>
								<th scope="row">관심사</th>
								<td>
									<div class="form-group">
										<input type="text" class="form-control" id="memberInterest"	value='${vo.memberInterest}' readonly>
									</div>
									<div class="form-check form-group">
										<input type="checkbox" class="form-check-input"	id="interest_cartoon" value="만화"> 
										<label class="form-check-label" for="interest_cartoon">만화</label>
									</div>
									<div class="form-check form-group">
										<input type="checkbox" class="form-check-input" id="interest_goods" value="굿즈"> 
										<label class="form-check-label" for="interest_goods">굿즈</label>
									</div>
									<div class="form-check form-group">
										<input type="checkbox" class="form-check-input"	id="interest_character" value="캐릭터"> 
										<label class="form-check-label" for="interest_character">캐릭터</label>
									</div>
									<button class="btn btn-primary" id="btnUpdateInterest">관심사	변경</button> 
									<input type="hidden" id="newMemberInterest">
								</td>
							</tr>
							<tr>
								<th scope="row">주소</th>
								<td>
									<div class="form-group">
										<input type="text" class="form-control" id="memberAddress" value='${vo.memberAddress}' readonly>
									</div> 
									<input type="hidden" id="newMemberAddress">
									<div class="form-group">
										<input type="text" class="form-control" id="postcode" placeholder="우편번호" required readonly>
										<button class="btn btn-primary" onclick="execDaumPostcode()">우편번호	찾기</button>
									</div>
									<div class="form-group">
										<input type="text" class="form-control" id="address" placeholder="주소" required readonly> 
										<input type="text" class="form-control" id="detailAddress" placeholder="상세주소">
									</div>
									<div class="form-group">
										<input type="text" class="form-control" id="extraAddress" placeholder="참고항목" required readonly>
										<button class="btn btn-primary" id="btnUpdateAddress" disabled>주소 변경</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
					<c:if test="${memberLevel != 3 }">
						<button class="btn btn-danger" id="btnDeleteMember">
							<a href="delete?memberId=${memberId}">회원 탈퇴</a>
						</button>
					</c:if>
				</div>
			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>
	<script type="text/javascript">
		var category = "";
	    $('#newPw').on('keyup', function () {
	        validatePasswords();
	    });
	
	    $('#newPwCheck').on('keyup', function () {
	        validatePasswords();
	    });
	
	    function validatePasswords() {
	        var newPw = $('#newPw').val();
	        var newPwCheck = $('#newPwCheck').val();
	        var pwEffectiveness = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*])[A-Za-z\d!@#$%^&*]{8,}$/;
	        var newPwValid = pwEffectiveness.test(newPw);
	        var newPwCheckValid = newPw === newPwCheck;
	
	        $('#newPwEffect').html(
	            newPwValid ? "비밀번호 사용이 가능합니다." : "영문, 숫자, 특수기호(!@#$%^&*) 모두 조합하여 8글자 이상."
	        );
	
	        if (newPwCheck !== "") {
	            $('#newPwCheckEffect').html(
	                newPwCheckValid ? "비밀번호가 일치합니다." : "비밀번호가 일치하지 않습니다."
	            );
	        }
	
	        if (newPwValid && newPwCheckValid) {
	            $('#btnUpdatePw').prop("disabled", false);
	        } else {
	            $('#btnUpdatePw').prop("disabled", true);
	        }
	    } // end validatePasswords
	
	    $('#btnUpdatePw').click(function () {
	        var currentPw = $('#currentPw').val();
	        var newPw = $('#newPw').val();
	        var newPwCheck = $('#newPwCheck').val();
	        var memberId = $('#memberId').val();
	        var obj = {
	            'currentPw': currentPw,
	            'newPw': newPw,
	            'newPwCheck': newPwCheck
	        };
	
	        $.ajax({
	            type: 'PUT',
	            url: 'updatepw/' + memberId,
	            headers: {
	                'Content-Type': 'application/json'
	            },
	            data: JSON.stringify(obj),
				beforeSend: function() {
					$('#loadingContainer').remove();
					
					var loadingContainer = $('<div id="loadingContainer"><div class="loading"></div></div>');
					$('body').append(loadingContainer);
					$('#loadingContainer').css('display','block');
				},
				complete: function() {
					$('#loadingContainer').css('display','none');	
				},
	            success: function (result) {
	                console.log(result);
	                if (result == 1) {
	                    alert('비밀번호 수정 성공');
	                    $('#newPwCheckEffect').html("");
	                    $('#newPwEffect').html("");
	                } else {
	                    alert('현재 비밀번호가 다릅니다.');
	                    $('#currentPw').val("");
	                }
	            }
	        });
	    }); // end btnUpdatePw
	
	    $('#newNickname').on('keyup', function () {
	        $('#btnUpdateNickname').prop("disabled", true);
	        var memberNickname = $('#newNickname').val();
	        var nicknameEffectiveness = /^[가-힣ㄱ-ㅎa-zA-Z0-9]{2,20}$/;
	        var newNicknameValid = nicknameEffectiveness.test(memberNickname);
	        var newNicknameCheck = false;
	
	        $.ajax({
	            type: 'POST',
	            url: 'checkNick',
	            data: {
	                memberNickname: memberNickname
	            },
				beforeSend: function() {
					$('#loadingContainer').remove();
					
					var loadingContainer = $('<div id="loadingContainer"><div class="loading"></div></div>');
					$('body').append(loadingContainer);
					$('#loadingContainer').css('display','block');
				},
				complete: function() {
					$('#loadingContainer').css('display','none');	
				},
	            success: function (result) {
	                console.log(result);
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
	            }
	        });
	
	        if (!newNicknameValid) {
	            $('#newNicknameEffect').html('영문/한글/숫자 2글자 이상으로 입력해주세요');
	        }
	    }); 
	
	    $('#btnUpdateNickname').click(function () {
	        var newNickname = $('#newNickname').val();
	        var memberId = $('#memberId').val();
			category = "member_nickname";
			var obj = {
					'newData' : newNickname,
					'category' : category
			}
			
			$.ajax({
	            type: 'PUT',
	            url: 'update/' + memberId,
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(obj),
				beforeSend: function() {
					$('#loadingContainer').remove();
					
					var loadingContainer = $('<div id="loadingContainer"><div class="loading"></div></div>');
					$('body').append(loadingContainer);
					$('#loadingContainer').css('display','block');
				},
				complete: function() {
					$('#loadingContainer').css('display','none');	
				},
	            success: function (result) {
	                console.log(result);
	                if (result == 1) {
	                    alert('닉네임 수정 성공');
	                    $('#memberNickname').val(newNickname);
	                    $('#newNicknameEffect').html("");
	                }
	            }
	        }); // end ajax
	    }); // end btnUpdateNickname
	
	    $('#newPhone').on('keyup', function () {
	        $('#btnUpdatePhone').prop("disabled", true);
	        var newPhone = $('#newPhone').val();
	        var phoneEffectiveness = /^01[0-9]-?[0-9]{3,4}-?[0-9]{4}$/;
	        var newPhoneValid = phoneEffectiveness.test(newPhone);
	
	        $('#newPhoneEffect').html(
	            newPhoneValid ? '사용가능한 전화번호 입니다' : ' 01로 시작하는 8~9자리 숫자를 입력해 주세요'
	        );
	
	        if (newPhoneValid) {
	            $('#btnUpdatePhone').prop("disabled", false);
	        }
	    });
	
	    $('#btnUpdatePhone').click(function () {
	        var newPhone = $('#newPhone').val();
	        var memberId = $('#memberId').val();
			category = "member_phone";
			var obj = {
					'newData' : newPhone,
					'category' : category
			}
			
			$.ajax({
	            type: 'PUT',
	            url: 'update/' + memberId,
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(obj),
				beforeSend: function() {
					$('#loadingContainer').remove();
					
					var loadingContainer = $('<div id="loadingContainer"><div class="loading"></div></div>');
					$('body').append(loadingContainer);
					$('#loadingContainer').css('display','block');
				},
				complete: function() {
					$('#loadingContainer').css('display','none');	
				},
	            success: function (result) {
	                console.log(result);
	                if (result == 1) {
	                    alert('연락처 수정 성공');
	                    $('#memberPhone').val(newPhone);
	                    $('#newPhoneEffect').html("");
	                }
	            }
	        });
	    });
	
	    $('#email_id').on('keyup', function () {
	        validateEmail();
	    });
	
	    $('#email_domain').on('keyup', function () {
	        validateEmail();
	    });
	
	    $('#domain_list').change(function () {
	        if ($(this).val() == "-직접입력-") {
	            $('#email_domain').val("");
	            $("#email_domain").attr("readonly", false);
	        } else {
	            $("#email_domain").val($(this).val());
	            $("#email_domain").attr("readonly", true);
	        }
	        validateEmail();
	    });
	
	    $('#btnUpdateEmail').click(function () {
	        var newMemberEmail = $('#newMemberEmail').val();
	        var memberId = $('#memberId').val();
			category = "member_email";
			var obj = {
					'newData' : newMemberEmail,
					'category' : category
			}
			
			$.ajax({
	            type: 'PUT',
	            url: 'update/' + memberId,
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(obj),
				beforeSend: function() {
					$('#loadingContainer').remove();
					
					var loadingContainer = $('<div id="loadingContainer"><div class="loading"></div></div>');
					$('body').append(loadingContainer);
					$('#loadingContainer').css('display','block');
				},
				complete: function() {
					$('#loadingContainer').css('display','none');	
				},
	            success: function (result) {
	                console.log(result);
	                if (result == 1) {
	                    alert('이메일 수정 성공');
	                    $('#memberEmail').val(newMemberEmail);
	                    $('#newEmailEffect').html("");
	                }
	            }
	        });
	    });
	
	    function validateEmail() {
	        var emailEffectiveness = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
	        var emailId = $('#email_id').val();
	        var emailDomain = $('#email_domain').val();
	        var newEmail = emailId + '@' + emailDomain;
	        $('#newMemberEmail').val(newEmail);
	
	        if (emailEffectiveness.test(newEmail)) {
	            $('#newEmailEffect').text('사용가능한 이메일입니다.');
	            $('#btnUpdateEmail').attr('disabled', false);
	        } else {
	            $('#newEmailEffect').text('잘못된 이메일 형식입니다.');
	            $('#btnUpdateEmail').attr('disabled', true);
	        }
	    } // end validateEmail
	
	    $('#btnUpdateInterest').click(function () {
	        var newMemberInterest = "";
	        newMemberInterest += $('#interest_goods').prop('checked') ? $('#interest_goods').val() + " " : "";
	        newMemberInterest += $('#interest_character').prop('checked') ? $('#interest_character').val() + " " : "";
	        newMemberInterest += $('#interest_cartoon').prop('checked') ? $('#interest_cartoon').val() + " " : "";
			category = "member_interest";
			var memberId = $('#memberId').val();
			var obj = {
					'newData' : newMemberInterest,
					'category' : category
			}
			if(newMemberInterest != "") {
				
				$.ajax({
		            type: 'PUT',
		            url: 'update/' + memberId,
	                headers: {
	                    'Content-Type': 'application/json'
	                },
	                data: JSON.stringify(obj),
					beforeSend: function() {
						$('#loadingContainer').remove();
						
						var loadingContainer = $('<div id="loadingContainer"><div class="loading"></div></div>');
						$('body').append(loadingContainer);
						$('#loadingContainer').css('display','block');
					},
					complete: function() {
						$('#loadingContainer').css('display','none');	
					},
		                success: function (result) {
		                    console.log(result);
		                    if (result == 1) {
		                        alert('관심사 수정 성공');
		                        $('#memberInterest').val(newMemberInterest);
		                    }
		                } // end success
		            }); // end ajax
	        } else {
	            alert('관심사를 한가지 이상 선택해 주세요');
	        }
	    });
	
	    $('#btnUpdateAddress').click(function () {
	        var newMemberAddress = $('#newMemberAddress').val();
	        var memberId = $('#memberId').val();
			category = "member_address";

			var obj = {
					'newData' : newMemberAddress,
					'category' : category
			}
			
			$.ajax({
	            type: 'PUT',
	            url: 'update/' + memberId,
                headers: {
                    'Content-Type': 'application/json'
                },
                data: JSON.stringify(obj),
				beforeSend: function() {
					$('#loadingContainer').remove();
					
					var loadingContainer = $('<div id="loadingContainer"><div class="loading"></div></div>');
					$('body').append(loadingContainer);
					$('#loadingContainer').css('display','block');
				},
				complete: function() {
					$('#loadingContainer').css('display','none');	
				},
	            success: function (result) {
	                console.log(result);
	                if (result == 1) {
	                    alert('주소 수정 성공');
	                    $('#memberAddress').val(newMemberAddress);
	                }
	            }
	        });
	    });
	</script>
	<%@ include file="../footer.jspf"%>
</body>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	new daum.Postcode({
		onclose : function(state) {
			//state는 우편번호 찾기 화면이 어떻게 닫혔는지에 대한 상태 변수 이며, 상세 설명은 아래 목록에서 확인하실 수 있습니다.
			if (state === 'FORCE_CLOSE') {
				//사용자가 브라우저 닫기 버튼을 통해 팝업창을 닫았을 경우, 실행될 코드를 작성하는 부분입니다.

			} else if (state === 'COMPLETE_CLOSE') {
				//사용자가 검색결과를 선택하여 팝업창이 닫혔을 경우, 실행될 코드를 작성하는 부분입니다.
				//oncomplete 콜백 함수가 실행 완료된 후에 실행됩니다.
			}
		}
	});
</script>
<script>
	function execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
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
						if (data.userSelectedType === 'R') {
							// 법정동명이 있을 경우 추가한다. (법정리는 제외)
							// 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
							if (data.bname !== ''
									&& /[동|로|가]$/g.test(data.bname)) {
								extraAddr += data.bname;
							}
							// 건물명이 있고, 공동주택일 경우 추가한다.
							if (data.buildingName !== ''
									&& data.apartment === 'Y') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
							if (extraAddr !== '') {
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
						var newMemberAddress = postcode + " " + address + " "
								+ detailAddress + extraAddress;

						$('#btnUpdateAddress').prop("disabled", false);
						document.getElementById("newMemberAddress").value = newMemberAddress;

					}
				}).open();
	}
</script>
</html>