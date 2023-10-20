package project.spring.calla.domain;

import java.util.Date;
import java.util.List;

public class UproductVO {
	private int uProductId;
	private String uProductName; // 등록
	private String uProductPrice; // 등록
	private int uProductViews;
	private int uProductLikes;
	private Date uProductCreatedDate;
	private String uProductCategori; // 등록
	private String memberAddress; //등록
	private String uProductContent; //등록
	
	private List<UImageVO> imageList;
	


	public UproductVO() {}

	public UproductVO(int uProductId, String uProductName, String uProductPrice, int uProductViews, int uProductLikes,
			Date uProductCreatedDate, String uProductCategori, String memberAddress, String uProductContent) {
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

	public List<UImageVO> getImageList() {
		return imageList;
	}

	public void setImageList(List<UImageVO> imageList) {
		this.imageList = imageList;
	}

	@Override
	public String toString() {
		return "UBoardVO [uProductId=" + uProductId + ", uProductName=" + uProductName + ", uProductPrice="
				+ uProductPrice + ", uProductViews=" + uProductViews + ", uProductLikes=" + uProductLikes
				+ ", uProductCreatedDate=" + uProductCreatedDate + ", uProductCategori=" + uProductCategori
				+ ", memberAddress=" + memberAddress + ", uProductContent=" + uProductContent + ", imageList="
				+ imageList + "]";
	}
	
	
	


	


	
}
