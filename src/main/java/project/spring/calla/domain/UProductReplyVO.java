package project.spring.calla.domain;

public class UProductReplyVO {
	
	private int uProductReplyId;
	private int uProductCommentId;
	private String MemberNickname;
	private String uProductReplyContent;
	private String uProductReplyCreatedDate;
	
	public UProductReplyVO() {}

	public UProductReplyVO(int uProductReplyId, int uProductCommentId, String memberNickname,
			String uProductReplyContent, String uProductReplyCreatedDate) {
		super();
		this.uProductReplyId = uProductReplyId;
		this.uProductCommentId = uProductCommentId;
		MemberNickname = memberNickname;
		this.uProductReplyContent = uProductReplyContent;
		this.uProductReplyCreatedDate = uProductReplyCreatedDate;
	}

	public int getuProductReplyId() {
		return uProductReplyId;
	}

	public void setuProductReplyId(int uProductReplyId) {
		this.uProductReplyId = uProductReplyId;
	}

	public int getuProductCommentId() {
		return uProductCommentId;
	}

	public void setuProductCommentId(int uProductCommentId) {
		this.uProductCommentId = uProductCommentId;
	}

	public String getMemberNickname() {
		return MemberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		MemberNickname = memberNickname;
	}

	public String getuProductReplyContent() {
		return uProductReplyContent;
	}

	public void setuProductReplyContent(String uProductReplyContent) {
		this.uProductReplyContent = uProductReplyContent;
	}

	public String getuProductReplyCreatedDate() {
		return uProductReplyCreatedDate;
	}

	public void setuProductReplyCreatedDate(String uProductReplyCreatedDate) {
		this.uProductReplyCreatedDate = uProductReplyCreatedDate;
	}

	@Override
	public String toString() {
		return "UProductReplyVO [uProductReplyId=" + uProductReplyId + ", uProductCommentId=" + uProductCommentId
				+ ", MemberNickname=" + MemberNickname + ", uProductReplyContent=" + uProductReplyContent
				+ ", uProductReplyCreatedDate=" + uProductReplyCreatedDate + "]";
	}



}
