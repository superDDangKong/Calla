package project.spring.calla.domain;

import java.util.Arrays;
import java.util.Date;

public class UproductCommentVO {
	private int uProductCommentId;
	private int uProductId;
	private String memberNickname;
	private String uProductCommentContent;
	private Date uProductCommentCreatedDate;
	private String uProductSecretComment;
	

	public UproductCommentVO() {}


	public UproductCommentVO(int uProductCommentId, int uProductId, String memberNickname,
			String uProductCommentContent, Date uProductCommentCreatedDate, String uProductSecretComment) {
		super();
		this.uProductCommentId = uProductCommentId;
		this.uProductId = uProductId;
		this.memberNickname = memberNickname;
		this.uProductCommentContent = uProductCommentContent;
		this.uProductCommentCreatedDate = uProductCommentCreatedDate;
		this.uProductSecretComment = uProductSecretComment;
	}


	public int getuProductCommentId() {
		return uProductCommentId;
	}


	public void setuProductCommentId(int uProductCommentId) {
		this.uProductCommentId = uProductCommentId;
	}


	public int getuProductId() {
		return uProductId;
	}


	public void setuProductId(int uProductId) {
		this.uProductId = uProductId;
	}


	public String getMemberNickname() {
		return memberNickname;
	}


	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}


	public String getuProductCommentContent() {
		return uProductCommentContent;
	}


	public void setuProductCommentContent(String uProductCommentContent) {
		this.uProductCommentContent = uProductCommentContent;
	}


	public Date getuProductCommentCreatedDate() {
		return uProductCommentCreatedDate;
	}


	public void setuProductCommentCreatedDate(Date uProductCommentCreatedDate) {
		this.uProductCommentCreatedDate = uProductCommentCreatedDate;
	}


	public String getuProductSecretComment() {
		return uProductSecretComment;
	}


	public void setuProductSecretComment(String uProductSecretComment) {
		this.uProductSecretComment = uProductSecretComment;
	}


	@Override
	public String toString() {
		return "UproductCommentVO [uProductCommentId=" + uProductCommentId + ", uProductId=" + uProductId
				+ ", memberNickname=" + memberNickname + ", uProductCommentContent=" + uProductCommentContent
				+ ", uProductCommentCreatedDate=" + uProductCommentCreatedDate + ", uProductSecretComment="
				+ uProductSecretComment + "]";
	}


}
