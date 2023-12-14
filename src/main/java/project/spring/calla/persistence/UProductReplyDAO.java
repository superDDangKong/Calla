package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductReplyVO;

public interface UProductReplyDAO {
	
	int insert(UProductReplyVO vo);
	List<UProductReplyVO> select(int uProductCommentId);
	UProductReplyVO selectvo(int uProductCommentId);
	int update(int uProductReplyId, String uProductReplyContent);
	int delete(int uProductReplyId);

}
