package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.FBoardReplyVO;

@Repository
public class FBoardReplyDAOImple implements FBoardReplyDAO {

	private static final Logger logger =
			LoggerFactory.getLogger(FBoardReplyDAOImple.class);
	
	private static final String NAMESPACE = 
			"project.spring.calla.FreeBoardReplyMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(FBoardReplyVO vo) {
		logger.info("insert() 호출 : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<FBoardReplyVO> select(int fBoardCommentId) {
		logger.info("select() 호출 : fBoardCommentId = " + fBoardCommentId);
		return sqlSession.selectList(NAMESPACE + ".select_all_by_board_comment_id", fBoardCommentId);
	}

	@Override
	public int update(int fBoardReplyId, String fBoardReplyContent) {
		logger.info("update() 호출");
		logger.info("replyId = " + fBoardReplyId + ", replyContent = " + fBoardReplyContent);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("fBoardReplytId", fBoardReplyId);
		args.put("fBoardReplyContent", fBoardReplyContent);
		return sqlSession.update(NAMESPACE + ".update", args);
	}

	@Override
	public int delete(int fBoardReplyId) {
		logger.info("delete() 호출 : replyId = " + fBoardReplyId);
		return sqlSession.delete(NAMESPACE + ".delete", fBoardReplyId);
	}

}
