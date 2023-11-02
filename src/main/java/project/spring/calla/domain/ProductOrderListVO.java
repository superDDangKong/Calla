package project.spring.calla.domain;

public class ProductOrderListVO {
	private int productOrderListId;
	private String memberId;
	private int productId;
	private int productAmount;
	
	public ProductOrderListVO() {}

	public ProductOrderListVO(int productOrderListId, String memberId, int productId, int productAmount) {
		this.productOrderListId = productOrderListId;
		this.memberId = memberId;
		this.productId = productId;
		this.productAmount = productAmount;
	}

	public int getProductOrderListId() {
		return productOrderListId;
	}

	public void setProductOrderListId(int productOrderListId) {
		this.productOrderListId = productOrderListId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
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

	@Override
	public String toString() {
		return "ProductOrderListVO [productOrderListId=" + productOrderListId + ", memberId=" + memberId
				+ ", productId=" + productId + ", productAmount=" + productAmount + "]";
	}
	
	
	
	
	
}
