package project.spring.calla.domain;

import java.util.Date;
import java.util.List;

public class UProductVO {
	private int uProductId;
	private String uProductName; // 등록
	private int uProductPrice; // 등록
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
	private String category;
	private String uProductStatement;
	
	public UProductVO() {}

	public UProductVO(int uProductId, String uProductName, int uProductPrice, int uProductViews, int uProductLikes,
			Date uProductCreatedDate, String uProductCategori, String memberAddress, String uProductContent,
			String memberNickname, String uProductImagePath, int uProductCommentCount, int uProductLikeId,
			int uProductRecentlyViewId, String category, String uProductStatement) {
		super();
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
		this.category = category;
		this.uProductStatement = uProductStatement;
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

	public int getuProductPrice() {
		return uProductPrice;
	}

	public void setuProductPrice(int uProductPrice) {
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

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getuProductStatement() {
		return uProductStatement;
	}

	public void setuProductStatement(String uProductStatement) {
		this.uProductStatement = uProductStatement;
	}

	@Override
	public String toString() {
		return "UProductVO [uProductId=" + uProductId + ", uProductName=" + uProductName + ", uProductPrice="
				+ uProductPrice + ", uProductViews=" + uProductViews + ", uProductLikes=" + uProductLikes
				+ ", uProductCreatedDate=" + uProductCreatedDate + ", uProductCategori=" + uProductCategori
				+ ", memberAddress=" + memberAddress + ", uProductContent=" + uProductContent + ", memberNickname="
				+ memberNickname + ", uProductImagePath=" + uProductImagePath + ", uProductCommentCount="
				+ uProductCommentCount + ", uProductLikeId=" + uProductLikeId + ", uProductRecentlyViewId="
				+ uProductRecentlyViewId + ", category=" + category + ", uProductStatement=" + uProductStatement + "]";
	}

	

	
}
