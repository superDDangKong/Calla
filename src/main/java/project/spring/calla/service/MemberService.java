package project.spring.calla.service;

import project.spring.calla.domain.MemberVO;

public interface MemberService {
	int create(MemberVO vo);
	int checkId(String memberId);
	int checkNick(String memberNickname);
	MemberVO read(String memberId);
	int update(MemberVO vo);
	String searchId(String memberName, String memberEmail);
	String searchPw(String memberId, String memberPhone);
	
}
