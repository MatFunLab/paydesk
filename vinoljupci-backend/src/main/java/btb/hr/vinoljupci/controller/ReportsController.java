package btb.hr.vinoljupci.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import btb.hr.vinoljupci.mail.MailBuilder;
import btb.hr.vinoljupci.model.FilterQueryToClient;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class ReportsController {
	
	private static final Logger log = LoggerFactory.getLogger(ReportsController.class);
	
	@RequestMapping(value = "/sendreport", method = RequestMethod.POST)
	   public int sendReport(@RequestBody HashMap<Object, ArrayList<FilterQueryToClient>> filterQueryObject) {
		 
		ArrayList<FilterQueryToClient> receivedFilterQueryObject =  filterQueryObject.get("filterQueryObject");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedFilterQueryObject);
		 	
			 MailBuilder email = new MailBuilder();
			 	
			 try {
				email.sendMail(receivedFilterQueryObject.get(0));
				return 1;
			} catch (UnsupportedEncodingException | MessagingException e1) {
				e1.printStackTrace();
				return -1;
			}

	 }
}
