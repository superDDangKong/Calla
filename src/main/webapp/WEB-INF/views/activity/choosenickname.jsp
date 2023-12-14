<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<head>
<style type="text/css">
table, th, td {
	border-style: solid;
	border-width: 1px;
	text-align: center;
}

ul {
	list-style-type: none;
	text-align: center;
}

li {
	display: inline-block;
	text-align: center;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h3>거래자 닉네임을 선택해주세요</h3>
	
		
			<input type="hidden" id = "uProductName" name="uProductName" value="${vo.uProductName }" readonly="readonly">
			<input type="hidden" id = "uProductPrice" name="uProductPrice" value="${vo.uProductPrice }" readonly="readonly">	
			<input type="hidden" id = "uProductCategori" name="uProductCategori" value="${vo.uProductCategori }" readonly="readonly">
			<input type="hidden" id = "memberAddress" name="memberAddress" value="${vo.memberAddress }" readonly="readonly">
			<input type="hidden" id = "uProductContent" name="uProductContent" value="${vo.uProductContent }" readonly="readonly">
			<input type="hidden" id = "uProductImagePath" name="uProductImagePath" value="${vo.uProductImagePath }" readonly="readonly">
			<input type="hidden" id = "sellerNickname" name="sellerNickname" value="${sessionScope.memberNickname }" readonly="readonly">
			<input type="hidden" id = "uProductId" name="uProductId" value="${vo.uProductId }" readonly="readonly">
			
			<table>
				<thead>
					<tr>
						<th style="width : 200px">닉네임</th>
						
					</tr>
				</thead>
				
				<tbody>
			<c:forEach var="vo" items="${list }">
				<tr>
					<td><input type="checkbox" id = "buyerNickname" name="buyerNickname" value="${vo.memberNickname }"
					onclick='checkOnlyOne(this)'>
					"${vo.memberNickname }"</td>
					
				</tr>
			</c:forEach>
				</tbody>
			</table>
			
			<br>
			
			<button id="btnAdd">등록</button>
			
		<script type="text/javascript">
		function checkOnlyOne(element) {
			  
			  const checkboxes 
			      = document.getElementsByName("buyerNickname");
			  
			  checkboxes.forEach((cb) => {
			    cb.checked = false;
			  })
			  
			  element.checked = true;
			}	
		
		</script>
			
		
		<script type="text/javascript">
		$(document).ready(function(){
			
			$('#btnAdd').click(function(){
				var uProductName = $('#uProductName').val();
				var uProductPrice = $('#uProductPrice').val();
				var uProductCategori = $('#uProductCategori').val();
				var memberAddress = $('#memberAddress').val();
				var uProductContent = $('#uProductContent').val();
				var uProductImagePath = $('#uProductImagePath').val();
				var sellerNickname = $('#sellerNickname').val();
				var uProductId = $('#uProductId').val();
				var buyerNickname = $('#buyerNickname').val();
				if($("#uProductSecretComment").is(":checked")){
					buyerNickname = $('#buyerNickname').val();
				}
				
				
				var obj = {
						'uProductName' : uProductName,
						'uProductPrice' : uProductPrice,
						'uProductCategori' : uProductCategori,
						'memberAddress' : memberAddress,
						'uProductContent' : uProductContent,
						'uProductImagePath' : uProductImagePath,
						'sellerNickname' : sellerNickname,
						'uProductId' : uProductId,
						'buyerNickname' : buyerNickname
						
						  }
				
				console.log(obj);
				
				$.ajax({
					type : 'POST',
					url : 'choosenickname',
					headers : {
						'Content-Type' : 'application/json'
					},
					data : JSON.stringify(obj),
					success : function(result){
						console.log(result);
						if(result == 1){
							alert('거래 성공!');
							window.opener.location.reload();
							window.close();
						}
					}
				});
			}); // end btnAdd
		}); // end document 
			
		</script>
	
</body>
</html>








