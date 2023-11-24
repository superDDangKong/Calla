package project.spring.calla.service;

import java.util.List;
import java.util.Map;

import project.spring.calla.domain.AlarmVO;
import project.spring.calla.domain.MemberVO;
import project.spring.calla.domain.ProductOrderVO;
import project.spring.calla.domain.UProductBuyVO;
import project.spring.calla.domain.UProductCommentVO;
import project.spring.calla.domain.UProductSellVO;
import project.spring.calla.domain.UProductVO;
import project.spring.calla.pageutil.MyPageCriteria;
import project.spring.calla.pageutil.PageCriteria;

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
