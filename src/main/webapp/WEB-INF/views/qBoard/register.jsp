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

.fileInfo {
	display : none;
	margin : auto;
}

#imgDelete {
	display : none;
}

.styled-checkbox {
        display: inline-block;
    }

    .styled-checkbox input {
        display: none;
    }

    .styled-checkbox label {
        position: relative;
        padding-left: 30px;
        cursor: pointer;
        font-size: 16px;
        line-height: 20px; /* Adjust line height for vertical centering */
    }

    .styled-checkbox label:before {
        content: '';
        position: absolute;
        left: 0;
        top: 50%; /* Center the checkbox vertically */
        transform: translateY(-50%); /* Adjust for vertical centering */
        width: 20px;
        height: 20px;
        border: 2px solid #3498db; /* Border color */ 
        background-color: #fff; /* Background color */
        border-radius: 5px;
        box-sizing: border-box;
    }

    .styled-checkbox input:checked + label:before {
        background-color: #3498db; /* Change background color when checked */
        border: 2px solid #3498db; /* Change border color when checked */
    }

    .styled-checkbox label:after {
        content: '';
        position: absolute;
        left: 7px;
        top: 7px;
        width: 6px;
        height: 12px;
        border: 2px solid #fff; /* Checkmark color */
        border-width: 0 2px 2px 0;
        transform: rotate(45deg);
        display: none;
    }

    .styled-checkbox input:checked + label:after {
        display: block;
    }
</style>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css"
	rel="stylesheet" />
<!-- Core theme CSS (includes Bootstrap)-->
<link href="../resources/css/styles.css" rel="stylesheet" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<%@ include file="../header.jspf" %> 
	<div class="container">
	<h2>글 작성 페이지</h2>
		<form action="register" method="post" enctype="multipart/form-data">
			<div>
				<input type="text" name="qBoardTitle" placeholder="제목 입력" required> <!-- 데이터를 입력할 땐 쿼리 기준 물음표 갯수로 -->
				 <div class="styled-checkbox">
        			<input type="radio" id="public" class="radio" name="qBoardStatus" value="공개" checked>
    				<label for="public">공개</label>
				    <input type="radio" id="private" class="radio" name="qBoardStatus" value="비공개">
				    <label for="private">비공개</label>
   				 </div>
			</div>
			<div>
				<input type="text" name="memberNickname" value="${memberNickname }" readonly="readonly"> <!-- 태그네임과 vo 이름과 같아야 한다 안그럼 에러나 -->
			</div>
			    <div class="custom-file">
			        <input type="file" name="customFile" class="custom-file-input" id="customFile">
			        <label class="custom-file-label" for="customFile">파일선택</label>
			    </div>
				<div class="content">
					<img id="imgDisplay" src="">
	 					<div>
	 						<p class="fileInfo">파일 이름: <span id="fileName"></span></p> 
	    					<p class="fileInfo">파일 크기: <span id="fileSize"></span></p><button id="imgDelete">삭제</button>
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
		$('.radio').change(function(){
			var checkValue = $('.radio:checked').attr('id');
			
			if(checkValue === 'public'){
				console.log("공개");
			} else {
				console.log("비공개");
			}
		})
		
	      $('#customFile').change(function(event) {
	        const file = event.target.files[0];
	        const imageDisplay = $('#imgDisplay'); // 이미지 태그
	        const imgInfo = $('.fileInfo'); // 이미지 정보(이름, 크기) 태그
	        const fileNameSpan = $('#fileName'); // 
	        const fileSizeSpan = $('#fileSize');
	        const imgDelete = $('#imgDelete');
	        
	        if (file) {
	          const reader = new FileReader();

	          reader.onload = function(e) {
	            imageDisplay.attr('src', e.target.result); // 이미지 경로 주입
	            imageDisplay.css('display', 'block'); // 이미지 화면에 보이게
	            $('.fileInfo').show(); // 이미지 정보 화면에 보이게
	            $('#imgDelete').show();
	            $('#customFile').css('display', 'none');
	            $('.custom-file-label').css('display', 'none');
	            $('.custom-file').css('display', 'none');
	          };
			  
	          fileNameSpan.text(file.name); // 파일 이름을 넣어줌
		      const fileSizeFormatted = formatBytes(file.size); // 파일 사이즈에 따라 단위 변경
		      fileSizeSpan.text(fileSizeFormatted); // 파일 사이즈를 입력
		      console.log(file.name);
		      console.log(file.size);
	          reader.readAsDataURL(file);
	          
	        } else {
	          imgDisplay.attr('src', '');
	        }
	        
	        $('#imgDelete').click(function() {
	        	imageDisplay.attr('src', '');
	        	$('.fileInfo').css("display", "none");
	        	imageDisplay.css("display", "none");
	        	$('#imgDelete').css("display", "none");
	        	$('#customFile').css('display', 'block');
	            $('.custom-file-label').css('display', 'block');
	            $('.custom-file').css('display', 'block');
	        	
	          }); // 이미지 삭제 
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