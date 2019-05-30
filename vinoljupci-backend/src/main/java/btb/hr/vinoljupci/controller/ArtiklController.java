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
import btb.hr.vinoljupci.model.Artikl;
import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.Shift;
import btb.hr.vinoljupci.model.Staff;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class ArtiklController {

	private static final Logger log = LoggerFactory.getLogger(ArtiklController.class);
	
	@Autowired
	IDaoService iDaoService;
	
	@RequestMapping(value = "/savearticles", method = RequestMethod.POST)
	   public int saveArtikl(@RequestBody HashMap<Object, ArrayList<Artikl>> artikli) {
		 
		 	 ArrayList<Artikl> receivedArtikl = artikli.get("artikli");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedArtikl.get(0).getName());
			 	
		 	int success = 0;
			try {
				 
				success =  iDaoService.add("artikl", receivedArtikl);
				return success;
			} catch(Exception e) {
				
				log.info("------------------------------Problem saving artikl! " + e);
				return -1;
			}
			
		 	
	 }
	
	
	@RequestMapping(value = "/editartikl", method = RequestMethod.POST)
	   public Artikl editArtikl(@RequestBody HashMap<Object, Artikl> artikl) {
		 
		 	 Artikl receivedArtikl = artikl.get("artikl");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedArtikl);
			 
		 	long id =  receivedArtikl.getId_artikli();
		 	
			
			try {
				 
				Artikl editedArtikl = (Artikl) iDaoService.edit("artikl", receivedArtikl);
				return editedArtikl;
			} catch(Exception e) {
				
				log.info("------------------------------Problem saving staff! " + e);
				return null;
			}
			
				
	 }
	
	@RequestMapping(value = "/deletearticles", method = RequestMethod.POST)
	   public int deleteArtikli(@RequestBody HashMap<Object, Artikl> artikl) {
		 
		 Artikl receivedArtikl =  artikl.get("artikl");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedArtikl.getId_artikli());
			 
		 	int success = 0;
			try {
				 
				success =  iDaoService.delete("artikl", receivedArtikl.getId_artikli());
				return success;
				
			} catch(Exception e) {
				log.info("------------------------------STAFF CONTROLLER ERROR - Problem with delete staff! " + e);
				return -1;
			}		
	 }
	
	@RequestMapping(value = "/getallarticles", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Artikl> getAllStaff() {
		
		ArrayList<Artikl> allArtikli = new ArrayList<Artikl>();
		try {
			allArtikli = (ArrayList<Artikl>) iDaoService.getAll("artikl");
		} catch (Exception e) {
			log.info("------------------------------Problem getting allArtikli! " + e);
		}
		
		return allArtikli;
	 

}
}
