package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.QBoardCommentVO;
import project.spring.calla.domain.QBoardReplyVO;
import project.spring.calla.persistence.QBoardReplyDAO;

@Service
public class QBoardReplyServiceImple implements QBoardReplyService {

	private static final Logger logger =
			LoggerFactory.getLogger(QBoardReplyServiceImple.class);
	
	@Autowired
	private QBoardReplyDAO qBoardReplyDAO;

	@Override // 대댓글 생성
	public int create(QBoardReplyVO vo) throws Exception {
		logger.info("create() 호출 vo : " + vo.toString());
		int result = qBoardReplyDAO.insert(vo);
		return result;
	}

	@Override // 대댓글 불러오기
	public List<QBoardReplyVO> read(int qBoardCommentId) {
		logger.info("read 호출 qBoardCommentId : " + qBoardCommentId);
		return qBoardReplyDAO.select(qBoardCommentId);
	}

	@Override // 수정
	public int update(int qBoardReplyId, String qBoardReplyContent) {
		logger.info("update() 호출");
		logger.info("qBoardReplyId : " + qBoardReplyId + ", qBoardReplyContent : " + qBoardReplyContent);
		return qBoardReplyDAO.update(qBoardReplyId, qBoardReplyContent);
	}

	@Override // 삭제
	public int delete(int qBoardReplyId) throws Exception {
		logger.info("delete() 호출");
		int result = qBoardReplyDAO.delete(qBoardReplyId);
		return result;
	}
	


}
