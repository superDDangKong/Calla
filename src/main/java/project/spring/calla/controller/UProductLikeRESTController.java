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
import project.spring.calla.domain.UProductLikeVO;
import project.spring.calla.service.ProductLikeService;
import project.spring.calla.service.UProductLikeService;

@RestController
@RequestMapping(value="/uProduct/likes")

public class UProductLikeRESTController {
	private static final Logger logger=
			LoggerFactory.getLogger(UProductLikeRESTController.class);
	
	@Autowired
	private UProductLikeService uproductLikeService;
		
	@PostMapping
	public ResponseEntity<Map<String, Object>> createProductLike(@RequestBody UProductLikeVO vo){
		logger.info("createProductLike() 호출 : vo = " + vo.toString());
		int result = 0;
		UProductLikeVO uproductLikeVO = uproductLikeService.read(vo.getuProductId(), vo.getMemberId());
		int uProductLikeId = vo.getuProductLikeId();
		
		try {
			result = uproductLikeService.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Map<String, Object> responseMap = new HashMap<>();
		responseMap.put("result", result);
		responseMap.put("uProductLikeId", uProductLikeId);
		
		return new ResponseEntity<Map<String, Object>>(responseMap, HttpStatus.OK);
	}
	
	@DeleteMapping("/{uProductId}/{memberId}")
	public ResponseEntity<Map<String, Object>> deleteProductLike(
	        @PathVariable("uProductId") int uProductId,
	        @PathVariable("memberId") String memberId) {
	    logger.info("deleteProductLike() 호출 : productId = " + uProductId + ", memberId = " + memberId);
	    
	    int result = 0;
	    try {
	        result = uproductLikeService.delete(uProductId, memberId);
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    Map<String, Object> responseMap = new HashMap<>();
	    responseMap.put("result", result);
	    
	    return new ResponseEntity<>(responseMap, HttpStatus.OK);
	}
	
}
