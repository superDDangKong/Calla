package project.spring.calla.service;

import project.spring.calla.domain.MemberVO;

public interface MemberService {
	MemberVO read(String memberId);
	int update(MemberVO vo);
}
