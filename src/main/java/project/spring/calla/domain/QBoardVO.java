package project.spring.calla.domain;

import java.util.Date;

public class QBoardVO {
	private int qBoardId;
	private String memberNickname;
	private String qBoardTitle;
	private String qBoardContent;
	private Date qBoardCreatedDate;
	private int qBoardViews;
	private int qBoardCommentCount;
	private String qBoardImagePath;
	
	public QBoardVO() {}
	
	public QBoardVO(int qBoardId, String memberNickname, String qBoardTitle, String qBoardContent,
			Date qBoardCreatedDate, int qBoardViews, int qBoardCommentCount, String qBoardImagePath) {
		super();
		this.qBoardId = qBoardId;
		this.memberNickname = memberNickname;
		this.qBoardTitle = qBoardTitle;
		this.qBoardContent = qBoardContent;
		this.qBoardCreatedDate = qBoardCreatedDate;
		this.qBoardViews = qBoardViews;
		this.qBoardCommentCount = qBoardCommentCount;
		this.qBoardImagePath = qBoardImagePath;
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

	public String getqBoardTitle() {
		return qBoardTitle;
	}

	public void setqBoardTitle(String qBoardTitle) {
		this.qBoardTitle = qBoardTitle;
	}

	public String getqBoardContent() {
		return qBoardContent;
	}

	public void setqBoardContent(String qBoardContent) {
		this.qBoardContent = qBoardContent;
	}

	public Date getqBoardCreatedDate() {
		return qBoardCreatedDate;
	}

	public void setqBoardCreatedDate(Date qBoardCreatedDate) {
		this.qBoardCreatedDate = qBoardCreatedDate;
	}

	public int getqBoardViews() {
		return qBoardViews;
	}

	public void setqBoardViews(int qBoardViews) {
		this.qBoardViews = qBoardViews;
	}

	public int getqBoardCommentCount() {
		return qBoardCommentCount;
	}

	public void setqBoardCommentCount(int qBoardCommentCount) {
		this.qBoardCommentCount = qBoardCommentCount;
	}

	public String getqBoardImagePath() {
		return qBoardImagePath;
	}

	public void setqBoardImagePath(String qBoardImagePath) {
		this.qBoardImagePath = qBoardImagePath;
	}

	@Override
	public String toString() {
		return "QBoardVO [qBoardId=" + qBoardId + ", memberNickname=" + memberNickname + ", qBoardTitle=" + qBoardTitle
				+ ", qBoardContent=" + qBoardContent + ", qBoardCreatedDate=" + qBoardCreatedDate + ", qBoardViews="
				+ qBoardViews + ", qBoardCommentCount=" + qBoardCommentCount + ", qBoardImagePath=" + qBoardImagePath
				+ "]";
	}

	
	
	
	
}
