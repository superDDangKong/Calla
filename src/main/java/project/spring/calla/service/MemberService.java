package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.MemberVO;

public interface MemberService {
	
	// controller
	String login(String memberId, String memberPw);
	MemberVO read(String memberId); // 
	String searchId(String memberName, String memberEmail);
	String searchPw(String memberId, String memberPhone);
	int delete(String memberId);
	List<MemberVO> read();
	
	// restcontroller
	int create(MemberVO vo);
	int checkId(String memberId);
	int checkNick(String memberNickname);
	int update(String memberId, String newData, String category);
	int updatePw(String memberId, String memberPw);
	int updateLevel(String memberId, int amount);
	
	int deleteUProduct(int uProductId);
}









