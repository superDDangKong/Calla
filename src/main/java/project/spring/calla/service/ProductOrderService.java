package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductOrderVO;

public interface ProductOrderService {
	int create(ProductOrderVO vo);
	int update(int productId, String memberId, String memberEmail, String recipientName, String memberAddress);
	int delete(int productId, String memberId);
	ProductOrderVO readBy(String memberId);
	List<ProductOrderVO> read();
//	int create(int productId, String productName, int productPrice, int productAmount, String memberId);
	List<ProductOrderVO> read(String memberId);
	int updateDeliveryStatus(int productId, String memberId, String deliveryStatus);
}
