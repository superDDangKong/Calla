package project.spring.calla.domain;

public class UProductMannerVO {
	
	private int uProductMannerId;
	private int uProductId;
	private String memberNickname;
	private String sellerNickname;
	
	public UProductMannerVO() {}

	public UProductMannerVO(int uProductMannerId, int uProductId, String memberNickname, String sellerNickname) {
		super();
		this.uProductMannerId = uProductMannerId;
		this.uProductId = uProductId;
		this.memberNickname = memberNickname;
		this.sellerNickname = sellerNickname;
	}

	public int getuProductMannerId() {
		return uProductMannerId;
	}

	public void setuProductMannerId(int uProductMannerId) {
		this.uProductMannerId = uProductMannerId;
	}

	public int getuProductId() {
		return uProductId;
	}

	public void setuProductId(int uProductId) {
		this.uProductId = uProductId;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public String getSellerNickname() {
		return sellerNickname;
	}

	public void setSellerNickname(String sellerNickname) {
		this.sellerNickname = sellerNickname;
	}

	@Override
	public String toString() {
		return "UProductMannerVO [uProductMannerId=" + uProductMannerId + ", uProductId=" + uProductId
				+ ", memberNickname=" + memberNickname + ", sellerNickname=" + sellerNickname + "]";
	}

	
	

}
