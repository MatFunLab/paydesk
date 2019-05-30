package btb.hr.vinoljupci.model;

public class Firm {

	private long id_firm;
	private String name;
	private String address;
	private long oib;
	private Unit unit_id;
	private int percentage;

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public long getId_firm() {
		return id_firm;
	}

	public void setId_firm(long id) {
		this.id_firm = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getOib() {
		return oib;
	}

	public void setOib(long oib) {
		this.oib = oib;
	}

	public Unit getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(Unit unit_id) {
		this.unit_id = unit_id;
	}

}
