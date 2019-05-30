package btb.hr.vinoljupci.model;

import java.util.ArrayList;
import java.util.Date;

public class Bill {
	
	private long id_bill;
	private Card card;
	private Date timestamp;
	private WineEvent wineEvent;
	private int billSum;
	
	

	public long getId_bill() {
		return id_bill;
	}
	public void setId_bill(long id_bill) {
		this.id_bill = id_bill;
	}
	
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public WineEvent getWineEvent() {
		return wineEvent;
	}
	public void setWineEvent(WineEvent wineEvent) {
		this.wineEvent = wineEvent;
	}
	public int getBillSum() {
		return billSum;
	}
	public void setBillSum(int billSum) {
		this.billSum = billSum;
	}
	
}
