package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.UProductLikeVO;

public interface UProductLikeService {
	int create(UProductLikeVO vo);  // 좋아요 등록
	List<UProductLikeVO> read(String memberNickname); // 좋아요 검색
	int delete(int uProductLikeId); // 좋아요 취소
}
