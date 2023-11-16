package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductLikeVO;
import project.spring.calla.domain.UProductLikeVO;

public interface UProductLikeDAO {
	int insert(UProductLikeVO vo);
	int delete(int uProductId, String memberId);
	int getTotalCount(int uProductId);
	int checkProductLike(int uProductId, String memberId);
	UProductLikeVO select(int uProductId, String memberId);
	int deleteById(int uProductLikeId);
}
