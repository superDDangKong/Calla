package project.spring.calla.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.MyPageCriteria;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.persistence.FBoardCommentDAO;
import project.spring.calla.persistence.FBoardDAO;
import project.spring.calla.persistence.MemberDAO;
import project.spring.calla.persistence.ProductCommentDAO;
import project.spring.calla.persistence.ProductDAO;
import project.spring.calla.persistence.ProductLikeDAO;
import project.spring.calla.persistence.QBoardCommentDAO;
import project.spring.calla.persistence.QBoardDAO;
import project.spring.calla.persistence.UProductCommentDAO;
import project.spring.calla.persistence.UProductDAO;
import project.spring.calla.persistence.UProductLikeDAO;

@Service
public class MemberServiceImple implements MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImple.class);

	@Autowired
	private MemberDAO MemberDAO;

	@Autowired
	private FBoardCommentDAO fBoardCommentDAO;

	@Autowired
	private QBoardCommentDAO qBoardCommentDAO;

	@Autowired
	private UProductCommentDAO uProductCommentDAO;

	@Autowired
	private ProductCommentDAO productCommentDAO;

	@Autowired
	private FBoardDAO fBoardDAO;

	@Autowired
	private QBoardDAO qBoardDAO;

	@Autowired
	private UProductDAO uProductDAO;

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private ProductLikeDAO productLikeDAO;

	@Autowired
	private UProductLikeDAO uProductLikeDAO;

	@Override
	public int create(MemberVO vo) {
		logger.info("create() " + vo.toString());
		int result = 0;
		try {
			return result = MemberDAO.insert(vo);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalStateException("´Ð³×ÀÓÀÌ³ª id¸¦ È®ÀÎÇØÁÖ¼¼¿ä");
			// ¾î¶² Ç×¸ñ¿¡¼­ ¿¡·¯°¡ »ý±ä°ÇÁö 
			
		}
	}

	@Override
	public int checkId(String memberId) {
		int result = 0;
		result = MemberDAO.checkId(memberId);
		return result;
	}

	@Override
	public int checkNick(String memberNickname) { //
		logger.info("checkNick() : memberNickname = " + memberNickname);
		return MemberDAO.checkNickname(memberNickname);
	}

	@Override
	public MemberVO read(String memberId) {
		logger.info("read(memberId) memberId : " + memberId);
		return MemberDAO.select(memberId);
	}

	@Override
	public int update(MemberVO vo) {
		logger.info("update() vo : " + vo.toString());
		return MemberDAO.update(vo);
	}

	@Override
	public String searchId(String memberName, String memberEmail) {
		logger.info("searchId()  memberName : " + memberName);
		logger.info("searchId()  memberEmail : " + memberEmail);
		return MemberDAO.searchId(memberName, memberEmail);
	}

	@Override
	public String searchPw(String memberId, String memberPhone) {
		logger.info("searchPw()  memberId : " + memberId);
		logger.info("searchPw()  memberPhone : " + memberPhone);
		return MemberDAO.searchPw(memberId, memberPhone);
	}

	@Override
	public Map<String, Integer> getTotalCountsByMemberNickname(String memberNickname) {
		logger.info("getTotalCountsByMemberNickname : " + memberNickname);
		Map<String, Integer> args = new HashMap();
		args.put("fBoardCount", fBoardDAO.getTotalCountsByMemberNickname(memberNickname));
		args.put("qBoardCount", qBoardDAO.getTotalCountsByMemberNickname(memberNickname));
		args.put("uProductCount", uProductDAO.getTotalCountsByMemberNickname(memberNickname));
		return args;
	}

	@Override
	public Map<String, Object> readLikes(String memberId) {
		logger.info("readLikes()  memberId : " + memberId);
		Map<String, Object> args = new HashMap();
		args.put("productLikeList", productDAO.selectLikes(memberId));
		args.put("uProductLikeList", uProductDAO.selectLikes(memberId));
		return args;
	}

	@Override
	public Map<String, Object> readRecentlyView(MyPageCriteria criteria, String memberId) {
		logger.info("readRecentlyView()  memberId : " + memberId);
		logger.info("getTotalCountsByRecentlyView  criteria : " + criteria.toString());
		Map<String, Object> args = new HashMap();
		args.put("productList", productDAO.selectRecentlyView(criteria, memberId));
		args.put("uProductList", uProductDAO.selectRecentlyView(criteria, memberId));
		return args;
	}

	@Override
	public Map<String, Integer> getTotalCountsByRecentlyView(String memberId) {
		logger.info("getTotalCountsByRecentlyView  memberId : " + memberId);

		Map<String, Integer> args = new HashMap();
		args.put("productCount", productDAO.getTotalCountsByRecentlyView(memberId));
		args.put("uProductCount", uProductDAO.getTotalCountsByRecentlyView(memberId));
		return args;
	}

	@Override
	public int updatePw(String memberId, String memberPw) {
		logger.info("update()  memberPw : " + memberPw);
		return MemberDAO.updatePw(memberId, memberPw);
	}

	@Transactional(value = "transactionManager")
	@Override
	public int updateNickname(String memberId, String memberNickname) {
		logger.info("updateNickname ");
		return MemberDAO.updateNickname(memberId, memberNickname);
	}

	@Override
	public int updatePhone(String memberId, String memberPhone) {
		return MemberDAO.updatePhone(memberId, memberPhone);
	}

	@Override
	public int updateEmail(String memberId, String memberEmail) {
		return MemberDAO.updateEmail(memberId, memberEmail);
	}

	@Override
	public int updateInterest(String memberId, String memberInterest) {
		return MemberDAO.updateInterest(memberId, memberInterest);
	}

	@Transactional(value = "transactionManager")
	@Override
	public int updateAddress(String memberId, String memberAddress) {
		return MemberDAO.updateAddress(memberId, memberAddress);
	}

	@Override
	public int updateLevel(String memberId, int amount) {
		return MemberDAO.updateLevel(memberId, amount);
	}

	@Override
	public List<MemberVO> read() {
		logger.info("read() ");
		return MemberDAO.select();
	}

	@Transactional(value = "transactionManager")
	@Override
	public int delete(String memberId) {
		logger.info("delete() ");
		return MemberDAO.delete(memberId);
	}

	@Override
	public int deleteRecentlyViewProduct(int productRecentlyViewId) {
		logger.info("deleteRecentlyViewProduct() ");
		return productDAO.deleteRecentlyView(productRecentlyViewId);
	}

	@Override
	public int deleteRecentlyViewUProduct(int uProductRecentlyViewId) {
		logger.info("deleteRecentlyViewUProduct()");
		return uProductDAO.deleteRecentlyView(uProductRecentlyViewId);
	}

	public List<UProductVO> readmyuproduct(PageCriteria criteria, String memberNickname) {
		logger.info("readmyuproduct() ");
		logger.info("start = " + criteria.getStart());
		logger.info("end = " + criteria.getEnd());
		logger.info("memberNickname = " + memberNickname);

		return MemberDAO.selectmyuproduct(criteria, memberNickname);
	}

	@Override
	public int getTotalCountsBymyuproduct(String memberNickname) {
		logger.info("getTotalCountsBymyuproduct() ");
		return MemberDAO.getTotalCountsBymyuproduct(memberNickname);
	}

	@Override
	public UProductVO read(int uProductId) {
		logger.info("read()  : boardId = " + uProductId);
		return MemberDAO.select(uProductId);
	}

	@Transactional(value = "transactionManager")
	@Override
	public int buysellcreate(UProductBuyVO vo, UProductSellVO svo) {
		int result = MemberDAO.insertbuy(vo);
		int results = MemberDAO.insertsell(svo);

		if (result == 1 && results == 1) {
			return 1;
		} else {
			return 0;
		}
	}

	@Transactional(value = "transactionManager")
	@Override
	public int deleteProductLike(int productLikeId, int amount, int productId) {
		try {
			logger.info("deleteUProductLike() ");
			productLikeDAO.deleteById(productLikeId);
			productDAO.updateLikeCount(amount, productId);
			return 1;
		} catch (Exception e) {
			throw new RuntimeException("", e);
		}
	}

	@Transactional(value = "transactionManager")
	@Override
	public int deleteUProductLike(int uProductLikeId, int amount, int uProductId) {
		logger.info("deleteUProductLike()");
		uProductLikeDAO.deleteById(uProductLikeId);
		uProductDAO.updateLikeCount(amount, uProductId);
		return 1;
	}

	@Override
	public List<UProductVO> readProductsByOption(PageCriteria criteria, String keyword, String interest,
			String option) {
		logger.info("readProductsByOption() ");
		return MemberDAO.selectProductsByOption(criteria, keyword, interest, option);
	}

	@Override
	public int getTotalCountsProductsByOption(String keyword, String interest, String option) {
		logger.info("getTotalCountsProductsByOption()");
		return MemberDAO.getTotalCountsProductsByOption(keyword, interest, option);
	}

	@Override
	public List<UProductVO> readBoards(String memberNickname, String option, MyPageCriteria criteria) {
		logger.info("readBoards() ");
		return MemberDAO.selectBoards(memberNickname, option, criteria);
	}

	@Override
	public int getTotalCountsBoard(String memberNickname, String option) {
		logger.info("getTotalCountsBoard ");
		return MemberDAO.getTotalCountsBoard(memberNickname, option);
	}

	@Override
	public List<UProductCommentVO> readComments(String memberNickname, String option, MyPageCriteria criteria) {
		logger.info("readComments() ");
		return MemberDAO.selectComments(memberNickname, option, criteria);
	}

	@Override
	public int getTotalCountsComment(String memberNickname, String option) {
		logger.info("getTotalCountsComment");
		return MemberDAO.getTotalCountsComment(memberNickname, option);
	}

	@Override
	public List<UProductVO> readLikes(String memberId, String option, MyPageCriteria criteria) {
		logger.info("readLikes()");
		return MemberDAO.selectLikes(memberId, option, criteria);
	}

	@Override
	public int getTotalCountsLike(String memberId, String option) {
		logger.info("getTotalCountsLike");
		return MemberDAO.getTotalCountsLike(memberId, option);
	}

	@Override
	public List<ProductOrderVO> readOrders(String memberId, MyPageCriteria criteria) {
		logger.info("readOrders() ");
		return MemberDAO.selectOrders(memberId, criteria);
	}

	@Override
	public int getTotalCountsOrders(String memberId) {
		logger.info("getTotalCountsOrderList ");
		return MemberDAO.getTotalCountsOrders(memberId);
	}

	@Override
	public int deleteUProduct(int uProductId) {
		logger.info("deleteUProduct() í˜¸ì¶œ");
		return MemberDAO.deleteUProduct(uProductId);
	}

}
