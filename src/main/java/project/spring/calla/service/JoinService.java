package project.spring.calla.service;

import project.spring.calla.domain.memberVO;

public interface JoinService {
	int create(memberVO vo);
	int checkId(String memberId);
	int checkNick(String memberNickname);
	
	

}
