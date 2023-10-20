package project.spring.calla.domain;

import java.util.Date;

public class FBoardCommentVO {
	private int fBoardCommentId;
	private int fBoardId;
	private String memberNickname;
	private String fBoardCommentContent;
	private Date fBoardCommentCreatedDate;
	
	public FBoardCommentVO() {}

	public FBoardCommentVO(int fBoardCommentId, int fBoardId, String memberNickname, String fBoardCommentContent,
			Date fBoardCommentCreatedDate) {
		this.fBoardCommentId = fBoardCommentId;
		this.fBoardId = fBoardId;
		this.memberNickname = memberNickname;
		this.fBoardCommentContent = fBoardCommentContent;
		this.fBoardCommentCreatedDate = fBoardCommentCreatedDate;
	}

	public int getfBoardCommentId() {
		return fBoardCommentId;
	}

	public void setfBoardCommentId(int fBoardCommentId) {
		this.fBoardCommentId = fBoardCommentId;
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

	public String getfBoardCommentContent() {
		return fBoardCommentContent;
	}

	public void setfBoardCommentContent(String fBoardCommentContent) {
		this.fBoardCommentContent = fBoardCommentContent;
	}

	public Date getfBoardCommentCreatedDate() {
		return fBoardCommentCreatedDate;
	}

	public void setfBoardCommentCreatedDate(Date fBoardCommentCreatedDate) {
		this.fBoardCommentCreatedDate = fBoardCommentCreatedDate;
	}

	@Override
	public String toString() {
		return "FBoardCommentVO [fBoardCommentId=" + fBoardCommentId + ", fBoardId=" + fBoardId + ", memberNickname="
				+ memberNickname + ", fBoardCommentContent=" + fBoardCommentContent + ", fBoardCommentCreatedDate="
				+ fBoardCommentCreatedDate + "]";
	}
	
	
}
