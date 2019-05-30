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
import btb.hr.vinoljupci.model.Region;
import btb.hr.vinoljupci.model.Role;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class RegionController {
	
private static final Logger log = LoggerFactory.getLogger(RegionController.class);
	
@Autowired
IDaoService iDaoService;

@RequestMapping(value = "/saveregion", method = RequestMethod.POST)
   public int saveCard(@RequestBody HashMap<Object, Region> region) {
	 
	 	 Region receivedRegion = region.get("region");
	 	 
	 	log.info("----------------------------------------RECEIVED : " + receivedRegion);
		 	
	 	int success = 0;
		try {
			success =  iDaoService.add("region", receivedRegion);
			return success;
		} catch(Exception e) {
			
			log.info("------------------------------Problem saving region! " + e);
			return -1;
		}
		
			
 }


@RequestMapping(value = "/editregion", method = RequestMethod.POST)
   public Region editRegion(@RequestBody HashMap<Object, Region> region) {
	 
		Region receivedRegion = region.get("region");
	 	 
	 	log.info("----------------------------------------RECEIVED : " + receivedRegion);
		 
	 	long id =  receivedRegion.getId_region();
	 	
	 	
		try { 
			Region editedRegion =  (Region) iDaoService.edit("region", receivedRegion);
			return editedRegion;
		} catch(Exception e) {
			log.info("------------------------------Problem editing region! " + e);
			return null;
		}		
 }

@RequestMapping(value = "/deleteregion", method = RequestMethod.POST)
   public int deleteRegion(@RequestBody HashMap<Object, Region> region) {
	 
		Region receivedRegion = region.get("region");
	 	 
	 	log.info("----------------------------------------RECEIVED : " + receivedRegion);
		 
	 	int success = 0;
		try {
			success =  iDaoService.delete("card", receivedRegion.getId_region());
			return success;
		} catch(Exception e) {
			log.info("------------------------------Region CONTROLLER ERROR - Problem with delete region! " + e);
			return -1;
		}		
 }

@RequestMapping(value = "/getallregions", method = RequestMethod.GET)
@ResponseBody
public ArrayList<Region> getAllRegion() {
	
	ArrayList<Region> allRegions = new ArrayList<Region>();
	try {
		allRegions = (ArrayList<Region>) iDaoService.getAll("region");
		return allRegions;
	} catch (Exception e) {
		log.info("------------------------------Problem getting allRegions! " + e);
		return null;
	}
}	

}
