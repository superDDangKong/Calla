package project.spring.calla.service;

import java.util.List;
import java.util.Map;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.MyPageCriteria;
import project.spring.calla.pageutil.PageCriteria;

public interface MemberService {
	int create(MemberVO vo);
	int checkId(String memberId);
	int checkNick(String memberNickname);
	MemberVO read(String memberId);
	List<MemberVO> read();
	int update(MemberVO vo);
	String searchId(String memberName, String memberEmail);
	String searchPw(String memberId, String memberPhone);
	Map<String, Integer> getTotalCountsByMemberNickname(String memberNickname);
	Map<String, Object> readLikes(String memberId);
	Map<String, Object> readRecentlyView(MyPageCriteria criteria, String memberId);
	Map<String, Integer> getTotalCountsByRecentlyView(String memberId);
	
	int updatePw(String memberId, String memberPw);
	int updateNickname(String memberId, String memberNickname);
	int updatePhone(String memberId, String memberPhone);
	int updateEmail(String memberId, String memberEmail);
	int updateInterest(String memberId, String memberInterest);
	int updateAddress(String memberId, String memberAddress);
	int updateLevel(String memberId, int amount);
	int delete(String memberId);
	
	int deleteRecentlyViewProduct(int productRecentlyViewId);
	int deleteRecentlyViewUProduct(int uProductRecentlyViewId);
	List<UProductVO> readmyuproduct(PageCriteria criteria, String memberNickname); // �뜝�룞�삕�뭹�뜝�룞�삕�뜝占� �뜝�떩�뼲�삕�뜝�룞�삕�뜝占�
	int getTotalCountsBymyuproduct(String memberNickname);
	UProductVO read(int uProductId); // �뜝�룞�삕�뭹ID �뜝�떩�뼲�삕�뜝�룞�삕�뜝占�
	int buysellcreate(UProductBuyVO vo, UProductSellVO svo);
	
	int deleteProductLike(int productLikeId, int amount, int productId);
	int deleteUProductLike(int uProductLikeId, int amount, int uProductId);
	
	List<UProductVO> readBoards(String memberNickname, String option, MyPageCriteria criteria);
	int getTotalCountsBoard(String memberNickname, String option);
	
	List<UProductCommentVO> readComments(String memberNickname, String option, MyPageCriteria criteria);
	int getTotalCountsComment(String memberNickname, String option);
	
	List<UProductVO> readLikes(String memberId, String option, MyPageCriteria criteria);
	int getTotalCountsLike(String memberId, String option);
	
	List<ProductOrderVO> readOrders(String memberId, MyPageCriteria criteria);
	int getTotalCountsOrders(String memberId);
	
	List<UProductVO> readProductsByOption(PageCriteria criteria, String keyword, String interest, String option);
	int getTotalCountsProductsByOption(String keyword, String interest, String option);
	
	int deleteUProduct(int uProductId);
	
	
}









