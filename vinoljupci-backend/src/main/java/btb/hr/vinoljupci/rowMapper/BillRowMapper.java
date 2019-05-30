package btb.hr.vinoljupci.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import btb.hr.vinoljupci.model.Artikl;
import btb.hr.vinoljupci.model.Bill;
import btb.hr.vinoljupci.model.BillPart;
import btb.hr.vinoljupci.model.Card;
import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.WineEvent;

public class BillRowMapper implements RowMapper<Bill> {

			public Bill mapRow(ResultSet rs, int rowNum) throws SQLException {
//				
//				//select * from bill b, card c, event e, firm f, wine_event w where b.card = c.id and b.event= e.id and b.firm = f.id and b.wine_event = b.w;
//				
				Bill bill = new Bill();
				
					Card card = new Card();
						card.setId_card(rs.getLong("c.id_card")); //in mysql table cards name of columns
						card.setCardNumber(rs.getInt("c.card_number"));
						card.setActivationTimes(rs.getInt("c.activation_times"));
						card.setCardValue(rs.getInt("c.card_value"));
						card.setTimestamp(rs.getDate("c.timestamp"));
						
					
					WineEvent wineEvent = new WineEvent();
						wineEvent.setId_wine_event(rs.getLong("w.id_wine_event"));
						wineEvent.setName(rs.getString("w.name"));
						wineEvent.setEventDateStart(rs.getDate("w.event_date_start"));
						wineEvent.setEventDateEnd(rs.getDate("w.event_date_end"));
						
					
						
					bill.setId_bill(rs.getLong("b.id_bill"));
					bill.setCard(card);
					bill.setTimestamp(rs.getDate("b.timestamp"));
					bill.setWineEvent(wineEvent);
					bill.setBillSum(rs.getInt("b.bill_sum"));
					
				return bill;
			}


}





