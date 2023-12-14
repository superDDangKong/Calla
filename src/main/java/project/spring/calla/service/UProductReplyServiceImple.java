package project.spring.calla.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductReplyVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.persistence.UProductCommentDAO;
import project.spring.calla.persistence.UProductDAO;
import project.spring.calla.persistence.UProductReplyDAO;

@Service
public class UProductReplyServiceImple implements UProductReplyService{
	private static final Logger logger = 
			LoggerFactory.getLogger(UProductReplyServiceImple.class);
	
	@Autowired
	private UProductReplyDAO uproductReplyDAO;
	
	@Autowired
	private UProductDAO uProductDAO;
	
	@Autowired
	private UProductCommentDAO uProductCommentDAO;

	@Override
	public int create(UProductReplyVO vo) throws Exception {
		logger.info("create() 호출 : vo = " + vo.toString());;
		int resultInsert = uproductReplyDAO.insert(vo);
		logger.info(resultInsert + " 행 댓글 입력 성공");
		return 1;
	}

	@Override
	public List<UProductReplyVO> read(int uProductCommentId, HttpSession session) {
		logger.info("read() 호출 : uProductCommentId = " + uProductCommentId);
		UProductCommentVO product = uProductCommentDAO.selectcomment(uProductCommentId); 
		
		UProductVO uproduct = uProductDAO.select(product.getuProductId());

		String memberNickname = (String) session.getAttribute("memberNickname");

		List<UProductReplyVO> items = uproductReplyDAO.select(uProductCommentId);
		
		logger.info("list 호출 : " + items);
		

		for (UProductReplyVO vo : items) {
			if(memberNickname == null) { // 로그인 안된 상태
				vo.setuProductReplyContent("비밀 댓글입니다");
			} else { 
				String commenter = product.getMemberNickname(); // 댓글 작성자
				String writer = uproduct.getMemberNickname(); // 물건 판매자 닉네임
				if(!memberNickname.equals(commenter) && !memberNickname.equals(writer)) { // 댓글 작성자가 아니거나 물건 판매자가 아닐 경우
					vo.setuProductReplyContent("비밀 댓글입니다");
				}
			}
		}
		
		return items;
	}
	

	@Override
	public int update(int uProductReplyId, String uProductReplyContent) {
		logger.info("update() 호출");
		logger.info("uProductReplyId = " + uProductReplyId + ", uProductReplyContent = " + uProductReplyContent);
		return uproductReplyDAO.update(uProductReplyId, uProductReplyContent);
	}

	@Override
	public int delete(int uProductReplyId) throws Exception {
		logger.info("delete() 호출 : uProductReplyId = " + uProductReplyId);
		int resultDelete = uproductReplyDAO.delete(uProductReplyId);
		logger.info(resultDelete + " 행 삭제 성공");
		return 1;
	}




	

}
