package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductImageVO;

public interface ProductImageService {
	int create(ProductImageVO vo) throws Exception;
	List<ProductImageVO> read(int productImageId);
	int update(int productImageId, String productImagePath);
	int delete(int productId) throws Exception;
}
