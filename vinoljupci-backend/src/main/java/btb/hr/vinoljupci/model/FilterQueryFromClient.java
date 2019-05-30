package btb.hr.vinoljupci.model;

import java.util.ArrayList;
import java.util.Date;

public class FilterQueryFromClient {
	private WineEvent wineEvent;
	private Firm firm;
	private Date startDate;
	private Date endDate;
	private int totalRevenue;
	private int percentage;
	private int firmRevenue;
	
	public WineEvent getWineEvent() {
		return wineEvent;
	}
	public void setWineEvent(WineEvent wineEvent) {
		this.wineEvent = wineEvent;
	}
	public Firm getFirm() {
		return firm;
	}
	public void setFirm(Firm firm) {
		this.firm = firm;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getTotalRevenue() {
		return totalRevenue;
	}
	public void setTotalRevenue(int totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public int getPercentage() {
		return percentage;
	}
	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}
	public int getFirmRevenue() {
		return firmRevenue;
	}
	public void setFirmRevenue(int firmRevenue) {
		this.firmRevenue = firmRevenue;
	}
	
}
