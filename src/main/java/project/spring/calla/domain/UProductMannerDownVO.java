package project.spring.calla.domain;

public class UProductMannerDownVO {
	
	private int uProductMannerDownId;
	private int uProductId;
	private String memberNickname;
	private String sellerNickname;
	
	public UProductMannerDownVO() {}

	public UProductMannerDownVO(int uProductMannerDownId, int uProductId, String memberNickname,
			String sellerNickname) {
		super();
		this.uProductMannerDownId = uProductMannerDownId;
		this.uProductId = uProductId;
		this.memberNickname = memberNickname;
		this.sellerNickname = sellerNickname;
	}

	public int getuProductMannerDownId() {
		return uProductMannerDownId;
	}

	public void setuProductMannerDownId(int uProductMannerDownId) {
		this.uProductMannerDownId = uProductMannerDownId;
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
		return "UProductMannerDownVO [uProductMannerDownId=" + uProductMannerDownId + ", uProductId=" + uProductId
				+ ", memberNickname=" + memberNickname + ", sellerNickname=" + sellerNickname + "]";
	}
		
	
	

}
