<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="../header.jspf"%>
<title>아이디 찾기</title>
<style type="text/css">
body {
	font-family: 'Arial', sans-serif;
	background-color: #f5f5f5;
	margin: 0;
	padding: 0;
}

.SingleLayout__DesktopWrap-sc-105bm2c-1 {
	max-width: 600px;
	margin: auto;
	background-color: #fff;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	margin-top: 50px;
}

.Title-sc-5pqo3e-0 {
	font-size: 24px;
	color: #333;
	margin-bottom: 20px;
}

.ButtonTabs__ButtonTab-sc-jaxi9y-0 {
	display: flex;
	margin-bottom: 20px;
}

.ButtonTabs__ButtonTab-sc-jaxi9y-0 button {
	flex: 1;
	padding: 10px;
	background-color: transparent;
	border: none;
	outline: none;
	cursor: pointer;
	font-size: 16px;
	color: #333;
	border-bottom: 2px solid transparent;
}

.ButtonTabs__ButtonTab-sc-jaxi9y-0 button.active {
	border-bottom: 2px solid #3498db;
	color: #3498db;
	font-weight: bold;
}

form {
	width: 100%;
}

.style__FormController-sc-sshr17-2 {
	margin-bottom: 20px;
}

.Input__InputWrap-sc-tapcpf-1 {
	position: relative;
	margin-bottom: 10px;
}

label {
	display: block;
	margin-bottom: 5px;
	font-size: 14px;
	color: #333;
}

input {
	width: 100%;
	padding: 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	box-sizing: border-box;
	font-size: 14px;
	outline: none;
}

.Button__StyledButton-sc-1cxc4dz-0 {
	background-color: #3498db;
	color: #fff;
	padding: 10px;
	border: none;
	border-radius: 5px;
	font-size: 16px;
	cursor: pointer;
}

.Button__StyledButton-sc-1cxc4dz-0:hover {
	background-color: #297fb8;
}
/* 클릭된 상태의 버튼 스타일 */
.Button__StyledButton-sc-1cxc4dz-0.active1 {
	background-color: #297fb8 !important;
}

.Button__StyledButton-sc-1cxc4dz-0.active2 {
	background-color: #297fb8 !important;
}


</style>
</head>
<body>
	<!-- <form action="searchId" method="post">
		이름 : <input type="text" name="memberName" required>
		이메일 : <input type="text" name="memberEmail" required>
		<input type="submit" value="아이디 찾기">
	</form> -->
	<div class="SingleLayout__DesktopWrap-sc-105bm2c-1 aVWRH py-6 px-5 px-sm-3 ">
		<div>
			<h1 class="Title-sc-5pqo3e-0 fopepa mb-8">아이디 · 비밀번호 찾기</h1>
			<div class="ButtonTabs__ButtonTab-sc-jaxi9y-0 bSEoaK mb-4">
				<button class="active1">아이디 찾기</button>
				<button class="active2">비밀번호 찾기</button>
			</div>
			<form action="searchId" method="post">
				<div class="style__FormController-sc-sshr17-2 iZjoOv">
					<div class="Input__InputWrap-sc-tapcpf-1 kjWnKT">
						<label id="condition">이름</label>
						<span class="Input__InputStateWrap-sc-tapcpf-0 bSpIrI">
							<input type="text" name="memberName" placeholder="이름 입력">
							<i class="icon-font icon-font-warning"></i>
						</span>
					</div>
				</div>
				<div class="style__FormController-sc-sshr17-2 iZjoOv">
					<div class="Input__InputWrap-sc-tapcpf-1 kjWnKT">
						<label id="condition1">이메일</label>
						<span class="Input__InputStateWrap-sc-tapcpf-0 bSpIrI">
							<input type="email" name="memberEmail" placeholder="이메일 입력">
							<i class="icon-font icon-font-warning"></i>
						</span>
					</div>
				</div>
				<button type="submit" target="_self" class="Button__StyledButton-sc-1cxc4dz-0 hlLPsc">아이디 찾기</button>
			</form>
		</div>
	</div> 
	
	
	<input type="hidden" name="searchResult" id="searchResult"
		value="${searchResult }">
	<input type="hidden" name="searchId" id="searchId" value="${searchId }">
	<script type="text/javascript">
		var searchResult = $('#searchResult').val();
		var searchId = $('#searchId').val();
		if (searchResult == 'fail') {
			alert('일치하는 회원 정보가 없습니다.')
		}

		$(document).ready(function() {
			$('.active1').click(function(){
				console.log("아이디 찾기");
				$('#condition').text("이름");
				$('input[name="memberName"]').attr('placeholder', '이름 입력');
				$('#condition1').text("이메일");
				$('input[name="memberEmail"]').attr('placeholder', '이메일 입력');
				$('input[name="memberEmail"]').attr('type', 'email');
				$('.Button__StyledButton-sc-1cxc4dz-0').text('아이디 찾기');

			})
			
			$('.active2').click(function(){
				console.log("비밀번호 찾기");
				$('#condition').text("아이디");
				$('input[name="memberName"]').attr('placeholder', '아이디 입력');
				$('#condition1').text("전화번호");
				$('input[name="memberEmail"]').attr('placeholder', '전화번호 입력');
				$('input[name="memberEmail"]').attr('type', 'tel');
				$('.Button__StyledButton-sc-1cxc4dz-0').text('비밀번호 찾기');

			})
			
		});
		
		
		
		
	</script>

	<%@ include file="../footer.jspf"%>
</body>
</html>