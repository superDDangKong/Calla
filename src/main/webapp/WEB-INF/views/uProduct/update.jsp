<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://code.jquery.com/jquery-3.7.1.slim.js"
	integrity="sha256-UgvvN8vBkgO0luPSUl2s8TIlOSYRoGFAX4jlCIm9Adc="
	crossorigin="anonymous"></script>
<%@ page import="org.springframework.web.multipart.MultipartFile" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상품 등록 페이지</title>
<script src="https://code.jquery.com/jquery-3.7.1.slim.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
</head>
<body>
	<h2>상품 등록</h2>
	<form action="update" method="post" enctype="multipart/form-data">
		<input type="hidden" name="page" value="${page }">
		<input type="hidden" name="uProductId" value="${vo.uProductId }">
		<div>
			<p>닉네임</p>
			<input type="text" name="memberNickname" value="${sessionScope.memberNickname }" readonly="readonly">
		</div>
		<div>
			<p>상품 제목</p>
			<input type="text" name="uProductName" placeholder="이름 입력" required>
		</div>
		<div>
			<p>작성자 주소</p>
				<input type="text" id="sample4_postcode" placeholder="우편번호">
				<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="sample4_roadAddress" placeholder="도로명주소">
				<input type="text" id="sample4_jibunAddress" placeholder="지번주소">
				<span id="guide" style="color:#999;display:none"></span>
				<input type="text" id="sample4_detailAddress" placeholder="상세주소">
				<input type="text" id="sample4_extraAddress" placeholder="참고항목">
				<input type="hidden" id="memberAddress" name="memberAddress">
		</div>
		<div>
			<p>가격</p>
			<input type="text" name="uProductPrice" placeholder="가격" required>
		</div>
		<div>
			<p>카테고리</p>
			<input type="text" name="uProductCategori" placeholder="카테고리" required>
		</div>
		<div>
			<p>내용</p>
			<input type="text" name="uProductContent" placeholder="내용" required>
		</div>
		<div>
			<p>이미지</p>
			<input id="productImage" type="file" name="productImage" placeholder="이미지" >
			
		</div>
		
		<div>
			<input type="submit" value="등록">
		</div>
		
		
	</form>
	
	
	<script>
    function sample4_execDaumPostcode() {
        new daum.Postcode({
            oncomplete: function(data) {
                var roadAddr = data.roadAddress;
                var extraRoadAddr = '';

                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraRoadAddr += data.bname;
                }

                if(data.buildingName !== '' && data.apartment === 'Y'){
                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }

                document.getElementById('sample4_postcode').value = data.zonecode;
                document.getElementById("sample4_roadAddress").value = roadAddr;
                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;

                if(roadAddr !== ''){
                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
                } else {
                    document.getElementById("sample4_extraAddress").value = '';
                }

                var guideTextBox = document.getElementById("guide");

                if(data.autoRoadAddress) {
                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
                    guideTextBox.innerHTML = '(예상 도로명 주소: ' + expRoadAddr + ')';
                    guideTextBox.style.display = 'block';
                } else if(data.autoJibunAddress) {
                    var expJibunAddr = data.autoJibunAddress;
                    guideTextBox.innerHTML = '(예상 지번 주소: ' + expJibunAddr + ')';
                    guideTextBox.style.display = 'block';
                } else {
                    guideTextBox.innerHTML = '';
                    guideTextBox.style.display = 'none';
                }

                var address = roadAddr;
                if (extraRoadAddr) {
                    address += extraRoadAddr;
                }
                document.getElementById('memberAddress').value = address;
            }
        }).open();
    }
</script>

		
</body>

</html>
