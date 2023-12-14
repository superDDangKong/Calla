package project.spring.calla.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.spring.calla.domain.AlarmVO;
import project.spring.calla.persistence.AlarmDAO;
import project.spring.calla.persistence.FBoardDAO;
import project.spring.calla.persistence.ProductDAO;
import project.spring.calla.persistence.QBoardDAO;
import project.spring.calla.persistence.UProductDAO;


@Service
public class AlarmServiceImple implements AlarmService {
		private static final Logger logger = 
				LoggerFactory.getLogger(AlarmServiceImple.class);
		
		@Autowired
		private AlarmDAO alarmDAO;
		
		@Autowired
		private ProductDAO productDAO;
		
		@Autowired
		private UProductDAO uProductDAO;
		
		@Autowired
		private FBoardDAO fBoardDAO;
		
		@Autowired
		private QBoardDAO qBoardDAO;
		
		@Override
		public List<AlarmVO> read(String memberNickname) {
			logger.info("read() 호출 : memberNickname = " + memberNickname);
			return alarmDAO.select(memberNickname);
		}

		@Override
		public int create(AlarmVO vo) {
			logger.info("read() 호출 : vo = " + vo.toString());
			return alarmDAO.insert(vo);
		}

		@Override
		public int updateCheck(int alarmId) {
			logger.info("updateCheck() 호출 : alarmId = " + alarmId);
			return alarmDAO.updateCheck(alarmId);
		}

		@Override
		public int check(String memberNickname) {
			logger.info("check() 호출 : memberNickname = " + memberNickname);
			return alarmDAO.check(memberNickname);
		}

		@Override
		public int delete(int alarmId) {
			logger.info("delete() 호출 : alarmId = " + alarmId);
			return alarmDAO.delete(alarmId);
		}

		@Override
		public int readCommentId(String alarmPrefix, String alarmCode, String sendNick) {
			logger.info("readCommentId() 호출 : alarmPrefix = " + alarmPrefix + "alarmCode = " + alarmCode + "sendNick = " + sendNick);			
			String selectTable = "";
			String idName = "";
			String commentCreatedDate = null;
			if (alarmCode.contains("댓글")) {
				if (alarmPrefix.equals("중고상품")) {
					selectTable = "u_product_comment";
					idName = "u_product_comment_id";
					commentCreatedDate = "u_product_comment_created_date";
				} else if (alarmPrefix.equals("자유게시판")) {
					selectTable = "f_board_comment";
					idName = "f_board_comment_id";
					commentCreatedDate = "f_board_comment_created_date";
				} else if (alarmPrefix.equals("문의게시판")) {
					selectTable = "q_board_comment";
					idName = "q_board_comment_id";
					commentCreatedDate = "q_board_comment_created_date";
				}
			} else if (alarmCode.contains("답글")) {
				if (alarmPrefix.equals("공용상품")) {
					selectTable = "product_reply";
					idName = "product_reply_id";
					commentCreatedDate = "product_reply_created_date";
				} else if (alarmPrefix.equals("중고상품")) {
					selectTable = "u_product_reply";
					idName = "u_product_reply_id";
					commentCreatedDate = "u_product_reply_created_date";
				} else if (alarmPrefix.equals("자유게시판")) {
					selectTable = "f_board_reply";
					idName = "f_board_reply_id";
					commentCreatedDate = "f_board_reply_created_date";
				} else if (alarmPrefix.equals("문의게시판")) {
					selectTable = "q_board_reply";
					idName = "q_board_reply_id";
					commentCreatedDate = "q_board_reply_created_date";
				}
			}
			return alarmDAO.selectCommentId(selectTable, idName, commentCreatedDate, sendNick);
		} // end readCommentId

		@Override
		public int findPage(AlarmVO vo) {
			String alarmPrefix = vo.getAlarmPrefix();
			String alarmCode = vo.getAlarmCode();
			int boardId = vo.getBoardId();
			int commentId = vo.getCommentId();
			
			String selectTable = "";
			String boardIdName = "";
			String commentIdName = "";
			String commentCreatedDate = null;
			
				if (alarmPrefix.equals("중고상품")) {
					selectTable = "u_product_comment";
					boardIdName = "u_product_id";
					commentIdName = "u_product_comment_id";
					commentCreatedDate = "u_product_comment_created_date";
				} else if (alarmPrefix.equals("자유게시판")) {
					selectTable = "f_board_comment";
					boardIdName = "f_board_id";
					commentIdName = "f_board_comment_id";
					commentCreatedDate = "f_board_comment_created_date";
				} else if (alarmPrefix.equals("문의게시판")) {
					selectTable = "q_board_comment";
					boardIdName = "q_board_id";
					commentIdName = "q_board_comment_id";
					commentCreatedDate = "q_board_comment_created_date";
				} else if (alarmPrefix.equals("공용상품") ) {
					selectTable = "product_comment";
					boardIdName = "product_id";
					commentIdName = "product_comment_id";
					commentCreatedDate = "product_comment_created_date";
				}
			return alarmDAO.findPage(selectTable, boardIdName, commentIdName, commentCreatedDate, boardId, commentId);
		}// end findPage

		@Override
		public int updateCommentId(int alarmId, int commentId) {
			logger.info("updateCommentId() 호출 : alarmId = " + alarmId + "commentId = " + commentId);
			return alarmDAO.updateCommentId(alarmId, commentId);
		}

		@Override
		public int updateReplyId(int alarmId, int commentId) {
			logger.info("updateReplyId() 호출 : alarmId = " + alarmId + "commentId = " + commentId);
			return alarmDAO.updateReplyId(alarmId, commentId);
		}

		@Override
		public int findCommentIdByReplyId(String alarmPrefix, int replyId) {
			logger.info("findCommentIdByReplyId 호출 " + "alarmPrefix = " + alarmPrefix + "replyId =  "  + replyId);
			String selectTable="";
			String commentIdName ="";
			String replyIdName="";
			if (alarmPrefix.equals("공용상품")) {
				selectTable = "product_reply";
				commentIdName = "product_comment_id";
				replyIdName = "product_reply_id";
			} else if (alarmPrefix.equals("중고상품")) {
				selectTable = "u_product_reply";
				commentIdName = "u_product_comment_id";
				replyIdName = "u_product_reply_id";
			} else if (alarmPrefix.equals("자유게시판")) {
				selectTable = "f_board_reply";
				commentIdName = "f_board_comment_id";
				replyIdName = "f_board_reply_id";
			} else if (alarmPrefix.equals("문의게시판")) {
				selectTable = "q_board_reply";
				commentIdName = "q_board_comment_id";
				replyIdName = "q_board_reply_id";
			}
			return alarmDAO.findCommentIdByReplyId(selectTable, commentIdName, replyIdName, replyId);
		}

}
