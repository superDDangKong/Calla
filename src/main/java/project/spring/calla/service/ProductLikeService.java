package project.spring.calla.service;

import project.spring.calla.domain.ProductLikeVO;

public interface ProductLikeService {
	int create(ProductLikeVO vo) throws Exception; // 좋아요 등록
	int delete(int productLikeId, int productId) throws Exception; // 좋아요 삭제
	int getTotalCounts(int productId);
}
