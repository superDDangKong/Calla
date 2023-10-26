package project.spring.calla.domain;

public class ProductLikeVO {

	int productLikeId;
	int productId;
	String memberNickname;
	
	public ProductLikeVO() {
	}
	
	public ProductLikeVO(int productLikeId, int productId, String memberNickname) {
		this.productLikeId = productLikeId;
		this.productId = productId;
		this.memberNickname = memberNickname;
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
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	
	@Override
	public String toString() {
		return "ProductLikeVO [productLikeId=" + productLikeId + ", productId=" + productId + ", memberNickname="
				+ memberNickname + "]";
	}
	
	
	
}
