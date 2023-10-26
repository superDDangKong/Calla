package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductLikeVO;

public interface ProductLikeDAO {
	int insert(ProductLikeVO vo);  // 좋아요 등록
	List<ProductLikeVO> select(String memberNickname); // 좋아요 검색
	int delete(int productLikeId); // 좋아요 취소
}
