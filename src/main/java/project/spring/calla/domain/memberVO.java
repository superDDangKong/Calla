package project.spring.calla.domain;

import java.util.Date;

public class memberVO {
	
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberEmail;
	private String memberPhone;
	private Date memberJoinDate;
	private String memeberInterest;
	private String memberAdress;
	private String memberNickname;
	private int memberLevel;
	
	public memberVO() {}

	public memberVO(String memberId, String memberPw, String memberName, String memberEmail, String memberPhone,
			Date memberJoinDate, String memeberInterest, String memberAdress, String memberNickname, int memberLevel) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		this.memberEmail = memberEmail;
		this.memberPhone = memberPhone;
		this.memberJoinDate = memberJoinDate;
		this.memeberInterest = memeberInterest;
		this.memberAdress = memberAdress;
		this.memberNickname = memberNickname;
		this.memberLevel = memberLevel;
	}

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getMemberPw() {
		return memberPw;
	}

	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getMemberPhone() {
		return memberPhone;
	}

	public void setMemberPhone(String memberPhone) {
		this.memberPhone = memberPhone;
	}

	public Date getMemberJoinDate() {
		return memberJoinDate;
	}

	public void setMemberJoinDate(Date memberJoinDate) {
		this.memberJoinDate = memberJoinDate;
	}

	public String getMemeberInterest() {
		return memeberInterest;
	}

	public void setMemeberInterest(String memeberInterest) {
		this.memeberInterest = memeberInterest;
	}

	public String getMemberAdress() {
		return memberAdress;
	}

	public void setMemberAdress(String memberAdress) {
		this.memberAdress = memberAdress;
	}

	public String getMemberNickname() {
		return memberNickname;
	}

	public void setMemberNickname(String memberNickname) {
		this.memberNickname = memberNickname;
	}

	public int getMemberLevel() {
		return memberLevel;
	}

	public void setMemberLevel(int memberLevel) {
		this.memberLevel = memberLevel;
	}

	@Override
	public String toString() {
		return "memberVO [memberId=" + memberId + ", memberPw=" + memberPw + ", memberName=" + memberName
				+ ", memberEmail=" + memberEmail + ", memberPhone=" + memberPhone + ", memberJoinDate=" + memberJoinDate
				+ ", memeberInterest=" + memeberInterest + ", memberAdress=" + memberAdress + ", memberNickname="
				+ memberNickname + ", memberLevel=" + memberLevel + "]";
	}

	


}