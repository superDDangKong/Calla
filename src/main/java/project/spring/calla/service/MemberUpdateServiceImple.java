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
import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.MyPageCriteria;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.persistence.FBoardCommentDAO;
import project.spring.calla.persistence.FBoardDAO;
import project.spring.calla.persistence.MemberUpdateDAO;
import project.spring.calla.persistence.ProductCommentDAO;
import project.spring.calla.persistence.ProductDAO;
import project.spring.calla.persistence.ProductLikeDAO;
import project.spring.calla.persistence.QBoardCommentDAO;
import project.spring.calla.persistence.QBoardDAO;
import project.spring.calla.persistence.UProductCommentDAO;
import project.spring.calla.persistence.UProductDAO;
import project.spring.calla.persistence.UProductLikeDAO;


@Service
public class MemberUpdateServiceImple implements MemberUpdateService {
		private static final Logger logger = 
				LoggerFactory.getLogger(MemberUpdateServiceImple.class);

    
	@Autowired
	private MemberUpdateDAO MemberUpdateDAO;

	@Override
	public int updatePw(String memberId, String memberPw) {
		logger.info("update() �샇異� memberPw : " + memberPw);
		return MemberUpdateDAO.updatePw(memberId, memberPw);
	}
	
	@Override
	public int updateNickname(String memberId, String memberNickname) {
		logger.info("updateNickname 호출");
		return MemberUpdateDAO.updateNickname(memberId, memberNickname);
	}

	@Override
	public int updatePhone(String memberId, String memberPhone) {
		return MemberUpdateDAO.updatePhone(memberId, memberPhone);
	}

	@Override
	public int updateEmail(String memberId, String memberEmail) {
		return MemberUpdateDAO.updateEmail(memberId, memberEmail);
	}

	@Override
	public int updateInterest(String memberId, String memberInterest) {
		return MemberUpdateDAO.updateInterest(memberId, memberInterest);
	}
	
	@Override
	public int updateAddress(String memberId, String memberAddress) {
		return MemberUpdateDAO.updateAddress(memberId, memberAddress);
	}

	
	@Override
	public int updateLevel(String memberId, int amount) {
		return MemberUpdateDAO.updateLevel(memberId, amount);
	}
	
	@Override
	public int delete(String memberId) {
		logger.info("delete() 호출");
		return MemberUpdateDAO.delete(memberId);
	}


}
