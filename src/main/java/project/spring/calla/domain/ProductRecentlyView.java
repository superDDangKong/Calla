package project.spring.calla.domain;

import java.util.Date;

public class ProductRecentlyView {
	private int productRecentlyViewId;
	private String memberId;
	private int productId;
	private Date productRecentlyViewDate;
	
	public ProductRecentlyView() {}

	public ProductRecentlyView(int productRecentlyViewId, String memberId, int productId,
			Date productRecentlyViewDate) {
		super();
		this.productRecentlyViewId = productRecentlyViewId;
		this.memberId = memberId;
		this.productId = productId;
		this.productRecentlyViewDate = productRecentlyViewDate;
	}

	public int getProductRecentlyViewId() {
		return productRecentlyViewId;
	}

	public void setProductRecentlyViewId(int productRecentlyViewId) {
		this.productRecentlyViewId = productRecentlyViewId;
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

	public Date getProductRecentlyViewDate() {
		return productRecentlyViewDate;
	}

	public void setProductRecentlyViewDate(Date productRecentlyViewDate) {
		this.productRecentlyViewDate = productRecentlyViewDate;
	}

	@Override
	public String toString() {
		return "ProductRecentlyView [productRecentlyViewId=" + productRecentlyViewId + ", memberId=" + memberId
				+ ", productId=" + productId + ", productRecentlyViewDate=" + productRecentlyViewDate + "]";
	}

	
}
