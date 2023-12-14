package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.UProductReplyVO;

@Repository
public class UProductReplyDAOImple implements UProductReplyDAO {
	
	private static final Logger logger =
			LoggerFactory.getLogger(ProductReplyDAOImple.class);
	
	private static final String NAMESPACE =
			"project.spring.calla.Uproductreplymapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public int insert(UProductReplyVO vo) {
		logger.info("insert() »£√‚ : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<UProductReplyVO> select(int uProductCommentId) {
		logger.info("select() »£√‚ : uProductCommentId = " + uProductCommentId);
		return sqlSession.selectList(NAMESPACE + ".select_all_by_product_comment_id", uProductCommentId);
	}
	
	@Override
	public UProductReplyVO selectvo(int uProductCommentId) {
		logger.info("select() »£ÔøΩÔøΩ : uProductId = " + uProductCommentId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_product_comment_id", uProductCommentId);
	}

	@Override
	public int update(int uProductReplyId, String uProductReplyContent) {
		logger.info("update() »£√‚");
		logger.info("replyId = " + uProductReplyId + ", replyContent = " + uProductReplyContent);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("uProductReplyId", uProductReplyId);
		args.put("uProductReplyContent", uProductReplyContent);
		return sqlSession.update(NAMESPACE + ".update", args);
	}

	@Override
	public int delete(int uProductReplyId) {
		logger.info("delete() »£√‚ : replyId = " + uProductReplyId);
		return sqlSession.delete(NAMESPACE + ".delete", uProductReplyId);
	}

	

}
