package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.FBoardReplyVO;

public interface FBoardReplyDAO {
	
	int insert(FBoardReplyVO vo);
	List<FBoardReplyVO> select(int fBoardCommentId);
	int update(int fBoardReplyId, String fBoardReplyContent);
	int delete(int fBoardReplyId);
}
