package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.ProductReplyVO;

@Repository
public class ProductReplyDAOImple implements ProductReplyDAO {
	
	private static final Logger logger =
			LoggerFactory.getLogger(ProductReplyDAOImple.class);
	
	private static final String NAMESPACE =
			"project.spring.calla.ProductReplyMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(ProductReplyVO vo) {
		logger.info("insert() 호출 : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<ProductReplyVO> select(int productCommentId) {
		logger.info("select() 호출 : productCommentId = " + productCommentId);
		return sqlSession.selectList(NAMESPACE + ".select_all_by_product_comment_id", productCommentId);
	}

	@Override
	public int update(int productReplyId, String productReplyContent) {
		logger.info("update() 호출");
		logger.info("replyId = " + productReplyId + ", replyContent = " + productReplyContent);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("productReplyId", productReplyId);
		args.put("productReplyContent", productReplyContent);
		return sqlSession.update(NAMESPACE + ".update", args);
	}

	@Override
	public int delete(int productReplyId) {
		logger.info("delete() 호출 : replyId = " + productReplyId);
		return sqlSession.delete(NAMESPACE + ".delete", productReplyId);
	}

}
