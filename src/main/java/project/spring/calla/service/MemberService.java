package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.MemberVO;

public interface MemberService {
	int create(MemberVO vo);
	int checkId(String memberId);
	int checkNick(String memberNickname);
	MemberVO read(String memberId); // (home, ) select*
	String login(String memberId, String memberPw);
	String searchId(String memberName, String memberEmail);
	String searchPw(String memberId, String memberPhone);
	
	int updatePw(String memberId, String memberPw);
	int updateNickname(String memberId, String memberNickname);
	int updatePhone(String memberId, String memberPhone);
	int updateEmail(String memberId, String memberEmail);
	int updateInterest(String memberId, String memberInterest);
	int updateAddress(String memberId, String memberAddress);
	List<MemberVO> read();
	int updateLevel(String memberId, int amount);
	int delete(String memberId);
	
}
