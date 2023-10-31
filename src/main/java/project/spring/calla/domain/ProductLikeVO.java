package project.spring.calla.domain;

public class ProductLikeVO {

	int productLikeId;
	int productId;
	String memberId;
	
	public ProductLikeVO() {
	}

	public ProductLikeVO(int productLikeId, int productId, String memberId) {
		this.productLikeId = productLikeId;
		this.productId = productId;
		this.memberId = memberId;
	}

	public int getProductLikeId() {
		return productLikeId;
	}

	public void setProductLikeId(int productLikeId) {
		this.productLikeId = productLikeId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	@Override
	public String toString() {
		return "ProductLikeVO [productLikeId=" + productLikeId + ", productId=" + productId + ", memberId=" + memberId
				+ "]";
	}
	
	
	
	
	
	
	
	
	
	
	
}