package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.ProductImageVO;

@Repository
public class ProductImageDAOImple implements ProductImageDAO {

	private static final Logger logger =
			LoggerFactory.getLogger(ProductImageDAOImple.class);
	
	private static final String NAMESPACE =
			"project.spring.calla.ProductImageMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(ProductImageVO vo) {
		logger.info("insert() 호출 : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<ProductImageVO> select(int productImageId) {
		logger.info("select() 호출 : productImageId = " + productImageId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_product_image_id", productImageId);
	}

	@Override
	public int update(int productImageId, String productImagePath) {
		logger.info("update() 호출 : productImageId = " + productImageId + ", productImagePath = " + productImagePath);
		Map<String, Object> args = new HashMap();
		args.put("productImageId", productImageId);
		args.put("productImagePath", productImagePath);
		return sqlSession.update(NAMESPACE + ".update", args);
	}

	@Override
	public int delete(int productId) {
		logger.info("delete() 호출 : productId = " + productId);
		return sqlSession.delete(NAMESPACE + ".delete", productId);
	}

}
