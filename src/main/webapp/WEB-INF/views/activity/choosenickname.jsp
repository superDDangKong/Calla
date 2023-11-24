<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script src="https://code.jquery.com/jquery-3.7.1.slim.js"
	integrity="sha256-UgvvN8vBkgO0luPSUl2s8TIlOSYRoGFAX4jlCIm9Adc="
	crossorigin="anonymous"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>거래자 닉네임을 선택해주세요</h3>
	
	<form action="choosenickname" id ="send_form" method="POST">
		
			<input type="hidden" name="uProductName" value="${vo.uProductName }" readonly="readonly">
			<input type="hidden" name="uProductPrice" value="${vo.uProductPrice }" readonly="readonly">	
			<input type="hidden" name="uProductCategori" value="${vo.uProductCategori }" readonly="readonly">
			<input type="hidden" name="memberAddress" value="${vo.memberAddress }" readonly="readonly">
			<input type="hidden" name="uProductContent" value="${vo.uProductContent }" readonly="readonly">
			<input type="hidden" name="uProductImagePath" value="${vo.uProductImagePath }" readonly="readonly">
			<input type="hidden" name="sellerNickname" value="${sessionScope.memberNickname }" readonly="readonly">
			<input type="hidden" name="uProductId" value="${vo.uProductId }" readonly="readonly">
			
			<table>
				<thead>
					<tr>
						<th style="width : 200px">닉네임</th>
						<th style="width : 700px"></th>
					</tr>
				</thead>
				
				<tbody>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td><input type="checkbox" name="buyerNickname" value="${vo.memberNickname }">"${vo.memberNickname }"</td>
				</tr>
			</c:forEach>
				</tbody>
			</table>
			
			<input type="submit" id="send" value="등록">
		
	</form>
	
		<script type="text/javascript">
 		
			String[] buyerNickname = request.getParameterValues("buyerNickname");
		
 			 // jquery로 값을 들고오면 더 간단
  			function jqueryTest() {
    		var str = "";  
   			 $("input[name=buyerNickname]:checked").each(function (index) {  
      			  str += $(this).val() + ",";  
   			 });  
   			 alert(str); // checked 된 값을 출력
 		 }
		</script>
	

	

</body>
</html>








