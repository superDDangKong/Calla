package project.spring.calla.persistence;

import java.util.List;

import project.spring.calla.domain.MemberVO;

public interface MemberDAO {
//	
//	int insert(MemberVO vo);
//	List<MemberVO> select();
	int update(MemberVO vo);
//	int delete();
	MemberVO select(String memberId);
	String login(String memberId, String memberPw);
}
