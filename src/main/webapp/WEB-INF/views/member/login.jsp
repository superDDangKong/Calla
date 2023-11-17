<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<style type="text/css">
/* body {
      font-family: 'Arial', sans-serif;
      background-color: #f4f4f4;
      display: flex;
      align-items: center;
      justify-content: center;
      height: 100vh;
      margin: 0;
    } */

    .custom-checkbox {
       display: inline-block;
      position: relative;
      padding-left: 35px;
      cursor: pointer;
      font-size: 16px;
      user-select: none;
    }

    .custom-checkbox input {
      position: absolute;
      opacity: 0;
      cursor: pointer;
    }

    .checkmark {
      position: absolute;
      top: 0;
      left: 0;
      height: 20px;
      width: 20px;
      background-color: #eee;
      border-radius: 3px;
      border: 1px solid #ccc;
    }

     .custom-checkbox input:checked + .checkmark:after {
      content: "\2713"; /* 체크 마크 아이콘 (유니코드) */
      position: absolute;
      display: block;
      top: 50%;
      left: 50%;
      transform: translate(-50%, -50%);
      font-size: 14px;
      color: #2196F3;
    } 
    


</style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="../header.jspf" %> 
<title>로그인</title>
</head>
<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title text-center">로그인</h4>
                         <form action="login" method="POST"> 
                            <div class="form-group">
                                <label for="memberId">아이디:</label>
                                <input type="text" id="memberId" name="memberId"  class="form-control">
                            </div>
                            <div class="form-group">
                                <label for="memberPw">비밀번호:</label>
                                <input type="password" id="memberPw" name="memberPw" class="form-control">
                            </div>
                            <button type="submit" class="btn btn-primary btn-block">로그인</button>
                            <c:if test="${not empty param.targetURL}">
                                <input type="hidden" id="targetURL" name="targetURL" value="${param.targetURL}">
                            </c:if>
                            <label class="custom-checkbox">아이디 저장
                            	<input id="rememberId" name="rememberId" type="checkbox" >
                            	<span class="checkmark"></span>
                            </label>
                        </form> 
                        <div class="text-center mt-3">
                            <a href="searchMemberInfo">아이디·비밀번호 찾기</a> 
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

	<input type="hidden" name="searchResult" id="searchResult" value="${searchResult }">
	<input type="hidden" name="searchId" id="searchId" value="${searchId }">
	<input type="hidden" name="searchPw" id="searchPw" value="${searchPw }">
	
	
	<script type="text/javascript">
		var searchResult = $('#searchResult').val();
		var searchId = $('#searchId').val();
		var searchPw = $('#searchPw').val();
		console.log('searchResult = ' + searchResult);
		console.log('searchId = ' + searchId);
		console.log('searchPw = ' + searchPw);
		if(searchResult == 'idSearch') {
			alert('회원님의 아이디는   ' + searchId + '    입니다');
		} else if(searchResult == 'pwSearch') {
			alert('회원님의 비밀번호는   ' + searchPw + '    입니다');
		}
		
		$(document).ready(function() {
            // 페이지 로드 시, localStorage에서 아이디를 가져와서 입력 필드에 설정
            var memberIdInput = $("#memberId");
            var rememberIdCheckbox = $("#rememberId");

            var savedId = localStorage.getItem("savedId");
            if (savedId) {
                memberIdInput.val(savedId);
                rememberIdCheckbox.prop("checked", true);
            }
            
            $(".btn-block").on("click", function() {
                var memberId = memberIdInput.val();
                var rememberId = rememberIdCheckbox.prop("checked");

                // 체크박스가 체크된 경우, 아이디를 localStorage에 저장
                if (rememberId) {
                    localStorage.setItem("savedId", memberId);
                    console.log('아이디가 localStorage에 저장되었습니다.');
                } else {
                    localStorage.removeItem("savedId");
                    console.log('localStorage에서 아이디가 삭제되었습니다.');
                }

                // 여기에서 로그인 로직을 수행하면 됩니다.
                // 로그인 성공 후의 동작을 추가하세요.
            });
		})
            
		/* $('#rememberId').change(function(){
			var rememberId = $('#rememberId').is(':checked'); // .is(':checked') 해당 요소에 체크가 되어있는지 여부를 판단하는 jquery 메서드
			var memberId = $('#memberId').val();
			console.log("아이디: " + memberId);
			console.log("아이디저장체크여부: " + rememberId);
			if(rememberId){
				localStorage.setItem("savedId", memberId);
				console.log('아이디가 localStorage에 ' + memberId + '가 저장되었습니다.');
			}
		})  */
		
		
		
	</script>
<%@ include file="../footer.jspf" %> 
</body>
</html>