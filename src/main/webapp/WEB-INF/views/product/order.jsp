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
<button id="home"><a href="/calla/">홈</a></button>
<a href="list?page=${page }"><input type="button" value="상품 목록"></a>
<body>
	<c:set var="memberId" value="${memberId}" />
	<c:set var="memberLevel" value="${memberLevel}" />
	<input type="hidden" id="memberId" value="${memberId}" />	
	<input type="hidden" id="memberLevel" value="${memberLevel}" />	
	
	<div class="container-fluid">
		<div class="row">
			<%@ include file="../sidebar2.jspf"%>
			<main class="container col-md-9 ms-sm-auto col-lg-9 px-md-1" >
			
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
                <th style="width: 100px"></th>
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
                            <td>
                            	${vo.memberAddress }
                            	<c:if test="${vo.deliveryStatus == '출고준비중' || vo.deliveryStatus == '출고완료'}">
	                            	<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" data-whatever="@getbootstrap">주소변경</button>
								</c:if>
                            </td>
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
											<c:if test="${vo.deliveryStatus == '출고준비중' }">
								            	<button class="cancelOrderBtn">주문 취소</button>
								            </c:if>
                            		</td>
                            		<td>
											<c:if test="${vo.deliveryStatus == '배송완료' }">
									            <button class="deleteOrderBtn">주문정보 삭제</button>
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
	
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <h5 class="modal-title" id="exampleModalLabel">정보 입력</h5>
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	          <span aria-hidden="true">&times;</span>
	        </button>
	      </div>
	      <div class="modal-body">
	      	<input type="hidden" class="productOrderId" value="${vo.productOrderId}" />	
	          <div>
	            <label for="recipientName" class="col-form-label">수령인:</label>
	            <input type="text" id="recipientName" required>
	          </div>
	          <div>
	            <label for="message-text" class="col-form-label">주소:</label>
	            <input type="text" id="sample4_postcode" placeholder="우편번호" required>
				<input type="button" onclick="sample4_execDaumPostcode()" value="우편번호 찾기"><br>
				<input type="text" id="sample4_roadAddress" placeholder="도로명주소" required>
				<input type="text" id="sample4_jibunAddress" placeholder="지번주소" required>
				<span id="guide" style="color:#999;display:none"></span>
				<input type="text" id="sample4_detailAddress" placeholder="상세주소">
				<input type="text" id="sample4_extraAddress" placeholder="참고항목">
	          </div>
	      </div>
	      <div class="modal-footer">
	        <button type="button" id="orderUpdateBtn" class="btn orderUpdateBtn">변경</button>
	        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
	        <input type="hidden" name="productId" value="${vo.productId}">
	      </div>
	    </div>
	  </div>
	</div>
			
			</main>
			
		</div>
	</div>

	
	
	
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
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
		            		   alert('배송상태수정완료');
		            		   window.location.reload();
						}
					}
				}); // end ajax
				
			}); // end select.change
			
			
				$('.cancelOrderBtn').click(function(){
					var productOrderId = $(this).closest('tr').find('.productOrderId').val();
					console.log('주문취소클릭');
					var currentRow = $(this).closest('tr'); // 현재 행
		            $.ajax({
		                type: 'DELETE', 
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
		        
		        $('.deleteOrderBtn').click(function(){
					var productOrderId = $(this).closest('tr').find('.productOrderId').val();
					console.log('주문취소클릭');
					var currentRow = $(this).closest('tr'); // 현재 행
		            $.ajax({
		                type: 'DELETE', 
		                url: 'orders/' + productOrderId,
		                headers: {
		                    'Content-Type': 'application/json'
		                },
		                success: function(result){
		                	console.log(productOrderId);
		                    if(result == 1){
		                        alert('주문정보 삭제 완료.');
		                        currentRow.remove(); // 행 삭제
		                        window.location.reload();
		                    } 
		                }
		                
		            }); // end ajax
		        }); // end deleteOrderBtn
			}); // end document
			
		        
		    $(document).on('click', '.btn-primary', function() {
		        var productOrderId = $(this).closest('tr').find('.productOrderId').val();    
		        console.log(productOrderId);
		        $('#exampleModal .productOrderId').val(productOrderId);
		    }); // end document    
			
			    $('#orderUpdateBtn').click(function(){
			    	var productOrderId = $('#exampleModal .productOrderId').val();
					var recipientName = $('#recipientName').val();
					var memberAddress = ($('#sample4_postcode').val() + $('#sample4_roadAddress').val() + $('#sample4_jibunAddress').val() + $('#sample4_detailAddress').val() + $('#sample4_extraAddress').val()); 
					var data = {
							productOrderId : productOrderId,
							recipientName : recipientName,
							memberAddress : memberAddress
					};
					console.log(data);
					
					$.ajax({
						type: 'PUT',
						url: 'orders/' + productOrderId + '/' + recipientName + '/' + memberAddress,
						headers: {
		                    'Content-Type': 'application/json'
		                },
		                data: JSON.stringify(data),
		                success: function (result){
		                	console.log('수정완료');
		                	alert('배송정보 수정 완료');
		                	window.location.reload();
		                }
					}); // end ajax
				}); // end orderUpdateBtn        
		    
			
	</script>
	
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