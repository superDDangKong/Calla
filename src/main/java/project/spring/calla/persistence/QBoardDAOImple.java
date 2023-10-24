package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.QBoardVO;
import project.spring.calla.pageutil.PageCriteria;


@Repository
public class QBoardDAOImple implements QBoardDAO {

	
	private static final Logger logger = 
				LoggerFactory.getLogger(QBoardDAOImple.class);
		
	private static final String NAMESPACE = 
				"project.spring.calla.QNABoardMapper";
	
	// MyBatis의 SqlSession을 사용하기 위해
	// 스프링 프레임워크가 생성한 bean을 주입(injection)받음
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(QBoardVO vo) {
		logger.info("insert() 호출 : vo = " + vo.toString());
		// TODO Auto-generated method stub
		return sqlSession.insert(NAMESPACE + ".insert", vo); 
		// NAMESPACR가 동일한 mapper를 찾아가서 id="insert"인
		// <insert> 태그에 vo 데이터를 전송
	}

	@Override
	public List<QBoardVO> select() {
		logger.info("select All() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_all"); 
	}

	@Override
	public QBoardVO select(int qBoardId) {
		logger.info("select() 호출 : boardId = " + qBoardId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_board_id", qBoardId);
	}

	@Override
	public int update(QBoardVO vo) {
		logger.info("update() 호출 : vo = " + vo.toString());
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int qBoardId) {
		logger.info("delete() 호출 : boardId = " + qBoardId);
		return sqlSession.delete(NAMESPACE + ".delete", qBoardId);
	}

	@Override
	public List<QBoardVO> select(PageCriteria criteria) {
		logger.info("select() 호출");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return sqlSession.selectList(NAMESPACE + ".paging", criteria);
	}

	@Override
	public int getTotalCounts() {
		logger.info("getTotalCounts() 호출");
		return sqlSession.selectOne(NAMESPACE + ".total_count");
	}

	@Override
	public List<QBoardVO> select(String memberNickname) {
		logger.info("select() 호출 : memberId = " + memberNickname);
		return sqlSession.selectList(NAMESPACE + ".select_by_memberNickname", "%" + memberNickname + "%");
	}

	@Override
	public List<QBoardVO> selectByTitleOrContent(String keyword) {
		logger.info("selectByTitleOrContent() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_by_title_content", "%" + keyword + "%");
		
	}

	@Override
	public int updateCommentCnt(int amount, int qBoardId) {
		logger.info("updateCommentCnt() : qBoardId = " + qBoardId);
		Map<String, Integer> args = new HashMap();
		args.put("amount", amount);
		args.put("qBoardId", qBoardId);
		return sqlSession.update(NAMESPACE + ".update_comment_count", args); // 여기 나중에 수정
	}

}
