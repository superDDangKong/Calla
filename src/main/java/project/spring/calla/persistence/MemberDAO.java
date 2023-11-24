package project.spring.calla.persistence;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.MyPageCriteria;
import project.spring.calla.pageutil.PageCriteria;
public interface MemberDAO {
	//throws DataIntegrityViolationException
	int insert(MemberVO vo) throws DataIntegrityViolationException; // 데이터베이스의 제약 조건, 무결성 규칙  
	int checkId(String memberId);
	int checkNickname(String memberNickname);
	int update(MemberVO vo);
	MemberVO select(String memberId);
	List<MemberVO> select();
	String login(String memberId, String memberPw);
	String searchId(String memberName, String memberEmail);
	String searchPw(String memberId, String memberPhone);
	int updatePw(String memberId, String memberPw);
	int updateNickname(String memberId, String memberNickname);
	int updatePhone(String memberId, String memberPhone);
	int updateEmail(String memberId, String memberEmail);
	int updateInterest(String memberId, String memberInterest);
	int updateAddress(String memberId, String memberAddress);
	int updateLevel(String memberId, int amount);
	int delete(String memberId);
	
	List<UProductVO> selectmyuproduct(PageCriteria criteria, String memberNickname);
	int getTotalCountsBymyuproduct(String memberNickname);
	
	UProductVO select(int uProductId); // 상품 검색
	int insertbuy(UProductBuyVO vo);
	int insertsell(UProductSellVO svo);
	
	List<UProductVO> selectBoards(String memberNickname, String option, MyPageCriteria criteria);
	int getTotalCountsBoard(String memberNickname, String option);
	
	List<UProductCommentVO> selectComments(String memberNickname, String option, MyPageCriteria criteria);
	int getTotalCountsComment(String memberNickname, String option);
	
	List<UProductVO> selectLikes(String memberId, String option, MyPageCriteria criteria);
	int getTotalCountsLike(String memberId, String option);
	
	List<ProductOrderVO> selectOrders(String memberId, MyPageCriteria criteria);
	int getTotalCountsOrders(String memberId);
	
	List<UProductVO> selectProductsByOption(PageCriteria criteria, String keyword, String interest, String option);
	int getTotalCountsProductsByOption(String keyword, String interest, String option);
	
}
