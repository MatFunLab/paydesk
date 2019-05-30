package btb.hr.vinoljupci.IDAO;

import javax.sql.DataSource;

import btb.hr.vinoljupci.model.Credentials;

public interface IDaoLogin {
	
	public void setDataSource(DataSource ds);
	public Object login(Credentials credentials);
}
