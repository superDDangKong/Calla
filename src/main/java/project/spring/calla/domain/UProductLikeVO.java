package project.spring.calla.domain;

public class UProductLikeVO {

	int uProductLikeId;
	int uProductId;
	String memberId;
	
	
	public UProductLikeVO() {}


	public UProductLikeVO(int uProductLikeId, int uProductId, String memberId) {
		super();
		this.uProductLikeId = uProductLikeId;
		this.uProductId = uProductId;
		this.memberId = memberId;
	}


	public int getuProductLikeId() {
		return uProductLikeId;
	}


	public void setuProductLikeId(int uProductLikeId) {
		this.uProductLikeId = uProductLikeId;
	}


	public int getuProductId() {
		return uProductId;
	}


	public void setuProductId(int uProductId) {
		this.uProductId = uProductId;
	}


	public String getMemberId() {
		return memberId;
	}


	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}


	@Override
	public String toString() {
		return "UProductLikeVO [uProductLikeId=" + uProductLikeId + ", uProductId=" + uProductId + ", memberId="
				+ memberId + "]";
	}
	
	
	
	
	
}
