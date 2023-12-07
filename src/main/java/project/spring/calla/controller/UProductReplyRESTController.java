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

import project.spring.calla.domain.ProductReplyVO;
import project.spring.calla.domain.UProductReplyVO;
import project.spring.calla.service.ProductReplyService;
import project.spring.calla.service.UProductReplyService;

@RestController
@RequestMapping(value="/uProduct/replies")
public class UProductReplyRESTController {
	private static final Logger logger =
			LoggerFactory.getLogger(UProductReplyRESTController.class);
	
	@Autowired
	private UProductReplyService uproductReplyService;
	
	@PostMapping // POST : 답글 입력
	public ResponseEntity<Integer> createReply(@RequestBody UProductReplyVO vo){
		logger.info("createReply() 호출 : vo = " + vo.toString());
		
		int result = 0;
		try {
			result = uproductReplyService.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("/all/{uProductCommentId}") // 댓글 선택(all)
	public ResponseEntity<List<UProductReplyVO>> readReplies(@PathVariable("uProductCommentId") int uProductCommentId){
		logger.info("readProductReplies() 호출 : uProductCommentId = " + uProductCommentId);
		
		List<UProductReplyVO> list = uproductReplyService.read(uProductCommentId);
		return new ResponseEntity<List<UProductReplyVO>>(list, HttpStatus.OK);
	}
	
	@PutMapping("/{uProductReplyId}") // PUT : 답글 수정
	public ResponseEntity<Integer> updateReply(@PathVariable("uProductReplyId") int uProductReplyId, @RequestBody String uProductReplyContent){
		logger.info("updateReply() 호출 : productReplyId = " + uProductReplyId);
		int result = uproductReplyService.update(uProductReplyId, uProductReplyContent);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{uProductReplyId}") // Delete : 답글 삭제
	public ResponseEntity<Integer> deleteReply(@PathVariable("uProductReplyId") int uProductReplyId){
		logger.info("uProductReplyId = " + uProductReplyId);
		
		int result = 0;
		try {
			result = uproductReplyService.delete(uProductReplyId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
} // end ProductReplyRESTControler
