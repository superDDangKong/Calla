package project.spring.calla.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.persistence.FBoardCommentDAO;
import project.spring.calla.persistence.FBoardDAO;
import project.spring.calla.persistence.UProductCommentDAO;
import project.spring.calla.persistence.UProductDAO;

@Service
public class UProductCommentServiceImple implements UProductCommentService {

	private static final Logger logger = LoggerFactory.getLogger(UProductCommentServiceImple.class);

	@Autowired
	private UProductCommentDAO uProductCommentDAO;

	@Autowired
	private UProductDAO uProductDAO;

	@Transactional(value = "transactionManager")
	@Override
	public int create(UProductCommentVO vo) throws Exception {
		logger.info("create() 호출 : vo = " + vo.toString());
		int resultInsert = uProductCommentDAO.insert(vo);
		logger.info(resultInsert + " 행 댓글 입력 성공");
		int result = uProductDAO.updateUproductCommentCount(1, vo.getuProductId());
		logger.info(result + " 행 수정 성공");
		return 1;
	}
	
	@Override
	public UProductCommentVO selectvo(int uProductId) {
		logger.info("read() 호占쏙옙 : boardId = " + uProductId);
		return uProductCommentDAO.selectvo(uProductId);
	}

	@Transactional(value = "transactionManager")
	@Override
	public List<UProductCommentVO> read(int uProductId, HttpSession session) {
		logger.info("read() 호출 : uProductId = " + uProductId);
		UProductVO product = uProductDAO.select(uProductId);
		

		String memberNickname = (String) session.getAttribute("memberNickname");

		List<UProductCommentVO> items = uProductCommentDAO.select(uProductId);
		
		logger.info("list 호출 : " + items);
		

		for (UProductCommentVO vo : items) {
			if(memberNickname == null) { // 로그인 안된 상태
				vo.setuProductCommentContent("비밀 댓글입니다");
			} else { 
				String commenter = vo.getMemberNickname();
				String writer = product.getMemberNickname();
				if(!memberNickname.equals(commenter) && !memberNickname.equals(writer)) { // 글 작성자가 아니거나 물건 판매자가 아닐 경우
					if(vo.getuProductSecretComment().equals("y")) // 비밀댓글 표시를 했을 경우
					vo.setuProductCommentContent("비밀 댓글입니다");
				}
			}
		}
		
		return items;
	}

	@Override
	public int update(int uProductCommentId, String uProductCommentContent) {
		logger.info("update() 호출");
		logger.info("fBoardCommentId = " + uProductCommentId + ", fBoardCommentContent = " + uProductCommentContent);
		return uProductCommentDAO.update(uProductCommentId, uProductCommentContent);
	}

	@Transactional(value = "transactionManager")
	@Override
	public int delete(int uProductCommentId, int uProductId) throws Exception {
		logger.info("delete() 호출 : uProductCommentId = " + uProductCommentId);
		int resultDelete = uProductCommentDAO.delete(uProductCommentId);
		logger.info(resultDelete + " 행 삭제 성공");
		int result = uProductDAO.updateUproductCommentCount(-1, uProductId);
		logger.info(result + "행 수정 성공");
		return 1;
	}

	@Override
	public List<UProductCommentVO> read(int uProductId) {
		logger.info("read() 호출 : uProductId = " + uProductId);
		return uProductCommentDAO.selected(uProductId);
	}

	

}
