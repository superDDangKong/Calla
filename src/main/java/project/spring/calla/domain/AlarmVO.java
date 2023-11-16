package project.spring.calla.domain;

import java.util.Date;

public class AlarmVO {
	int alarmId;
	String memberId;
	String code;
	Boolean check;
	Date createdDate;
	String prefix;
	
	public AlarmVO(int alarmId, String memberId, String code, Boolean check, Date createdDate, String prefix) {
		super();
		this.alarmId = alarmId;
		this.memberId = memberId;
		this.code = code;
		this.check = check;
		this.createdDate = createdDate;
		this.prefix = prefix;
	}
	public AlarmVO() {
		super();
	}
	public int getAlarmId() {
		return alarmId;
	}
	public void setAlarmId(int alarmId) {
		this.alarmId = alarmId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Boolean getCheck() {
		return check;
	}
	public void setCheck(Boolean check) {
		this.check = check;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	@Override
	public String toString() {
		return "AlarmVO [alarmId=" + alarmId + ", memberId=" + memberId + ", code=" + code + ", check=" + check
				+ ", createdDate=" + createdDate + ", prefix=" + prefix + "]";
	}
	
	
}
