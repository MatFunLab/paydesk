package btb.hr.vinoljupci.controller;
import java.util.ArrayList;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import IService.ICardService;
import IService.IDaoService;
import IService.IPaymentService;
import btb.hr.vinoljupci.model.Bill;
import btb.hr.vinoljupci.model.Card;
import btb.hr.vinoljupci.model.CardAndLastId;



@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class BillController {
	
	private static final Logger log = LoggerFactory.getLogger(BillController.class);
	
	@Autowired
	IDaoService iDaoService;
	
	@Autowired 
	IPaymentService iPaymentService;
	
	@Autowired
	ICardService iCardService;
	
	@RequestMapping(value = "/payment", method = RequestMethod.POST)
	   public CardAndLastId saveBill(@RequestBody HashMap<Object, Bill> bill) {
		 
		 	 Bill receivedBill = bill.get("bill");
		 	
		 	int lastIdBill = 0;
		 	
			try {
				 
				 lastIdBill =  iDaoService.add("bill", receivedBill);
				
					if(lastIdBill > 0) {
						Card newCard = iPaymentService.changeValue(receivedBill);
						
					
						Bill lastBillInsert = (Bill) iDaoService.getById("bill", lastIdBill);
						
					
					
						CardAndLastId cardAndLastId = new CardAndLastId();
							cardAndLastId.setCard(newCard);			
							cardAndLastId.setLastBillInserted(lastBillInsert);
						if(newCard != null) {
							return cardAndLastId;
						} else {
							throw new Exception("Problem with card change value -line 68 BillController - /payment");
						}
					} else {
						throw new Exception("Problem with saving bill -line 62 BillController - /payment");
					}
			    
			} catch(Exception e) {
				
				log.info("------------------------------Problem saving bill! " + e);
				return null;
			}
			
	 }
	
	
	@RequestMapping(value = "/editbill", method = RequestMethod.POST)
	   public Bill editBill(@RequestBody HashMap<Object, Bill> bill) {
		 
		 Bill receivedBill = bill.get("bill");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedBill);
			 
		 	long id =  receivedBill.getId_bill();
		 	
			try {
				 
				Bill editedBill =  (Bill) iDaoService.edit("bill", receivedBill);
				
				return editedBill;
			} catch(Exception e) {
				
				log.info("------------------------------Problem saving staff! " + e);
				return null;
			}
			
				
	 }
	
	@RequestMapping(value = "/deletebill", method = RequestMethod.POST)
	   public int deleteStaffMember(@RequestBody HashMap<Object, Bill> bill) {
		 
			Bill receivedBill = bill.get("bill");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedBill);
		 	
		 	int success = 0;
		 	
			try {
				success =  iDaoService.delete("bill", receivedBill.getId_bill());
				return success;
			} catch(Exception e) {
				log.info("------------------------------BILL CONTROLLER ERROR - Problem with delete bill = " + e);
				return -1;
			}		
	 }
	
	@RequestMapping(value = "/getallbills", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Bill> getAllBills() {
		
		ArrayList<Bill> allBills = new ArrayList<Bill>();
		try {
			allBills = (ArrayList<Bill>) iDaoService.getAll("bill");
			return allBills;
		} catch (Exception e) {
			log.info("------------------------------Problem getting allArtikli! " + e);
			return null;
		}
}

}
