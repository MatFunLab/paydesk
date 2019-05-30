package btb.hr.vinoljupci.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import btb.hr.vinoljupci.model.Card;

public class CardsRowMapper implements RowMapper<Card> {

	public Card mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Card card = new Card();
			card.setId_card(rs.getInt("id_card")); 
			card.setCardNumber(rs.getInt("card_number"));
			card.setActivationTimes(rs.getInt("activation_times"));
			card.setCardValue(rs.getInt("card_value"));
			card.setTimestamp(rs.getDate("timestamp"));
		
		return card;
	}

}
