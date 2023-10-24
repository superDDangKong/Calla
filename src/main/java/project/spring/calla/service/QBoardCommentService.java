package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.QBoardCommentVO;

public interface QBoardCommentService {
	int create(QBoardCommentVO vo) throws Exception;
	List<QBoardCommentVO> read(int qBoardId);
	int update(int qBoardCommentId, String qBoardCommentContent);
	int delete(int qBoardCommentId, int qBoardId) throws Exception;
}
