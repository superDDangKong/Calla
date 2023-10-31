package project.spring.calla.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.ProductOrderVO;

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
	public List<ProductOrderVO> select() {
		logger.info("select() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_all");
	}

	@Override
	public int update(ProductOrderVO vo) {
		logger.info("update() 호출 : vo = " + vo.toString());
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int productOrderId) {
		logger.info("delete() 호출 : productOrderId = " + productOrderId);
		return sqlSession.delete(NAMESPACE + ".delete", productOrderId);
	}

	

}
