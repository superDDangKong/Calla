package project.spring.calla.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.spring.calla.domain.ProductLikeVO;
import project.spring.calla.service.ProductLikeService;

@RestController
@RequestMapping(value="/product/likes")

public class ProductLikeRESTController {
	private static final Logger logger=
			LoggerFactory.getLogger(ProductLikeRESTController.class);
	
	@Autowired
	private ProductLikeService productLikeService;
	
	@PostMapping
	public ResponseEntity<Integer> createProductLike(@RequestBody ProductLikeVO vo){
		logger.info("createProductLike() »£√‚ : vo = " + vo.toString());
		int result = 0;
		
		try {
			result = productLikeService.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);	
	}

	@DeleteMapping("/{productLikeId}")
	public ResponseEntity<Integer> deleteProductComment(
			@PathVariable("productLikeId") int productLikeId,
			@RequestBody int productId){
		logger.info("productLikeId = " + productLikeId);
		
		int result = 0;
		try {
			result = productLikeService.delete(productLikeId, productId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
}
