package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.MyPageCriteria;
import project.spring.calla.pageutil.PageCriteria;
public interface MemberUpdateDAO {
	
	int updatePw(String memberId, String memberPw);
	int updateNickname(String memberId, String memberNickname);
	int updatePhone(String memberId, String memberPhone);
	int updateEmail(String memberId, String memberEmail);
	int updateInterest(String memberId, String memberInterest);
	int updateAddress(String memberId, String memberAddress);
	int updateLevel(String memberId, int amount);
	int delete(String memberId);
	
}
