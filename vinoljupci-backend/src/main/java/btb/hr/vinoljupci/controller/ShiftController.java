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

import IService.IDaoService;
import btb.hr.vinoljupci.model.Role;
import btb.hr.vinoljupci.model.Shift;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class ShiftController {
	
private static final Logger log = LoggerFactory.getLogger(ShiftController.class);
	
	@Autowired
	IDaoService iDaoService;
	
	@RequestMapping(value = "/saveshift", method = RequestMethod.POST)
	   public int saveShift(@RequestBody HashMap<Object, Shift> shift) {
		 
		 	 Shift receivedShift = shift.get("shift");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedShift);
			 	
		 	int success = 0;
			try {
				success =  iDaoService.add("shift", receivedShift);
				return success;
			} catch(Exception e) {
				
				log.info("------------------------------Problem saving shift! " + e);
				return -1;
			}
			
				
	 }
	
	
	@RequestMapping(value = "/editshift", method = RequestMethod.POST)
	   public Shift editShift(@RequestBody HashMap<Object, Shift> shift) {
		 
			Shift receivedShift = shift.get("shift");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedShift);
			 
		 	long id =  receivedShift.getId_shift();
		 	
			try { 
				Shift editedShift =  (Shift) iDaoService.edit("shift", receivedShift);
				return editedShift;
			} catch(Exception e) {
				log.info("------------------------------Problem editing shift! " + e);
				return null;
			}		
	 }
	
	@RequestMapping(value = "/deleteshift", method = RequestMethod.POST)
	   public int deleteShift(@RequestBody HashMap<Object, Shift> shift) {
		 
			Shift receivedShift = shift.get("shift");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedShift);
			 
		 	int success = 0;
			try {
				success =  iDaoService.delete("shift", receivedShift.getId_shift());
				return success;
			} catch(Exception e) {
				log.info("------------------------------SHIFT CONTROLLER ERROR - Problem with delete shift! " + e);
				return -1;
			}		
	 }
	
	@RequestMapping(value = "/getallshifts", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Shift> getAllShifts() {
		
		ArrayList<Shift> allShifts = new ArrayList<Shift>();
		try {
			allShifts = (ArrayList<Shift>) iDaoService.getAll("shift");
			return allShifts;
		} catch (Exception e) {
			log.info("------------------------------Problem getting allShifts! " + e);
			return null;
		}
	}	


}
