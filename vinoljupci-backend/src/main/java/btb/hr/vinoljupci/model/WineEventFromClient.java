package btb.hr.vinoljupci.model;

import java.util.ArrayList;
import java.util.Date;

public class WineEventFromClient {
	private long id_wine_event;
	private String name;
	private Date event_date_start;
	private Date event_date_end;
	private ArrayList<Firm> firms;
	
	public long getId_wine_event() {
		return id_wine_event;
	}
	public void setId_wine_event(long id_wine_event) {
		this.id_wine_event = id_wine_event;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getEvent_date_start() {
		return event_date_start;
	}
	public void setEvent_date_start(Date event_date_start) {
		this.event_date_start = event_date_start;
	}
	public Date getEvent_date_end() {
		return event_date_end;
	}
	public void setEvent_date_end(Date event_date_end) {
		this.event_date_end = event_date_end;
	}
	public ArrayList<Firm> getFirms() {
		return firms;
	}
	public void setFirms(ArrayList<Firm> firms) {
		this.firms = firms;
	}
	
	
}
