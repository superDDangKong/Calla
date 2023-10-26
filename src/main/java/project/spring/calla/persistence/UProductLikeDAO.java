package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.UProductLikeVO;

public interface UProductLikeDAO {
	int insert(UProductLikeVO vo);  // 좋아요 등록
	List<UProductLikeVO> select(String memberNickname); // 좋아요 검색
	int delete(int uProductLikeId); // 좋아요 취소
}
