package btb.hr.vinoljupci.model;

import java.util.ArrayList;
import java.util.Date;

public class WineEvent {
	
	
	private long id_wine_event;
	private String name;
	private Date eventDateStart;
	private Date eventDateEnd;
	
	
	public long getId_wine_event() {
		return id_wine_event;
	}
	public void setId_wine_event(long id) {
		this.id_wine_event = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Date getEventDateStart() {
		return eventDateStart;
	}
	public void setEventDateStart(Date eventDateStart) {
		this.eventDateStart = eventDateStart;
	}
	public Date getEventDateEnd() {
		return eventDateEnd;
	}
	public void setEventDateEnd(Date eventDateEnd) {
		this.eventDateEnd = eventDateEnd;
	} 
	 
}
