<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<!-- Bootstrap css -->
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
   rel="stylesheet"
   integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
   crossorigin="anonymous" />
<!-- Bootstrap icons -->
<link
   href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
   rel="stylesheet" />
<!-- Bootstrap core JS-->
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<style type="text/css">
ul {
	list-style-type : none;
}

li {
	display : inline-block;
}

</style>
<meta charset="UTF-8">
<title>OrderList</title>
	<h1>List</h1>
	<%@ include file="../header.jspf" %> 	
	<h1>장바구니</h1>
	<button id="home"><a href="/calla/">홈</a></button>
	<a href="list?page=${page }"><input type="button" value="상품 목록"></a>
	
</head>
<body>
	<c:set var="memberId" value="${memberId}" />
	<input type="hidden" id="memberId" value="${memberId}" />	
	
	<div id="productOrderList">
	<div class="all_check_input_div">
		<input type="checkbox" class="all_check_input input_size_20" checked="checked"><span class="all_chcek_span">전체선택</span>
	</div>
	 <table>
        <thead>
            <tr>
                <th style="width: 60px"></th>
                <th style="width: 60px">상품번호</th>
                <th style="width: 60px">이미지</th>
                <th style="width: 60px">이름</th>
                <th style="width: 60px">카테고리</th>
                <th style="width: 60px">가격</th>
                <th style="width: 60px">수량</th>
            </tr>
        </thead>
        <c:choose>
            <c:when test="${empty productList}">
                <tbody>
                    <tr>
                        <td colspan="5">등록된 상품이 없습니다.</td>
                    </tr>
                </tbody>
            </c:when>
            <c:otherwise>
                <tbody>
                    <c:forEach var="vo" items="${productList}">
                        <tr>                            
                            <td>
                           		<input type="checkbox" class="ProductOrderCheckBox input_size_20" checked="checked">
                            </td>
                            <td>${vo.productId }</td>
                            <td>
                            	<div>
									<img src="display?fileName=${vo.productImagePath}" width="100px" height="100px">
								</div>
                            </td>
                            <td><a href="detail?productId=${vo.productId }&memberId=${memberId }&page=${pageMaker.criteria.page}">${vo.productName }</a></td>
                            <td>${vo.productCategori }</td>
                            <td>${vo.productPrice }</td>
                            <td>
                            	<input type="hidden" name="productId" value="${vo.productId}">
							    <span class="currentAmount">${vo.productAmount}</span>
							    <button class="decreaseBtn" data-productId="${vo.productId}">-</button>
							    <button class="increaseBtn" data-productId="${vo.productId}">+</button>
                            </td>
                            <td>
                        		<input type="hidden" name="productId" value="${vo.productId}">
            					<button class="ordDelete" data-productId="${vo.productId}">삭제</button>
               				</td>
                        </tr>
                    </c:forEach>
                  
                </tbody>
            </c:otherwise>
        </c:choose>
    </table>
	</div>
		
	
	<c:if test="${not empty productList}">
    	<div class="totalPrice">
        	<div class="sum">
            	총 합계 : <fmt:formatNumber pattern="###,###,###" value="${totalSum}" />원
        	</div>
    	</div>
 
	</c:if>
	
	
	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@getbootstrap">구매</button>

	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">New message</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	         <div>
				<label for="cardSelect">카드 선택:</label>
        		<select id="memberCard">
		            <option value="신한카드">신한카드</option>
		            <option value="삼성카드">삼성카드</option>
		            <option value="현대카드">현대카드</option>
		            <option value="KB국민카드">KB국민카드</option>
		            <option value="하나카드">하나카드</option>
		            <option value="우리카드">우리카드</option>
		            <option value="롯데카드">롯데카드</option>
		            <option value="BC카드">BC카드</option>
		            <option value="NH농협카드">NH농협카드</option>
		            <option value="씨티카드">씨티카드</option>
		            <option value="SC제일은행">SC제일은행</option>
		            <option value="유희왕카드">유희왕카드</option>
		        </select>
			  </div>
	          <div>
    		  	<label for="memberCardNumber">카드 번호:</label>
    		    <input type="text" id="memberCardNumber" name="memberCardNumber" placeholder="카드 번호를 입력하세요" oninput="this.value = this.value.replace(/[^0-9]/g, '')" required>
    		  </div>	
	          <div>
    			<label for="email">이메일:</label>
    			<input type="text" id="memberEmail" name="memberEmail" placeholder="(선택) 이메일을 입력하세요">
    		  </div>	
	          <div>
	            <label for="recipientName" class="col-form-label">수령인:</label>
	            <input type="text" id="recipientName" required>
	          </div>
	          <div>
	            <label for="message-text" class="col-form-label">주소:</label>
	            <input type="text" id="sample4_postcode" placeholder="우편번호">
				<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="sample4_roadAddress" placeholder="도로명주소">
				<input type="text" id="sample4_jibunAddress" placeholder="지번주소">
				<span id="guide" style="color:#999;display:none"></span>
				<input type="text" id="sample4_detailAddress" placeholder="상세주소">
				<input type="text" id="sample4_extraAddress" placeholder="참고항목">
	          </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" id="orderBtn" class="btn orderBtn">구매</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <input type="hidden" name="productId" value="${vo.productId}">
	      </div>
	    </div>
	  </div>
	</div>
	
	
	<script type="text/javascript">
	$(document).ready(function(){
	    
		var ordDelete = $('#ordDelete');
		$('.ordDelete').click(function(){
			var productId = $(this).attr('data-productId');
			var memberId = $('#memberId').val();
			console.log(productId);
			console.log('클릭');
			console.log(memberId);
			$.ajax({
				type : 'DELETE',
				url : 'orderLists/' + productId + '/' + memberId,
				headers: {
	                   'Content-Type': 'application/json'
	               },
	               success : function(result){
	            	   console.log(result);
	            	   if(result == 1){
	            		   alert('삭제');
	            		   document.location.reload();
	            	   }
	               }
			}); // end ajax
		}); // end click
	
	
	        var decreaseBtn = $('.decreaseBtn');
	        var increaseBtn = $('.increaseBtn');

	        decreaseBtn.click(function () {
	            var productId = $(this).attr('data-productId');
	            var currentAmount = $(this).siblings('.currentAmount');
	            var productAmount = parseInt(currentAmount.text(), 10);

	            if (productAmount > 0) {
	                productAmount--;
	                currentAmount.text(productAmount);
	                updateProductAmount(productId, productAmount);
	            }
	        });

	        increaseBtn.click(function () {
	            var productId = $(this).attr('data-productId');
	            var currentAmount = $(this).siblings('.currentAmount');
	            var productAmount = parseInt(currentAmount.text(), 10);

	            productAmount++;
	            currentAmount.text(productAmount);
	            updateProductAmount(productId, productAmount);
	        });

	        function updateProductAmount(productId, productAmount) {
	            var memberId = $('#memberId').val();
	            var data = {
	                productId: productId,
	                memberId: memberId,
	                productAmount: productAmount
	            };

	            $.ajax({
	                type: 'PUT',
	                url: 'orderLists/' + productId + '/' + memberId + '/' + productAmount,
	                headers: {
	                    'Content-Type': 'application/json'
	                },
	                data: JSON.stringify(data),
	                success: function (result) {
	                    console.log('수량 업데이트 완료');
	                    updateTotalPrice();
	                }
	        	
	        }); //end ajax
	        
	        
	    } //end updateProductAmount
	    
	    $('.all_check_input').change(function() {
	        $('.ProductOrderCheckBox').prop('checked', this.checked);
	        checkedTotalPrice();
	    }); // end all_check_input
	    
	    $('.ProductOrderCheckBox').change(function() {
	        checkedTotalPrice();
	    });
	    
	    function checkedTotalPrice() {
	        var total = 0;
	        $('.ProductOrderCheckBox:checked').each(function() {
	            var row = $(this).closest('tr');
	            var isChecked = $(this).is(':checked');
	            if (isChecked) {
	                var price = parseFloat(row.find('td:eq(5)').text().replace(/\D/g, ''));
	                var amount = parseFloat(row.find('.currentAmount').text());
	                total += price * amount;
	            }
	        });
	        $('.totalPrice .sum').text('총 합계 : ' + new Intl.NumberFormat().format(total) + '원');
	    }

	function updateTotalPrice() {
	    var total = 0;
	    $('.ProductOrderCheckBox:checked').each(function() {
	        var row = $(this).closest('tr');
	        var isChecked = $(this).is(':checked');
	        if (isChecked) {
	            var price = parseFloat(row.find('td:eq(5)').text());
	            var amount = parseFloat(row.find('.currentAmount').text());
	            total += price * amount;
	        }
	    });
	    $('.totalPrice .sum').text('총 합계 : ' + new Intl.NumberFormat().format(total) + '원');
	} // end updateTotalPrice()
	
	$('#orderBtn').click(function(){
		var selectedProducts = []; // 선택한 상품
		var memberId = $('#memberId').val();
	    var memberCard = $('#memberCard').val();
	    var memberCardNumber = $('#memberCardNumber').val();
	    var memberEmail = $('#memberEmail').val();
	    var recipientName = $('#recipientName').val();
	    var memberAddress = ($('#sample4_postcode').val() + $('#sample4_roadAddress').val() + $('#sample4_jibunAddress').val() + $('#sample4_detailAddress').val() + $('#sample4_extraAddress').val());
		
		// 선택한 상품의 데이터 
		$('.ProductOrderCheckBox:checked').each(function() {
	        var row = $(this).closest('tr');
	        var isChecked = $(this).is(':checked');
	        if (isChecked) {
	        	 var productId = row.find('td:eq(1)').text(); // 상품번호
	        	 var productImagePath = row.find('td:eq(2) img').attr('src').replace('display?fileName=', '');
	        	 var productName = row.find('td:eq(3) a').text(); // 상품 이름
	             var productPrice = row.find('td:eq(5)').text(); // 상품 가격
	             var productAmount = row.find('.currentAmount').text(); // 수량

	             var productData = {
	                 memberId: memberId,
	     	         memberCard : memberCard,
	     	         memberCardNumber : memberCardNumber,
	     	         memberEmail : memberEmail,
	     	         recipientName : recipientName,
	     	         memberAddress : memberAddress,
	            	 productId : productId,
	                 productImagePath : productImagePath,
	                 productName : productName,
	                 productPrice : productPrice,
	                 productAmount : productAmount
	            };
	            selectedProducts.push(productData);
	        }
	    });
		
		// 선택한 상품 정보를 담은 JSON 객체
	   
		console.log(selectedProducts);
		
	    $.ajax({
	        type: 'POST',
	        url: 'orders',
	        headers: {
                'Content-Type': 'application/json'
            }, 
	        data: JSON.stringify(selectedProducts), // 변경: 데이터를 JSON 문자열로 변환
	        success: function(result){
	        	
	            console.log(result);
	            if(result > '0'){
	            	window.location.href = 'list';
	            }
	        }
	    });
	});
	
	function validateEmail() {
        const emailField = document.getElementById('email');
        const email = emailField.value;
        
        const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

        if (!emailRegex.test(email) && email !== "") {
            alert("유효한 이메일 주소를 입력하세요.");
            emailField.value = "";
        }
    } // end validateEmail()
	
	}); // end document
	
	</script>
	
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<script>
	    //본 예제에서는 도로명 주소 표기 방식에 대한 법령에 따라, 내려오는 데이터를 조합하여 올바른 주소를 구성하는 방법을 설명합니다.
	    function sample4_execDaumPostcode() {
	        new daum.Postcode({
	            oncomplete: function(data) {
	                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	
	                // 도로명 주소의 노출 규칙에 따라 주소를 표시한다.
	                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	                var roadAddr = data.roadAddress; // 도로명 주소 변수
	                var extraRoadAddr = ''; // 참고 항목 변수
	
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraRoadAddr += data.bname;
	                }
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                   extraRoadAddr += (extraRoadAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraRoadAddr !== ''){
	                    extraRoadAddr = ' (' + extraRoadAddr + ')';
	                }
	
	                // 우편번호와 주소 정보를 해당 필드에 넣는다.
	                document.getElementById('sample4_postcode').value = data.zonecode;
	                document.getElementById("sample4_roadAddress").value = roadAddr;
	                document.getElementById("sample4_jibunAddress").value = data.jibunAddress;
	                
	                // 참고항목 문자열이 있을 경우 해당 필드에 넣는다.
	                if(roadAddr !== ''){
	                    document.getElementById("sample4_extraAddress").value = extraRoadAddr;
	                } else {
	                    document.getElementById("sample4_extraAddress").value = '';
	                }
	
	                var guideTextBox = document.getElementById("guide");
	                // 사용자가 '선택 안함'을 클릭한 경우, 예상 주소라는 표시를 해준다.
	                if(data.autoRoadAddress) {
	                    var expRoadAddr = data.autoRoadAddress + extraRoadAddr;
	                    guideTextBox.innerHTML = '(예상 도로명 주소 : ' + expRoadAddr + ')';
	                    guideTextBox.style.display = 'block';
	
	                } else if(data.autoJibunAddress) {
	                    var expJibunAddr = data.autoJibunAddress;
	                    guideTextBox.innerHTML = '(예상 지번 주소 : ' + expJibunAddr + ')';
	                    guideTextBox.style.display = 'block';
	                } else {
	                    guideTextBox.innerHTML = '';
	                    guideTextBox.style.display = 'none';
	                }
	            }
	        }).open();
	    }
	   
	</script>
</body>
</html>