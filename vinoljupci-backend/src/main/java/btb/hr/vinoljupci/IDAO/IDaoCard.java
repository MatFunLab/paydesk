package btb.hr.vinoljupci.IDAO;

import java.sql.SQLException;

import javax.sql.DataSource;

import btb.hr.vinoljupci.model.Card;

public interface IDaoCard {
	
	public void setDataSource(DataSource ds) throws SQLException;
	
	public Card checkCard(long cardNumber) throws SQLException;
	
	public Card fillCard(Card card, int valueToUpdate, int activationTimes) throws SQLException;

	public Card resetCard(Card card) throws SQLException;
}
