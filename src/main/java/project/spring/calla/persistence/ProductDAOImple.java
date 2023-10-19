package project.spring.calla.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.ProductVO;
import project.spring.calla.pageutil.PageCriteria;

@Repository
public class ProductDAOImple implements ProductDAO {
	
	private static final Logger logger=
			LoggerFactory.getLogger(ProductDAOImple.class);
	
	private static final String NAMESPACE =
			"project.spring.calla.ProductMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(ProductVO vo) {
		logger.info("insert() 호출");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	} // end insert()

	@Override
	public List<ProductVO> select() {
		logger.info("select() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_all");
	} // end select()

	@Override
	public ProductVO select(int productId) {
		logger.info("select() 호출 : productId = " + productId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_product_id", productId);
	}

	@Override
	public int update(ProductVO vo) {
		logger.info("update() 호출 : vo = " + vo.toString());
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int productId) {
		logger.info("delete() 호출 : productId = " + productId);
		return sqlSession.delete(NAMESPACE + ".delete", productId);
	}

	@Override
	public List<ProductVO> select(PageCriteria criteria) {
		logger.info("select() 호출");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return sqlSession.selectList(NAMESPACE + ".paging", criteria);
	}

	@Override
	public int getTotalCount() {
		logger.info("getTotalCounts()");
		return sqlSession.selectOne(NAMESPACE + ".total_count");
	}

	@Override
	public List<ProductVO> select(String productName) {
		logger.info("select() 호출 : productName = " + productName);
		return sqlSession.selectList(NAMESPACE + ".select_by_product_name", "%" + productName);
	}

	@Override
	public List<ProductVO> selectByName(String keyword) {
		logger.info("selectByName() 호출");
		return sqlSession.selectList(NAMESPACE + ".select_by_name", "%" + keyword + "%");
	}

	

}
