package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.ProductOrderListVO;

@Repository
public class ProductOrderListDAOImple implements ProductOrderListDAO {
	
	private static final Logger logger=
			LoggerFactory.getLogger(ProductOrderListDAOImple.class);
	
	private static final String NAMESPACE =
			"project.spring.calla.ProductOrderListMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(ProductOrderListVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public List<ProductOrderListVO> select() {
		logger.info("select() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_all");
	}

	@Override
	public int update(int productId, String memberId, int productAmount) {
		logger.info("update() 호출");
		Map<String, Object> args = new HashMap();
		args.put("productId", productId);
		args.put("memberId", memberId);
		args.put("productAmount", productAmount);
		return sqlSession.update(NAMESPACE + ".update", args);
	}

	@Override
	public int delete(int productId, String memberId) {
		logger.info("delete() 호출 : memberId = " + memberId);
		Map<String, Object> args = new HashMap();
		args.put("productId", productId);
		args.put("memberId", memberId);
		return sqlSession.delete(NAMESPACE + ".delete", args);
	}

	@Override
	public ProductOrderListVO select(int productId, String memberId) {
		logger.info("select() 호출 : productId = " + productId + ",memberId = " + memberId);
		Map<String, Object> args = new HashMap();
		args.put("productId", productId);
		args.put("memberId", memberId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_member_id", args);
	}

	@Override
	public List<ProductOrderListVO> selectBy(String memberId) {
		logger.info("selectBy() 호출 : " + memberId);
		return sqlSession.selectList(NAMESPACE + ".select_by_id", memberId);
	}

	

}
