package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.domain.ProductOrderVO.ProductData;

@Repository
public class ProductOrderDAOImple implements ProductOrderDAO {
	
	private static final Logger logger=
			LoggerFactory.getLogger(ProductOrderDAOImple.class);
	
	private static final String NAMESPACE =
			"project.spring.calla.ProductOrderMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(ProductOrderVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public int update(int productId, String memberId, String memberEmail, String recipientName, String memberAddress) {
		logger.info("update() 호출");
		Map<String, Object> args = new HashMap();
		args.put("productId", productId);
		args.put("memberId", memberId);
		args.put("memberEmail", memberEmail);
		args.put("recipientName", recipientName);
		args.put("memberAddress", memberAddress);
		return sqlSession.update(NAMESPACE + ".update", args);
	}

	@Override
	public int delete(int productId, String memberId) {
		logger.info("delete() 호출");
		Map<String, Object> args = new HashMap();
		args.put("productId", productId);
		args.put("memberId", memberId);
		return sqlSession.delete(NAMESPACE + ".delete", args);
	}

	@Override
	public ProductOrderVO select(String memberId) {
		logger.info("select() 호출 : memberId = " + memberId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_memberId", memberId);
	}

	@Override
	public int insert(ProductData productData) {
		logger.info("insert() 호출 ");
		return sqlSession.insert(NAMESPACE + ".insert", productData);
	}

//	@Override
//	public int insert(int productId, String productName, int productPrice, int productAmount, String memberId) {
//		logger.info("insert() 호출");
//		Map<String, Object> args = new HashMap();
//		args.put("productId", productId);
//		args.put("productName", productName);
//		args.put("productPrice", productPrice);
//		args.put("productAmount", productAmount);
//		args.put(memberId, memberId);
//		return sqlSession.insert(NAMESPACE + ".insert", args);
//	}

//	@Override
//	public List<ProductOrderVO> select() {
//		logger.info("select() 호출");
//		return sqlSession.selectList(NAMESPACE + ".select_all");
//	}

}
