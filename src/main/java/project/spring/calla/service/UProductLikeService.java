package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.ProductLikeVO;
import project.spring.calla.domain.UProductLikeVO;


public interface UProductLikeService {
	int create(UProductLikeVO vo) throws Exception;
	int delete(int uProductId, String memberId) throws Exception;
	int getTotalCounts(int uProductId);
	int checkProductLike(int uProductLikeId, String memberId);
	UProductLikeVO read(int uProductId, String memberId);
}
