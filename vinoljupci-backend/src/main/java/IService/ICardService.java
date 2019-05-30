package IService;

import java.sql.SQLException;

import btb.hr.vinoljupci.model.Card;

public interface ICardService {
	
	public Card checkCard(long cardNumber) throws SQLException;
	
	public Card fillCard(Card card, int valueToUpdate, int activationTimes) throws SQLException;

	public Card resetCard(Card card) throws SQLException;
	
}
