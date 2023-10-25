package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.UproductCommentVO;

@Repository
public class UProductCommentDAOImple implements UProductCommentDAO{
	
	private static final Logger logger =
			LoggerFactory.getLogger(UProductCommentDAOImple.class);
	
	private static final String NAMESPACE = 
			"project.spring.calla.Uproductcommentmapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(UproductCommentVO vo) {
		logger.info("insert() 호출 : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<UproductCommentVO> select(int uProductId) {
		logger.info("select() 호출 : uProductId = " + uProductId);
		return sqlSession.selectList(NAMESPACE + ".select_all_by_board_id", uProductId);
	}

	@Override
	public int update(int uProductCommentId, String uProductCommentContent) {
		logger.info("update() 호출");
		logger.info("replyId = " + uProductCommentId + ", replyContent = " + uProductCommentContent);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("fBoardCommentId", uProductCommentId);
		args.put("fBoardCommentContent", uProductCommentContent);
		return sqlSession.update(NAMESPACE + ".update", args);
	}

	@Override
	public int delete(int uProductCommentId) {
		logger.info("delete() 호출 : replyId = " + uProductCommentId);
		return sqlSession.delete(NAMESPACE + ".delete", uProductCommentId);
	}

}
