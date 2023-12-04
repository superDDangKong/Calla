<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여기쪽지</title>
<style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
        }

        .main-container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .chat-container {
            width: 300px;
            background-color: #fff;
            border-radius: 8px;
            overflow: hidden;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        .chat-header {
            background-color: #3498db;
            color: #fff;
            padding: 10px;
            text-align: center;
        }

        .chat-messages {
            max-height: 300px;
            overflow-y: auto;
            padding: 10px;
        }

        .message {
            margin-bottom: 10px;
        }

        .message.sender {
            text-align: right;
        }

        .message.receiver {
            text-align: left;
        }

        .message-content {
            background-color: #3498db;
            color: #fff;
            padding: 8px;
            border-radius: 5px;
            display: inline-block;
        }

        .chat-input {
            display: flex;
            align-items: center;
            padding: 10px;
            background-color: #f2f2f2;
        }

        .attachment-button {
            background-color: #3498db;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 8px;
            margin-right: 10px;
            cursor: pointer;
        }

        .message-input {
            flex: 1;
            border: 1px solid #ccc;
            border-radius: 5px;
            padding: 8px;
        }

        .send-button {
            background-color: #3498db;
            color: #fff;
            border: none;
            border-radius: 5px;
            padding: 8px;
            cursor: pointer;
        }
    </style>
</head>
<body>
    <div class="main-container">
        <section class="chat-container">
            <div class="chat-header">
                채팅방 제목
            </div>
            <div class="chat-messages">
                <!-- 대화 내용이 여기에 들어갑니다. -->
                <div class="message sender">
                    <div class="message-content">안녕하세요!</div>
                </div>
                <div class="message receiver">
                    <div class="message-content">안녕하세요! 반갑습니다.</div>
                </div>
                <!-- 추가적인 대화 메시지를 여기에 추가할 수 있습니다. -->
            </div>
            <div class="chat-input">
                <input class="message-input" type="text" placeholder="내용을 입력하세요" name="message">
                <button class="send-button">
                    <span>전송</span>
                </button>
            </div>
        </section>
    </div>
</body>
</html> 

 --%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>여기쪽지</title>
<style>
    body {
        margin: 0;
        padding: 0;
        font-family: Arial, sans-serif;
        background-color: #f0f0f0;
    }

    .main-container {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }

    .chat-container {
        width: 600px;
        background-color: #fff;
        border-radius: 8px;
        overflow: hidden;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .chat-header {
        background-color: #3498db;
        color: #fff;
        padding: 20px;
        text-align: center;
    }

    .chat-messages {
        max-height: 600px; /* 세로 크기 조절 */
        overflow-y: auto;
        padding: 20px;
    }

    .message {
        margin-bottom: 20px;
    }

    /* .message-content {
        background-color: #3498db;
        color: #fff;
        padding: 16px;
        border-radius: 8px;
        display: inline-block;
    }  */
    
	.message-receiver {
        text-align: right; /* 이 부분을 추가하여 내용을 오른쪽으로 정렬합니다. */
    }
	
    .chat-input {
        display: flex;
        align-items: center;
        padding: 20px;
        background-color: #f2f2f2;
    }

    .message-input {
        flex: 1;
        border: 1px solid #ccc;
        border-radius: 8px;
        padding: 16px;
        font-size: 16px;
    }

    .send-button {
        background-color: #3498db;
        color: #fff;
        border: none;
        border-radius: 8px;
        padding: 16px;
        cursor: pointer;
    }
</style>
</head>
<body>
    <div class="main-container">
        <section class="chat-container">
            <div class="chat-header">
                1:1채팅 문의
            </div>
            <div class="chat-messages">
                <!-- 대화 내용이 여기에 들어갑니다. -->
                <div class="message-sender">
                    <div class="message-content"></div>
                </div>
                <div class="message-receiver">
                    <div class="message-content"></div>
                </div>
            </div>
            <div class="chat-input">
                <input class="message-input" type="text" placeholder="내용을 입력하세요" name="message">
                <button class="send-button">
                    <span>전송</span>
                </button>
            </div>
        </section>
    </div>
<script type="text/javascript">
	
</script>
</body>
</html>
 