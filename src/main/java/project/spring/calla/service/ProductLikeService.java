package project.spring.calla.service;

import project.spring.calla.domain.ProductLikeVO;

public interface ProductLikeService {
	int create(ProductLikeVO vo) throws Exception;
	int delete(int productId, String memberId) throws Exception;
	int getTotalCounts(int productId);
	int checkProductLike(int productLikeId, String memberId);
	ProductLikeVO read(int productId, String memberId);
}
