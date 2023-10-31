package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.ProductLikeVO;

@Repository
public class ProductLikeDAOImple implements ProductLikeDAO {
	
	private static final Logger logger =
			LoggerFactory.getLogger(ProductLikeDAOImple.class);
	
	private static final String NAMESPACE =
			"project.spring.calla.ProductLikeMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(ProductLikeVO vo) {
		logger.info("insert() 호출 : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public int delete(String memberId) {
		logger.info("delete() 호출 : memberId = " + memberId);
		return sqlSession.delete(NAMESPACE + ".delete", memberId);
	}

	@Override
	public int getTotalCount(int productId) {
		logger.info("getTotalCounts() 호출 : productId = " + productId);
		return sqlSession.selectOne(NAMESPACE + ".total_count", productId);
	}

	@Override
	public int checkProductLike(int productId, String memberId) {
		logger.info("checkProductLike() 호출 : productId = " + productId + ", memberId = " + memberId);
	    Map<String, Object> args = new HashMap<>();
	    args.put("productId", productId);
	    args.put("memberId", memberId);
	    Integer result = sqlSession.selectOne(NAMESPACE + ".checkProductLike", args);

	    if (result != null && result > 0) {
	        // 좋아요를 한 것
	        return 1;
	    } else {
	        // 좋아요를 하지 않은 것
	        return 0;
	    }
	}

	@Override
	public ProductLikeVO select(int productId, String memberId) {
		logger.info("select() 호출 : productId = " + productId + ",memberId = " + memberId);
		Map<String, Object> args = new HashMap();
		args.put("productId", productId);
		args.put("memberId", memberId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_member_id", args);
	}

}
