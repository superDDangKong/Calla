package project.spring.calla.domain;

public class UProductLikeVO {

	int uProductLikeId;
	int uProductId;
	String memberNickname;
	public UProductLikeVO() {
	}
	public UProductLikeVO(int uProductLikeId, int uProductId, String memberNickname) {
		super();
		this.uProductLikeId = uProductLikeId;
		this.uProductId = uProductId;
		this.memberNickname = memberNickname;
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
	public String getMemberNickname() {
		return memberNickname;
	}
	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}
	@Override
	public String toString() {
		return "UProductLikeVO [uProductLikeId=" + uProductLikeId + ", uProductId=" + uProductId + ", memberNickname="
				+ memberNickname + "]";
	}
	
	
}
