package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.QBoardReplyVO;

public interface QBoardReplyService {
	int create(QBoardReplyVO vo) throws Exception;
	List<QBoardReplyVO> read(int qBoardCommentId);
	int update(int qBoardReplyId, String qBoardReplyContent);
	int delete(int qBoardReplyId) throws Exception;
}
