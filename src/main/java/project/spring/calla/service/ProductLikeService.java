package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductLikeVO;

public interface ProductLikeService {
	int create(ProductLikeVO vo);  // 좋아요 등록
	List<ProductLikeVO> read(String memberNickname); // 좋아요 검색
	int delete(int productLikeId); // 좋아요 취소
}
