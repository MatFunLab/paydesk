package btb.hr.vinoljupci.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import btb.hr.vinoljupci.IDAO.IDaoCRUD;
import btb.hr.vinoljupci.IDAO.IDaoCard;
import btb.hr.vinoljupci.model.Card;
import btb.hr.vinoljupci.rowMapper.CardsRowMapper;
import btb.hr.vinoljupci.rowMapper.UserRowMapper;

@Component
public class DaoCard implements IDaoCard{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(DaoCard.class);

	@Override
	public void setDataSource(DataSource ds) throws SQLException {
		
	}

	@Override
	public Card checkCard(long cardNumber) throws SQLException {
		String sqlGetOne = "SELECT * FROM card WHERE card_number=?";
		Object[] args = new Object[] {cardNumber};
		Card card = jdbcTemplate.queryForObject(sqlGetOne, args, new CardsRowMapper());
	
		return card;
	
	}

	@Override
	public Card fillCard(Card card, int valueToUpdate, int activationTimes) throws SQLException {
		
		Card filledCard = new Card();
		
		String sqlUpdateCard = "UPDATE card SET activation_times=?, card_value=? WHERE card_number=?"; 
		Object[] args = new Object[] {activationTimes, valueToUpdate, card.getCardNumber()};
		int success = jdbcTemplate.update(sqlUpdateCard, args);
			if(success == 1) {
				String query ="SELECT * FROM card WHERE card_number=?";
				Object[] arg = new Object[] {card.getCardNumber()};
				 filledCard = jdbcTemplate.queryForObject("SELECT * FROM card WHERE card_number=?", arg, new CardsRowMapper());
				 return filledCard;
			} else {
				log.info("Unsuccessful update card value - line 47");
				return null;
			}
		
	}

	@Override
	public Card resetCard(Card card) throws SQLException {
		Card resettedCard = new Card();
		
		String sqlUpdateCard = "UPDATE card SET card_value=0 WHERE card_number=?"; 
		Object[] args = new Object[] {card.getCardNumber()};
		int success = jdbcTemplate.update(sqlUpdateCard, args);
			if(success == 1) {
				String query ="SELECT * FROM card WHERE card_number=?";
				Object[] arg = new Object[] {card.getCardNumber()};
				 resettedCard = jdbcTemplate.queryForObject(query, arg, new CardsRowMapper());
				 return resettedCard;
			} else {
				log.info("Unsuccessful update card value - line 47");
				return null;
			}
	}
	


	public int insertOne(Object obj) {
		Card card = (Card)obj;
		try { 
			String sqlCardSave = "INSERT INTO card (card_number, activation_times, card_value, timestamp) VALUES (?,?,?,?)";	
			Object[] args = new Object[] {card.getCardNumber(), card.getActivationTimes(), card.getCardValue(), card.getCardValue(), card.getTimestamp()};
			int success = jdbcTemplate.update(sqlCardSave, args);
			return success;

		} catch(Exception e) {
			log.info("Error inserting new record in CARD table" + e);
			return -1;
		}
	}

	public int delete(long id) {
		
		int success = 0;
		
		try {
			String sqlDelete = "DELETE from card WHERE id_card=?";
			Object[] args = new Object[] {id};
			success = jdbcTemplate.update(sqlDelete, args);
			return success;
		} catch(Exception e) {
			log.info("ERROR SQL QUERY delete from table card" + e);
			return success;
		}
	}

	public Object edit(Object obj) {
		
		Card card = (Card)obj;
		
		try {
			String sqlUpdate = "UPDATE card (card_number, activation_times, card_value, timestamp) SET card_number=?, activation_times=?, card_value=?, timestamp=?"; 
			Object[] args = new Object[] {card.getCardNumber(), card.getActivationTimes(), card.getCardValue(), card.getTimestamp()};
			int success = jdbcTemplate.update(sqlUpdate, args, new CardsRowMapper());
			if(success == 1) {
					try {
						String sqlGetUpdatedObject = "SELECT * FROM card WHERE id_card=?";
					    Object[] arg=  new Object[]{card.getId_card()}; 
					    Card updatedCard = jdbcTemplate.queryForObject(sqlGetUpdatedObject, arg, new CardsRowMapper());
					      return updatedCard;
					} catch(Exception e) {
						log.info("Problem with getting updated card - DaoCard.edit()");
						return null;
					}
			} else {
				throw new Exception();
			}
			
		} catch(Exception e) {
			return null;
		}		
		
	}


	public ArrayList<?> getAll() {
		
		ArrayList<?> list = new ArrayList<>();
		String sqlGetAll = "SELECT * FROM card";
		list = (ArrayList<?>) jdbcTemplate.query(sqlGetAll, new CardsRowMapper());
		return list;
	}

	public Object getOne(long id) {
		String sqlGetOne = "SELECT * FROM card WHERE card.id_card=?";
		Object[] args = new Object[] {id};
		Object obj = jdbcTemplate.queryForObject(sqlGetOne, args, new CardsRowMapper());
		return obj;
	}

	public int insertMany(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}
}
