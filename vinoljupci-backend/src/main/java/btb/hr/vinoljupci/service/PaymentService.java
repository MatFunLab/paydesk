package btb.hr.vinoljupci.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import IService.IPaymentService;
import btb.hr.vinoljupci.DAO.DaoArtikl;
import btb.hr.vinoljupci.DAO.DaoPay;
import btb.hr.vinoljupci.model.Bill;
import btb.hr.vinoljupci.model.Card;

@Service("iPaymentService")
public class PaymentService implements IPaymentService{

	@Autowired 
	DaoPay daoPay;

	@Override
	public Card changeValue(Bill bill) throws SQLException {
		
		bill.getCard();
		return daoPay.changeValue(bill);
	}
	
	
}
