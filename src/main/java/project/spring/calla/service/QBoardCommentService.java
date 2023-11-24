package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.QBoardCommentVO;
import project.spring.calla.pageutil.PageCriteria;

public interface QBoardCommentService {
	int create(QBoardCommentVO vo) throws Exception;
	List<QBoardCommentVO> read(int qBoardId);
	int update(int qBoardCommentId, String qBoardCommentContent);
	int delete(int qBoardCommentId, int qBoardId) throws Exception;
	List<QBoardCommentVO> read(PageCriteria criteria, int qBoardId);
	int getTotalCounts(int qBoardId);
}
