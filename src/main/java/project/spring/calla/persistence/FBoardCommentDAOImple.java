package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.FBoardCommentVO;
import project.spring.calla.domain.FBoardVO;
import project.spring.calla.pageutil.PageCriteria;

@Repository
public class FBoardCommentDAOImple implements FBoardCommentDAO {

	private static final Logger logger =
			LoggerFactory.getLogger(FBoardDAOImple.class);
	
	private static final String NAMESPACE = 
			"project.spring.calla.FreeBoardCommentMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(FBoardCommentVO vo) {
		logger.info("insert() 호출 : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<FBoardCommentVO> select(int fBoardId) {
		logger.info("select() 호출 : fBoardId = " + fBoardId);
		return sqlSession.selectList(NAMESPACE + ".select_all_by_board_id", fBoardId);
	}

	@Override
	public int update(int fBoardCommentId, String fBoardCommentContent) {
		logger.info("update() 호출");
		logger.info("replyId = " + fBoardCommentId + ", replyContent = " + fBoardCommentContent);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("fBoardCommentId", fBoardCommentId);
		args.put("fBoardCommentContent", fBoardCommentContent);
		return sqlSession.update(NAMESPACE + ".update", args);
	}

	@Override
	public int delete(int fBoardCommentId) {
		logger.info("delete() 호출 : replyId = " + fBoardCommentId);
		return sqlSession.delete(NAMESPACE + ".delete", fBoardCommentId);
	}

	@Override
	public List<FBoardCommentVO> select(PageCriteria criteria, int fBoardId) {
		Map<String, Object> args = new HashMap();
		args.put("start", criteria.getStart());
		args.put("end", criteria.getEnd());
		args.put("fBoardId", fBoardId);
		return sqlSession.selectList(NAMESPACE + ".paging", args);
	}

	@Override
	public int getTotalCounts(int fBoardId) {
		logger.info("getTotalCounts()");
		return sqlSession.selectOne(NAMESPACE + ".total_count", fBoardId);
	}

	@Override
	public List<FBoardCommentVO> select(String memberNickname) {
		logger.info("select(memberNickname) 호출 memberNickname = " + memberNickname);
		return sqlSession.selectList(NAMESPACE + ".select_by_memberNickname", memberNickname);
	}

	
}
