package project.spring.calla.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.MemberVO;
import project.spring.calla.persistence.FBoardCommentDAO;
import project.spring.calla.persistence.FBoardDAO;
import project.spring.calla.persistence.MemberDAO;
import project.spring.calla.persistence.ProductCommentDAO;
import project.spring.calla.persistence.ProductDAO;
import project.spring.calla.persistence.ProductLikeDAO;
import project.spring.calla.persistence.QBoardCommentDAO;
import project.spring.calla.persistence.QBoardDAO;
import project.spring.calla.persistence.UProductCommentDAO;
import project.spring.calla.persistence.UProductDAO;
import project.spring.calla.persistence.UProductLikeDAO;

@Service
public class MemberServiceImple implements MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberServiceImple.class);

	@Autowired
	private MemberDAO MemberDAO;
	
	
	@Override
	public String login(String memberId, String memberPw) {
		logger.info("login() 호출 memberId : " + memberId);
		return MemberDAO.login(memberId, memberPw);
	}
	
	@Override
	public MemberVO read(String memberId) {
		logger.info("read(memberId) 호출 memberId : " + memberId);
		return MemberDAO.select(memberId);
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

	@Override
	public int delete(String memberId) {
		logger.info("delete() 호출");
		return MemberDAO.delete(memberId);
	}

	@Override
	public List<MemberVO> read() {
		logger.info("read() 호출");
		return MemberDAO.select();
	}
	
	@Override
	public int create(MemberVO vo) {
		logger.info("create() " + vo.toString());
		int result = 0;
		try {
			return result = MemberDAO.insert(vo);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalStateException("�г����̳� id�� Ȯ�����ּ���");
			// � �׸񿡼� ������ ������� 
			
		}
	}

	@Override
	public int checkId(String memberId) {
		int result = 0;
		result = MemberDAO.checkId(memberId);
		return result;
	}

	@Override
	public int checkNick(String memberNickname) { //
		logger.info("checkNick() : memberNickname = " + memberNickname);
		return MemberDAO.checkNickname(memberNickname);
	}
	
	@Override
	public int update(String memberId, String newData, String category) {
		logger.info("update() memberId : " + memberId + "newData = " + newData + "category = " + category);
		return MemberDAO.update(memberId, newData, category);
	}

	@Override
	public int updatePw(String memberId, String memberPw) {
		logger.info("update()  memberPw : " + memberPw);
		return MemberDAO.updatePw(memberId, memberPw);
	}
	
	@Override
	public int updateLevel(String memberId, int amount) {
		return MemberDAO.updateLevel(memberId, amount);
	}

	@Override
	public int deleteUProduct(int uProductId) {
		logger.info("deleteUProduct() 호출");
		return MemberDAO.deleteUProduct(uProductId);
	}


}
