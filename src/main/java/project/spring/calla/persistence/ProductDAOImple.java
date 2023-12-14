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
import project.spring.calla.util.MyPageCriteria;
import project.spring.calla.util.PageCriteria;

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
		logger.info("delete() 호占쏙옙 : productId = " + productId);
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
		logger.info("getTotalCounts() 호출");
		return sqlSession.selectOne(NAMESPACE + ".total_count");
	}

	@Override
	public int updateCommentCount(int amount, int productId) {
		logger.info("updateCommentCount() 호출 : productId = " + productId);
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
		logger.info("getTotalTitleContent() 호출");
		return sqlSession.selectOne(NAMESPACE + ".total_count_by_product_name_or_product_content", "%" + keyword + "%");
	}

	@Override
	public int updateViews(int views, int productId) {
		logger.info("updateViews() 호출 : fBoardId = " + productId);
		Map<String, Integer> args = new HashMap();
		args.put("views", views);
		args.put("productId", productId);
		return sqlSession.update(NAMESPACE + ".update_views", args);
	}

	@Override
	public int updateLikeCount(int amount, int productId) {
		logger.info("updateLikeCount() 호출 : productId = " + productId);
		Map<String, Integer> args = new HashMap();
		args.put("amount", amount);
		args.put("productId", productId);
		return sqlSession.update(NAMESPACE + ".update_like_count", args);
	}

	@Override
	public List<ProductVO> selectLikes(String memberId) {
		logger.info("selectLikes() 호출 : memberId = " + memberId);
		return sqlSession.selectList(NAMESPACE + ".select_likes", memberId);
	}
	


	public List<ProductVO> selectProductWithAmount(String memberId) {
	    return sqlSession.selectList(NAMESPACE + ".select_product_with_amount", memberId);
	}

	@Override
	public List<ProductVO> selectByInterest(String interest) {
		logger.info("selectselectByInterest : interest = " + interest);
		return sqlSession.selectList(NAMESPACE + ".select_by_interest", interest);
	}

	@Override
	public int insertRecentlyView(int productId, String memberId) {
		logger.info("insertRecentlyView : productId = " + productId);
		logger.info("insertRecentlyView : memberId = " + memberId);
		Map<String, Object> args = new HashMap();
		args.put("productId", productId);
		args.put("memberId", memberId);
		return sqlSession.insert(NAMESPACE + ".insert_recently_view", args);
	}

	@Override
	public List<ProductVO> selectRecentlyView(MyPageCriteria criteria, String memberId) {
		logger.info("selectRecentlyView() : memberId = " + memberId);
		logger.info("selectRecentlyView() : criteria = " + criteria.getPage());
		logger.info("selectRecentlyView() : criteria = " + criteria.getStart());
		Map<String, Object> args = new HashMap();
		args.put("memberId", memberId);
		args.put("start", criteria.getStart());
		args.put("end", criteria.getEnd());
		return sqlSession.selectList(NAMESPACE + ".select_recently_view", args);
	}
	
	@Override
	public int getTotalCountsByRecentlyView(String memberId) {
		logger.info("getTotalCountsByRecentlyView() : memberId = " + memberId);
		return sqlSession.selectOne(NAMESPACE + ".get_total_counts_by_recently_view", memberId);
	}

	@Override
	public int deleteRecentlyView(int productRecentlyViewId) {
		logger.info("deleteRecentlyView : productRecentlyViewId = " + productRecentlyViewId);
		return sqlSession.delete(NAMESPACE + ".delete_recently_view", productRecentlyViewId);
	}

	@Override
	public int updateRatedCount(int amount, int productId) {
		logger.info("updateRatedCount() : productId = " + productId);
		Map<String, Integer> args = new HashMap();
		args.put("amount", amount);
		args.put("productId", productId);
		return sqlSession.update(NAMESPACE + ".update_rated_count", args);
	}

	@Override
	public int update(int productId) {
		logger.info("update() 호출");
		return sqlSession.update(NAMESPACE + ".update", productId);
	}

	@Override
	public List<ProductVO> selectByProductCategori(PageCriteria criteria, String keyword) {
		logger.info("selectByProductCategori() 호출");
		Map<String, Object> args = new HashMap();
		args.put("criteria", criteria);
		args.put("start", criteria.getStart());
		args.put("end", criteria.getEnd());
		args.put("keyword", "%" + keyword + "%");
		logger.info("args = " + args);
		return sqlSession.selectList(NAMESPACE + ".select_by_product_categori", args);
	}

	@Override
	public int getTotalCountByProductCategori(String keyword) {
		logger.info("getTotalCountByProductCategori() 호출");
		return sqlSession.selectOne(NAMESPACE + ".total_count_by_product_categori", "%" + keyword + "%");
	}



	

	
	
	

}
