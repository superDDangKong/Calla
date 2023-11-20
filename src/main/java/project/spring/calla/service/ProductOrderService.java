package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductOrderVO;

public interface ProductOrderService {
	int create(ProductOrderVO vo);
	int update(int productId, String memberId, String memberEmail, String recipientName, String memberAddress);
	int delete(int productorderId);
	ProductOrderVO readBy(String memberId);
	List<ProductOrderVO> read();
//	int create(int productId, String productName, int productPrice, int productAmount, String memberId);
	List<ProductOrderVO> read(String memberId);
	int updateDeliveryStatus(int productOrderId, String deliveryStatus);
}
