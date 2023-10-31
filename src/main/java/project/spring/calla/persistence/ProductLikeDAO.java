package project.spring.calla.persistence;

import project.spring.calla.domain.ProductLikeVO;

public interface ProductLikeDAO {
	int insert(ProductLikeVO vo);
	int delete(String memberId);
	int getTotalCount(int productId);
	int checkProductLike(int productId, String memberId);
	ProductLikeVO select(int productId, String memberId);
}
