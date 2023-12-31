package project.spring.calla.domain;

import java.util.Date;

public class ProductOrderVO {
	private String memberId;
	private Date productOrderCreatedDate;
	private String memberCard;
	private String memberCardNumber;
	private String memberEmail;
	private String recipientName; // 수령인
	private String memberAddress;
	private int productOrderId;
	private int productId;
	private String productImagePath;
    private String productName;
    private int productPrice;
    private int productAmount;
    private String deliveryStatus; //배송현황
	private MemberVO member;
	
    public ProductOrderVO() {
	}

	public ProductOrderVO(String memberId, Date productOrderCreatedDate, String memberCard, String memberCardNumber,
			String memberEmail, String recipientName, String memberAddress, int productOrderId, int productId,
			String productImagePath, String productName, int productPrice, int productAmount, String deliveryStatus,
			MemberVO member) {
		this.memberId = memberId;
		this.productOrderCreatedDate = productOrderCreatedDate;
		this.memberCard = memberCard;
		this.memberCardNumber = memberCardNumber;
		this.memberEmail = memberEmail;
		this.recipientName = recipientName;
		this.memberAddress = memberAddress;
		this.productOrderId = productOrderId;
		this.productId = productId;
		this.productImagePath = productImagePath;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productAmount = productAmount;
		this.deliveryStatus = deliveryStatus;
		this.member = member;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public Date getProductOrderCreatedDate() {
		return productOrderCreatedDate;
	}

	public void setProductOrderCreatedDate(Date productOrderCreatedDate) {
		this.productOrderCreatedDate = productOrderCreatedDate;
	}

	public String getMemberCard() {
		return memberCard;
	}

	public void setMemberCard(String memberCard) {
		this.memberCard = memberCard;
	}

	public String getMemberCardNumber() {
		return memberCardNumber;
	}

	public void setMemberCardNumber(String memberCardNumber) {
		this.memberCardNumber = memberCardNumber;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getMemberAddress() {
		return memberAddress;
	}

	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}

	public int getProductOrderId() {
		return productOrderId;
	}

	public void setProductOrderId(int productOrderId) {
		this.productOrderId = productOrderId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductImagePath() {
		return productImagePath;
	}

	public void setProductImagePath(String productImagePath) {
		this.productImagePath = productImagePath;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public MemberVO getMember() {
		return member;
	}

	public void setMember(MemberVO member) {
		this.member = member;
	}

	@Override
	public String toString() {
		return "ProductOrderVO [memberId=" + memberId + ", productOrderCreatedDate=" + productOrderCreatedDate
				+ ", memberCard=" + memberCard + ", memberCardNumber=" + memberCardNumber + ", memberEmail="
				+ memberEmail + ", recipientName=" + recipientName + ", memberAddress=" + memberAddress
				+ ", productOrderId=" + productOrderId + ", productId=" + productId + ", productImagePath="
				+ productImagePath + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productAmount=" + productAmount + ", deliveryStatus=" + deliveryStatus + ", member=" + member
				+ "]";
	}
    
	
    
    
}
