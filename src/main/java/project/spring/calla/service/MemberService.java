package project.spring.calla.service;

import java.util.List;
import java.util.Map;

import project.spring.calla.domain.MemberVO;

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
	int updatePw(String memberId, String memberPw);
	int updateNickname(String memberId, String memberNickname);
	int updatePhone(String memberId, String memberPhone);
	int updateEmail(String memberId, String memberEmail);
	int updateInterest(String memberId, String memberInterest);
	int updateAddress(String memberId, String memberAddress);
	int updateLevel(String memberId, int amount);
}
