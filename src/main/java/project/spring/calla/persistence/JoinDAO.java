package project.spring.calla.persistence;

import project.spring.calla.domain.memberVO;

public interface JoinDAO {
	
	int insert(memberVO vo);
	int checkId(String memberId);
	int checkNickname(String memberNickname);
}
