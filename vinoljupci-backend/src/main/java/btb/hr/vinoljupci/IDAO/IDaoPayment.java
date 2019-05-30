package btb.hr.vinoljupci.IDAO;

import java.sql.SQLException;

import javax.sql.DataSource;

import btb.hr.vinoljupci.model.Bill;
import btb.hr.vinoljupci.model.Card;

public interface IDaoPayment {
	
	public void setDataSource(DataSource ds);
	
	public Card changeValue(Bill bill) throws SQLException;
}
