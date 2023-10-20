package project.spring.calla.persistence;

import project.spring.calla.domain.MemberVO;

public interface MemberDAO {
	
	int insert(MemberVO vo);
	int checkId(String memberId);
	int checkNickname(String memberNickname);
}
