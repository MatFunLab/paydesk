package btb.hr.vinoljupci.model;

public class BillPart {

	private long id_bill_parts;
	private Artikl artikl;
	private int amount;
	private Bill bill;
	
	public long getId_bill_parts() {
		return id_bill_parts;
	}
	public void setId_bill_parts(long id_bill_parts) {
		this.id_bill_parts = id_bill_parts;
	}
	public Artikl getArtikl() {
		return artikl;
	}
	public void setArtikl(Artikl artikl) {
		this.artikl = artikl;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Bill getBill() {
		return bill;
	}
	public void setBill(Bill bill) {
		this.bill = bill;
	}
	
}
