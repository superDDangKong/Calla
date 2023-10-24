package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.QBoardCommentVO;

public interface QBoardCommentDAO {
	int insert(QBoardCommentVO vo);
	List<QBoardCommentVO> select(int qBoardId);
	int update(int qBoardCommentId, String qBoardCommentContent);
	int delete(int qBoardCommentId);
	
}
