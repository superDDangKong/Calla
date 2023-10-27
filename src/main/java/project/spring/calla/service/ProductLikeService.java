package project.spring.calla.service;

import project.spring.calla.domain.ProductLikeVO;

public interface ProductLikeService {
	int create(ProductLikeVO vo) throws Exception; // ���ƿ� ���
	int delete(int productLikeId, int productId) throws Exception; // ���ƿ� ����
	int getTotalCounts(int productId);
}
