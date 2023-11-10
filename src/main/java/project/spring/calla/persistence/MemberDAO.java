package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.AllBoardVO;
import project.spring.calla.domain.MemberVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.MyPageCriteria;
import project.spring.calla.pageutil.PageCriteria;
public interface MemberDAO {
	
	int insert(MemberVO vo);
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
	
	List<AllBoardVO> selectAllBoards(MyPageCriteria criteria, String memberNickname);
	int getTotalCountsAllBoards(String memberNickname);
}
