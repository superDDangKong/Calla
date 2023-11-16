package project.spring.calla.controller;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.service.ProductOrderListService;
import project.spring.calla.service.ProductOrderService;

@RestController
@RequestMapping(value="/product/orders")
public class productOrderRESTController {
	
	private static final Logger logger=
			LoggerFactory.getLogger(productOrderRESTController.class);
	
	@Autowired
	private ProductOrderService productOrderService;
	
	@Autowired
	private ProductOrderListService productOrderListService;
	

	
	@PostMapping()
	 public ResponseEntity<Integer> createProductOrder(@RequestBody List<ProductOrderVO> productOrderList){
	      logger.info("createProductOrderList() »£√‚ : productOrderList = " + productOrderList);
	      
	      int result = 0;
	      
	      try {
			for (ProductOrderVO vo : productOrderList) {
				int result1 = productOrderService.create(vo);
				result += result1;
				
				int productId = vo.getProductId();
				String memberId = vo.getMemberId();
				
				productOrderListService.delete(productId, memberId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	       

	    return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	
	
	@PutMapping("/{productId}/{memberId}/{memberEmail}/{recipientName}/{memberAddress}")
	public ResponseEntity<Integer> updateProductOrder(
			@PathVariable("productId") int productId,
			@PathVariable("memberId") String memberId,
			@PathVariable("memberEmail") String memberEmail,
			@PathVariable("recipientName") String recipientName,
			@PathVariable("memberAddress") String memberAddress
			){
		int result = productOrderService.update(productId, memberId, memberEmail, recipientName, memberAddress);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@PutMapping("/{productId}/{memberId}/{deliveryStatus}")
	public ResponseEntity<Integer> updateDeliveryStatus(
			@PathVariable("productId") int productId,
			@PathVariable("memberId") String memberId,
			@PathVariable("deliveryStatus") String deliveryStatus){
		int result = productOrderService.updateDeliveryStatus(productId, memberId, deliveryStatus);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/{productId}/{memberId}")
	public ResponseEntity<Integer> deleteProductOrder(
			@PathVariable("productId") int productId,
			@PathVariable("memberId") String memberId){
		logger.info("memberId = " + memberId + "productId = " + productId);
		
		int result = 0;
		try {
			result = productOrderService.delete(productId, memberId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}
	
}
