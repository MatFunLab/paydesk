package btb.hr.vinoljupci.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import IService.IDaoService;
import btb.hr.vinoljupci.model.Shift;
import btb.hr.vinoljupci.model.Staff;
import btb.hr.vinoljupci.model.User;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class StaffController {
	
	private static final Logger log = LoggerFactory.getLogger(StaffController.class);
	
	@Autowired
	IDaoService iDaoService;
	
	@RequestMapping(value = "/savestaff", method = RequestMethod.POST)
	   public int saveStaff(@RequestBody HashMap<Object, Staff> staff) {
		 
		 	 Staff receivedStaff = staff.get("staff");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedStaff);
			 	
		 	int success = 0;
			try {
				success =  iDaoService.add("staff", receivedStaff);
				return success;
			} catch(Exception e) {
				
				log.info("------------------------------Problem saving staff! " + e);
				return -1;
			}
			
				
	 }
	
	
	@RequestMapping(value = "/editstaff", method = RequestMethod.POST)
	   public Staff editStaff(@RequestBody HashMap<Object, Staff> staff) {
		 
			Staff receivedStaff = staff.get("staff");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedStaff.getId_staff());
			 
			try { 
				Staff editedStaff =  (Staff) iDaoService.edit("staff", receivedStaff);
				
				return editedStaff;
			} catch(Exception e) {
				log.info("------------------------------Problem editing staff! " + e);
				return null;
			}		
	 }
	
	@RequestMapping(value = "/deletestaff", method = RequestMethod.POST)
	   public int deleteStaff(@RequestBody HashMap<Object, Staff> staff) {
		 
			Staff receivedStaff = staff.get("staff");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedStaff);
			 
		 	int success = 0;
			try {
				success =  iDaoService.delete("staff", receivedStaff.getId_staff());
				return success;
			} catch(Exception e) {
				log.info("------------------------------STAFF CONTROLLER ERROR - Problem with delete staff! " + e);
				return -1;
			}		
	 }
	
	@RequestMapping(value = "/getallstaff", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Staff> getAllStaff() {
		
		ArrayList<Staff> allStaff = new ArrayList<Staff>();
		try {
			allStaff = (ArrayList<Staff>) iDaoService.getAll("staff");
			return allStaff;
		} catch (Exception e) {
			log.info("------------------------------Problem getting allStaff! " + e);
			return null;
		}
	}	
}
