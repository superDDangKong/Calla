package project.spring.calla.service;

import java.util.Map;

import project.spring.calla.domain.MemberVO;

public interface MemberService {
	int create(MemberVO vo);
	int checkId(String memberId);
	int checkNick(String memberNickname);
	MemberVO read(String memberId);
	int update(MemberVO vo);
	String searchId(String memberName, String memberEmail);
	String searchPw(String memberId, String memberPhone);
	Map<String, Object> readComments(String memberNickname);
	Map<String, Object> readBoards(String memberNickname);
	int updatePw(String memberId, String memberPw);
}
