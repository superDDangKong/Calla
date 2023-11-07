package project.spring.calla.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSessionAttributeListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.spring.calla.domain.ProductOrderListVO;
import project.spring.calla.service.ProductOrderListService;
import project.spring.calla.service.ProductService;

@RestController
@RequestMapping(value="/product/orderLists")
public class ProductOrderListRESTController {
	
	private static final Logger logger=
			LoggerFactory.getLogger(ProductOrderListRESTController.class);
	
	@Autowired
	private ProductOrderListService productOrderListService;
	
	@Autowired
	private ProductService productService;
	
	@PostMapping()
	public ResponseEntity<Integer> createProductOrderList(@RequestBody ProductOrderListVO vo){
		logger.info("createProductOrderList() 호출 : vo = " + vo.toString());
		
		int result = 0;
		try {
			result = productOrderListService.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("/all/{porductOrderListId}")
	public ResponseEntity<List<ProductOrderListVO>> productOrderLists(){
		logger.info("orders() 호출");
		
		List<ProductOrderListVO> list = productOrderListService.read();
		return new ResponseEntity<List<ProductOrderListVO>>(list, HttpStatus.OK);
	}
	
	@PutMapping("/{productId}/{memberId}/{productAmount}")
	public ResponseEntity<Integer> updateProductOrderList(
			@PathVariable("productId") int productId,
			@PathVariable("memberId") String memberId,
			@PathVariable("productAmount") int productAmount
			){
		int result = productOrderListService.update(productId, memberId, productAmount);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{productId}/{memberId}")
	public ResponseEntity<Integer> deleteProductOrderList(
			@PathVariable("productId") int productId,
			@PathVariable("memberId") String memberId){
		logger.info("memberId = " + memberId);
		
		int result = 0;
		try {
			result = productOrderListService.delete(productId, memberId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	
}
