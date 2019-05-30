package btb.hr.vinoljupci.model;

import java.util.Date;

public class FilterQueryToClient {
	
	private String wineEventName;
	private String firmName;
	private int percentage;
	private Date timestamp;
	private int billSum;
	private String userMail;
	
	public String getUserMail() {
		return userMail;
	}
	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}
	
	public String getWineEventName() {
		return wineEventName;
	}
	public void setWineEventName(String wineEventName) {
		this.wineEventName = wineEventName;
	}
	public String getFirmName() {
		return firmName;
	}
	public void setFirmName(String firmName) {
		this.firmName = firmName;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public int getBillSum() {
		return billSum;
	}
	public void setBillSum(int billSum) {
		this.billSum = billSum;
	}

}
