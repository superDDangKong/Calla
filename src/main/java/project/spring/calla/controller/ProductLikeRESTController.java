package project.spring.calla.controller;

import java.util.HashMap;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Map<String, Object>> createProductLike(@RequestBody ProductLikeVO vo){
		logger.info("createProductLike() 호출 : vo = " + vo.toString());
		int result = 0;
		ProductLikeVO productLikeVO = productLikeService.read(vo.getProductId(), vo.getMemberId());
		int productLikeId = vo.getProductLikeId(); 
		
		try {
			result = productLikeService.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("result", result);
		responseMap.put("productLikeId", productLikeId);
		
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
	
	@DeleteMapping("/{productId}/{memberId}")
	public ResponseEntity<Map<String, Object>> deleteProductLike(
	        @PathVariable("productId") int productId,
	        @PathVariable("memberId") String memberId) {
	    logger.info("deleteProductLike() 호출 : productId = " + productId + ", memberId = " + memberId);
	    
	    int result = 0;
	    try {
	        result = productLikeService.delete(productId, memberId);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    Map<String, Object> responseMap = new HashMap<>();
	    responseMap.put("result", result);
	    
	    return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
	
}
