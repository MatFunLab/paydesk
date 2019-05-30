package btb.hr.vinoljupci.model;

import java.util.ArrayList;

public class UnitFromClient {
	private long id;
	private String name;
	private ArrayList<Firm> firm;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Firm> getFirm() {
		return firm;
	}
	public void setFirm(ArrayList<Firm> firm) {
		this.firm = firm;
	}
}
