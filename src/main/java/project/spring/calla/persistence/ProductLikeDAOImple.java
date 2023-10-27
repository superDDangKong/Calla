package project.spring.calla.persistence;

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
		logger.info("insert() ȣ�� : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public int delete(int productLikeId) {
		logger.info("delete() ȣ�� : productLikeId = " + productLikeId);
		return sqlSession.delete(NAMESPACE + ".delete", productLikeId);
	}

	@Override
	public int getTotalCount(int productId) {
		logger.info("getTotalCounts() ȣ�� : productId = " + productId);
		return sqlSession.selectOne(NAMESPACE + ".total_count", productId);
	}

}
