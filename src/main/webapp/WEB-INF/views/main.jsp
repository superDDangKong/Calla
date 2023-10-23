<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<meta charset="UTF-8">
<title>Calla</title>
</head>

<body>

<h2 style="color:red">Calla</h2>
<div id="login">
<input type="hidden" id="memberNickname" value=${memberNickname }>
<input type="hidden" id="memberId" value=${memberId }>
</div>

<script type="text/javascript">
	$(document).ready(function(){
		var list = "";
		var memberId = $('#memberId').val();
		if ($('#memberNickname').val() != "") {
			list = "<button><a href='member/myPage?memberId=" + memberId + "'>마이페이지</a></button>"
				+ "<button><a href='member/logout'>로그아웃</a></button>"
				+ "<br><br>"
				+ "<button><a href='product/list'>공용상품</a></button>"
				+ '&nbsp;&nbsp;'
				+ "<button><a href='uregister'>중고상품</a></button>"
				+ '&nbsp;&nbsp;'
				+ "<button><a href='qBoard'>문의게시판</a></button>"
				+ '&nbsp;&nbsp;'
				+ "<button><a href='fBoard/list'>자유게시판</a></button>"
				
		} else {
			list = "<button><a href='member/login'>로그인</a></button>"	
				+ "<button><a href='member/join'>회원가입</a></button>"
		}
		
		$('#login').html(list)
	}) // end document.ready
</script>
