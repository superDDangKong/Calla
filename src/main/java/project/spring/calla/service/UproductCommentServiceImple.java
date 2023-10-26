package project.spring.calla.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.spring.calla.domain.UproductCommentVO;
import project.spring.calla.domain.UproductVO;
import project.spring.calla.pageutil.PageCriteria;
import project.spring.calla.persistence.FBoardCommentDAO;
import project.spring.calla.persistence.FBoardDAO;
import project.spring.calla.persistence.UProductCommentDAO;
import project.spring.calla.persistence.UproductDAO;

@Service
public class UproductCommentServiceImple implements UproductCommentService {

	private static final Logger logger = LoggerFactory.getLogger(UproductCommentServiceImple.class);

	@Autowired
	private UProductCommentDAO uProductCommentDAO;

	@Autowired
	private UproductDAO uProductDAO;

	@Transactional(value = "transactionManager")
	@Override
	public int create(UproductCommentVO vo) throws Exception {
		logger.info("create() 호출 : vo = " + vo.toString());
		int resultInsert = uProductCommentDAO.insert(vo);
		logger.info(resultInsert + " 행 댓글 입력 성공");
		int result = uProductDAO.updateUproductCommentCount(1, vo.getuProductId());
		logger.info(result + " 행 수정 성공");
		return 1;
	}

	@Transactional(value = "transactionManager")
	@Override
	public List<UproductCommentVO> read(int uProductId, HttpSession session) {
		logger.info("read() 호출 : uProductId = " + uProductId);
		UproductVO product = uProductDAO.select(uProductId);

		String memberNickname = (String) session.getAttribute("memberNickname");

		List<UproductCommentVO> items = uProductCommentDAO.select(uProductId);

		for (UproductCommentVO vo : items) {
			if(memberNickname == null) {
				vo.setuProductCommentContent("비밀 댓글입니다");
			} else {
				String commenter = vo.getMemberNickname();
				String writer = product.getMemberNickname();
				if(!memberNickname.equals(commenter) && !memberNickname.equals(writer)) {
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

}
