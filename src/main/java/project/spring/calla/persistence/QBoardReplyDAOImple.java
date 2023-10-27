package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.QBoardReplyVO;
@Repository
public class QBoardReplyDAOImple implements QBoardReplyDAO {
	
	private static final Logger logger =
			LoggerFactory.getLogger(QBoardReplyDAOImple.class);
	private static final String NAMESPACE = 
			"project.spring.calla.QNABoardReplyMapper";
	
	@Autowired
	private SqlSession sqlSession;
	@Override
	public int insert(QBoardReplyVO vo) {
		logger.info("insert() 호출 vo : " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<QBoardReplyVO> select(int qBoardCommentId) {
		logger.info("select() 호출 qBoardCommenetId : " + qBoardCommentId);
		return sqlSession.selectList(NAMESPACE + ".select_all_by_board_comment_id", qBoardCommentId);
	}

	@Override
	public int update(int qBoardReplyId, String qBoardReplyContent) {
		logger.info("update 호출");
		logger.info("qBoardReplyId : " + qBoardReplyId + "qBoardReplyContent" + qBoardReplyContent);
		Map<String, Object> aya = new HashMap<String, Object>();
		aya.put("qBoardReplyId", qBoardReplyId);
		aya.put(qBoardReplyContent, qBoardReplyContent);
		return sqlSession.update(NAMESPACE + ".update", aya);
	}

	@Override
	public int delete(int qBoardReplyId) {
		logger.info("delete() 호출 : qBoardReplyId : " + qBoardReplyId);
		return sqlSession.delete(NAMESPACE + ".delete", qBoardReplyId);
	}

}
