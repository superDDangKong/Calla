package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.FBoardCommentVO;

public interface FBoardCommentDAO {
	
	int insert(FBoardCommentVO vo);
	List<FBoardCommentVO> select(int fBoardId);
	int update(int fBoardCommentId, String fBoardCommentContent);
	int delete(int fBoardCommentId);
}
