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

import project.spring.calla.domain.FBoardCommentVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.service.FBoardCommentService;

@RestController
@RequestMapping(value="/fBoard/comments")
public class FBoardCommentRESTController {

	private static final Logger logger 
				= LoggerFactory.getLogger(FBoardCommentRESTController.class);

	@Autowired
	private FBoardCommentService fBoardCommentService;

	@PostMapping
	public ResponseEntity<Integer> createComment(@RequestBody FBoardCommentVO vo) {
		logger.info("createComment() »£√‚ : vo = " + vo.toString());
		int result = 0;
		try {
			result = fBoardCommentService.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@GetMapping("/all/{FBoardId}/{commentPage}/{commentNumsPerPage}") // GET : ¥Ò±€ º±≈√(all)
	public ResponseEntity<Map<String, Object>> readComments(
			@PathVariable("FBoardId") int fBoardId, @PathVariable("commentPage") Integer commentPage, 
			@PathVariable("commentNumsPerPage") Integer commentNumsPerPage) {
		List<FBoardCommentVO> list = null;
		PageCriteria criteria = new PageCriteria();
		PageMaker pageMaker = new PageMaker();
		
		if(commentPage != null) {
			criteria.setPage(commentPage);
		}
		
		if(commentNumsPerPage != null) {
			criteria.setNumsPerPage(commentNumsPerPage);
		}
		
		list = fBoardCommentService.read(criteria, fBoardId);
		
		pageMaker.setTotalCount(fBoardCommentService.getTotalCounts(fBoardId));
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("list", list);
		responseMap.put("pageMaker", pageMaker);

		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		
	}

	@PutMapping("/{FBoardCommentId}") // PUT : ¥Ò±€ ºˆ¡§
	public ResponseEntity<Integer> updateComment(
			@PathVariable("FBoardCommentId") int fBoardCommentId,
			@RequestBody String fBoardCommentContent) {
		int result = fBoardCommentService.update(fBoardCommentId, fBoardCommentContent);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	@DeleteMapping("/{FBoardCommentId}")
	public ResponseEntity<Integer> deleteComment(@PathVariable("FBoardCommentId") int fBoardCommentId,
			@RequestBody int fBoardId) {
		logger.info("FBoardCommentId = " + fBoardCommentId);

		int result = 0;
		try {
			result = fBoardCommentService.delete(fBoardCommentId, fBoardId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}
