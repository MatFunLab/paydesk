package btb.hr.vinoljupci.DAO;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import btb.hr.vinoljupci.IDAO.IDaoPayment;
import btb.hr.vinoljupci.model.Bill;
import btb.hr.vinoljupci.model.Card;
import btb.hr.vinoljupci.model.User;
import btb.hr.vinoljupci.rowMapper.CardsRowMapper;

@Component
public class DaoPay implements IDaoPayment{

	private static final Logger log = LoggerFactory.getLogger(DaoPay.class);
	
	@Autowired 
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void setDataSource(DataSource ds) {
		
	}

	@Override
	public Card changeValue(Bill bill) throws SQLException {
		
		Card newCard = new Card();
			
			int valueBeforePayment = bill.getCard().getCardValue();
			log.info("-------BEFORE PAYMENT  "+valueBeforePayment);
			int sum = bill.getBillSum();
			log.info("-------SUM  "+sum);
			int valueAfterPayment = 0 ;
			if(sum < valueBeforePayment) { 
				 valueAfterPayment = valueBeforePayment - sum;
			} else {
				log.info("Bill value is greater than card value, visitor needs to fill the card first!");
				return null;
			}
			
			
		try { 
			String sqlNewCardValue = "UPDATE card SET card_value=? WHERE card_number=?";	
			Object[] args = new Object[] {valueAfterPayment, bill.getCard().getCardNumber()};
			 int success = jdbcTemplate.update(sqlNewCardValue, args);
				if(success > 0) {
			String sql = "SELECT * FROM card WHERE id_card=?";
			Object[] arg = new Object[] {bill.getCard().getId_card()};
			newCard = jdbcTemplate.queryForObject(sql, arg, new CardsRowMapper());
			return newCard;
				} else {
					log.info("problem updating values in card table! - changeValue() - daoPay.java");
					return null;
				}
			
		} catch(Exception e) {
			log.info("Error updating value(payment - DaoPay.java) CARD table" + e);
			return null;
		}
		
	}

}
