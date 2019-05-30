package btb.hr.vinoljupci.model;

import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.Region;
import btb.hr.vinoljupci.model.Role;

public class User {
	
	private long id_user;
	private Role role;
	private long oib;
	private String name;
	private String surname;
	private String mail;
	private String password;
	private long mobile;
	private Firm firm;
	private Region region;
	
	public long getId_user() {
		return id_user;
	}
	public void setId_user(long id) {
		this.id_user = id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public long getOib() {
		return oib;
	}
	public void setOib(long oib) {
		this.oib = oib;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public long getMobile() {
		return mobile;
	}
	public void setMobile(long mobile) {
		this.mobile = mobile;
	}
	public Firm getFirm() {
		return firm;
	}
	public void setFirm(Firm firm) {
		this.firm = firm;
	}
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	
	
	
	
}
