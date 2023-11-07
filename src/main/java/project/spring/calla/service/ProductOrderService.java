package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.domain.ProductOrderVO.ProductData;

public interface ProductOrderService {
	int create(ProductOrderVO vo);
	int update(int productId, String memberId, String memberEmail, String recipientName, String memberAddress);
	int delete(int productId, String memberId);
	ProductOrderVO read(String memberId);
//	List<ProductOrderVO> read();
//	int create(int productId, String productName, int productPrice, int productAmount, String memberId);
	int create(ProductData productData);
}
