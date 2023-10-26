package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.ProductCommentVO;
import project.spring.calla.domain.UproductCommentVO;
import project.spring.calla.pageutil.PageCriteria;

@Repository
public class ProductCommentDAOImple implements ProductCommentDAO {
	
	private static final Logger logger =
			LoggerFactory.getLogger(ProductCommentDAOImple.class);
	
	private static final String NAMESPACE =
			"project.spring.calla.ProductCommentMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(ProductCommentVO vo) {
		logger.info("insert() ȣ�� : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<ProductCommentVO> select(int productId) {
		logger.info("select() ȣ�� : productId = " + productId);
		return sqlSession.selectList(NAMESPACE + ".select_all_by_product_id", productId);
	}

	@Override
	public int update(int productCommentId, String productCommentContent) {
		logger.info("update() ȣ��");
		logger.info("productCommentId = " + productCommentId + ", productCommentContent = " + productCommentContent);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("productCommentId", productCommentId);
		args.put("productCommentContent", productCommentContent);
		return sqlSession.update(NAMESPACE + ".update", args);
	}

	@Override
	public int delete(int productCommentId) {
		logger.info("delete() ȣ�� : productCommentId = " + productCommentId);
		return sqlSession.delete(NAMESPACE + ".delete", productCommentId);
	}

	@Override
	public List<ProductCommentVO> select(PageCriteria criteria, int productId) {
		Map<String, Object> args = new HashMap();
		args.put("start", criteria.getStart());
		args.put("end", criteria.getEnd());
		args.put("productId", productId);
		return sqlSession.selectList(NAMESPACE + ".paging", args);
	}

	@Override
	public int getTotalCount(int productId) {
		logger.info("getTotalCounts()");
		return sqlSession.selectOne(NAMESPACE + ".total_count", productId);
	}

	@Override
	public List<ProductCommentVO> select(String memberNickname) {
		logger.info("select(memberNickname) ȣ�� memberNickname = " + memberNickname);
		return sqlSession.selectList(NAMESPACE + ".select_by_memberNickname", memberNickname);
	}
}
