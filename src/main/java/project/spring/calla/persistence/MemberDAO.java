package project.spring.calla.persistence;

import project.spring.calla.domain.MemberVO;
import java.util.List;
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
}
