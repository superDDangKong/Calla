package project.spring.calla.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import project.spring.calla.domain.AlarmVO;
import project.spring.calla.service.AlarmService;

@Component
@RequestMapping("/echo")
public class EchoHandler extends TextWebSocketHandler{
	
	@Autowired
	private AlarmService alarmService;
	
	
	private static final Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
	//로그인 한 인원 전체
//	private List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
	
	Map<String, WebSocketSession> userSessions = new HashMap<>();
	
	
	//클라이언트가 웹 소켓 생성
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		logger.info("세션 생성" + session.getId());
		logger.info("Socket 연결" + session);
		//웹 소켓이 생성될 때마다 리스트에 넣어줌
//		for(WebSocketSession sess : sessions) {
//			if (session.getAttributes().get("memberNickname").equals(sess.getAttributes().get("memberNickname")))
//			sessions.remove(sess);
//			break;
//		}
//		sessions.add(session);
//		logger.info("sessions size = " + sessions.size());
		
		Map<String, Object> sessionVal =  session.getAttributes();
		String memberNickname = (String) sessionVal.get("memberNickname");
		
		if(userSessions.get(memberNickname) != null) {
			//userId에 원래 웹세션값이 저장되어 있다면 update
			userSessions.replace(memberNickname, session);
		} else {
			//userId에 웹세션값이 없다면 put
			userSessions.put(memberNickname, session);
		}
		logger.info("userSessions size = " + userSessions.size());
		logger.info(userSessions.toString());
	}
	
	//JS에서 메세지 받을 때.
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {// 메시지
		logger.info("handlerTextMessage 호출");
		//protocol 게시판,boardId,boardTitle(name),작성자id,댓글자nick,댓글내용
		String msg = message.getPayload();
		logger.info(msg);
		AlarmVO vo = new AlarmVO();
		// 내글에 댓글, 내 댓글에 답글, 중고 후기글 등록 되었습니다, 중고 후기글 쓰러가기, 배송이 시작되었습니다.
		// 내글에 댓글
		// memberNickname, alarmCode, alarmPrefix, content, sendNickname, title, boardId
		if(!StringUtils.isEmpty(msg)) {
			String[] strs = msg.split(",");
			if (strs != null && strs.length == 7) {
				String registerNick = strs[0];
				String alarmCode = strs[1];
				String alarmPrefix = strs[2];
				String content = strs[3];
				String sendNick = strs[4];
				String title = strs[5];
				int boardId = Integer.parseInt(strs[6]);
				vo.setMemberNickname(registerNick);
				vo.setAlarmCode(alarmCode);
				vo.setAlarmPrefix(alarmPrefix);
				vo.setContent(content);
				vo.setSendNickname(sendNick);
				vo.setTitle(title);
				vo.setBoardId(boardId);
				
				if(!registerNick.equals(sendNick)) { // 받는사람과 보내는 사람이 다를때만 알람 등록
					int insertResult = alarmService.create(vo);
					logger.info(insertResult+"행 알람 삽입");

					
					if (insertResult == 1) { // 알람 등록이 성공했을때만 알람 전송
						
						List<AlarmVO> list = alarmService.read(registerNick);
						int alarmId = list.get(0).getAlarmId();
						WebSocketSession responseIdSession = userSessions.get(registerNick);
						TextMessage tmpMsg = new TextMessage("");
						
						if(alarmCode.contains("댓글")) { // 댓글 알람
							int commentId = alarmService.readCommentId(alarmPrefix, alarmCode, sendNick);
							logger.info("commentId = " + commentId);
	
							int updateResult = alarmService.updateCommentId(alarmId, commentId);
							logger.info("updateResult = " + updateResult +"행 업데이트 완료");
							
							tmpMsg = new TextMessage(title + "," + alarmCode + "," + sendNick + "," + content + "," + boardId + "," + alarmPrefix + "," + alarmId + "," + commentId);
								
						} else if (alarmCode.contains("답글")) { // 답글알람
							int replyId = alarmService.readCommentId(alarmPrefix, alarmCode, sendNick);
							logger.info("replyId = " + replyId);
							
							int updateReplyResult = alarmService.updateReplyId(alarmId, replyId);
							logger.info("updateResult = " + updateReplyResult +"행 업데이트 완료");
							
							int commentId = alarmService.findCommentIdByReplyId(alarmPrefix, replyId);
							logger.info("commentId = " + commentId);
							
							int updateResult = alarmService.updateCommentId(alarmId, commentId);
							logger.info("updateResult = " + updateResult +"행 업데이트 완료");
						
							tmpMsg = new TextMessage(title + "," + alarmCode + "," + sendNick + "," + content + "," + boardId + "," + alarmPrefix + "," + alarmId + "," + commentId + "," + replyId);
							
						}
						
						if (responseIdSession != null) { // 받는사람이 로그인 상태 일때만 알람 전송	
							logger.info("if responseId" + responseIdSession);
							responseIdSession.sendMessage(tmpMsg);
						}
					}
				}
			}
		} 
//		for(WebSocketSession single : sessions) {
//			대댓글 때 사용
//			String msg = message.getPayload();
//			String[] str = msg.split(",");
//			//JS에서 원하는대로 send하여 해당 기능 별 알람 구현
//			//질문에 답변 달렸을 때(질문자 ID와 제목 들고옴)
//			if(str != null && str.length == 2) {
//				String id = str[0];
//				String q_subject = str[1];
//				int count = alarmDao.selectAlarmCount(id); //알람이 존재할 때
//			}		
			
			//세션아이디
//			String hsid = (String) single.getAttributes().get("memberId");
//			
//			
//			//세션값이 같을때, 알람보낼 것이 있을 때만 전송 -> 로그인 한 사용자가 처음으로 알람 받는 순간임
//			//해당 sendMsg에 DB정보 넣어서 체크 안된 알람 전부 전송하기
//			if(single.getAttributes().get("memberId").equals(session.getAttributes().get("memberId"))) {//체크 안된 알림들만 담아서 View
//				List<AlarmVO> vo = new ArrayList<>();
//				vo = alarmDAO.select(hsid);
//				logger.info("vo = " + vo.toString());
//				for(AlarmVO alarm : vo) {
//					int idx = alarm.getAlarmId();
//					logger.info("idx = " + idx);
//					String prefix = alarm.getPrefix();
//					String code = alarm.getCode();
//					if(code.equals("NewPost")) {
//						code = "답변이 등록되었습니다.";
//					}
//					TextMessage sendMsg = new TextMessage("("+idx+")" + prefix + "에 " + code);
//					single.sendMessage(sendMsg);
//				}
//			}
//		}
		//protocol: 보내는id, 받는id, 내용 (sendId, receiveUserId, content)
		/*String msg = message.getPayload();
		logger.info(msg);
		
		if(!StringUtils.isEmpty(msg)) {
			String[] strs = msg.split(",");
			if (strs != null && strs.length == 3) {
				String sendId = strs[0];
				String receiveUserId = strs[1];
				String content = strs[2];
				
				//broadcasting
				if(receiveUserId.equals("")) {
					for (WebSocketSession sess: sessions) {
						//message를 TextMessage형태로 받음 (22번째줄, string x)
						sess.sendMessage(new TextMessage(receiveUserId + ":" + message.getPayload()));
					}
				} else {
					WebSocketSession responseIdSession = userSessions.get(receiveUserId);
					if (responseIdSession != null) {
						TextMessage tmpMsg = new TextMessage(sendId + "," + receiveUserId + "," + content);
						responseIdSession.sendMessage(tmpMsg);
					}
				}
				
			}
		}*/
	} // end handlerTextMassage
	
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {//연결 해제
//		sessions.remove(session);
		userSessions.remove(session);
		logger.info("Socket 끊음" + session + " : " + status);
		//웹 소켓이 종료될 때마다 리스트에서 뺀다.
	}
}