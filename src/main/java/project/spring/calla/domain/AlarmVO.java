package project.spring.calla.domain;

import java.util.Date;

public class AlarmVO {
	int alarmId;
	String memberNickname;
	String alarmCode;
	String alarmChecked;
	Date alarmCreatedDate;
	String alarmPrefix;
	String content;
	String sendNickname;
	String title;
	int boardId;
	int commentId;
	int replyId;
	
	public AlarmVO() {}

	public AlarmVO(int alarmId, String memberNickname, String alarmCode, String alarmChecked, Date alarmCreatedDate,
			String alarmPrefix, String content, String sendNickname, String title, int boardId, int commentId,
			int replyId) {
		super();
		this.alarmId = alarmId;
		this.memberNickname = memberNickname;
		this.alarmCode = alarmCode;
		this.alarmChecked = alarmChecked;
		this.alarmCreatedDate = alarmCreatedDate;
		this.alarmPrefix = alarmPrefix;
		this.content = content;
		this.sendNickname = sendNickname;
		this.title = title;
		this.boardId = boardId;
		this.commentId = commentId;
		this.replyId = replyId;
	}

	public int getAlarmId() {
		return alarmId;
	}

	public void setAlarmId(int alarmId) {
		this.alarmId = alarmId;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getAlarmCode() {
		return alarmCode;
	}

	public void setAlarmCode(String alarmCode) {
		this.alarmCode = alarmCode;
	}

	public String getAlarmChecked() {
		return alarmChecked;
	}

	public void setAlarmChecked(String alarmChecked) {
		this.alarmChecked = alarmChecked;
	}

	public Date getAlarmCreatedDate() {
		return alarmCreatedDate;
	}

	public void setAlarmCreatedDate(Date alarmCreatedDate) {
		this.alarmCreatedDate = alarmCreatedDate;
	}

	public String getAlarmPrefix() {
		return alarmPrefix;
	}

	public void setAlarmPrefix(String alarmPrefix) {
		this.alarmPrefix = alarmPrefix;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendNickname() {
		return sendNickname;
	}

	public void setSendNickname(String sendNickname) {
		this.sendNickname = sendNickname;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getBoardId() {
		return boardId;
	}

	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	@Override
	public String toString() {
		return "AlarmVO [alarmId=" + alarmId + ", memberNickname=" + memberNickname + ", alarmCode=" + alarmCode
				+ ", alarmChecked=" + alarmChecked + ", alarmCreatedDate=" + alarmCreatedDate + ", alarmPrefix="
				+ alarmPrefix + ", content=" + content + ", sendNickname=" + sendNickname + ", title=" + title
				+ ", boardId=" + boardId + ", commentId=" + commentId + ", replyId=" + replyId + "]";
	}

	
	
}