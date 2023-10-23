package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.persistence.FBoardDAO;

@Service // @Component
// * 서비스 계층(Service/Business Layer)
// - 표현 계층(Presentation Layer)과 영속 계층(Persistence Layer)사이를 
//	 연결하여 두 계층이 직접적으로 통신하지 않도록 하는 역할
// - 트랜잭션(transaction) 관리
// - DB와 상관없이 데이터를 처리 가능

public class FBoardServiceImple implements FBoardService{
	private static final Logger logger =
			LoggerFactory.getLogger(FBoardServiceImple.class);
	
	@Autowired
	private FBoardDAO dao;
	
	@Override
	public int create(FBoardVO vo) {
		logger.info("create() 호출 : vo = " + vo.toString());
		return dao.insert(vo);
	}

	@Override
	public List<FBoardVO> read(PageCriteria criteria) {
		logger.info("read() 호출");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());

		return dao.select(criteria);
	}
	
	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts() 호출");
		return dao.getTotalCounts();
	}
	

	@Override
	public List<FBoardVO> readByMemberNickname(PageCriteria criteria, String keyword) {
		logger.info("readByMemberNickname() 호출");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		logger.info("memberNickname = " + keyword);
		
		return dao.selectByMemberNickname(criteria, keyword);
	}

	@Override
	public int getTotalCountsByMemberNickname(String keyword) {
		logger.info("getTotalCountsByMemberNickname() 호출");
		return dao.getTotalCountsByMemberNickname(keyword);
	}

	@Override
	public List<FBoardVO> readByTitleOrContent(PageCriteria criteria, String keyword) {
		logger.info("readByTitleOrContent() 호출");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		logger.info("keyword = " + keyword);
		
		return dao.selectByTitleOrContent(criteria, keyword);
	}
	
	@Override
	public int getTotalCountsByTitleContent(String keyword) {
		logger.info("getTotalCountsByTitleContent() 호출");
		return dao.getTotalCountsByTitleContent(keyword);
	}

	@Override
	public FBoardVO read(int fBoardId) {
		logger.info("read() 호출 : fBoardId = " + fBoardId);
		
		return dao.select(fBoardId);
	}

	@Override
	public int update(FBoardVO vo) {
		logger.info("update() 호출 : vo = " + vo.toString());
		return dao.update(vo);
	}

	@Override
	public int delete(int fBoardId) {
		logger.info("delete() 호출 : fBoardId = " + fBoardId);
		return dao.delete(fBoardId);
	}



	@Override
	public int updateViews(int views, int fBoardId) {
		logger.info("updateViews() 호출");
		return dao.updateViews(views, fBoardId);
	}





}
