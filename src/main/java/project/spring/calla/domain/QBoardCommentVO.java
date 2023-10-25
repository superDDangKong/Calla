package project.spring.calla.domain;

import java.util.Date;

public class QBoardCommentVO {
	private int qBoardCommentId;
	private int qBoardId;
	private String memberNickname;
	private String qBoardCommentContent;
	private Date qBoardCommentCreatedDate;
	
	public QBoardCommentVO() {}

	public QBoardCommentVO(int qBoardCommentId, int qBoardId, String memberNickname, String qBoardCommentContent,
			Date qBoardCommentCreatedDate) {
		this.qBoardCommentId = qBoardCommentId;
		this.qBoardId = qBoardId;
		this.memberNickname = memberNickname;
		this.qBoardCommentContent = qBoardCommentContent;
		this.qBoardCommentCreatedDate = qBoardCommentCreatedDate;
	}

	public int getqBoardCommentId() {
		return qBoardCommentId;
	}

	public void setqBoardCommentId(int qBoardCommentId) {
		this.qBoardCommentId = qBoardCommentId;
	}

	public int getqBoardId() {
		return qBoardId;
	}

	public void setqBoardId(int qBoardId) {
		this.qBoardId = qBoardId;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getqBoardCommentContent() {
		return qBoardCommentContent;
	}

	public void setqBoardCommentContent(String qBoardCommentContent) {
		this.qBoardCommentContent = qBoardCommentContent;
	}

	public Date getqBoardCommentCreatedDate() {
		return qBoardCommentCreatedDate;
	}

	public void setqBoardCommentCreatedDate(Date qBoardCommentCreatedDate) {
		this.qBoardCommentCreatedDate = qBoardCommentCreatedDate;
	}

	@Override
	public String toString() {
		return "QBoardCommentVO [qBoardCommentId=" + qBoardCommentId + ", qBoardId=" + qBoardId + ", memberNickname="
				+ memberNickname + ", qBoardCommentContent=" + qBoardCommentContent + ", qBoardCommentCreatedDate="
				+ qBoardCommentCreatedDate + "]";
	}
	
	
	
}
