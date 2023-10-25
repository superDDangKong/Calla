package project.spring.calla.domain;

import java.util.Date;

public class ProductReplyVO {
	private int productReplyId;
	private int productCommentId;
	private String memberNickName;
	private String prodcutReplyContent;
	private Date productReplyCreatedDate;
	
	public ProductReplyVO() {}

	public ProductReplyVO(int productReplyId, int productCommentId, String memberNickName, String prodcutReplyContent,
			Date productReplyCreatedDate) {
		this.productReplyId = productReplyId;
		this.productCommentId = productCommentId;
		this.memberNickName = memberNickName;
		this.prodcutReplyContent = prodcutReplyContent;
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

	public String getProdcutReplyContent() {
		return prodcutReplyContent;
	}

	public void setProdcutReplyContent(String prodcutReplyContent) {
		this.prodcutReplyContent = prodcutReplyContent;
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
				+ ", memberNickName=" + memberNickName + ", prodcutReplyContent=" + prodcutReplyContent
				+ ", productReplyCreatedDate=" + productReplyCreatedDate + "]";
	}
	
	
	
	
}
