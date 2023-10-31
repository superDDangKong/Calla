package project.spring.calla.domain;

public class ProductOrderVO {
	private int productOrderId;
	private String memberNickname;
	private int productId;
	private int productAmount;
	private int productPrice;
	
	public ProductOrderVO() {}

	public ProductOrderVO(int productOrderId, String memberNickname, int productId, int productAmount,
			int productPrice) {
		this.productOrderId = productOrderId;
		this.memberNickname = memberNickname;
		this.productId = productId;
		this.productAmount = productAmount;
		this.productPrice = productPrice;
	}

	public int getProductOrderId() {
		return productOrderId;
	}

	public void setProductOrderId(int productOrderId) {
		this.productOrderId = productOrderId;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}

	public int getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}

	@Override
	public String toString() {
		return "ProductOrderVO [productOrderId=" + productOrderId + ", memberNickname=" + memberNickname
				+ ", productId=" + productId + ", productAmount=" + productAmount + ", productPrice=" + productPrice
				+ "]";
	}
	
	
	
}
