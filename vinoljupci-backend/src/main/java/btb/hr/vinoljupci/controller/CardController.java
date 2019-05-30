package btb.hr.vinoljupci.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import IService.ICardService;
import IService.IDaoService;
import btb.hr.vinoljupci.model.Artikl;
import btb.hr.vinoljupci.model.Bill;
import btb.hr.vinoljupci.model.BillPart;
import btb.hr.vinoljupci.model.Card;
import btb.hr.vinoljupci.model.DataToUpdateCard;
import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.Region;
import btb.hr.vinoljupci.model.Role;
import btb.hr.vinoljupci.model.User;
import btb.hr.vinoljupci.model.WineEvent;
import btb.hr.vinoljupci.service.DaoService;




@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class CardController {
	
private static final Logger log = LoggerFactory.getLogger(CardController.class);
	
	@Autowired
	ICardService iCardService;
	
	@RequestMapping(value = "/checkcard",  produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public Card getCard(@RequestParam long card) throws SQLException {
		log.info("CARD NUM" + card);
		Card foundCard = null;
		try {
			foundCard = (Card)iCardService.checkCard(card);
			return foundCard;
		} catch(Exception e) {
			log.info("--------ERROR: " + e);
			return null;
		}
	}
	
	@RequestMapping(value = "/fillcard", method = RequestMethod.POST)
	   public Card fillCard(@RequestBody HashMap<Object, DataToUpdateCard> cardToUpdate) {
		 
		Card card = cardToUpdate.get("dataToUpdateCard").getCard();
		int activationTimes = cardToUpdate.get("dataToUpdateCard").getDataToUpdateActivationTimes();
		int valueToUpdate = cardToUpdate.get("dataToUpdateCard").getDataToUpdateValue();
		 	 
		 	log.info("----------------------------------------RECEIVED : " + activationTimes);
			 	
		 	int success = 0;
			try {
				Card filledCard =  iCardService.fillCard(card, valueToUpdate, activationTimes);
				return filledCard;
			} catch(Exception e) {
				log.info("------------------------------Problem filling card! " + e);
				return null;
			}
		 	
	 }
	
	@RequestMapping(value = "/resetcard", method = RequestMethod.POST)
	   public Card resetCard(@RequestBody HashMap<Object, Card> cardToReset) {
		 
		 	 Card card = cardToReset.get("card");
		 	log.info("----------------------------------------RECEIVED : " + card.getCardNumber());
			 	
		 	int success = 0;
			try {
				Card filledCard =  iCardService.resetCard(card);
				return filledCard;
			} catch(Exception e) {
				log.info("------------------------------Problem filling card! " + e);
				return null;
			}
		 	
	 }
	
	
	@RequestMapping(value = "/savecard", method = RequestMethod.POST)
	   public int saveCard(@RequestBody HashMap<Object, Card> card) {
		 
//		 	 Card receivedCard = card.get("card");
//		 	 
//		 	log.info("----------------------------------------RECEIVED : " + receivedCard);
//			 	
//		 	int success = 0;
//			try {
//				success =  iDaoService.add("card", receivedCard);
//				return success;
//			} catch(Exception e) {
//				
//				log.info("------------------------------Problem saving card! " + e);
//				return -1;
//			}
		return 0;
			
				
	 }
	
	
	@RequestMapping(value = "/editcard", method = RequestMethod.POST)
	   public Card editCard(@RequestBody HashMap<Object, Card> card) {
		 
//			Card receivedCard = card.get("card");
//		 	 
//		 	log.info("----------------------------------------RECEIVED : " + receivedCard);
//			 
//		 	long id =  receivedCard.getId_card();
//		 	
//		 	
//			try { 
//				Card editedCard =  (Card) iDaoService.edit("card", receivedCard);
//				return editedCard;
//			} catch(Exception e) {
//				log.info("------------------------------Problem editing card! " + e);
//				return null;
//			}	
		return null;
	 }
	
	@RequestMapping(value = "/deletecard", method = RequestMethod.POST)
	   public int deleteCard(@RequestBody HashMap<Object, Card> card) {
		 
//		 	Card receivedCard = card.get("card");
//		 	 
//		 	log.info("----------------------------------------RECEIVED : " + receivedCard);
//			 
//		 	int success = 0;
//			try {
//				success =  iDaoService.delete("card", receivedCard.getId_card());
//				return success;
//			} catch(Exception e) {
//				log.info("------------------------------CARD CONTROLLER ERROR - Problem with delete card! " + e);
//				return -1;
//			}		
		return 0;
	 }
	
	@RequestMapping(value = "/getallcards", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Card> getAllCards() {
		
//		ArrayList<Card> allCards = new ArrayList<Card>();
//		try {
//			allCards = (ArrayList<Card>) iDaoService.getAll("card");
//			return allCards;
//		} catch (Exception e) {
//			log.info("------------------------------Problem getting allCards! " + e);
//			return null;
//		}
		
		return null;
	}	
}






