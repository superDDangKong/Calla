package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.MailVO;
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
	
	/*
	 * // 이메일 관련 메소드 void registMailAuthentication(String memberEmail, String
	 * authenticationKey);//부모창 mail 확인하기, 확인하기버튼 누르는 순간 인증키랑 mail 저장 int
	 * mailAuthenticationConfirm(MailVO mailAuth);
	 */
	
	
}









