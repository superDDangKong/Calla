package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductImageVO;

public interface ProductImageDAO {
	int insert(ProductImageVO vo);
	List<ProductImageVO> select(int productImageId);
	int update(int productImageId, String productImagePath);
	int delete(int productId);
}
