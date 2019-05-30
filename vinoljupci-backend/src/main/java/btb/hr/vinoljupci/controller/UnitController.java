package btb.hr.vinoljupci.controller;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import IService.IDaoService;
import IService.IUnitFirmService;
import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.Staff;
import btb.hr.vinoljupci.model.Unit;
import btb.hr.vinoljupci.model.UnitFirm;
import btb.hr.vinoljupci.model.UnitFromClient;
import btb.hr.vinoljupci.service.DaoService;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class UnitController {
	
	private static final Logger log = LoggerFactory.getLogger(UnitController.class);
	
	@Autowired
	DaoService iDaoService;
	
	@Autowired
	IUnitFirmService iUnitFirmService;
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@RequestMapping(value = "/saveunit", method = RequestMethod.POST)
	   public int saveUnit(@RequestBody HashMap<Object, UnitFromClient> unitFromClient) {
		 
		 	 UnitFromClient receivedUnit =  unitFromClient.get("unit");
		 	 
		 	 
		 	log.info("----------------------------------------RECEIVED FIRM: " + receivedUnit.getFirm().get(0).getName());
		 	log.info("----------------------------------------RECEIVED UNIT ID: " + receivedUnit.getId());
		 	log.info("----------------------------------------RECEIVED UNIT NAME: " + receivedUnit.getName());
		 	
		 	
		 	// premapiranja za spremanje unita u unit table 
			 	Unit unit = new Unit();
			 		unit.setName(receivedUnit.getName());
			 	
			 int successFirmsAddOnUnits = 0;
			 try {
				int idOfLastUnitInserted = iDaoService.add("unit", unit);
				 if(idOfLastUnitInserted >= 1) {
					 try {
						 	 unit.setId_unit(idOfLastUnitInserted);
							 iDaoService.addUnitOnFirm(receivedUnit.getFirm(), unit);
						 	
						 return successFirmsAddOnUnits;
						 
					 } catch(Exception e) {
						 log.info("Problem with saving firms in table UNITFIRM" + e);
						 return -1;
					 }
				 } else {
					 log.info("Problem saving units in UNIT table!");
					 return -1;
				 }
				 
				 
			 } catch(Exception e) {
				 log.info("------------------------------Problem saving unit! " + e);
					return -1;
			 }
//			 ArrayList<Firm> firmsOnUnit = new ArrayList<Firm>();
//			 	
//			 for(int i = 0; i<receivedUnit.getFirm().size(); i++) {
//				 firmsOnUnit.add(receivedUnit.getFirms().get(i));
//			 }
//			 int count = 0;
//			 while(count<firmsOnUnit.size()) {
//				 
//				 count++;
//			 }
			 
			 	
//		 	int successUnit = 0;
//          int successFirms = 0;
//			try {
//				successUnit =  iDaoService.add("unit", receivedUnit.getName());
//		 		successFirms = iDaoService.add("unitFirm", receivedUnit.getFirm());
//				return success;
//			} catch(Exception e) {
//				
//				log.info("------------------------------Problem saving unit! " + e);
//				return -1;
//			}
			
				
	 }
	
	
	@RequestMapping(value = "/editunit", method = RequestMethod.POST)
	   public Unit editUnit(@RequestBody HashMap<Object, Unit> unit) {
		 
			Unit receivedUnit = unit.get("unit");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedUnit);
			 
		 	
			try { 
				Unit editedUnit =  (Unit) iDaoService.edit("staff", receivedUnit);
				return editedUnit;
			} catch(Exception e) {
				log.info("------------------------------Problem editing staff! " + e);
				return null;
			}		
	 }
	
	@RequestMapping(value = "/deleteunit", method = RequestMethod.POST)
	   public int deleteUnit(@RequestBody HashMap<Object, Unit> unit) {
		 
			Unit receivedUnit = unit.get("unit");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedUnit);
			 
		 	int success = 0;
			try {
				success =  iDaoService.delete("unit", receivedUnit.getId_unit());
				return success;
			} catch(Exception e) {
				log.info("------------------------------UNIT CONTROLLER ERROR - Problem with delete unit! " + e);
				return -1;
			}		
	 }
	
	@RequestMapping(value = "/getallunits", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<Unit> getAllUnit() {
		
		ArrayList<Unit> allUnits = new ArrayList<Unit>();
		try {
			allUnits = (ArrayList<Unit>) iDaoService.getAll("unit");
			return allUnits;
		} catch (Exception e) {
			log.info("------------------------------Problem getting allUnits! " + e);
			return null;
		}
	}	
	
	@RequestMapping(value = "/getallfirmsonunits", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList<UnitFromClient> getAllFirmsOnUnits() {
		
		ArrayList<UnitFromClient> allFirmsOnUnits = new ArrayList<UnitFromClient>();
		try {
			allFirmsOnUnits =  iUnitFirmService.getAllFirmsOnUnits();
			
			return allFirmsOnUnits;
		} catch (Exception e) {
			log.info("------------------------------Problem getting allFirmsOnUnits! " + e);
			return null;
		}
	}	
}
