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
	logger.info("createReply() 호출 : vo = " + vo.toString());

	int result = 0;
	try {
		result = fBoardReplyService.create(vo);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return new ResponseEntity<Integer>(result, HttpStatus.OK);
}

@GetMapping("/all/{FBoardCommentId}") // GET : 댓글 선택(all)
public ResponseEntity<List<FBoardReplyVO>> readReplies(@PathVariable("FBoardCommentId") int fBoardCommentId) {
	logger.info("readReplies() 호출 : fBoardCommentId = " + fBoardCommentId);

	List<FBoardReplyVO> list = fBoardReplyService.read(fBoardCommentId);
	return new ResponseEntity<List<FBoardReplyVO>>(list, HttpStatus.OK);
}

@PutMapping("/{FBoardReplyId}") 
public ResponseEntity<Integer> updateReply(@PathVariable("FBoardReplyId") int fBoardReplyId,
		@RequestBody String fBoardReplyContent) {
	logger.info("updateReply() 호출 : fBoardReplyId = " + fBoardReplyId);
	int result = fBoardReplyService.update(fBoardReplyId, fBoardReplyContent);
	return new ResponseEntity<Integer>(result, HttpStatus.OK);
}

@DeleteMapping("/{FBoardReplyId}")
public ResponseEntity<Integer> deleteReply(@PathVariable("FBoardReplyId") int fBoardReplyId) {
	logger.info("FBoardReplyId = " + fBoardReplyId);

	int result = 0;
	try {
		result = fBoardReplyService.delete(fBoardReplyId);
	} catch (Exception e) {
		e.printStackTrace();
	}
	return new ResponseEntity<Integer>(result, HttpStatus.OK);
}

}
