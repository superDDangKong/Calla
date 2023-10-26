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

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.PageMaker;
import project.spring.calla.service.ProductCommentService;

@RestController
@RequestMapping(value="/product/comments")

public class ProductCommentRESTController {
	private static final Logger logger=
			LoggerFactory.getLogger(ProductCommentRESTController.class);
	
	@Autowired
	private ProductCommentService productCommentService;
	
	@PostMapping
	public ResponseEntity<Integer> createProductComment(@RequestBody ProductCommentVO vo ){
		logger.info("createProductComment() 호출 : vo = " + vo.toString());
		int result = 0;

		try {
			result = productCommentService.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("/all/{productId}/{commentPage}/{commentNumsPerPage}") // GET : 댓글 선택(all)
	public ResponseEntity<Map<String, Object>> readComments(
			@PathVariable("productId") int productId, @PathVariable("commentPage") Integer commentPage,
			@PathVariable("commentNumsPerPage") Integer commentNumsPerPage){
		// @PathVariable("productId") : /all/{productId} 값을 설정된 변수에 저장
		logger.info("readComments() 호출 : productId = " + productId);
		logger.info("readComments() 호출 : commentPage = " + commentPage);
		logger.info("readComments() 호출 : commentNumsPerPage = " + commentNumsPerPage);
		List<ProductCommentVO> list = null;
		PageCriteria criteria = new PageCriteria();
		
		if(commentPage != null) {
			criteria.setPage(commentPage);
		}
		
		if(commentNumsPerPage != null) {
			criteria.setNumsPerPage(commentNumsPerPage);
		}
		
		PageMaker pageMaker = new PageMaker();
		list = productCommentService.read(criteria, productId);
		
		pageMaker.setTotalCount(productCommentService.getTotalCounts(productId));
		pageMaker.setCriteria(criteria);
		pageMaker.setPageData();
		
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("list", list);
		responseMap.put("pageMaker", pageMaker);

		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
		
	}
	
	
	@PutMapping("/{productCommentId}")
	public ResponseEntity<Integer> updateProductComment(
			@PathVariable("productCommentId") int productCommentId,
			@RequestBody String productCommentContent
			){
		int result = productCommentService.update(productCommentId, productCommentContent);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{productCommentId}")
	public ResponseEntity<Integer> deleteProductComment(
			@PathVariable("productCommentId") int productCommentId,
			@RequestBody int productId){
		logger.info("productCommentId = " + productCommentId);
		
		int result = 0;
		try {
			result = productCommentService.delete(productCommentId, productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
			
}
