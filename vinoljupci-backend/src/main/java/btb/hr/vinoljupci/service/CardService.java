package btb.hr.vinoljupci.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import IService.ICardService;
import btb.hr.vinoljupci.DAO.DaoArtikl;
import btb.hr.vinoljupci.DAO.DaoCard;
import btb.hr.vinoljupci.model.Card;

@Service("iCardService")
public class CardService implements ICardService{

	@Autowired 
	DaoCard daoCard;
	
	@Override
	public Card checkCard(long cardNumber) throws SQLException {
		return daoCard.checkCard(cardNumber);
	}

	@Override
	public Card resetCard(Card card) throws SQLException {
		return daoCard.resetCard(card);
	}

	@Override
	public Card fillCard(Card card, int valueToUpdate, int activationTimes) throws SQLException {
		
		if(activationTimes == 0) {
			activationTimes = 1;	
		} 
		 int valueBefore = card.getCardValue();
		 int newValue = valueBefore + valueToUpdate;
		 
		return daoCard.fillCard(card, newValue, activationTimes);
		
	}

}
