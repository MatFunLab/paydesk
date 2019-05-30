package btb.hr.vinoljupci.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import btb.hr.vinoljupci.model.Artikl;
import btb.hr.vinoljupci.model.Bill;
import btb.hr.vinoljupci.model.BillPart;

import btb.hr.vinoljupci.model.Card;
import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.WineEvent;

public class BillPartRowMapper implements RowMapper<BillPart>{

	public BillPart mapRow(ResultSet rs, int rowNum) throws SQLException {
		BillPart billPart = new BillPart();
			Artikl artikl = new Artikl();
				Firm firm = new Firm();
					firm.setId_firm(rs.getLong("f.id"));
					firm.setName(rs.getString("f.name"));
					firm.setOib(rs.getLong("f.oib"));
					firm.setAddress(rs.getString("f.address"));
		
				artikl.setId_artikli(rs.getLong("a.id_artikli"));
				artikl.setName(rs.getString("a.name"));
				artikl.setPrice(rs.getInt("a.price"));
				artikl.setFirm(firm);
		Bill bill = new Bill();
		Card card = new Card();
		card.setId_card(rs.getLong("c.id_card")); //in mysql table cards name of columns
		card.setCardNumber(rs.getInt("c.card_number"));
		card.setActivationTimes(rs.getInt("c.activation_times"));
		card.setCardValue(rs.getInt("c.card_value"));
		card.setTimestamp(rs.getDate("c.date_card"));
		
	
	WineEvent wineEvent = new WineEvent();
		wineEvent.setId_wine_event(rs.getLong("id_wine_event"));
		wineEvent.setName(rs.getString("w.name"));
		wineEvent.setEventDateStart(rs.getDate("w.event_date_start"));
		wineEvent.setEventDateEnd(rs.getDate("w.event_date_end"));

	bill.setId_bill(rs.getLong("bill.id_bill"));
	bill.setCard(card);
	bill.setTimestamp(rs.getDate("bill.timestamp"));
	bill.setWineEvent(wineEvent);
	bill.setBillSum(rs.getInt("bill.bill_sum"));
	
			billPart.setId_bill_parts(rs.getLong("b.id_bill_parts"));
			billPart.setArtikl(artikl);
			billPart.setAmount(rs.getInt("b.amount"));
			billPart.setBill(bill);
		
		return billPart;
	}

}
