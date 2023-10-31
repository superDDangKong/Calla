package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductOrderVO;

public interface ProductOrderService {
	int create(ProductOrderVO vo);
	int update(ProductOrderVO vo);
	int delete(int productOrderId);
	List<ProductOrderVO> read();
}
