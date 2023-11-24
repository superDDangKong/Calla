package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.QBoardVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.persistence.QBoardDAO;

@Service // @Component
// * 서비스 계층(Service/Business Layer)
// - 표현 계층(Presentation Layer)과 영속 계층(persistence Layer)사이를
//	 연결하여 두 계층이 직접적으로 통신하지 않도록 하는 역할
// - 트랜잭션(transaction) 관리
// - DB와 상관없이 데이터를 처리 가능
// 테이블당 dao를 하나씩 써야함
public class QBoardServiceImple implements QBoardService{
	private static final Logger logger = 
			LoggerFactory.getLogger(QBoardServiceImple.class);
	
	@Autowired
	private QBoardDAO dao;
	
	@Override
	public int create(QBoardVO vo) {
		logger.info("create() 호출 : vo = " + vo.toString());
		return dao.insert(vo);
	}

	@Override
	public List<QBoardVO> read(PageCriteria criteria) {
		logger.info("read() 호출");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return dao.select(criteria);
	}

	@Override
	public QBoardVO read(int qBoardId) {
		logger.info("read() 호출 : boardId = " + qBoardId + ", ");
		return dao.select(qBoardId);
	}

	@Override
	public int update(QBoardVO vo) {
		logger.info("update() 호출 : vo = " + vo.toString());
		return dao.update(vo);
	}

	@Override
	public int delete(int qBoardId) {
		logger.info("delete() 호출 : qBoardId = " + qBoardId);
		return dao.delete(qBoardId);
	}

	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts() 호출 ");
		return dao.getTotalCounts();
	}

	@Override
	public int updateViews(int views, int qBoardId) {
		logger.info("updateViews() 호출");
		return dao.updateViews(views, qBoardId);
	}

	@Override
	public List<QBoardVO> readBymemberNickname(PageCriteria criteria, String keyword) {
		logger.info("readByMemberNickname() 호출");
		return dao.selectByMemberNickname(criteria, keyword);
	}

	@Override
	public int getTotalCountsByMeberNickname(String keyword) {
		logger.info("getTotalCountsByMeberNickname() 호출" );
		return dao.getTotalCountsByMemberNickname(keyword);
	}

	@Override
	public List<QBoardVO> readByTitle(PageCriteria criteria, String keyword) {
		logger.info("readByTitle() 호출");
		return dao.selectByTitle(criteria, keyword);
	}

	@Override
	public int getTotalCountsByTitle(String keyword) {
		logger.info("getTotalCountsByTitle() 호출");
		return dao.getTotalCountsByTitle(keyword);
	}

}
