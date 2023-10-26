package project.spring.calla.domain;

import java.util.Date;

public class ProductReplyVO {
	private int productReplyId;
	private int productCommentId;
	private String memberNickName;
	private String productReplyContent;
	private Date productReplyCreatedDate;
	
	public ProductReplyVO() {}

	public ProductReplyVO(int productReplyId, int productCommentId, String memberNickName, String productReplyContent,
			Date productReplyCreatedDate) {
		this.productReplyId = productReplyId;
		this.productCommentId = productCommentId;
		this.memberNickName = memberNickName;
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

	public String getMemberNickName() {
		return memberNickName;
	}

	public void setMemberNickName(String memberNickName) {
		this.memberNickName = memberNickName;
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
				+ ", memberNickName=" + memberNickName + ", productReplyContent=" + productReplyContent
				+ ", productReplyCreatedDate=" + productReplyCreatedDate + "]";
	}
	
	
	
	
}
