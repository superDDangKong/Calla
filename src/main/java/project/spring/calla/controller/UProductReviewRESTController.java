package project.spring.calla.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.spring.calla.domain.UProductMannerVO;
import project.spring.calla.service.UProductReviewService;

// * RESTful url과 의미
// /replies (POST) : 댓글 추가(insert)
// /replies/all/숫자 (GET) : 해당 글 번호(boardId)의 모든 갯글 검색(select)
// /replies/숫자 (PUT) : 해당 댓글 번호(replyId)의 내용을 수정(update)
// /replies/숫자 (DELETE) : 해당 댓글 번호(replyId)의 댓글을 삭제(delete)

@RestController
@RequestMapping(value = "/uProduct/manner")
public class UProductReviewRESTController {
	private static final Logger logger = LoggerFactory.getLogger(UProductReviewRESTController.class);

	@Autowired
	private UProductReviewService reviewservice;
	
	@Transactional(value = "transactionManager")
	@PostMapping // POST : 댓글 입력
	public ResponseEntity<Integer> createReply(@RequestBody UProductMannerVO vo) {
		// @RequestBody
		// - 클라이언트에서 전송받은 json epdlxjfmf
		// 자바 객체로 변환해주는 annotation
		logger.info("createReply() 호출 : vo = " + vo.toString());

	
		int result = 0;
		String sellerNickname = vo.getSellerNickname();
		logger.info("createReply() 호출 : uProductId = " + vo.getuProductId());

		try {
			
			int count = reviewservice.count(vo.getuProductId());
			logger.info("reviewservice() 호출 : count = " + count);
			if(count == 0) {
			
			result = reviewservice.insertmanner(vo);
		
			reviewservice.updatememberManner(sellerNickname);
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
			
		} else {
			return new ResponseEntity<Integer>(result, HttpStatus.OK);
		}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	

}
