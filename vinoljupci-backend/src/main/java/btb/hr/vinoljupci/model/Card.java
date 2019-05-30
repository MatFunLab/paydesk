package btb.hr.vinoljupci.model;

import java.util.Date;

public class Card {
	
	  private long id_card;
	  private int cardNumber;
	  private int cardValue;
	  private Date timestamp;
	  private int activationTimes;
	  
	public long getId_card() {
		return id_card;
	}
	public void setId_card(long id) {
		this.id_card = id;
	}
	public int getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getCardValue() {
		return cardValue;
	}
	public void setCardValue(int cardValue) {
		this.cardValue = cardValue;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public int getActivationTimes() {
		return activationTimes;
	}
	public void setActivationTimes(int activationTimes) {
		this.activationTimes = activationTimes;
	}
	
}
