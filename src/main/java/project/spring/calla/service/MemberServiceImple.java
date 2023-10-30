package project.spring.calla.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.persistence.FBoardCommentDAO;
import project.spring.calla.persistence.FBoardDAO;
import project.spring.calla.persistence.MemberDAO;
import project.spring.calla.persistence.ProductCommentDAO;
import project.spring.calla.persistence.ProductDAO;
import project.spring.calla.persistence.QBoardCommentDAO;
import project.spring.calla.persistence.QBoardDAO;
import project.spring.calla.persistence.UProductCommentDAO;
import project.spring.calla.persistence.UproductDAO;


@Service
public class MemberServiceImple implements MemberService {
		private static final Logger logger = 
				LoggerFactory.getLogger(MemberServiceImple.class);

    
	@Autowired
	private MemberDAO MemberDAO;
	
	@Autowired
	private FBoardCommentDAO fBoardCommentDAO;
  
	@Autowired
	private QBoardCommentDAO qBoardCommentDAO;
	
	@Autowired
	private UProductCommentDAO uProductCommentDAO;
	
	@Autowired
	private ProductCommentDAO productCommentDAO;
	
	@Autowired
	private FBoardDAO fBoardDAO;
  
	@Autowired
	private QBoardDAO qBoardDAO;
	
	@Autowired
	private UproductDAO uProductDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	
	@Override
	public int create(MemberVO vo) { 
		logger.info("create() ȣ�� : vo = " + vo.toString());
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
		logger.info("checkNick() 호출 : memberNickname = " + memberNickname);
		return MemberDAO.checkNickname(memberNickname);
	}
	  


	@Override
	public MemberVO read(String memberId) {
		logger.info("read(memberId) ȣ�� memberId : " + memberId);
		return MemberDAO.select(memberId);
	}

	@Override
	public int update(MemberVO vo) {
		logger.info("update() ȣ�� vo : " + vo.toString());
		return MemberDAO.update(vo);
	}

	@Override
	public String searchId(String memberName, String memberEmail) {
		logger.info("searchId() ȣ�� memberName : " + memberName);
		logger.info("searchId() ȣ�� memberEmail : " + memberEmail);
		return MemberDAO.searchId(memberName, memberEmail);
	}

	@Override
	public String searchPw(String memberId, String memberPhone) {
		logger.info("searchPw() ȣ�� memberId : " + memberId);
		logger.info("searchPw() ȣ�� memberPhone : " + memberPhone);
		return MemberDAO.searchPw(memberId, memberPhone);
	}

	@Transactional(value = "transactionManager")
	@Override
	public Map<String, Object> readComments(String memberNickname) {
		logger.info("readComments() ȣ�� memberNickname : " + memberNickname);
		Map<String, Object> args = new HashMap();
		args.put("ProductCommentList", productCommentDAO.select(memberNickname));
		args.put("uProductCommentList", uProductCommentDAO.select(memberNickname));
		args.put("fBoardCommentList", fBoardCommentDAO.select(memberNickname));
		args.put("qBoardCommentList", qBoardCommentDAO.select(memberNickname));
		return args;
	}
	
	@Transactional(value = "transactionManager")
	@Override
	public Map<String, Object> readBoards(String memberNickname) {
		logger.info("readBoards() ȣ�� memberNickname : " + memberNickname);
		Map<String, Object> args = new HashMap();
		args.put("fBoardList", fBoardDAO.selectAllByMemberNickname(memberNickname));
		args.put("qBoardList", qBoardDAO.selectAllByMemberNickname(memberNickname));
		args.put("uProductList", uProductDAO.selectAllByMemberNickname(memberNickname));
		return args;
	}

	@Override
	public int updatePw(String memberId, String memberPw) {
		logger.info("update() 호출 memberPw : " + memberPw);
		return MemberDAO.updatePw(memberId, memberPw);
	}

	@Override
	public int updateNickname(String memberId, String memberNickname) {
		return MemberDAO.updateNickname(memberId, memberNickname);
	}

	@Override
	public int updatePhone(String memberId, String memberPhone) {
		return MemberDAO.updatePhone(memberId, memberPhone);
	}

	@Override
	public int updateEmail(String memberId, String memberEmail) {
		return MemberDAO.updateEmail(memberId, memberEmail);
	}

	@Override
	public int updateInterest(String memberId, String memberInterest) {
		return MemberDAO.updateInterest(memberId, memberInterest);
	}

	@Override
	public int updateAddress(String memberId, String memberAddress) {
		return MemberDAO.updateAddress(memberId, memberAddress);
	}
	
	@Override
	public int updateLevel(String memberId, int amount) {
		return MemberDAO.updateLevel(memberId, amount);
	}
	
	@Override
	public List<MemberVO> read() {
		logger.info("read() 호출");
		return MemberDAO.select();
	}


	
}
