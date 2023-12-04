package project.spring.calla.domain;

public class MailVO {
	private String memberEmail;
	private String authenticationKey;
	private int authenticationStatus;
	
	public MailVO() {}

	public MailVO(String memberEmail, String authenticationKey, int authenticationStatus) {
		super();
		this.memberEmail = memberEmail;
		this.authenticationKey = authenticationKey; // 인증키
		this.authenticationStatus = authenticationStatus; // 인증여부
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public String getAuthenticationKey() {
		return authenticationKey;
	}

	public void setAuthenticationKey(String authenticationKey) {
		this.authenticationKey = authenticationKey;
	}

	public int getAuthenticationStatus() {
		return authenticationStatus;
	}

	public void setAuthenticationStatus(int authenticationStatus) {
		this.authenticationStatus = authenticationStatus;
	}

	@Override
	public String toString() {
		return "MailVO [memberEmail=" + memberEmail + ", authenticationKey=" + authenticationKey
				+ ", authenticationStatus=" + authenticationStatus + "]";
	}
	
	
	
	
	
}
