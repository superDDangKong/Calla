package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.FBoardCommentVO;
import project.spring.calla.util.PageCriteria;

public interface FBoardCommentDAO {
	
	int insert(FBoardCommentVO vo);
	List<FBoardCommentVO> select(int fBoardId);
	int update(int fBoardCommentId, String fBoardCommentContent);
	int delete(int fBoardCommentId);
	List<FBoardCommentVO> select(PageCriteria criteria, int fBoardId);
	int getTotalCounts(int fBoardId);
	List<FBoardCommentVO> select(String memberNickname);
}
