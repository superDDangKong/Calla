<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>FAQ chatAi</title>
<style type="text/css">
body {
    font-family: 'Arial', sans-serif;
    margin: 0;
    padding: 0;
	background-color: #f5f5f5;
}

#chat-container {
    max-width: 600px;
    margin: 50px auto;
    background-color: #fff;
    border-radius: 10px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	overflow: hidden;
}

#chat-header {
	background-color: #4CAF50;
    color: #fff;
    padding: 10px;
    text-align: center;
	font-size: 24px;
}

.chat-buttons {
    display: flex;
    justify-content: center;
	margin: 10px 0;
}

.chat {
    padding: 10px 15px;
    margin: 0 5px;
    background-color: #4CAF50;
    color: #fff;
    border: none;
    border-radius: 5px;
	cursor: pointer;
}

.chat-style {
    padding: 20px;
    height: 300px;
	overflow-y: scroll;
}

.chat-body {
	position: relative;
}

.answer-add {
    display: flex;
	margin-top: 15px;
}

.answer-add input {
    flex: 1;
    padding: 8px;
    border: 1px solid #ccc;
	border-radius: 5px 0 0 5px;
}

.answer-add .answer-bth {
	display: inline-block;
	padding: 8px 15px;
	background-color: #4CAF50;
	color: #fff;
	border: 1px solid #4CAF50;
	border-radius: 0 5px 5px 0;
	cursor: pointer;
}

.answer-add .answer-bth:hover {
	background-color: #45a049;
}
</style>
</head>
<body>
<%@ include file="../header.jspf" %>
<!-- <div class="content container-fluid bootstrap snippets">
    <div class="row row-broken">
        <div class="col-sm-12 col-xs-12 chat" style="overflow: hidden; outline: none;" tabindex="5001">
          <div class="col-inside-lg decor-default"> 장식 기본값
            <div class="chat-body">채팅 본문
              <div id="chat-messages"></div> 채팅 메시지
              <div class="answer-add"> 답변추가
                <input placeholder="Escriba un mensaje">
                <span class="answer-btn answer-btn-2"></span>
              </div>
            </div>
          </div>
        </div>
   	</div>
</div>	  -->
<div>
	<div>
		<h1>챗봇</h1>
		<div>
			<button id="chat-bot" class="chat">챗봇</button>
			<button id="chat-admin" class="chat">관리자 채팅</button>
		</div>
		<form id="question" action="" method="post">
			<div class="chat-style">
				<div class="chat-body">
					<div id="chat-messages"></div>
					<div class=answer-add>
						<input placeholder="메세지를 입력하세요">
						<span class="answer-bth"></span>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>

</body>

</html>