package project.spring.calla.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.spring.calla.domain.FBoardReplyVO;
import project.spring.calla.domain.MemberVO;
import project.spring.calla.persistence.MemberDAO;


@Service
public class MemberServiceImple implements MemberService {
		private static final Logger logger = 
				LoggerFactory.getLogger(MemberServiceImple.class);

    
	@Autowired
	private MemberDAO MemberDAO;
  
	@Override
	public int create(MemberVO vo) { 
		logger.info("create() 호출 : vo = " + vo.toString());
		return MemberDAO.insert(vo); 
		
	}
	
	@Override
	public int checkId(String memberId) { // 
		int result = 0;
        result = MemberDAO.checkId(memberId);
		return result;
	}
	
	@Override
	public int checkNick(String memberNickname) { // 
		int result = 0;
        result = MemberDAO.checkNickname(memberNickname);
		return result;
	}
	  
	 
	   




	@Override
	public MemberVO read(String memberId) {
		logger.info("read(memberId) ȣ�� memberId : " + memberId);
		return MemberDAO.select(memberId);
	}

	@Override
	public int update(MemberVO vo) {
		logger.info("update() 호출 vo : " + vo.toString());
		return MemberDAO.update(vo);
	}

	@Override
	public String searchId(String memberName, String memberEmail) {
		logger.info("searchId() 호출 memberName : " + memberName);
		logger.info("searchId() 호출 memberEmail : " + memberEmail);
		return MemberDAO.searchId(memberName, memberEmail);
	}

	@Override
	public String searchPw(String memberId, String memberPhone) {
		logger.info("searchPw() 호출 memberId : " + memberId);
		logger.info("searchPw() 호출 memberPhone : " + memberPhone);
		return MemberDAO.searchPw(memberId, memberPhone);
	}

	
}
