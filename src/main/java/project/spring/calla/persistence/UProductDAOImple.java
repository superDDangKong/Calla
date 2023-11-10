package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.FBoardVO;
import project.spring.calla.domain.ProductVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.pageutil.MyPageCriteria;

@Repository
public class UProductDAOImple implements UProductDAO {
	
	private static final Logger logger=
			LoggerFactory.getLogger(UProductDAOImple.class);
	
	private static final String NAMESPACE =
			"project.spring.calla.UProductMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insert(UProductVO vo) {
		logger.info("insert() ȣ��");
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	} // end insert()

	@Override
	public List<UProductVO> select() {
		logger.info("select() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_all");
	} // end select()

	@Override
	public UProductVO select(int uProductId) {
		logger.info("select() ȣ�� : uProductId = " + uProductId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_product_id", uProductId);
	}

	@Override
	public int update(UProductVO vo) {
		logger.info("update() ȣ�� : vo = " + vo.toString());
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public int delete(int uProductId) {
		logger.info("delete() ȣ�� : productId = " + uProductId);
		return sqlSession.delete(NAMESPACE + ".delete", uProductId);
	}

	@Override
	public List<UProductVO> select(PageCriteria criteria) {
		logger.info("select() ȣ��");
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
	public List<UProductVO> select(String uProductName) {
		logger.info("select() ȣ�� : uProductName = " + uProductName);
		return sqlSession.selectList(NAMESPACE + ".select_by_product_name", "%" + uProductName);
	}

	@Override
	public List<UProductVO> selectByName(String keyword) {
		logger.info("selectByName() ȣ��");
		return sqlSession.selectList(NAMESPACE + ".select_by_name", "%" + keyword + "%");
	}

	@Override
	public int updateUproductCommentCount(int amount, int uProductId) {
		logger.info("updateProdcutCommentCount() : uProductId = " + uProductId);
		Map<String, Integer> args = new HashMap();
		args.put("amount", amount);
		args.put("uProductId", uProductId);
		return sqlSession.update(NAMESPACE + ".update_product_comment_count", args);
	}

	@Override
	public List<UProductVO> selectAllByMemberNickname(MyPageCriteria criteria, String memberNickname) {
		logger.info("selectAllByMemberNickname() ȣ�� memberNickname = " + memberNickname);
		Map<String, Object> args = new HashMap();
		args.put("criteria", criteria);
		args.put("memberNickname", memberNickname);
		return sqlSession.selectList(NAMESPACE + ".select_all_by_memberNickname", args);
	}

	@Override
	public int getTotalCountsByMemberNickname(String memberNickname) {
		logger.info("getTotalCountByMemberNickname()");
		return sqlSession.selectOne(NAMESPACE + ".get_total_count_by_memberNickname", memberNickname);
	}

	@Override
	public List<UProductVO> selectByCategoriorName(PageCriteria criteria, String keyword) {
		logger.info("selectByTitleOrContent() ȣ��");
		Map<String, Object> args = new HashMap();
		args.put("criteria", criteria);
		args.put("start", criteria.getStart());
		args.put("end", criteria.getEnd());
		args.put("keyword", "%" + keyword + "%");
		logger.info("args = " + args);
		return sqlSession.selectList(NAMESPACE + ".select_by_u_product_categori", args);
	}

	@Override
	public int getTotalCountsByCategoriorName(String keyword) {
		logger.info("getTotalTitleContent()");
		return sqlSession.selectOne(NAMESPACE + ".total_count_by_u_product_categori", "%" + keyword + "%");
	}

	@Override
	public List<UProductVO> selectByUproductCreatedDate(PageCriteria criteria) {
		logger.info("selectByUproductCreatedDate() ȣ��");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		return sqlSession.selectList(NAMESPACE + ".select_by_u_product_created_date", criteria);
	}

	@Override
	public int getTotalCountsByUproductCreatedDate() {
		logger.info("getTotalCountsByUproductCreatedDate()");
		return sqlSession.selectOne(NAMESPACE + ".total_count_by_u_product_created_date");
	}

	@Override
	public List<UProductVO> selectByAddress(PageCriteria criteria, String keyword) {
		logger.info("selectByAddress() ȣ��");
		Map<String, Object> args = new HashMap();
		args.put("criteria", criteria);
		args.put("start", criteria.getStart());
		args.put("end", criteria.getEnd());
		args.put("keyword", "%" + keyword + "%");
		logger.info("args = " + args);
		return sqlSession.selectList(NAMESPACE + ".select_by_u_product_Address", args);
	}

	@Override
	public int getTotalCountsByAddress(String keyword) {
		logger.info("getTotalTitleContent()");
		return sqlSession.selectOne(NAMESPACE + ".total_count_by_u_product_Address", "%" + keyword + "%");
	}

	@Override
	public List<UProductVO> recommendCategori(String uProductCategori, int uProductId) {
		logger.info("select() ȣ�� : uProductCategori = " + uProductCategori);
		Map<String, Object> args = new HashMap();
		args.put("uProductCategori", uProductCategori);
		args.put("uProductId", uProductId);
		logger.info("args = " + args);
		return sqlSession.selectList(NAMESPACE + ".recommend_by_u_product_categori", args);
	}

	@Override
	public List<UProductBuyVO> selectbuyuproduct(PageCriteria criteria, String buyerNickname) {
		logger.info("selectbuyuproduct() ȣ��");
		Map<String, Object> args = new HashMap();
		args.put("criteria", criteria);
		args.put("start", criteria.getStart());
		args.put("end", criteria.getEnd());
		args.put("buyerNickname", buyerNickname);
		logger.info("args = " + args);
		return sqlSession.selectList(NAMESPACE + ".select_buy_all_by_memberNickname", args);
	}

	@Override
	public int getTotalCountsbuyuproduct(String buyerNickname) {
		logger.info("getTotalCountsBymyuproduct()");
		return sqlSession.selectOne(NAMESPACE + ".total_count_by_u_product_buy", buyerNickname);
	}

	@Override
	public List<UProductSellVO> selectselluproduct(PageCriteria criteria, String sellerNickname) {
		logger.info("selectselluproduct() ȣ��");
		Map<String, Object> args = new HashMap();
		args.put("criteria", criteria);
		args.put("start", criteria.getStart());
		args.put("end", criteria.getEnd());
		args.put("sellerNickname", sellerNickname);
		logger.info("args = " + args);
		return sqlSession.selectList(NAMESPACE + ".select_sell_all_by_memberNickname", args);
	}

	@Override
	public int getTotalCountsselluproduct(String sellerNickname) {
		logger.info("getTotalCountsBymyuproduct()");
		return sqlSession.selectOne(NAMESPACE + ".total_count_by_u_product_sell", sellerNickname);
	}

	

	@Override
	public List<UProductVO> selectByInterest(String interest) {
		logger.info("selectselectByInterest : interest = " + interest);
		return sqlSession.selectList(NAMESPACE + ".select_by_interest", interest);
	}
	
	
	@Override
	public int insertRecentlyView(int uProductId, String memberId) {
	logger.info("insertRecentlyView : uProductId = " + uProductId);
	logger.info("insertRecentlyView : memberId = " + memberId);
	Map<String, Object> args = new HashMap();
	args.put("uProductId", uProductId);
	args.put("memberId", memberId);
	return sqlSession.insert(NAMESPACE + ".insert_recently_view", args);
	}
	
	@Override
	public List<UProductVO> selectRecentlyView(MyPageCriteria criteria, String memberId) {
		logger.info("selectRecentlyView() : memberId = " + memberId);
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
	public int deleteRecentlyView(int uProductRecentlyViewId) {
		logger.info("deleteRecentlyView : uProductRecentlyViewId = " + uProductRecentlyViewId);
		return sqlSession.delete(NAMESPACE + ".delete_recently_view", uProductRecentlyViewId);
	}

	@Override
	public List<UProductVO> selectLikes(String memberId) {
		logger.info("selectLikes() : memberId = " + memberId);
		return sqlSession.selectList(NAMESPACE + ".select_likes", memberId);
	}

	@Override
	public UProductBuyVO selectseller(String sellerNickname) {
		logger.info("select() ȣ�� : sellerNickname = " + sellerNickname);
		return sqlSession.selectOne(NAMESPACE + ".select_by_sellernickname", sellerNickname);
	}

	@Override
	public List<UProductVO> selectbynickname(PageCriteria criteria, String memberNickname) {
		logger.info("selectbynickname() ȣ��");
		Map<String, Object> args = new HashMap();
		args.put("criteria", criteria);
		args.put("start", criteria.getStart());
		args.put("end", criteria.getEnd());
		args.put("memberNickname", memberNickname);
		logger.info("args = " + args);
		return sqlSession.selectList(NAMESPACE + ".select_by_u_product_membernickname", args);
	}

	@Override
	public int getTotalCountsbynickname(String memberNickname) {
		logger.info("getTotalCountsbynickname()");
		return sqlSession.selectOne(NAMESPACE + ".total_count_by_u_product_membernickname", memberNickname);
	}

	@Override
	public UProductVO selectnickname(String memberNickname) {
		logger.info("select() ȣ�� : memberNickname = " + memberNickname);
		return sqlSession.selectOne(NAMESPACE + ".select_by_membernickname", memberNickname);
	}
	public int updateLikeCount(int amount, int uProductId) {
		logger.info("updateLikeCount() : uProductId = " + uProductId);
		Map<String, Integer> args = new HashMap();
		args.put("amount", amount);
		args.put("uProductId", uProductId);
		return sqlSession.update(NAMESPACE + ".update_like_count", args);
	}
	
}
