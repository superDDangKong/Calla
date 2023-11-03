package project.spring.calla.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.spring.calla.domain.FBoardCommentVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.service.MemberService;

@RestController
@RequestMapping(value="/member/info")
public class MemberInfoRESTController {
	private static final Logger logger =
			LoggerFactory.getLogger(MemberInfoRESTController.class);
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/orderList/all/{memberId}") // GET : 댓글 선택(all)
	public ResponseEntity<Map<String, Object>> getAllOrderList(
			@PathVariable("memberId") String memberId) {
		logger.info("getAllOrderList() 호출 : memberId = " + memberId);
		List<FBoardCommentVO> list = null;
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("list", list);

		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		
	}
}
	
	

