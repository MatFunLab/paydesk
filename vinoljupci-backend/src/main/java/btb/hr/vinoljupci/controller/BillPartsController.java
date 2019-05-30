package btb.hr.vinoljupci.controller;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import IService.IDaoService;
import btb.hr.vinoljupci.model.Artikl;
import btb.hr.vinoljupci.model.BillPart;

import btb.hr.vinoljupci.model.Card;
import btb.hr.vinoljupci.rowMapper.CardsRowMapper;



@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class BillPartsController {

private static final Logger log = LoggerFactory.getLogger(BillPartsController.class);
	
	@Autowired
	IDaoService iDaoService;
	
	@RequestMapping(value = "/savebillpart", method = RequestMethod.POST)
	   public int saveBillPart(@RequestBody HashMap<Object, ArrayList<BillPart>> billPart) {
		 
		 	 ArrayList<BillPart> receivedBillPart = billPart.get("billPart");
		 	 
		 	log.info("----------------------------------------RECEIVED BILL PART: " + receivedBillPart.get(0).getBill());
			
		 	int success = 0;
			try {
				success =  iDaoService.add("billParts", receivedBillPart);
				return success;
			} catch(Exception e) {
				
				log.info("------------------------------Problem saving billPart! " + e);
				return -1;
			}
			
				
	 }
	
	
	@RequestMapping(value = "/editbillpart", method = RequestMethod.POST)
	   public BillPart editBillPart(@RequestBody HashMap<Object, BillPart> billPart) {
		 
			BillPart receivedBillPart = billPart.get("billPart");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedBillPart);
			 
		 	long id =  receivedBillPart.getId_bill_parts();
		 	
		 	
			try {
				 
				BillPart editedBillPart =  (BillPart) iDaoService.edit("artikl", receivedBillPart);
				return editedBillPart;
			} catch(Exception e) {
				log.info("------------------------------Problem editing billPart! " + e);
				return null;
			}		
	 }
	
	@RequestMapping(value = "/deletebillpart", method = RequestMethod.POST)
	   public int deleteBillPart(@RequestBody HashMap<Object, BillPart> billPart) {
		 
		 BillPart receivedBillPart =  billPart.get("billPart");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedBillPart);
			 
		 	int success = 0;
			try {
				success =  iDaoService.delete("billPart", receivedBillPart.getId_bill_parts());
				return success;
			} catch(Exception e) {
				log.info("------------------------------STAFF CONTROLLER ERROR - Problem with delete staff! " + e);
				return -1;
			}		
	 }
	
	@RequestMapping(value = "/getallbillparts", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<BillPart> getAllBillPart() {
		
		ArrayList<BillPart> allBillPart = new ArrayList<BillPart>();
		try {
			allBillPart = (ArrayList<BillPart>) iDaoService.getAll("billParts");
			return allBillPart;
		} catch (Exception e) {
			log.info("------------------------------Problem getting allArtikli! " + e);
			return null;
		}
		
		
	}
}
