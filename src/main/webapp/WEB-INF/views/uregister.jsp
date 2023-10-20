<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성 페이지</title>
<script src="http://code.jquery.com/jquery-3.7.1.js"></script>
</head>
<style type="text/css">
[contenteditable]:empty:before {
	content: attr(placeholder);
	display: block; /* For Firefox */
	border: 1px solid #ddd;
	color: #333;
	font-size: 12px;
	width: 300px;
	height: 150px;
	padding: 5px;
}

#result_card img {
	max-width: 100%;
	height: auto;
	display: block;
	padding: 5px;
	margin-top: 10px;
	margin: auto;
}

#result_card {
	position: relative;
}

.imgDeleteBtn {
	position: absolute;
	top: 0;
	right: 5%;
	background-color: #ef7d7d;
	color: wheat;
	font-weight: 900;
	width: 30px;
	height: 30px;
	border-radius: 50%;
	line-height: 26px;
	text-align: center;
	border: none;
	display: block;
	cursor: pointer;
}
</style>
<body>
	<h2>상품 등록 페이지</h2>
	<form action="uregister" method="POST">
		<div>
			<p>상품 이름 :</p>
			<input type="text" name="uProductName" placeholder="상품 이름" required>
		</div>
		<div>
			<p>작성자 :</p>
			<input type="text" value="박진성" readonly="readonly">
		</div>

		<div>
			<p>상품 가격 :</p>
			<input type="text" name="uProductPrice" placeholder="제목 입력" required>
		</div>

		<div>
			<p>상품 카테고리 :</p>
			<input type="text" name="uProductCategori" placeholder="제목 입력"
				required>
		</div>

		<div>
			<p>작성자 주소 :</p>
			<input type="text" name="memberAddress" placeholder="제목 입력" required>
		</div>

		<div>
			<p>내용 :</p>
			<textarea rows="20" cols="100" name="uProductContent"
				placeholder="내용 입력"></textarea>
		</div>

		<div class="form_section">
			<div class="form_section_title">
				<label>상품 이미지</label>
			</div>
			<div class="form_section_content"></div>
			<input type="file" id="fileItem" name='uploadFile'
				style="height: 30px;">
			<div id="uploadResult">
				<!--  
				<div id="result_card">
					<div class="imgDeleteBtn">x</div>
					<img src="/calla/display?fileName=test.png">
				</div>
				-->

			</div>
		</div>

		<div>
			<input type="submit" value="등록">
		</div>

	</form>

	<script type="text/javascript">
		/* 이미지 업로드 */
		$("input[type='file']").on("change", function(e) {

			let formData = new FormData();
			let fileInput = $('input[name="uploadFile"]');
			let fileList = fileInput[0].files;
			let fileObj = fileList[0];

			/*
			if(!fileCheck(fileObj.name, fileObj.size)){
				return false;
			}
			 */

			formData.append("uploadFile", fileObj);

			$.ajax({
				url : '/calla/uploadAjaxAction',
				processData : false,
				contentType : false,
				data : formData,
				type : 'POST',
				dataType : 'json',
				success : function(result) {
					console.log(result);
					showUploadImage(result);
				},
				error : function(result) {
					alert("이미지 파일이 아닙니다.");
				}
			});

		});

		let regex = new RegExp("(.*?)\.(jpg|png)$");
		let maxSize = 1048576; //1MB	

		function fileCheck(fileName, fileSize) {

			if (fileSize >= maxSize) {
				alert("파일 사이즈 초과");
				return false;
			}

			if (!regex.test(fileName)) {
				alert("해당 종류의 파일은 업로드할 수 없습니다.");
				return false;
			}

			return true;

		}

		/* 이미지 출력 */
		function showUploadImage(uploadResultArr) {

			if (!uploadResultArr || uploadResultArr.length == 0) {
				return
			}

			let uploadResult = $("#uploadResult");

			let obj = uploadResultArr[0];

			let str = "";

			let fileCallPath = encodeURIComponent(obj.uploadPath.replace(/\\/g, '/') + "/s_" + obj.uuid + "_" + obj.fileName);

			str += "<div id='result_card'>";
			str += "<img src='/calla/display?fileName=" + fileCallPath + "'>";
			str += "<div class='imgDeleteBtn'>x</div>";
			str += "<input type='hidden' name='imageList[0].fileName' value='"+ obj.fileName +"'>";
			str += "<input type='hidden' name='imageList[0].uuid' value='"+ obj.uuid +"'>";
			str += "<input type='hidden' name='imageList[0].uploadPath' value='"+ obj.uploadPath +"'>";		
			str += "</div>";

			uploadResult.append(str);

		}
	</script>

</body>
</html>







