<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<title>주문내역</title>
<%@ include file="../header.jspf" %> 	
</head>
<h1>주문내역</h1>
<body>
	<c:set var="memberId" value="${memberId}" />
	<c:set var="memberLevel" value="${memberLevel}" />
	<input type="hidden" id="memberId" value="${memberId}" />	
	<input type="hidden" id="memberLevel" value="${memberLevel}" />	
	
	
	<div id="productOrder">
	
	 <table>
        <thead>
            <tr>
                <th style="width: 100px"></th>
                <th style="width: 100px">주문 날짜</th>
                <th style="width: 100px">주문자</th>
                <th style="width: 150px">상품 이름</th>
                <th style="width: 150px">이미지</th>
                <th style="width: 100px">수량</th>
                <th style="width: 100px">가격</th>
                <th style="width: 100px">총 가격</th>
                <th style="width: 300px">배송지</th>
                <th style="width: 100px">수령인</th>
                <th style="width: 100px">배송상황</th>
                               
            </tr>
        </thead>
        <c:choose>
            <c:when test="${empty productOrderList}">
                <tbody>
                    <tr>
                    	<td></td>
                        <td colspan="5">등록된 상품이 없습니다.</td>
                    </tr>
                </tbody>
            </c:when>
            <c:otherwise>
                <tbody>
                    <c:forEach var="vo" items="${productOrderList}">
                        <tr>
                        	<td>
	                    		<input type="hidden" class="productOrderId" value="${vo.productOrderId}" />	
                        	</td>
                            <td>
	                        	<fmt:formatDate value="${vo.productOrderCreatedDate }"
								pattern="yyyy.MM.dd" var="productOrderCreatedDate" />                            
                            	${productOrderCreatedDate }
                            </td>
                            <td>${vo.memberId }</td>
                            <td><a href="detail?productId=${vo.productId }&memberId=${vo.memberId }&page=${pageMaker.criteria.page}">${vo.productName }</a></td>
                            <td>
                            	<div>
									<img src="display?fileName=${vo.productImagePath}" width="100px" height="100px">
								</div>
                            </td>
                            <td>${vo.productAmount }</td>
                            <td>${vo.productPrice }</td>
                            <td>
                            	<c:set var="totalPrice" value="${vo.productAmount * vo.productPrice}" />
                            	${totalPrice }
                            </td>
                            <td>${vo.memberAddress }</td>
                            <td>${vo.recipientName }</td>
                            <c:choose>
                            	<c:when test="${memberLevel >= 2}">
                            		<td>
                            			<div>
                            				<label for="deliveryStatus"> </label>
												<select id="deliveryStatus" name="deliveryStatus">
													<option value="현재상태">${vo.deliveryStatus }</option>
													<option value="출고준비중">출고준비중</option>
													<option value="출고완료">출고완료</option>
													<option value="배송중">배송중</option>
													<option value="배송완료">배송완료</option>
												</select>
                            			</div>
                            		</td>
                            	</c:when>
                            	<c:otherwise>
                            		<td>
                            			${vo.deliveryStatus }
											<c:if test="${vo.deliveryStatus == '출고준비중'}">
								            	<button class="cancelOrderBtn">주문 취소</button>
								            </c:if>
                            		</td>
                            	</c:otherwise>
                            </c:choose>
                            	
                            <td>
                            	<input type="hidden" name="productId" value="${vo.productId}">
                            </td>
                        </tr>
                    </c:forEach>
                  
                </tbody>
            </c:otherwise>
        </c:choose>
    </table>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			$('select[name="deliveryStatus"]').change(function(){
				var productOrderId = $(this).closest('tr').find('.productOrderId').val();
				var deliveryStatus = $(this).val();
				
				$.ajax({
					type: 'PUT',
					url: 'orders/' + productOrderId + '/' + deliveryStatus,
					headers: {
	                    'Content-Type': 'application/json'
	                },
					data: {
						productOrderId : productOrderId,
						deliveryStatus : deliveryStatus
					},
					success: function(result){
						console.log(productOrderId);
						console.log(result);
		            	   if(result == 1){
		            		   alert('배송정보수정완료');
		            		   
						}
					}
				}); // end ajax
				
			}); // end select.change
				$('.cancelOrderBtn').click(function(){
					var productOrderId = $(this).closest('tr').find('.productOrderId').val();
					console.log('주문취소클릭');
					var currentRow = $(this).closest('tr'); // 현재 행
		            $.ajax({
		                type: 'DELETE', // 주문을 취소하기 위해 DELETE 메서드 사용
		                url: 'orders/' + productOrderId,
		                headers: {
		                    'Content-Type': 'application/json'
		                },
		                success: function(result){
		                	console.log(productOrderId);
		                    if(result == 1){
		                        alert('주문이 취소되었습니다.');
		                        currentRow.remove(); // 행 삭제
		                        window.location.reload();
		                    } 
		                }
		                
		            }); // end ajax
		        }); // end cancleOrderBtn
			
		}); // end document
	</script>
	
</body>
</html>