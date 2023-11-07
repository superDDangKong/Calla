package project.spring.calla.service;

import java.util.List;
import java.util.Map;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.pageutil.RecentlyViewPageCriteria;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
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
	Map<String, Object> readComments(String memberNickname);
	Map<String, Object> readBoards(String memberNickname);
	Map<String, Object> readLikes(String memberId);
	Map<String, Object> readRecentlyView(RecentlyViewPageCriteria criteria, String memberId);
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
	List<UProductVO> readmyuproduct(PageCriteria criteria, String memberNickname); // ��ǰ��� �о����
	int getTotalCountsBymyuproduct(String memberNickname);
	UProductVO read(int uProductId); // ��ǰID �о����
	
	int buysellcreate(UProductBuyVO vo, UProductSellVO svo);
}
