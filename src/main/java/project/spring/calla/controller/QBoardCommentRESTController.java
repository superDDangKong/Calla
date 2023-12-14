package project.spring.calla.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import project.spring.calla.domain.QBoardCommentVO;
import project.spring.calla.service.QBoardCommentService;
import project.spring.calla.util.PageCriteria;
import project.spring.calla.util.PageMaker;

// * RESTful url과 의미
// /replies (POST) : 댓글 추가(insert)
// /replies/all/숫자 (GET) : 해당 글 번호(boardId)의 모든 댓글 검색(select)
// /replies/숫자 (PUT) : 해당 댓글 번호(replyId)의 내용을 수정(update)
// /replies/숫자 (DELETE) : 해당 댓글 번호(replyId)의 댓글을 삭제(delete)

@RestController // controller responsebody 섞은
@RequestMapping(value = "/qBoard/comments")
public class QBoardCommentRESTController {
	private static final Logger logger = LoggerFactory.getLogger(QBoardCommentRESTController.class);

	@Autowired
	private QBoardCommentService qBoardCommentService;

	// @Mapping
	// @PostMapping 이 메서드는 HTTP POST 요청을 처리합니다.
	@PostMapping // POST : 댓글 입력
	public ResponseEntity<Integer> createqBoardComment(@RequestBody QBoardCommentVO vo) {
		// @RequestBody
		// - 클라이언트에서 전송받은 json 데이터를
		// 자바 객체로 변환해주는 annotation
		logger.info("createqBoardComment() 호출 : vo = " + vo.toString());

		// ResponseEntity<T> : Rest 방식에서 데이터를 리턴할 때 쓰이는 객체
		// - 데이터를 HttStatus를 전송
		// - <T> : 보내고자 하는 데이터 타입
		int result = 0; // 초기화를 미리 해줘야함
		try {
			result = qBoardCommentService.create(vo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	// @GetMapping 이 메서드는 HTTP GET 요청을 처리합니다.
	@GetMapping("/all/{qBoardId}") // GET : 댓글 선택(all)
	public ResponseEntity<List<QBoardCommentVO>> readComments(@PathVariable("qBoardId") int qBoardId) {
		// @PathVariable("boardId") : /all/{boardId} 값을 설정된 변수에 저장
		logger.info("readComments() 호출 : qBoardId = " + qBoardId);

		List<QBoardCommentVO> list = qBoardCommentService.read(qBoardId);
		return new ResponseEntity<List<QBoardCommentVO>>(list, HttpStatus.OK);
	}

	@GetMapping("/all/{qBoardId}/{commentPage}/{commentNumsPerPage}")
	public ResponseEntity<Map<String, Object>> readComments(@PathVariable("qBoardId") int qBoardId,
			@PathVariable("commentPage") Integer commentPage,
			@PathVariable("commentNumsPerPage") Integer commentNumsPerPage) {
		logger.info("readComments() 호출 : qBoardId = " + qBoardId);
		logger.info("readComments() 호출 : commentPage = " + commentPage);
		logger.info("readComments() 호출 : commentNumsPerPage = " + commentNumsPerPage);
		List<QBoardCommentVO> list = null;
		PageCriteria criteria = new PageCriteria();
		
		if(commentPage != null) {
			criteria.setPage(commentPage);
		}
		
		if(commentPage != null) {
			criteria.setNumsPerPage(commentNumsPerPage);
		}
		
		PageMaker pageMaker = new PageMaker();
		list = qBoardCommentService.read(criteria, qBoardId);
		
		pageMaker.setTotalCount(qBoardCommentService.getTotalCounts(qBoardId));
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("list", list);
		responseMap.put("pageMaker", pageMaker);
		
		return new ResponseEntity<Map<String,Object>>(responseMap, HttpStatus.OK);
	}

	@PutMapping("/{qBoardCommentId}") // PUT : 댓글 수정
	public ResponseEntity<Integer> updateComment(@PathVariable("qBoardCommentId") int qBoardCommentId,
			@RequestBody String qBoardCommentContent) {
		int result = qBoardCommentService.update(qBoardCommentId, qBoardCommentContent);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{qBoardCommentId}")
	public ResponseEntity<Integer> deleteReply(@PathVariable("qBoardCommentId") int qBoardCommentId,
			@RequestBody int qBoardId) {
		logger.info("qBoardCommentId = " + qBoardCommentId);

		int result = 0;
		try {
			result = qBoardCommentService.delete(qBoardCommentId, qBoardId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}
