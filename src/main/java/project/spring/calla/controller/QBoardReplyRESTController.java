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

import project.spring.calla.domain.QBoardReplyVO;
import project.spring.calla.service.QBoardReplyService;


@RestController
@RequestMapping(value="/qBoard/replies")
public class QBoardReplyRESTController {
	private static final Logger logger
	= LoggerFactory.getLogger(QBoardReplyRESTController.class);
	
	@Autowired
	private QBoardReplyService qBoardReplyService;
	
	@PostMapping
	public ResponseEntity<Integer> createReply(@RequestBody QBoardReplyVO vo){
		logger.info("createReply() 호출 : vo = " + vo.toString());
		int result = 0;
		
		try {
			result = qBoardReplyService.create(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("/all/{QBoardCommentId}") // 댓글 선택 + 답글 불러오기
	public ResponseEntity<List<QBoardReplyVO>> readReplies(@PathVariable("QBoardCommentId") int qBoardCommentId){
		// @PathVariable("qBoardId") : /all/{qBboardId} 값을 설정된 변수에 저장
		logger.info("readReplies() 호출 : qBoardCommentId = " + qBoardCommentId);
		List<QBoardReplyVO> list = qBoardReplyService.read(qBoardCommentId);
		return new ResponseEntity<List<QBoardReplyVO>>(list, HttpStatus.OK);
	}
	
	@PutMapping("/{QBoardReplyId}") // PUT : 댓글 수정
	public ResponseEntity<Integer> updateReply(@PathVariable("QBoardReplyId") int qBoardReplyId, @RequestBody String qBoardReplyContent){
		logger.info("updateReply() 호출 : qBoardReplyId = " + qBoardReplyId);
		int result = qBoardReplyService.update(qBoardReplyId, qBoardReplyContent);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/{QBoardReplyId}") 
	public ResponseEntity<Integer> deleteReply(@PathVariable("QBoardReplyId") int qBoardReplyId) {
		logger.info("QBoardReplyId = " + qBoardReplyId);
	    int result = 0;
	    try { 
	    	result = qBoardReplyService.delete(qBoardReplyId); 
	    	} catch (Exception e) { 
	    		e.printStackTrace(); 
	    	} 
	    return new 	ResponseEntity<Integer>(result, HttpStatus.OK); }
	 
}
