package project.spring.calla.domain;

import java.util.Date;

public class UProductSellVO {

	private int uProductSellId;
	private String uProductName; // 등록
	private int uProductPrice; // 등록
	private Date uProductCreatedDate;
	private String uProductCategori; // 등록
	private String memberAddress; // 등록
	private String uProductContent; // 등록
	private String sellerNickname;
	private String buyerNickname;
	private String uProductImagePath;
	private int uProductId;

	public UProductSellVO() {}

	public UProductSellVO(int uProductSellId, String uProductName, int uProductPrice, Date uProductCreatedDate,
			String uProductCategori, String memberAddress, String uProductContent, String sellerNickname,
			String buyerNickname, String uProductImagePath, int uProductId) {
		super();
		this.uProductSellId = uProductSellId;
		this.uProductName = uProductName;
		this.uProductPrice = uProductPrice;
		this.uProductCreatedDate = uProductCreatedDate;
		this.uProductCategori = uProductCategori;
		this.memberAddress = memberAddress;
		this.uProductContent = uProductContent;
		this.sellerNickname = sellerNickname;
		this.buyerNickname = buyerNickname;
		this.uProductImagePath = uProductImagePath;
		this.uProductId = uProductId;
	}

	public int getuProductSellId() {
		return uProductSellId;
	}

	public void setuProductSellId(int uProductSellId) {
		this.uProductSellId = uProductSellId;
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

	public String getSellerNickname() {
		return sellerNickname;
	}

	public void setSellerNickname(String sellerNickname) {
		this.sellerNickname = sellerNickname;
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

	@Override
	public String toString() {
		return "UProductSellVO [uProductSellId=" + uProductSellId + ", uProductName=" + uProductName
				+ ", uProductPrice=" + uProductPrice + ", uProductCreatedDate=" + uProductCreatedDate
				+ ", uProductCategori=" + uProductCategori + ", memberAddress=" + memberAddress + ", uProductContent="
				+ uProductContent + ", sellerNickname=" + sellerNickname + ", buyerNickname=" + buyerNickname
				+ ", uProductImagePath=" + uProductImagePath + ", uProductId=" + uProductId + "]";
	}

	
	

	


}
