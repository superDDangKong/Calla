<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<%@ include file="../header.jspf"%>
<title>회원 등업</title>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<%@ include file="../sidebar2.jspf"%>

			<main class="container col-md-6 ms-sm-auto col-lg-6 px-md-4">
				<h1>Calla 회원 목록</h1>
				<table>
					<thead>
						<tr>
							<th style="width: 100px">아이디</th>
							<th style="width: 100px">이름</th>
							<th style="width: 100px">닉네임</th>
							<th style="width: 100px">가입일</th>
							<th style="width: 100px">레벨</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="vo" items="${list}">
							<tr>
								<td class="memberId">${vo.memberId }</td>
								<td>${vo.memberName }</td>
								<td>${vo.memberNickname }</td>
								<fmt:formatDate value="${vo.memberJoinDate }"
									pattern="yyyy-MM-dd HH:mm:ss" var="memberJoinDate" />
								<td>${memberJoinDate }</td>
								<td class="memberLevel">${vo.memberLevel }</td>
								<c:if test="${vo.memberLevel != 3 }">
									<td><button class="btnUpOrDown">등급 관리</button></td>
								</c:if>
								<td class="manageOption"></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</main>
			<%@ include file="../sidebarRight.jspf"%>
		</div>
	</div>
	<%@ include file="../footer.jspf"%>
	<script type="text/javascript">
	$(document).ready(function () {
	    $('.btnUpOrDown').on('click', function () {
	        $('.manageOption').html("");
	        console.log(this);
	        var memberLevel = $(this).closest('td').siblings('.memberLevel');

	        if (memberLevel.text() == '1') {
	            $(this).closest('td').siblings('.manageOption').html("<button class='btnUpdateLevel'>등급올리기</button>");
	        } else if (memberLevel.text() == '2') {
	            $(this).closest('td').siblings('.manageOption').html("<button class='btnUpdateLevel'>등급내리기</button>");
	        }
	    });

	    $('.manageOption').on('click', '.btnUpdateLevel', function () {
	        console.log('btnUpdate.click 호출');
	        var memberId = $(this).closest('td').siblings('.memberId');
	        var memberLevel = $(this).closest('td').siblings('.memberLevel');

	        $.ajax({
	            type: 'PUT',
	            url: 'updatelevel/' + memberId.text(),
	            data: memberLevel.text(),
				beforeSend: function() {
					$('#loadingContainer').remove();
					
					var loadingContainer = $('<div id="loadingContainer"><div class="loading"></div></div>');
					$('body').append(loadingContainer);
					$('#loadingContainer').css('display','block');
				},
				complete: function() {
					$('#loadingContainer').css('display','none');	
				},
	            success: function (newMemberLevel) {
	                console.log(newMemberLevel);
	                if (newMemberLevel == 1) {
	                    alert('등급 다운 성공');
	                    memberLevel.text("1");
	                    $(memberId).closest('td').siblings('.manageOption').html("");
	                } else {
	                    alert('등급 업 성공')
	                    memberLevel.text("2");
	                    $(memberId).closest('td').siblings('.manageOption').html("");
	                }
	            }
	        });
	    });
	});
	</script>
</body>
</html>