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
import btb.hr.vinoljupci.model.Card;
import btb.hr.vinoljupci.model.Role;
import btb.hr.vinoljupci.model.User;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class RoleController {
	
	private static final Logger log = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	IDaoService iDaoService;
	
	@RequestMapping(value = "/saverole", method = RequestMethod.POST)
	   public int saveRole(@RequestBody HashMap<Object, Role> role) {
		 
		 	 Role receivedRole = role.get("role");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedRole);
			 	
		 	int success = 0;
			try {
				success =  iDaoService.add("role", receivedRole);
				return success;
			} catch(Exception e) {
				
				log.info("------------------------------Problem saving role! " + e);
				return -1;
			}
			
				
	 }
	
	
	@RequestMapping(value = "/editrole", method = RequestMethod.POST)
	   public Role editRole(@RequestBody HashMap<Object, Role> role) {
		 
			Role receivedRole = role.get("role");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedRole);
			 
		 	long id =  receivedRole.getId_role();
		 	
		 	
			try { 
				Role editedRole =  (Role) iDaoService.edit("role", receivedRole);
				return editedRole;
			} catch(Exception e) {
				log.info("------------------------------Problem editing role! " + e);
				return null;
			}		
	 }
	
	@RequestMapping(value = "/deleterole", method = RequestMethod.POST)
	   public int deleteRole(@RequestBody HashMap<Object, Role> role) {
		 
			Role receivedRole = role.get("role");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedRole);
			 
		 	int success = 0;
			try {
				success =  iDaoService.delete("role", receivedRole.getId_role());
				return success;
			} catch(Exception e) {
				log.info("------------------------------ROLE CONTROLLER ERROR - Problem with delete role! " + e);
				return -1;
			}		
	 }
	
	@RequestMapping(value = "/getallroles", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Role> getAllRoles() {
		
		ArrayList<Role> allRoles = new ArrayList<Role>();
		try {
			allRoles = (ArrayList<Role>) iDaoService.getAll("role");
			return allRoles;
		} catch (Exception e) {
			log.info("------------------------------Problem getting allRoles! " + e);
			return null;
		}
	}	
}
