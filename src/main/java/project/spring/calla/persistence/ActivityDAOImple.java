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
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.util.MyPageCriteria;
import project.spring.calla.util.PageCriteria;

@Repository
public class ActivityDAOImple implements ActivityDAO{


	private static final Logger logger =
			LoggerFactory.getLogger(ActivityDAOImple.class);
	
	private static final String NAMESPACE = 
			"project.spring.calla.ActivityMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public List<UProductVO> selectmyuproduct(PageCriteria criteria, String memberNickname) {
		logger.info("selectByAddress() 호출");
		Map<String, Object> args = new HashMap();
		args.put("criteria", criteria);
		args.put("start", criteria.getStart());
		args.put("end", criteria.getEnd());
		args.put("memberNickname", memberNickname);
		logger.info("args = " + args);
		return sqlSession.selectList(NAMESPACE + ".select_by_my_u_product", args);
	}

	@Override
	public int getTotalCountsBymyuproduct(String memberNickname) {
		logger.info("getTotalCountsBymyuproduct()");
		return sqlSession.selectOne(NAMESPACE + ".total_count_by_my_u_product", memberNickname);
	}

	@Override
	public UProductVO select(int uProductId) {
		logger.info("select() 호출 : uProductId = " + uProductId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_product_id", uProductId);
	}

	@Override
	public int insertbuy(UProductBuyVO vo) {
		logger.info("insertbuy() : vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".buy_insert", vo);
	}

	@Override
	public int insertsell(UProductSellVO svo) {
		logger.info("insertsell() : vo = " + svo.toString());
		return sqlSession.insert(NAMESPACE + ".sell_insert", svo);
	}

	@Override
	public List<UProductVO> selectProductsByOption(PageCriteria criteria, String keyword, String interest, String option) {
		logger.info("selectAllBoards 호출");
		Map<String, Object> args = new HashMap();
		args.put("criteria", criteria);
		args.put("keyword", "%" + keyword + "%");
		args.put("interest", "%" + interest + "%");
		args.put("option", "%" + option + "%");
		return sqlSession.selectList(NAMESPACE + ".select_products_by_option", args);
	}

	@Override
	public int getTotalCountsProductsByOption(String keyword, String interest, String option) {
		Map<String, String> args = new HashMap();
		args.put("keyword", "%" + keyword + "%");
		args.put("interest", "%" + interest + "%");
		args.put("option", "%" + option + "%");
		return sqlSession.selectOne(NAMESPACE + ".total_count_products_by_option", args);
	}
	
	@Override
	public List<UProductVO> selectBoards(String memberNickname, String option, MyPageCriteria criteria) {
		logger.info("selecBoards 호출");
		Map<String, Object> args = new HashMap();
		args.put("criteria", criteria);
		args.put("memberNickname", memberNickname);
		args.put("option", "%" + option + "%");
		return sqlSession.selectList(NAMESPACE + ".select_boards", args);
	}

	@Override
	public int getTotalCountsBoard(String memberNickname, String option) {
		logger.info("getTotalCountsBoard 호출");
		Map<String, String> args = new HashMap();
		args.put("memberNickname", memberNickname);
		args.put("option", "%" + option + "%");
		return sqlSession.selectOne(NAMESPACE + ".total_count_boards", args);
	}
	
	@Override
	public List<UProductCommentVO> selectComments(String memberNickname, String option, MyPageCriteria criteria) {
		logger.info("selecComments 호출");
		Map<String, Object> args = new HashMap();
		args.put("criteria", criteria);
		args.put("memberNickname", memberNickname);
		args.put("option", "%" + option + "%");
		return sqlSession.selectList(NAMESPACE + ".select_comments", args);
	}

	@Override
	public int getTotalCountsComment(String memberNickname, String option) {
		logger.info("getTotalCountsComment 호출");
		Map<String, String> args = new HashMap();
		args.put("memberNickname", memberNickname);
		args.put("option", "%" + option + "%");
		return sqlSession.selectOne(NAMESPACE + ".total_count_comments", args);
	}

	@Override
	public List<UProductVO> selectLikes(String memberId, String option, MyPageCriteria criteria) {
		logger.info("selecLikes 호출");
		Map<String, Object> args = new HashMap();
		args.put("criteria", criteria);
		args.put("memberId", memberId);
		args.put("option", "%" + option + "%");
		return sqlSession.selectList(NAMESPACE + ".select_likes", args);
	}

	@Override
	public int getTotalCountsLike(String memberId, String option) {
		logger.info("getTotalCountsLike 호출");
		Map<String, String> args = new HashMap();
		args.put("memberId", memberId);
		args.put("option", "%" + option + "%");
		return sqlSession.selectOne(NAMESPACE + ".total_count_likes", args);
	}

	@Override
	public List<ProductOrderVO> selectOrders(String memberId, MyPageCriteria criteria) {
		logger.info("selecOrders 호출");
		Map<String, Object> args = new HashMap();
		args.put("criteria", criteria);
		args.put("memberId", memberId);
		return sqlSession.selectList(NAMESPACE + ".select_orders", args);
	}

	@Override
	public int getTotalCountsOrders(String memberId) {
		logger.info("getTotalCountsOrders 호출");
		Map<String, String> args = new HashMap();
		args.put("memberId", memberId);
		return sqlSession.selectOne(NAMESPACE + ".total_count_orders", memberId);
	}
	
	
	
}
