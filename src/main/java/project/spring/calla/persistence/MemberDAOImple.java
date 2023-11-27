package project.spring.calla.persistence;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.MyPageCriteria;
import project.spring.calla.pageutil.PageCriteria;

@Repository
public class MemberDAOImple implements MemberDAO{


	private static final Logger logger =
			LoggerFactory.getLogger(MemberDAOImple.class);
	
	private static final String NAMESPACE = 
			"project.spring.calla.MemberMapper";
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int checkId(String memberId) { 
		logger.info("select_by_id() ");
		int result  = sqlSession.selectOne(NAMESPACE + ".select_by_id", memberId);
		return result;
	}

	@Override
	public int checkNickname(String memberNickname) { //
		logger.info("checkNickname() ");
		return sqlSession.selectOne(NAMESPACE + ".select_by_nickname", memberNickname);
	}

	@Override
	public int insert(MemberVO vo) throws IllegalStateException {
		int result = 0;
		try {
			logger.info("insert() : vo = " + vo.toString());
			result = sqlSession.insert(NAMESPACE + ".insert", vo);
		} catch (DataIntegrityViolationException e) { // 
			String errorMessage = "È¸¿øÁ¤º¸¸¦ ÀúÀåÇÏ´Â Áß¿¡ ¹®Á¦°¡ ¹ß»ýÇß½À´Ï´Ù. Áßº¹È®ÀÎÀ» ÇØÁÖ¼¼¿ä.";
			logger.error(errorMessage, e);
			throw new IllegalStateException(errorMessage, e); 
		}	// ÇöÀç »óÅÂ¿¡¼­ È¸¿ø Á¤º¸¸¦ ÀúÀåÇÏ´Âµ¥ ¹®Á¦°¡ ¹ß»ýÇßÀ» ¶§ ¹ß»ýÇÏ´Â ¿¹¿ÜÀÔ´Ï´Ù.
		return result;
	}
	
	public String login(String memberId, String memberPw) {
		logger.info("login() memberId = " + memberId + "memberPw = " + memberPw);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("memberId", memberId);
		args.put("memberPw", memberPw);
		return sqlSession.selectOne(NAMESPACE + ".login", args);
	}

	@Override
	public MemberVO select(String memberId) {
		logger.info("select(memberId) memberId : " + memberId);
		return sqlSession.selectOne(NAMESPACE + ".select_by_member_id", memberId);
	}

	@Override 
	public int update(MemberVO vo) {
		logger.info("update()  vo : " + vo);
		return sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public String searchId(String memberName, String memberEmail) {
		logger.info("searchId()  memberName : " + memberName);
		logger.info("searchId()  memberEmail : " + memberEmail);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberName", memberName);
		args.put("memberEmail", memberEmail);
		return sqlSession.selectOne(NAMESPACE + ".search_id", args);
	}

	@Override
	public String searchPw(String memberId, String memberPhone) {
		logger.info("searchPw()  memberId : " + memberId);
		logger.info("searchPw()  memberPhone : " + memberPhone);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberPhone", memberPhone);
		return sqlSession.selectOne(NAMESPACE + ".search_pw", args);
	}

	@Override
	public int updatePw(String memberId, String memberPw) {
		logger.info("updatePw()  memberPw : " + memberPw);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberPw", memberPw);
		return sqlSession.update(NAMESPACE + ".updatePw", args);
	}

	@Override
	public int updateNickname(String memberId, String memberNickname) {
		logger.info("updateNickname() memberNickname : " + memberNickname);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberNickname", memberNickname);
		return sqlSession.update(NAMESPACE + ".updateNickname", args);
	}

	@Override
	public int updatePhone(String memberId, String memberPhone) {
		logger.info("updatePhone()  memberPhone : " + memberPhone);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberPhone", memberPhone);
		return sqlSession.update(NAMESPACE + ".updatePhone", args);
	}

	@Override
	public int updateEmail(String memberId, String memberEmail) {
		logger.info("updateEmail()  memberEmail : " + memberEmail);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberEmail", memberEmail);
		return sqlSession.update(NAMESPACE + ".updateEmail", args);
	}

	@Override
	public int updateInterest(String memberId, String memberInterest) {
		logger.info("updateInterest()  memberInterest : " + memberInterest);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberInterest", memberInterest);
		return sqlSession.update(NAMESPACE + ".updateInterest", args);

	}

	@Override
	public int updateAddress(String memberId, String memberAddress) {
		logger.info("updateAddress()  memberAddress : " + memberAddress);
		Map<String, String> args = new HashMap<String, String>();
		args.put("memberId", memberId);
		args.put("memberAddress", memberAddress);
		return sqlSession.update(NAMESPACE + ".updateAddress", args);
	}

	@Override
	public List<MemberVO> select() {
		logger.info("updateAddress() ");
		return sqlSession.selectList(NAMESPACE + ".select");
	}

	@Override
	public int updateLevel(String memberId, int amount) {
		logger.info("updateLevel()");
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("memberId", memberId);
		args.put("amount", amount);
		return sqlSession.update(NAMESPACE + ".updateLevel", args);
	}

	@Override
	public int delete(String memberId) {
		logger.info("delete()");
		return sqlSession.delete(NAMESPACE + ".delete", memberId);
	}

	@Override
	public List<UProductVO> selectmyuproduct(PageCriteria criteria, String memberNickname) {
		logger.info("selectByAddress() ");
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
		logger.info("select() : uProductId = " + uProductId);
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
		logger.info("selectAllBoards ");
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
		logger.info("selecBoards ");
		Map<String, Object> args = new HashMap();
		args.put("criteria", criteria);
		args.put("memberNickname", memberNickname);
		args.put("option", "%" + option + "%");
		return sqlSession.selectList(NAMESPACE + ".select_boards", args);
	}

	@Override
	public int getTotalCountsBoard(String memberNickname, String option) {
		logger.info("getTotalCountsBoard ");
		Map<String, String> args = new HashMap();
		args.put("memberNickname", memberNickname);
		args.put("option", "%" + option + "%");
		return sqlSession.selectOne(NAMESPACE + ".total_count_boards", args);
	}
	
	@Override
	public List<UProductCommentVO> selectComments(String memberNickname, String option, MyPageCriteria criteria) {
		logger.info("selecComments ");
		Map<String, Object> args = new HashMap();
		args.put("criteria", criteria);
		args.put("memberNickname", memberNickname);
		args.put("option", "%" + option + "%");
		return sqlSession.selectList(NAMESPACE + ".select_comments", args);
	}

	@Override
	public int getTotalCountsComment(String memberNickname, String option) {
		logger.info("getTotalCountsComment");
		Map<String, String> args = new HashMap();
		args.put("memberNickname", memberNickname);
		args.put("option", "%" + option + "%");
		return sqlSession.selectOne(NAMESPACE + ".total_count_comments", args);
	}

	@Override
	public List<UProductVO> selectLikes(String memberId, String option, MyPageCriteria criteria) {
		logger.info("selecLikes");
		Map<String, Object> args = new HashMap();
		args.put("criteria", criteria);
		args.put("memberId", memberId);
		args.put("option", "%" + option + "%");
		return sqlSession.selectList(NAMESPACE + ".select_likes", args);
	}

	@Override
	public int getTotalCountsLike(String memberId, String option) {
		logger.info("getTotalCountsLike");
		Map<String, String> args = new HashMap();
		args.put("memberId", memberId);
		args.put("option", "%" + option + "%");
		return sqlSession.selectOne(NAMESPACE + ".total_count_likes", args);
	}

	@Override
	public List<ProductOrderVO> selectOrders(String memberId, MyPageCriteria criteria) {
		logger.info("selecOrders");
		Map<String, Object> args = new HashMap();
		args.put("criteria", criteria);
		args.put("memberId", memberId);
		return sqlSession.selectList(NAMESPACE + ".select_orders", args);
	}

	@Override
	public int getTotalCountsOrders(String memberId) {
		logger.info("getTotalCountsOrders");
		Map<String, String> args = new HashMap();
		args.put("memberId", memberId);
		return sqlSession.selectOne(NAMESPACE + ".total_count_orders", memberId);
	}

	@Override
	public int deleteUProduct(int uProductId) {
		logger.info("delete() í˜¸ì¶œ");
		return sqlSession.delete(NAMESPACE + ".delete_uproduct", uProductId);
	}
	
}
