package project.spring.calla.scheduler;

import java.time.Instant;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.service.ProductOrderService;

@Component
public class ProductOrderScheduler {
	private static final Logger logger 
		= LoggerFactory.getLogger(ProductOrderScheduler.class);
	
	@Autowired
	private ProductOrderService productOrderService;
	
	@Scheduled(fixedRate = 60 * 60 * 1000) // 1시간
	public void updateDeliveryStatus() {
		
		logger.info("updateDeliveryStatus() 호출");
		
		// ProductOrderVO 가져오기
		List<ProductOrderVO> orders = productOrderService.read();
		logger.info("orders = " + orders);
		// 현재 시간
		LocalDateTime currentTime = LocalDateTime.now();
		
		for(ProductOrderVO order : orders) {
			Date orderTime = order.getProductOrderCreatedDate(); // 생선된 시간
			Instant instant = orderTime.toInstant(); // Date를 Instant로 
			LocalDateTime afterOrderTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime(); // Instant를 LocalDateTime으로 
			LocalDateTime oneMinuteAfterOrderTime = afterOrderTime.plusHours(24); // 구매시간 + 24시간
			
			if(currentTime.isAfter(oneMinuteAfterOrderTime)) {
				String currentStatus = order.getDeliveryStatus();
				String newDeliveryStatus;
				
				if(currentStatus.equals("출고준비중")) {
					newDeliveryStatus = "출고완료";
				} else if(currentStatus.equals("출고완료")) {
					newDeliveryStatus = "배송중";
				} else if(currentStatus.equals("배송중")) {
					newDeliveryStatus = "배송완료";
				} else {
					newDeliveryStatus = currentStatus;
				}
				
				logger.info("productOrderId: " + order.getProductOrderId() + ", newDeliveryStatus: " + newDeliveryStatus);
                
				
				productOrderService.updateStatus(order.getProductOrderId(), newDeliveryStatus);
			}
		} // end for()
	} // end updateDeliveryStatus()
	
} // end productOrderScheduler