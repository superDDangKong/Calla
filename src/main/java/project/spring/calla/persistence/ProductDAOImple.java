package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public int updateCommentCount(int amount, int productId) {
		logger.info("updateCommentCount() : productId = " + productId);
		Map<String, Integer> args = new HashMap();
		args.put("amount", amount);
		args.put("productId", productId);
		return sqlSession.update(NAMESPACE + ".update_comment_count", args);
	}

	@Override
	public List<ProductVO> selectByProductNameOrProductContent(PageCriteria criteria, String keyword) {
		logger.info("selectByTitleOrContent() 호출");
		Map<String, Object> args = new HashMap();
//		args.put("criteria", criteria);
		args.put("start", criteria.getStart());
		args.put("end", criteria.getEnd());
		args.put("keyword", "%" + keyword + "%");
		logger.info("args = " + args);
		return sqlSession.selectList(NAMESPACE + ".select_by_product_name_or_product_content", args);
	}

	@Override
	public int getTotalCountsByProductNameOrProductContent(String keyword) {
		logger.info("getTotalTitleContent()");
		return sqlSession.selectOne(NAMESPACE + ".total_count_by_product_name_or_product_content", "%" + keyword + "%");
	}

	@Override
	public int updateViews(int views, int productId) {
		logger.info("updateViews() : fBoardId = " + productId);
		Map<String, Integer> args = new HashMap();
		args.put("views", views);
		args.put("productId", productId);
		return sqlSession.update(NAMESPACE + ".update_views", args);
	}

	@Override
	public int updateLikeCount(int amount, int productId) {
		logger.info("updateLikeCount() : productId = " + productId);
		Map<String, Integer> args = new HashMap();
		args.put("amount", amount);
		args.put("productId", productId);
		return sqlSession.update(NAMESPACE + ".update_like_count", args);
		
	}

	

}
