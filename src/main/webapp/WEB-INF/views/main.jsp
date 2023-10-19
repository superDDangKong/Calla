<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="login">
<input type="hidden" id="memberNickname" value=${memberNickname }>
<input type="hidden" id="memberId" value=${memberId }>
</div>

<div id="product">

</div>

<script type="text/javascript">
	$(document).ready(function(){
		var list = "";
		var memberId = $('#memberId').val();
		if ($('#memberNickname').val() != "") {
			list = "<button><a href='myPage?memberId=" + memberId + "'>마이페이지</a></button>"
				+ "<button><a href='logout'>로그아웃</a></button>"
				+ "<button><a href='product'>공용상품</a></button>"
				+ "<button><a href='uProduct'>중고상품</a></button>"
				+ "<button><a href='qBoard'>문의게시판</a></button>"
				+ "<button><a href='fBoard'>자유게시판</a></button>"
				
		} else {
			list = "<button><a href='login'>로그인</a></button>"	
		}
		
		$('#login').html(list)
	}) // end document.ready
</script>
</body>
</html>