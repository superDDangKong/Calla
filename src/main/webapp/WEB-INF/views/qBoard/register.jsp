<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<meta charset="UTF-8">
<title>글 작성 페이지</title>
<style type="text/css">
textarea {
   /*  border: none; /* 테두리 없음 */ */
}


.custom-file-label{
	width : 906px;
}

#imgDisplay {
      width: 200px; /* 원하는 너비로 설정 */
      height: 150px; /* 원하는 높이로 설정 */
      display : none;
}

.imgInfo * {
	display : none;
}
</style>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<%@ include file="../header.jspf" %> 
	<div class="container">
	<h2>글 작성 페이지</h2>
		<form action="register" method="POST">
			<div>
				<input type="text" name="qBoardTitle" placeholder="제목 입력" required> <!-- 데이터를 입력할 땐 쿼리 기준 물음표 갯수로 -->
			</div>
			<div>
				<input type="text" name="memberNickname" value="${memberNickname }" readonly="readonly"> <!-- 태그네임과 vo 이름과 같아야 한다 안그럼 에러나 -->
			</div>
			    <div class="custom-file">
			        <input type="file" name="qBoardImagePath" class="custom-file-input" id="customFile">
			        <label class="custom-file-label" for="customFile">파일선택</label>
			    </div>
				<div class="content">
					<img id="imgDisplay" src="">
	 					<div class="imgInfo">
	 						<p>파일 이름: <span id="fileName"></span></p>
	    					<p>파일 크기: <span id="fileSize"></span> 바이트</p>
	 					</div> 
	 				<textarea rows="20" cols="120" name="qBoardContent" placeholder="내용 입력" ></textarea>
				</div>
			<div>
				<input type="submit" value="등록"> 
				
			</div>
		</form>
	</div>
	<script type="text/javascript">
	$(document).ready(function() {
	      $('#customFile').change(function(event) {
	        const file = event.target.files[0];
	        const imageDisplay = $('#imgDisplay');
	        const imgInfo = $('#imgInfo');
	        const allChildren = imgInfo.find('*');
	        const fileNameSpan = $('#fileName');
	        const fileSizeSpan = $('#fileSize');

	        if (file) {
	          const reader = new FileReader();

	          reader.onload = function(e) {
	            imageDisplay.attr('src', e.target.result);
	            imageDisplay.css('display', 'block'); // 이미지 표시
	            $('.imgInfo').children().show(); // 화면에 보이게 표시
	          };
			  
	            fileNameSpan.text(file.name); // 파일 이름을 입력
		        const fileSizeFormatted = formatBytes(file.size); // 파일 사이즈에 따라 단위 변경
		        fileSizeSpan.text(fileSizeFormatted); // 파일 사이즈를 입력
		        console.log(file.name);
		        console.log(file.size);
	          reader.readAsDataURL(file);
	        } else {
	          imgDisplay.attr('src', '');
	          allChildren.hide(); // .imgInfo 클래스의 모든 하위 요소를 숨김
	        }
	      }); // end change
	      function formatBytes(bytes, decimals = 2) {
	    	  if (bytes === 0) return '0 Bytes';

	    	  const k = 1024;
	    	  const dm = decimals < 0 ? 0 : decimals;
	    	  const sizes = ['Bytes', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];

	    	  const i = Math.floor(Math.log(bytes) / Math.log(k));

	    	  return parseFloat((bytes / Math.pow(k, i)).toFixed(dm)) + ' ' + sizes[i];
	    	} // end functon
	      
	    }); // end document 
	


	</script>
	<%@ include file="../footer.jspf"%>
</body>
</html>