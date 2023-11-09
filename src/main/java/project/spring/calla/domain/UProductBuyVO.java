package project.spring.calla.domain;

import java.util.Date;

public class UProductBuyVO {
	
	private int uProductBuyId;
	private String uProductName; // 등록
	private String uProductPrice; // 등록
	private Date uProductCreatedDate;
	private String uProductCategori; // 등록
	private String memberAddress; //등록
	private String uProductContent; //등록
	private String buyerNickname;
	private String uProductImagePath;
	private int uProductId;
	private String sellerNickname;
	
	public UProductBuyVO() {}

	public UProductBuyVO(int uProductBuyId, String uProductName, String uProductPrice, Date uProductCreatedDate,
			String uProductCategori, String memberAddress, String uProductContent, String buyerNickname,
			String uProductImagePath, int uProductId, String sellerNickname) {
		super();
		this.uProductBuyId = uProductBuyId;
		this.uProductName = uProductName;
		this.uProductPrice = uProductPrice;
		this.uProductCreatedDate = uProductCreatedDate;
		this.uProductCategori = uProductCategori;
		this.memberAddress = memberAddress;
		this.uProductContent = uProductContent;
		this.buyerNickname = buyerNickname;
		this.uProductImagePath = uProductImagePath;
		this.uProductId = uProductId;
		this.sellerNickname = sellerNickname;
	}

	public int getuProductBuyId() {
		return uProductBuyId;
	}

	public void setuProductBuyId(int uProductBuyId) {
		this.uProductBuyId = uProductBuyId;
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

	public String getBuyerNickname() {
		return buyerNickname;
	}

	public void setBuyerNickname(String buyerNickname) {
		this.buyerNickname = buyerNickname;
	}

	public String getuProductImagePath() {
		return uProductImagePath;
	}

	public void setuProductImagePath(String uProductImagePath) {
		this.uProductImagePath = uProductImagePath;
	}

	public int getuProductId() {
		return uProductId;
	}

	public void setuProductId(int uProductId) {
		this.uProductId = uProductId;
	}

	public String getSellerNickname() {
		return sellerNickname;
	}

	public void setSellerNickname(String sellerNickname) {
		this.sellerNickname = sellerNickname;
	}

	@Override
	public String toString() {
		return "UProductBuyVO [uProductBuyId=" + uProductBuyId + ", uProductName=" + uProductName + ", uProductPrice="
				+ uProductPrice + ", uProductCreatedDate=" + uProductCreatedDate + ", uProductCategori="
				+ uProductCategori + ", memberAddress=" + memberAddress + ", uProductContent=" + uProductContent
				+ ", buyerNickname=" + buyerNickname + ", uProductImagePath=" + uProductImagePath + ", uProductId="
				+ uProductId + ", sellerNickname=" + sellerNickname + "]";
	}

	


	
	

}
