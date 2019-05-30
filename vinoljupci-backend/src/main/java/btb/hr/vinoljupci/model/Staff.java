package btb.hr.vinoljupci.model;

public class Staff {
	
	private long id_staff;
	private long oib;
	private String name;
	private String surname;
	private String mail;
	private String password;
	private long mobile;
	private Shift shift;
	private Role role_id;
	
	
	
	public Role getRole_id() {
		return role_id;
	}
	public void setRole_id(Role role_id) {
		this.role_id = role_id;
	}
	public long getId_staff() {
		return id_staff;
	}
	public void setId_staff(long id) {
		this.id_staff = id;
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
	public Shift getShift() {
		return shift;
	}
	public void setShift(Shift shift) {
		this.shift = shift;
	}
	
	
	

}
