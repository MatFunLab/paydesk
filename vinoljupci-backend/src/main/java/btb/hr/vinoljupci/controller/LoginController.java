package btb.hr.vinoljupci.controller;

import java.io.IOException;
import java.sql.SQLException;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import IService.IDaoService;
import IService.ILoginService;
import btb.hr.vinoljupci.model.Card;
import btb.hr.vinoljupci.model.Credentials;
import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.Region;
import btb.hr.vinoljupci.model.Role;
import btb.hr.vinoljupci.model.User;
import btb.hr.vinoljupci.rowMapper.UserRowMapper;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class LoginController {
	
	private static final Logger log = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	ILoginService iLoginService;
	
	 @RequestMapping(value = "/verifyuser", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	 public Object verifyUser(@RequestBody HashMap<Object, Credentials> credentials) {
		 
		
		Credentials receivedCredentials = credentials.get("credentials");
		 	
		 log.info("Usercredentials is----------------------" + receivedCredentials.getUserMail());
		 
		 	
			Object obj = null;
			
			try {
				 obj = iLoginService.checkLogin(receivedCredentials);
				 return obj; 
			} catch (Exception e){
				log.info("-------------Error :" + e);
				return null;
			}
			 	 	  	
	 }
	 
}		

