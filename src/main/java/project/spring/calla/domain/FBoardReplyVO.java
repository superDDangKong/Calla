package project.spring.calla.domain;

import java.util.Date;

public class FBoardReplyVO {
	
	private int fBoardReplyId;
	private int fBoardCommentId;
	private String memberNickname;
	private String fBoardReplyContent;
	private Date fBoardReplyCreatedDate;
	
	public FBoardReplyVO() {}
	
	public FBoardReplyVO(int fBoardReplyId, int fBoardCommentId, String memberNickname, String fBoardReplyContent,
			Date fBoardReplyCreatedDate) {
		this.fBoardReplyId = fBoardReplyId;
		this.fBoardCommentId = fBoardCommentId;
		this.memberNickname = memberNickname;
		this.fBoardReplyContent = fBoardReplyContent;
		this.fBoardReplyCreatedDate = fBoardReplyCreatedDate;
	}
	public int getfBoardReplyId() {
		return fBoardReplyId;
	}
	public void setfBoardReplyId(int fBoardReplyId) {
		this.fBoardReplyId = fBoardReplyId;
	}
	public int getfBoardCommentId() {
		return fBoardCommentId;
	}
	public void setfBoardCommentId(int fBoardCommentId) {
		this.fBoardCommentId = fBoardCommentId;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getfBoardReplyContent() {
		return fBoardReplyContent;
	}
	public void setfBoardReplyContent(String fBoardReplyContent) {
		this.fBoardReplyContent = fBoardReplyContent;
	}
	public Date getfBoardReplyCreatedDate() {
		return fBoardReplyCreatedDate;
	}
	public void setfBoardReplyCreatedDate(Date fBoardReplyCreatedDate) {
		this.fBoardReplyCreatedDate = fBoardReplyCreatedDate;
	}
	@Override
	public String toString() {
		return "FBoardReplyVO [fBoardReplyId=" + fBoardReplyId + ", fBoardCommentId=" + fBoardCommentId
				+ ", memberNickname=" + memberNickname + ", fBoardReplyContent=" + fBoardReplyContent
				+ ", fBoardReplyCreatedDate=" + fBoardReplyCreatedDate + "]";
	}
	
	
}
