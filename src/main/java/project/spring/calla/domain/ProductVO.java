package project.spring.calla.domain;

import java.util.Date;
import java.util.List;

public class ProductVO {
	private int productId; // 占쏙옙품 占쏙옙호
	private String productName; // 占쏙옙품 占싱몌옙
	private int productPrice; // 占쏙옙품 占쏙옙占쏙옙
	private int productViews; // 占쏙옙품 占쏙옙회占쏙옙
	private int productLikes; // 占쏙옙품 占쏙옙占싣울옙 占쏙옙
	private Date productCreatedDate; // 占쏙옙품 占쏙옙占� 占쏙옙짜
	private String productCategori; // 占쏙옙품 카占쌓곤옙占쏙옙
	private String productContent; // 占쏙옙품 占쏙옙占쏙옙
	private int productCommentCount; // 占쏙옙품 占쏙옙占� 占쏙옙占쏙옙
	private String memberNickname; // 회占쏙옙 占싻놂옙占쏙옙
	private int memberLevel; // 회占쏙옙 占쏙옙占�
	private int productAmount;
	private int productLikeId;
	private int productRecentlyViewId;
	private int productRatedCount; // 占쏙옙품 占쏙옙占쏙옙 占쏙옙占쏙옙
	private String productImagePath;
	
	public ProductVO() {}

	public ProductVO(int productId, String productName, int productPrice, int productViews, int productLikes,
			Date productCreatedDate, String productCategori, String productContent, int productCommentCount,
			String memberNickname, int memberLevel, int productAmount, int productLikeId, int productRecentlyViewId,
			int productRatedCount, String productImagePath) {
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productViews = productViews;
		this.productLikes = productLikes;
		this.productCreatedDate = productCreatedDate;
		this.productCategori = productCategori;
		this.productContent = productContent;
		this.productCommentCount = productCommentCount;
		this.memberNickname = memberNickname;
		this.memberLevel = memberLevel;
		this.productAmount = productAmount;
		this.productLikeId = productLikeId;
		this.productRecentlyViewId = productRecentlyViewId;
		this.productRatedCount = productRatedCount;
		this.productImagePath = productImagePath;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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

	public int getProductViews() {
		return productViews;
	}

	public void setProductViews(int productViews) {
		this.productViews = productViews;
	}

	public int getProductLikes() {
		return productLikes;
	}

	public void setProductLikes(int productLikes) {
		this.productLikes = productLikes;
	}

	public Date getProductCreatedDate() {
		return productCreatedDate;
	}

	public void setProductCreatedDate(Date productCreatedDate) {
		this.productCreatedDate = productCreatedDate;
	}

	public String getProductCategori() {
		return productCategori;
	}

	public void setProductCategori(String productCategori) {
		this.productCategori = productCategori;
	}

	public String getProductContent() {
		return productContent;
	}

	public void setProductContent(String productContent) {
		this.productContent = productContent;
	}

	public int getProductCommentCount() {
		return productCommentCount;
	}

	public void setProductCommentCount(int productCommentCount) {
		this.productCommentCount = productCommentCount;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public int getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}

	public int getProductLikeId() {
		return productLikeId;
	}

	public void setProductLikeId(int productLikeId) {
		this.productLikeId = productLikeId;
	}

	public int getProductRecentlyViewId() {
		return productRecentlyViewId;
	}

	public void setProductRecentlyViewId(int productRecentlyViewId) {
		this.productRecentlyViewId = productRecentlyViewId;
	}

	public int getProductRatedCount() {
		return productRatedCount;
	}

	public void setProductRatedCount(int productRatedCount) {
		this.productRatedCount = productRatedCount;
	}

	public String getProductImagePath() {
		return productImagePath;
	}

	public void setProductImagePath(String productImagePath) {
		this.productImagePath = productImagePath;
	}

	@Override
	public String toString() {
		return "ProductVO [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productViews=" + productViews + ", productLikes=" + productLikes + ", productCreatedDate="
				+ productCreatedDate + ", productCategori=" + productCategori + ", productContent=" + productContent
				+ ", productCommentCount=" + productCommentCount + ", memberNickname=" + memberNickname
				+ ", memberLevel=" + memberLevel + ", productAmount=" + productAmount + ", productLikeId="
				+ productLikeId + ", productRecentlyViewId=" + productRecentlyViewId + ", productRatedCount="
				+ productRatedCount + ", productImagePath=" + productImagePath + "]";
	}

	
	
}
