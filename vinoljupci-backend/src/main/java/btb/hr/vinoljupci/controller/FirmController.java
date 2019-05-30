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
import btb.hr.vinoljupci.model.Card;
import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.Region;
import btb.hr.vinoljupci.rowMapper.CardsRowMapper;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class FirmController {
	
private static final Logger log = LoggerFactory.getLogger(FirmController.class);
	
	@Autowired
	IDaoService iDaoService;
	
	@RequestMapping(value = "/savefirm", method = RequestMethod.POST)
	   public int saveFirm(@RequestBody HashMap<Object, Firm> firm) {
		 
		 	 Firm receivedFirm = firm.get("firm");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedFirm);
			 	
			try {
				int success =  iDaoService.add("firm", receivedFirm);
				return success;
			} catch(Exception e) {
				
				log.info("------------------------------Problem saving firm! " + e);
				return -1;
			}
				
	 }
	
	
	@RequestMapping(value = "/editfirm", method = RequestMethod.POST)
	   public Firm editFirm(@RequestBody HashMap<Object, Firm> firm) {
		 
			Firm receivedFirm= firm.get("firm");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedFirm);
			 
		 	long id =  receivedFirm.getId_firm();
		 	
			try { 
				Firm editedFirm =  (Firm) iDaoService.edit("firm", receivedFirm);
				return editedFirm;
			} catch(Exception e) {
				log.info("------------------------------Problem editing FIRM! " + e);
				return null;
			}		
	 }
	
	@RequestMapping(value = "/deletefirm", method = RequestMethod.POST)
	   public int deleteFirm(@RequestBody HashMap<Object, Firm> firm) {
		 
		 	Firm receivedFirm = firm.get("firm");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedFirm);
			 
		 	int success = 0;
			try {
				success =  iDaoService.delete("firm", receivedFirm.getId_firm());
				return success;
			} catch(Exception e) {
				log.info("------------------------------FIRM CONTROLLER ERROR - Problem with delete firm! " + e);
				return -1;
			}		
	 }
	
	@RequestMapping(value = "/getallfirms", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Firm> getAllFirms() {
		
		ArrayList<Firm> allFirms = new ArrayList<Firm>();
		try {
			allFirms = (ArrayList<Firm>) iDaoService.getAll("firm");
			return allFirms;
		} catch (Exception e) {
			log.info("------------------------------Problem getting allFirms! " + e);
			return null;
		}
	}	
}
