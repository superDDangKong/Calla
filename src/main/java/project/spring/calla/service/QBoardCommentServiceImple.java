package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.spring.calla.domain.QBoardCommentVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.persistence.QBoardCommentDAO;
import project.spring.calla.persistence.QBoardDAO;

@Service
public class QBoardCommentServiceImple implements QBoardCommentService {
	private static Logger logger =
			LoggerFactory.getLogger(QBoardCommentServiceImple.class);
	
	@Autowired
	private QBoardCommentDAO qBoardCommentDAO;
	
	// @Transactional
	// - 두 개의 데이터베이스의 변경이 생길 때,
	// 위의 쿼리는 수행이 된 상태에서, 아래 쿼리가 에러가 발생하면
	// 위의 쿼리는 rollback 되어야 한다.
	// 이런 기능을 제공해주는 Spring annotation
	// - root-context.xml에서 설정
	
	@Autowired
	private QBoardDAO qBoardDAO;
	
	@Transactional(value = "transactionManager")
	@Override
	public int create(QBoardCommentVO vo) throws Exception{
		// dao는 테이블당 하나?
		// 댓글(test_reply)의 데이터가 증가하면
		// 게시판(test_board)의 댓글 개수(reply_cnt)가 변경되어야 함
		// test_reply 테이블에 insert를 수행하면
		// test_board 테이블에 update를 수행한다.
		logger.info("create() 호출 : vo = " + vo.toString());
		int resultInsert = qBoardCommentDAO.insert(vo); // 댓글 입력
		logger.info(resultInsert + " 행 댓글 입력 성공");
		int result = qBoardDAO.updateCommentCnt(1, vo.getqBoardId());
		logger.info(result + " 행 수정 성공");
		return 1;
	}

	@Override
	public List<QBoardCommentVO> read(int qBoardId) {
		logger.info("read() 호출 : qBoardId = " + qBoardId);
		return qBoardCommentDAO.select(qBoardId);
	}

	@Override
	public int update(int qBoardCommentId, String qBoardCommentContent) {
		logger.info("update() 호출");
		logger.info("qBoardCommentId = " + qBoardCommentId + ", qBoardCommentContent = " + qBoardCommentContent);
		return qBoardCommentDAO.update(qBoardCommentId, qBoardCommentContent);
	}
	
	@Transactional(value = "transactionManager")
	@Override
	public int delete(int qBoardCommentId, int qBoardId) throws Exception{
		logger.info("delete() 호출 : qBoardCommentId = " + qBoardCommentId);
		int resultDelete = qBoardCommentDAO.delete(qBoardCommentId);
		logger.info(resultDelete + " 행 삭제 성공");
		int result = qBoardDAO.updateCommentCnt(-1, qBoardId);
		logger.info(result + "행 수정 성공");
		return 1;
	}

	@Override
	public List<QBoardCommentVO> read(PageCriteria criteria, int qBoardId) {
		logger.info("read() 호출");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		logger.info("qBoardId = " + qBoardId);
		return qBoardCommentDAO.select(criteria, qBoardId);
	}

	@Override
	public int getTotalCounts(int qBoardId) {
		logger.info("getTotalCounts() 호출");
		return qBoardCommentDAO.getTotalCounts(qBoardId);
	}

}
