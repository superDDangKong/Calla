package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.QBoardCommentVO;
import project.spring.calla.pageutil.PageCriteria;

public interface QBoardCommentDAO {
	int insert(QBoardCommentVO vo);
	List<QBoardCommentVO> select(int qBoardId);
	int update(int qBoardCommentId, String qBoardCommentContent);
	int delete(int qBoardCommentId);
	List<QBoardCommentVO> select(String memberNickname);
	List<QBoardCommentVO> select(PageCriteria criteria, int qBoardId);
	int getTotalCounts(int qBoardId);
}
