package project.spring.calla.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.spring.calla.domain.FBoardCommentVO;
import project.spring.calla.domain.FBoardReplyVO;
import project.spring.calla.service.FBoardCommentService;
import project.spring.calla.service.FBoardReplyService;

@RestController
@RequestMapping(value="/fBoard/replies")
public class FBoardReplyRESTController {
	
	private static final Logger logger 
	= LoggerFactory.getLogger(FBoardReplyRESTController.class);

@Autowired
private FBoardReplyService fBoardReplyService;


@PostMapping // POST : 댓글 입력
public ResponseEntity<Integer> createReply(@RequestBody FBoardReplyVO vo) {
	// @RequestBody
	// - 클라이언트에서 전송받은 json 데이터를
	// 자바 객체로 변환해주는 annotation
	logger.info("createReply() 호출 : vo = " + vo.toString());

	// ResponseEntity<T> : Rest 방식에서 데이터를 리턴할 때 쓰이는 객체
	// - 데이터 HttpStatus를 전송
	// - <T> : 보내고자 하는 데이터 타입
	int result = 0;
	try {
		result = fBoardReplyService.create(vo);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return new ResponseEntity<Integer>(result, HttpStatus.OK);
}

@GetMapping("/all/{FBoardCommentId}") // GET : 댓글 선택(all)
public ResponseEntity<List<FBoardReplyVO>> readReplies(@PathVariable("FBoardCommentId") int fBoardCommentId) {
	// @PathVariable("fBoardId") : /all/{fBboardId} 값을 설정된 변수에 저장
	logger.info("readReplies() 호출 : fBoardCommentId = " + fBoardCommentId);

	List<FBoardReplyVO> list = fBoardReplyService.read(fBoardCommentId);
	return new ResponseEntity<List<FBoardReplyVO>>(list, HttpStatus.OK);
}

@PutMapping("/{FBoardReplyId}") // PUT : 댓글 수정
public ResponseEntity<Integer> updateReply(@PathVariable("FBoardReplyId") int fBoardReplyId,
		@RequestBody String fBoardReplyContent) {
	int result = fBoardReplyService.update(fBoardReplyId, fBoardReplyContent);
	return new ResponseEntity<Integer>(result, HttpStatus.OK);
}

@DeleteMapping("/{FBoardReplyId}")
public ResponseEntity<Integer> deleteReply(@PathVariable("FBoardReplyId") int fBoardReplyId,
		@RequestBody int fBoardCommentId) {
	logger.info("FBoardReplyId = " + fBoardReplyId);

	int result = 0;
	try {
		result = fBoardReplyService.delete(fBoardReplyId, fBoardCommentId);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return new ResponseEntity<Integer>(result, HttpStatus.OK);
}

}
