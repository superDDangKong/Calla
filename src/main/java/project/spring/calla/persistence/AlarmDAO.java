package project.spring.calla.persistence;

import java.util.Date;
import java.util.List;

import project.spring.calla.domain.AlarmVO;

public interface AlarmDAO {
	
	List<AlarmVO> select(String memberNickname);
	int insert(AlarmVO vo);
	int updateCheck(int alarmId);
	int check(String memberNickname);
	int delete(int alarmId);
	int selectCommentId(String selectTable, String idName, String commentCreatedDate, String sendNick);
	int findPage(String selectTable, String boardIdName, String commentIdName, String commentCreatedDate, int boardId, int commentId);
	int updateCommentId(int alarmId, int commentId);
	int updateReplyId(int alarmId, int commentId);
	int findCommentIdByReplyId(String selectTable, String commentIdName, String replyIdName, int replyId);
}
