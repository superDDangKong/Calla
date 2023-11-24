package project.spring.calla.persistence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import project.spring.calla.domain.AlarmVO;
import project.spring.calla.pageutil.PageCriteria;

@Repository
public class AlarmDAOImple implements AlarmDAO {

	private static final Logger logger =
			LoggerFactory.getLogger(AlarmDAOImple.class);
	
	private static final String NAMESPACE = 
			"project.spring.calla.AlarmMapper";
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<AlarmVO> select(String memberNickname) {
		logger.info("select() 호출 hsid = " + memberNickname);
		return sqlSession.selectList(NAMESPACE + ".select", memberNickname);
	}

	@Override
	public int insert(AlarmVO vo) {
		logger.info("select() 호출 vo = " + vo.toString());
		return sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public int updateCheck(int alarmId) {
		logger.info("updateCheck() 호출 alarmId = " + alarmId);
		return sqlSession.update(NAMESPACE + ".update_check", alarmId);
	}

	@Override
	public int check(String memberNickname) {
		logger.info("check() 호출 memberNickname = " + memberNickname);
		return sqlSession.selectOne(NAMESPACE + ".check", memberNickname);
	}

	@Override
	public int delete(int alarmId) {
		logger.info("delete() 호출 alarmId = " + alarmId);
		return sqlSession.delete(NAMESPACE + ".delete", alarmId);
	}

	@Override
	public int selectCommentId(String selectTable, String idName, String commentCreatedDate, String sendNick) {
		logger.info("selectCommentId() 호출 selectTable = " + selectTable + "idName = " + idName + "sendNick = " + sendNick);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("selectTable", selectTable);
		args.put("idName", idName);
		args.put("sendNick", sendNick);
		args.put("commentCreatedDate", commentCreatedDate);
		return sqlSession.selectOne(NAMESPACE + ".selectCommentId", args);
	}

	@Override
	public int findPage(String selectTable, String boardIdName, String commentIdName, String commentCreatedDate, int boardId, int commentId) {
			logger.info("findPage() 호출 selectTable = " + selectTable + "boardIdName = " + boardIdName + "commentIdNAme = " + commentIdName + "boardId = " + boardId + "commentId = " + commentId);
			Map<String, Object> args = new HashMap<String, Object>();
			args.put("selectTable", selectTable);
			args.put("boardIdName", boardIdName);
			args.put("commentIdName", commentIdName);
			args.put("boardId", boardId);
			args.put("commentId", commentId);
			args.put("commentCreatedDate", commentCreatedDate);
			int rn = sqlSession.selectOne(NAMESPACE + ".findPage", args);
			logger.info("rn = " + rn);
			int page = 0;
			PageCriteria criteria = new PageCriteria();
			int numsPerPage = criteria.getNumsPerPage();
			
			page = ((rn-1) / numsPerPage) + 1;
			return page;
			
	}

	@Override
	public int updateCommentId(int alarmId, int commentId) {
		logger.info("updateCommentId() 호출 : alarmId = " + alarmId + "commentId = " + commentId);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("alarmId", alarmId);
		args.put("commentId", commentId);
		return sqlSession.update(NAMESPACE + ".update_comment_id", args);
	}
	
	@Override
	public int updateReplyId(int alarmId, int commentId) {
		logger.info("updateReplyId() 호출 : alarmId = " + alarmId + "commentId = " + commentId);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("alarmId", alarmId);
		args.put("commentId", commentId);
		return sqlSession.update(NAMESPACE + ".update_reply_id", args);
	}

	@Override
	public int findCommentIdByReplyId(String selectTable, String commentIdName, String replyIdName, int replyId) {
		logger.info("findReplyIdByReplyId 호출 selectTable = " + selectTable +  "replyId =  "  + replyId);
		Map<String, Object> args = new HashMap<String, Object>();
		args.put("selectTable", selectTable);
		args.put("commentIdName", commentIdName);
		args.put("replyIdName", replyIdName);
		args.put("replyId", replyId);
	
		return sqlSession.selectOne(NAMESPACE + ".find_comment_id_by_reply_id", args);
		
	}

}
