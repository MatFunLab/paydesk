package btb.hr.vinoljupci.model;

public class Artikl {

	private long id_artikli;
	private String name;
	private int price;
	private Firm firm;
	
	public long getId_artikli() {
		return id_artikli;
	}
	public void setId_artikli(long id) {
		this.id_artikli = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Firm getFirm() {
		return firm;
	}
	public void setFirm(Firm firm) {
		this.firm = firm;
	}
	
	
	
}
