package project.spring.calla.domain;

import java.util.Date;

public class FBoardVO {

	private int fBoardId;
	private String memberNickname;
	private String fBoardTitle;
	private String fBoardContent;
	private Date fBoardCreatedDate;
	private int fBoardViews;
	private int fBoardCommentCount;
	
	public FBoardVO() {}

	public FBoardVO(int fBoardId, String memberNickname, String fBoardTitle, String fBoardContent,
			Date fBoardCreatedDate, int fBoardViews, int fBoardCommentCount) {
		this.fBoardId = fBoardId;
		this.memberNickname = memberNickname;
		this.fBoardTitle = fBoardTitle;
		this.fBoardContent = fBoardContent;
		this.fBoardCreatedDate = fBoardCreatedDate;
		this.fBoardViews = fBoardViews;
		this.fBoardCommentCount = fBoardCommentCount;
	}

	public int getfBoardId() {
		return fBoardId;
	}

	public void setfBoardId(int fBoardId) {
		this.fBoardId = fBoardId;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getfBoardTitle() {
		return fBoardTitle;
	}

	public void setfBoardTitle(String fBoardTitle) {
		this.fBoardTitle = fBoardTitle;
	}

	public String getfBoardContent() {
		return fBoardContent;
	}

	public void setfBoardContent(String fBoardContent) {
		this.fBoardContent = fBoardContent;
	}

	public Date getfBoardCreatedDate() {
		return fBoardCreatedDate;
	}

	public void setfBoardCreatedDate(Date fBoardCreatedDate) {
		this.fBoardCreatedDate = fBoardCreatedDate;
	}

	public int getfBoardViews() {
		return fBoardViews;
	}

	public void setfBoardViews(int fBoardViews) {
		this.fBoardViews = fBoardViews;
	}

	public int getfBoardCommentCount() {
		return fBoardCommentCount;
	}

	public void setfBoardCommentCount(int fBoardCommentCount) {
		this.fBoardCommentCount = fBoardCommentCount;
	}

	@Override
	public String toString() {
		return "FBoardVO [fBoardId=" + fBoardId + ", memberNickname=" + memberNickname + ", fBoardTitle=" + fBoardTitle
				+ ", fBoardContent=" + fBoardContent + ", fBoardCreatedDate=" + fBoardCreatedDate + ", fBoardViews="
				+ fBoardViews + ", fBoardCommentCount=" + fBoardCommentCount + "]";
	}

	
	
	
}
