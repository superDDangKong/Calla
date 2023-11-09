package project.spring.calla.domain;

import java.util.Date;

public class UProductReviewVO {
	
	private String uProductReviewId;
	private String memberNickname;
	private String uProductReviewTitle;
	private Date uProductReviewCreatedDate;
	private String uProductReviewContent;
	private String sellerNickname;
	
	public UProductReviewVO() {}

	public UProductReviewVO(String uProductReviewId, String memberNickname, String uProductReviewTitle,
			Date uProductReviewCreatedDate, String uProductReviewContent, String sellerNickname) {
		super();
		this.uProductReviewId = uProductReviewId;
		this.memberNickname = memberNickname;
		this.uProductReviewTitle = uProductReviewTitle;
		this.uProductReviewCreatedDate = uProductReviewCreatedDate;
		this.uProductReviewContent = uProductReviewContent;
		this.sellerNickname = sellerNickname;
	}

	public String getuProductReviewId() {
		return uProductReviewId;
	}

	public void setuProductReviewId(String uProductReviewId) {
		this.uProductReviewId = uProductReviewId;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getuProductReviewTitle() {
		return uProductReviewTitle;
	}

	public void setuProductReviewTitle(String uProductReviewTitle) {
		this.uProductReviewTitle = uProductReviewTitle;
	}

	public Date getuProductReviewCreatedDate() {
		return uProductReviewCreatedDate;
	}

	public void setuProductReviewCreatedDate(Date uProductReviewCreatedDate) {
		this.uProductReviewCreatedDate = uProductReviewCreatedDate;
	}

	public String getuProductReviewContent() {
		return uProductReviewContent;
	}

	public void setuProductReviewContent(String uProductReviewContent) {
		this.uProductReviewContent = uProductReviewContent;
	}

	public String getSellerNickname() {
		return sellerNickname;
	}

	public void setSellerNickname(String sellerNickname) {
		this.sellerNickname = sellerNickname;
	}

	@Override
	public String toString() {
		return "UProductReviewVO [uProductReviewId=" + uProductReviewId + ", memberNickname=" + memberNickname
				+ ", uProductReviewTitle=" + uProductReviewTitle + ", uProductReviewCreatedDate="
				+ uProductReviewCreatedDate + ", uProductReviewContent=" + uProductReviewContent + ", sellerNickname="
				+ sellerNickname + "]";
	}

	


}
