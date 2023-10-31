package project.spring.calla.controller;

import java.util.List;import javax.servlet.http.HttpSessionAttributeListener;

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

import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.service.ProductOrderService;
import project.spring.calla.service.ProductService;

@RestController
@RequestMapping(value="/product/orders")
public class ProductOrderRESTController {
	
	private static final Logger logger=
			LoggerFactory.getLogger(ProductOrderRESTController.class);
	
	@Autowired
	private ProductOrderService productOrderService;
	
	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ResponseEntity<Integer> createProductOrder(@RequestBody ProductOrderVO vo){
		logger.info("createProductOrder() 호출 : vo = " + vo.toString());
		int result = 0;
		try {
			result = productOrderService.create(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@GetMapping("/all/{porductOrderId}")
	public ResponseEntity<List<ProductOrderVO>> productOrders(){
		logger.info("orders() 호출");
		
		List<ProductOrderVO> list = productOrderService.read();
		return new ResponseEntity<List<ProductOrderVO>>(list, HttpStatus.OK);
	}
	
	@PutMapping("/{productOrderId}")
	public ResponseEntity<Integer> updateProductOrder(
			@PathVariable("productOrderId") int productOrderId,
			@RequestBody ProductOrderVO vo
			){
		int result = productOrderService.update(vo);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{productOrderId}")
	public ResponseEntity<Integer> deleteProductOrder(
			@PathVariable("productOrderId") int productOrderId){
		logger.info("productOrderId = " + productOrderId);
		
		int result = 0;
		try {
			result = productOrderService.delete(productOrderId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
}
