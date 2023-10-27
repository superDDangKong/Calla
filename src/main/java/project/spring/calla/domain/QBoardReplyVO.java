package project.spring.calla.domain;

import java.util.Date;

public class QBoardReplyVO {
	private int qBoardReplyId;
	private int qBoardCommentId;
	private String memberNickname;
	private String qBoardReplyContent;
	private Date qBoardReplyCreatedDate;
	
	public QBoardReplyVO() {}

	public QBoardReplyVO(int qBoardReplyId, int qBoardCommentId, String memberNickname, String qBoardReplyContent,
			Date qBoardReplyCreatedDate) {
		super();
		this.qBoardReplyId = qBoardReplyId;
		this.qBoardCommentId = qBoardCommentId;
		this.memberNickname = memberNickname;
		this.qBoardReplyContent = qBoardReplyContent;
		this.qBoardReplyCreatedDate = qBoardReplyCreatedDate;
	}

	public int getqBoardReplyId() {
		return qBoardReplyId;
	}

	public void setqBoardReplyId(int qBoardReplyId) {
		this.qBoardReplyId = qBoardReplyId;
	}

	public int getqBoardCommentId() {
		return qBoardCommentId;
	}

	public void setqBoardCommentId(int qBoardCommentId) {
		this.qBoardCommentId = qBoardCommentId;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getqBoardReplyContent() {
		return qBoardReplyContent;
	}

	public void setqBoardReplyContent(String qBoardReplyContent) {
		this.qBoardReplyContent = qBoardReplyContent;
	}

	public Date getqBoardReplyCreatedDate() {
		return qBoardReplyCreatedDate;
	}

	public void setqBoardReplyCreatedDate(Date qBoardReplyCreatedDate) {
		this.qBoardReplyCreatedDate = qBoardReplyCreatedDate;
	}

	@Override
	public String toString() {
		return "QBoardReplyVO [qBoardReplyId=" + qBoardReplyId + ", qBoardCommentId=" + qBoardCommentId
				+ ", memberNickname=" + memberNickname + ", qBoardReplyContent=" + qBoardReplyContent
				+ ", qBoardReplyCreatedDate=" + qBoardReplyCreatedDate + "]";
	}
	
}
