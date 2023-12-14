package project.spring.calla.service;

import java.util.List;

import project.spring.calla.domain.AlarmVO;

public interface AlarmService {
	List<AlarmVO> read(String memberNickname);
	int create(AlarmVO vo);
	int updateCheck(int alarmId);
	int check(String memberNickname);
	int delete(int alarmId);
	int readCommentId(String alarmPrefix, String alarmCode, String sendNick);
	int findPage(AlarmVO vo);
	int updateCommentId(int alarmId, int commentId);
	int updateReplyId(int alarmId, int commentId);
	int findCommentIdByReplyId(String alarmPrefix, int replyId);
}
