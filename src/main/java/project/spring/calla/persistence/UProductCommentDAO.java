package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.domain.UProductCommentVO;


public interface UProductCommentDAO {
	
	int insert(UProductCommentVO vo);
	List<UProductCommentVO> select(int uProductId);
	int update(int uProductCommentId, String uProductCommentContent);
	int delete(int uProductCommentId);
	List<UProductCommentVO> select(String memberNickname);
}
