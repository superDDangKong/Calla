<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
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
<title>Order</title>
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
				url : 'orders/' + productId + '/' + memberId,
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
	}); // end document
	
	 $(document).ready(function () {
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
	                url: 'orders/' + productId + '/' + memberId + '/' + productAmount,
	                headers: {
	                    'Content-Type': 'application/json'
	                },
	                data: JSON.stringify(data),
	                success: function (result) {
	                    console.log('수량 업데이트 완료');
	                    document.location.reload();
	                }
	        	
	        }); //end ajax
	        
	        
	    } //end updateProductAmount
	    
	    
	    
	}); // end documnet
	
	$(document).ready(function() {
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
	}); // end document
	
	</script>
	
</body>
</html>