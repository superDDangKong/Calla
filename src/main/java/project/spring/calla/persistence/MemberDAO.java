package project.spring.calla.persistence;

import project.spring.calla.domain.MemberVO;
import java.util.List;
public interface MemberDAO {
	
	int insert(MemberVO vo);
	int checkId(String memberId);
	int checkNickname(String memberNickname);
	int update(MemberVO vo);
	MemberVO select(String memberId);
	String login(String memberId, String memberPw);
	String searchId(String memberName, String memberEmail);
	String searchPw(String memberId, String memberPhone);
	int updatePw(String memberId, String memberPw);
}
