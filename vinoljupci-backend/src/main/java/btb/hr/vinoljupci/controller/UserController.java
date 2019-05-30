package btb.hr.vinoljupci.controller;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import IService.IDaoService;
import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.Region;
import btb.hr.vinoljupci.model.Role;
import btb.hr.vinoljupci.model.Staff;
import btb.hr.vinoljupci.model.User;



@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class UserController {
	
	private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	IDaoService iDaoService;
	
	//---------------------------------  GET  ONE --------------------------------------------- 
	@RequestMapping(value = "/getuser/{id}",  produces = "application/json", method = RequestMethod.GET)
	@ResponseBody
	public User getUser(@RequestParam long id) throws SQLException {
		
		User user = null;
		try {
			user = (User)iDaoService.getById("user", id);
			return user;
		} catch(Exception e) {
			log.info("--------ERROR: " + e);
			return null;
		}
	}
	@RequestMapping(value = "/saveuser", method = RequestMethod.POST)
	   public int saveUser(@RequestBody HashMap<Object, User> user) {
		 
		 	 User receivedUser = user.get("user");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedUser);
			 	
		 	int success = 0;
			try {
				success =  iDaoService.add("user", receivedUser);
				return success;
			} catch(Exception e) {
				log.info("------------------------------Problem saving user! " + e);
				return -1;
			}	
	 }
	
	
	@RequestMapping(value = "/edituser", method = RequestMethod.POST)
	   public User editUser(@RequestBody HashMap<Object, User> user) {
		 
			User receivedUser = user.get("user");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedUser);
			 
		 
			try { 
				User editedUser =  (User) iDaoService.edit("user", receivedUser);
				return editedUser;
			} catch(Exception e) {
				log.info("------------------------------Problem editing user! " + e);
				return null;
			}		
	 }
	
	@RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
	   public int deleteUser(@RequestBody HashMap<Object, User> user) {
		 
			User receivedUser = user.get("user");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedUser);
			 
		 	int success = 0;
			try {
				success =  iDaoService.delete("user", receivedUser.getId_user());
				return success;
			} catch(Exception e) {
				log.info("------------------------------USER CONTROLLER ERROR - Problem with delete user! " + e);
				return -1;
			}		
	 }
	
	@RequestMapping(value = "/getallusers", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<User> getAllUsers() {
		
		ArrayList<User> allUsers = new ArrayList<User>();
		try {
			allUsers = (ArrayList<User>) iDaoService.getAll("user");
			return allUsers;
		} catch (Exception e) {
			log.info("------------------------------Problem getting allUsers! " + e);
			return null;
		}
	}	
}
