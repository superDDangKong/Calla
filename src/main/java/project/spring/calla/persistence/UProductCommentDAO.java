package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.domain.UproductCommentVO;


public interface UProductCommentDAO {
	
	int insert(UproductCommentVO vo);
	List<UproductCommentVO> select(int uProductId);
	int update(int uProductCommentId, String uProductCommentContent);
	int delete(int uProductCommentId);
	List<UproductCommentVO> select(String memberNickname);
}
