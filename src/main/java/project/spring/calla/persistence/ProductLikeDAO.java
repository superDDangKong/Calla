package project.spring.calla.persistence;

import project.spring.calla.domain.ProductLikeVO;

public interface ProductLikeDAO {
	int insert(ProductLikeVO vo);
	int delete(int productLikeId);
	int getTotalCount(int productId);
}
