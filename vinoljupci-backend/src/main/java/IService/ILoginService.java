package IService;

import java.sql.SQLException;

import btb.hr.vinoljupci.model.Credentials;

public interface ILoginService {
	
	public Object checkLogin(Credentials credentials) throws SQLException;
}
