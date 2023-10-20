package project.spring.calla.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.persistence.MemberDAO;

@Service
public class MemberServiceImple implements MemberService {
	private static final Logger logger = 
			LoggerFactory.getLogger(MemberServiceImple.class);
	
	@Autowired
	private MemberDAO dao;

	@Override
	public int create(MemberVO vo) { // 사용자 계정 생성
		logger.info("create() 호출 : vo = " + vo.toString());
		int resultInsert = dao.insert(vo); // 계정 입력? 생성?
		logger.info(resultInsert + "행 계정생성");
		return 1;
		
	}
	
	@Override
	public int checkId(String memberId) { // 아이디 중복확인
		int result = 0;
        result = dao.checkId(memberId);
		return result;
	}
	
	@Override
	public int checkNick(String memberNickname) { // 닉네임 중복확인
		int result = 0;
        result = dao.checkNickname(memberNickname);
		return result;
	}
	  
	 
	   
}
