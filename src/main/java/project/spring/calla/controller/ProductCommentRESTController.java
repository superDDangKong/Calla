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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.service.ProductCommentService;

@RestController
@RequestMapping(value="/product/productComments")

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
	
	@GetMapping("/all/{productId}")
	public ResponseEntity<List<ProductCommentVO>> readProductComments(
			@PathVariable("productId") int productId){
		logger.info("readProductComments() 호출 : productId = " + productId);
		
		List<ProductCommentVO> list = productCommentService.read(productId);
		return new ResponseEntity<List<ProductCommentVO>>(list, HttpStatus.OK);
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
