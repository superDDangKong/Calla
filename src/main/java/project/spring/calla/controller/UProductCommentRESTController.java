package project.spring.calla.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

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
import project.spring.calla.domain.UproductCommentVO;
import project.spring.calla.service.FBoardCommentService;
import project.spring.calla.service.UproductCommentService;

@RestController
@RequestMapping(value="/uProduct/uproductcomments")
public class UProductCommentRESTController {

	private static final Logger logger 
				= LoggerFactory.getLogger(UProductCommentRESTController.class);

	@Autowired
	private UproductCommentService uProductCommentService;

	@PostMapping // POST : 댓글 입력
	public ResponseEntity<Integer> createComment(@RequestBody UproductCommentVO vo) {
		// @RequestBody
		// - 클라이언트에서 전송받은 json 데이터를
		// 자바 객체로 변환해주는 annotation
		logger.info("createComment() 호출 : vo = " + vo.toString());

		// ResponseEntity<T> : Rest 방식에서 데이터를 리턴할 때 쓰이는 객체
		// - 데이터 HttpStatus를 전송
		// - <T> : 보내고자 하는 데이터 타입
		int result = 0;
		try {
			result = uProductCommentService.create(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@GetMapping("/all/{uProductId}") // GET : 댓글 선택(all)
	public ResponseEntity<List<UproductCommentVO>> readComments(@PathVariable("uProductId") int uProductId, HttpSession session) {
		// @PathVariable("fBoardId") : /all/{fBboardId} 값을 설정된 변수에 저장
		logger.info("readComments() 호출 : uProductId = " + uProductId);

		List<UproductCommentVO> list = uProductCommentService.read(uProductId, session);
		return new ResponseEntity<List<UproductCommentVO>>(list, HttpStatus.OK);
	}
	
	
	
	
	
	

	@PutMapping("/{uProductCommentId}") // PUT : 댓글 수정
	public ResponseEntity<Integer> updateComment(@PathVariable("uProductCommentId") int uProductCommentId,
			@RequestBody String uProductCommentContent) {
		int result = uProductCommentService.update(uProductCommentId, uProductCommentContent);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{uProductCommentId}") // 댓글 삭제 
	public ResponseEntity<Integer> deleteComment(@PathVariable("uProductCommentId") int uProductCommentId,
			@RequestBody int uProductId) {
		logger.info("FBoardCommentId = " + uProductCommentId);

		int result = 0;
		try {
			result = uProductCommentService.delete(uProductCommentId, uProductId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}
