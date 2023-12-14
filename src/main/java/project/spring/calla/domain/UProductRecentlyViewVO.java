package project.spring.calla.domain;

import java.util.Date;

public class UProductRecentlyViewVO {
	private int uProductRecentlyViewId;
	private String memberId;
	private int uProductId;
	private Date uProductRecentlyViewDate;
	
	public UProductRecentlyViewVO() {}

	public UProductRecentlyViewVO(int uProductRecentlyViewId, String memberId, int uProductId,
			Date uProductRecentlyViewDate) {
		super();
		this.uProductRecentlyViewId = uProductRecentlyViewId;
		this.memberId = memberId;
		this.uProductId = uProductId;
		this.uProductRecentlyViewDate = uProductRecentlyViewDate;
	}

	public int getuProductRecentlyViewId() {
		return uProductRecentlyViewId;
	}

	public void setuProductRecentlyViewId(int uProductRecentlyViewId) {
		this.uProductRecentlyViewId = uProductRecentlyViewId;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public int getuProductId() {
		return uProductId;
	}

	public void setuProductId(int uProductId) {
		this.uProductId = uProductId;
	}

	public Date getuProductRecentlyViewDate() {
		return uProductRecentlyViewDate;
	}

	public void setuProductRecentlyViewDate(Date uProductRecentlyViewDate) {
		this.uProductRecentlyViewDate = uProductRecentlyViewDate;
	}

	@Override
	public String toString() {
		return "UProductRecentlyView [uProductRecentlyViewId=" + uProductRecentlyViewId + ", memberId=" + memberId
				+ ", uProductId=" + uProductId + ", uProductRecentlyViewDate=" + uProductRecentlyViewDate + "]";
	}
	
	
}
