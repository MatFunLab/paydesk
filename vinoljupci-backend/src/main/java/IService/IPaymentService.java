package IService;

import java.sql.SQLException;

import btb.hr.vinoljupci.model.Bill;
import btb.hr.vinoljupci.model.Card;

public interface IPaymentService {
	
	public Card changeValue(Bill bill) throws SQLException;
}
