package project.spring.calla.domain;

import java.util.Date;

public class ProductReplyVO {
	private int productReplyId;
	private int productCommentId;
	private String memberNickname;
	private String productReplyContent;
	private Date productReplyCreatedDate;
	
	public ProductReplyVO() {}

	public ProductReplyVO(int productReplyId, int productCommentId, String memberNickname, String productReplyContent,
			Date productReplyCreatedDate) {

		this.productReplyId = productReplyId;
		this.productCommentId = productCommentId;
		this.memberNickname = memberNickname;
		this.productReplyContent = productReplyContent;
		this.productReplyCreatedDate = productReplyCreatedDate;
	}

	public int getProductReplyId() {
		return productReplyId;
	}

	public void setProductReplyId(int productReplyId) {
		this.productReplyId = productReplyId;
	}

	public int getProductCommentId() {
		return productCommentId;
	}

	public void setProductCommentId(int productCommentId) {
		this.productCommentId = productCommentId;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getProductReplyContent() {
		return productReplyContent;
	}

	public void setProductReplyContent(String productReplyContent) {
		this.productReplyContent = productReplyContent;
	}

	public Date getProductReplyCreatedDate() {
		return productReplyCreatedDate;
	}

	public void setProductReplyCreatedDate(Date productReplyCreatedDate) {
		this.productReplyCreatedDate = productReplyCreatedDate;
	}

	@Override
	public String toString() {
		return "ProductReplyVO [productReplyId=" + productReplyId + ", productCommentId=" + productCommentId
				+ ", memberNickname=" + memberNickname + ", productReplyContent=" + productReplyContent
				+ ", productReplyCreatedDate=" + productReplyCreatedDate + "]";
	}

	
	
	
}
