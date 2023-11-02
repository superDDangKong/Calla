package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductOrderListVO;

public interface ProductOrderListService {
	int create(ProductOrderListVO vo);
	int delete(int productId, String memberId);
	List<ProductOrderListVO> read();
	ProductOrderListVO read(int productId, String memberId);
	List<ProductOrderListVO> readBy(String memberId);
	int update(int productId, String memberId, int productAmount);
}
