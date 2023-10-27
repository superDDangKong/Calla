package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.QBoardReplyVO;

public interface QBoardReplyDAO {
	int insert(QBoardReplyVO vo);
	List<QBoardReplyVO> select(int qBoardCommentId);
	int update(int qBoardReplyId, String qBoardReplyContent);
	int delete(int qBoardReplyId);
}
