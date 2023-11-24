package project.spring.calla.domain;

public class ProductImageVO {
	private int productId;
	private int productImageId;
	private String productImagePath;
	
	public ProductImageVO() {
	}

	public ProductImageVO(int productId, int productImageId, String productImagePath) {
		this.productId = productId;
		this.productImageId = productImageId;
		this.productImagePath = productImagePath;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductImageId() {
		return productImageId;
	}

	public void setProductImageId(int productImageId) {
		this.productImageId = productImageId;
	}

	public String getProductImagePath() {
		return productImagePath;
	}

	public void setProductImagePath(String productImagePath) {
		this.productImagePath = productImagePath;
	}

	@Override
	public String toString() {
		return "ProductImageVO [productId=" + productId + ", productImageId=" + productImageId + ", productImagePath="
				+ productImagePath + "]";
	}

	
	
}
