package project.spring.calla.domain;

import java.util.Date;


public class AllBoardVO {
	private int productId; // 상품 번호
	private String productName; // 상품 이름
	private int productPrice; // 상품 가격
	private int productViews; // 상품 조회수
	private int productLikes; // 상품 좋아요 수
	private Date productCreatedDate; // 상품 등록 날짜
	private String productCategori; // 상품 카테고리
	private String productImagePath; // 상품 이미지 경로
	private String productContent; // 상품 설명
	private int productCommentCount; // 상품 댓글 갯수
	private int memberLevel; // 회원 등급
	private int productAmount;
	private int productLikeId;
	private int productRecentlyViewId;
	
	private String category;
	private int uProductId;
	private String uProductName; // 등록
	private String uProductPrice; // 등록
	private int uProductViews;
	private int uProductLikes;
	private Date uProductCreatedDate;
	private String uProductCategori; // 등록
	private String memberAddress; //등록
	private String uProductContent; //등록
	private String memberNickname;
	private String uProductImagePath; 
	private int uProductCommentCount;
	private int uProductLikeId;
	private int uProductRecentlyViewId;
	
	private int fBoardId;
	private String fBoardTitle;
	private String fBoardContent;
	private Date fBoardCreatedDate;
	private int fBoardViews;
	private int fBoardCommentCount;
	private String fBoardImagePath;
	
	private int qBoardId;
	private String qBoardTitle;
	private String qBoardContent;
	private Date qBoardCreatedDate;
	private int qBoardViews;
	private int qBoardCommentCount;
	private String qBoardImagePath;
	
	public AllBoardVO(int productId, String productName, int productPrice, int productViews, int productLikes,
			Date productCreatedDate, String productCategori, String productImagePath, String productContent,
			int productCommentCount, int memberLevel, int productAmount, int productLikeId, int productRecentlyViewId,
			String category, int uProductId, String uProductName, String uProductPrice, int uProductViews,
			int uProductLikes, Date uProductCreatedDate, String uProductCategori, String memberAddress,
			String uProductContent, String memberNickname, String uProductImagePath, int uProductCommentCount,
			int uProductLikeId, int uProductRecentlyViewId, int fBoardId, String fBoardTitle, String fBoardContent,
			Date fBoardCreatedDate, int fBoardViews, int fBoardCommentCount, String fBoardImagePath, int qBoardId,
			String qBoardTitle, String qBoardContent, Date qBoardCreatedDate, int qBoardViews, int qBoardCommentCount,
			String qBoardImagePath) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productViews = productViews;
		this.productLikes = productLikes;
		this.productCreatedDate = productCreatedDate;
		this.productCategori = productCategori;
		this.productImagePath = productImagePath;
		this.productContent = productContent;
		this.productCommentCount = productCommentCount;
		this.memberLevel = memberLevel;
		this.productAmount = productAmount;
		this.productLikeId = productLikeId;
		this.productRecentlyViewId = productRecentlyViewId;
		this.category = category;
		this.uProductId = uProductId;
		this.uProductName = uProductName;
		this.uProductPrice = uProductPrice;
		this.uProductViews = uProductViews;
		this.uProductLikes = uProductLikes;
		this.uProductCreatedDate = uProductCreatedDate;
		this.uProductCategori = uProductCategori;
		this.memberAddress = memberAddress;
		this.uProductContent = uProductContent;
		this.memberNickname = memberNickname;
		this.uProductImagePath = uProductImagePath;
		this.uProductCommentCount = uProductCommentCount;
		this.uProductLikeId = uProductLikeId;
		this.uProductRecentlyViewId = uProductRecentlyViewId;
		this.fBoardId = fBoardId;
		this.fBoardTitle = fBoardTitle;
		this.fBoardContent = fBoardContent;
		this.fBoardCreatedDate = fBoardCreatedDate;
		this.fBoardViews = fBoardViews;
		this.fBoardCommentCount = fBoardCommentCount;
		this.fBoardImagePath = fBoardImagePath;
		this.qBoardId = qBoardId;
		this.qBoardTitle = qBoardTitle;
		this.qBoardContent = qBoardContent;
		this.qBoardCreatedDate = qBoardCreatedDate;
		this.qBoardViews = qBoardViews;
		this.qBoardCommentCount = qBoardCommentCount;
		this.qBoardImagePath = qBoardImagePath;
	}

	public AllBoardVO() {}

	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
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
	public String getProductImagePath() {
		return productImagePath;
	}
	public void setProductImagePath(String productImagePath) {
		this.productImagePath = productImagePath;
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
	public int getuProductId() {
		return uProductId;
	}
	public void setuProductId(int uProductId) {
		this.uProductId = uProductId;
	}
	public String getuProductName() {
		return uProductName;
	}
	public void setuProductName(String uProductName) {
		this.uProductName = uProductName;
	}
	public String getuProductPrice() {
		return uProductPrice;
	}
	public void setuProductPrice(String uProductPrice) {
		this.uProductPrice = uProductPrice;
	}
	public int getuProductViews() {
		return uProductViews;
	}
	public void setuProductViews(int uProductViews) {
		this.uProductViews = uProductViews;
	}
	public int getuProductLikes() {
		return uProductLikes;
	}
	public void setuProductLikes(int uProductLikes) {
		this.uProductLikes = uProductLikes;
	}
	public Date getuProductCreatedDate() {
		return uProductCreatedDate;
	}
	public void setuProductCreatedDate(Date uProductCreatedDate) {
		this.uProductCreatedDate = uProductCreatedDate;
	}
	public String getuProductCategori() {
		return uProductCategori;
	}
	public void setuProductCategori(String uProductCategori) {
		this.uProductCategori = uProductCategori;
	}
	public String getMemberAddress() {
		return memberAddress;
	}
	public void setMemberAddress(String memberAddress) {
		this.memberAddress = memberAddress;
	}
	public String getuProductContent() {
		return uProductContent;
	}
	public void setuProductContent(String uProductContent) {
		this.uProductContent = uProductContent;
	}
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	public String getuProductImagePath() {
		return uProductImagePath;
	}
	public void setuProductImagePath(String uProductImagePath) {
		this.uProductImagePath = uProductImagePath;
	}
	public int getuProductCommentCount() {
		return uProductCommentCount;
	}
	public void setuProductCommentCount(int uProductCommentCount) {
		this.uProductCommentCount = uProductCommentCount;
	}
	public int getuProductLikeId() {
		return uProductLikeId;
	}
	public void setuProductLikeId(int uProductLikeId) {
		this.uProductLikeId = uProductLikeId;
	}
	public int getuProductRecentlyViewId() {
		return uProductRecentlyViewId;
	}
	public void setuProductRecentlyViewId(int uProductRecentlyViewId) {
		this.uProductRecentlyViewId = uProductRecentlyViewId;
	}
	public int getfBoardId() {
		return fBoardId;
	}
	public void setfBoardId(int fBoardId) {
		this.fBoardId = fBoardId;
	}
	public String getfBoardTitle() {
		return fBoardTitle;
	}
	public void setfBoardTitle(String fBoardTitle) {
		this.fBoardTitle = fBoardTitle;
	}
	public String getfBoardContent() {
		return fBoardContent;
	}
	public void setfBoardContent(String fBoardContent) {
		this.fBoardContent = fBoardContent;
	}
	public Date getfBoardCreatedDate() {
		return fBoardCreatedDate;
	}
	public void setfBoardCreatedDate(Date fBoardCreatedDate) {
		this.fBoardCreatedDate = fBoardCreatedDate;
	}
	public int getfBoardViews() {
		return fBoardViews;
	}
	public void setfBoardViews(int fBoardViews) {
		this.fBoardViews = fBoardViews;
	}
	public int getfBoardCommentCount() {
		return fBoardCommentCount;
	}
	public void setfBoardCommentCount(int fBoardCommentCount) {
		this.fBoardCommentCount = fBoardCommentCount;
	}
	public String getfBoardImagePath() {
		return fBoardImagePath;
	}
	public void setfBoardImagePath(String fBoardImagePath) {
		this.fBoardImagePath = fBoardImagePath;
	}
	public int getqBoardId() {
		return qBoardId;
	}
	public void setqBoardId(int qBoardId) {
		this.qBoardId = qBoardId;
	}
	public String getqBoardTitle() {
		return qBoardTitle;
	}
	public void setqBoardTitle(String qBoardTitle) {
		this.qBoardTitle = qBoardTitle;
	}
	public String getqBoardContent() {
		return qBoardContent;
	}
	public void setqBoardContent(String qBoardContent) {
		this.qBoardContent = qBoardContent;
	}
	public Date getqBoardCreatedDate() {
		return qBoardCreatedDate;
	}
	public void setqBoardCreatedDate(Date qBoardCreatedDate) {
		this.qBoardCreatedDate = qBoardCreatedDate;
	}
	public int getqBoardViews() {
		return qBoardViews;
	}
	public void setqBoardViews(int qBoardViews) {
		this.qBoardViews = qBoardViews;
	}
	public int getqBoardCommentCount() {
		return qBoardCommentCount;
	}
	public void setqBoardCommentCount(int qBoardCommentCount) {
		this.qBoardCommentCount = qBoardCommentCount;
	}
	public String getqBoardImagePath() {
		return qBoardImagePath;
	}
	public void setqBoardImagePath(String qBoardImagePath) {
		this.qBoardImagePath = qBoardImagePath;
	}

	@Override
	public String toString() {
		return "AllBoardVO [productId=" + productId + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", productViews=" + productViews + ", productLikes=" + productLikes + ", productCreatedDate="
				+ productCreatedDate + ", productCategori=" + productCategori + ", productImagePath=" + productImagePath
				+ ", productContent=" + productContent + ", productCommentCount=" + productCommentCount
				+ ", memberLevel=" + memberLevel + ", productAmount=" + productAmount + ", productLikeId="
				+ productLikeId + ", productRecentlyViewId=" + productRecentlyViewId + ", category=" + category
				+ ", uProductId=" + uProductId + ", uProductName=" + uProductName + ", uProductPrice=" + uProductPrice
				+ ", uProductViews=" + uProductViews + ", uProductLikes=" + uProductLikes + ", uProductCreatedDate="
				+ uProductCreatedDate + ", uProductCategori=" + uProductCategori + ", memberAddress=" + memberAddress
				+ ", uProductContent=" + uProductContent + ", memberNickname=" + memberNickname + ", uProductImagePath="
				+ uProductImagePath + ", uProductCommentCount=" + uProductCommentCount + ", uProductLikeId="
				+ uProductLikeId + ", uProductRecentlyViewId=" + uProductRecentlyViewId + ", fBoardId=" + fBoardId
				+ ", fBoardTitle=" + fBoardTitle + ", fBoardContent=" + fBoardContent + ", fBoardCreatedDate="
				+ fBoardCreatedDate + ", fBoardViews=" + fBoardViews + ", fBoardCommentCount=" + fBoardCommentCount
				+ ", fBoardImagePath=" + fBoardImagePath + ", qBoardId=" + qBoardId + ", qBoardTitle=" + qBoardTitle
				+ ", qBoardContent=" + qBoardContent + ", qBoardCreatedDate=" + qBoardCreatedDate + ", qBoardViews="
				+ qBoardViews + ", qBoardCommentCount=" + qBoardCommentCount + ", qBoardImagePath=" + qBoardImagePath
				+ "]";
	}
	
	
}
