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
import btb.hr.vinoljupci.model.Unit;
import btb.hr.vinoljupci.model.User;
import btb.hr.vinoljupci.model.WineEvent;
import btb.hr.vinoljupci.model.WineEventFirm;
import btb.hr.vinoljupci.model.WineEventFromClient;
import btb.hr.vinoljupci.service.DaoService;



@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class WineEventController {
	
		private static final Logger log = LoggerFactory.getLogger(WineEventController.class);
			
		@Autowired
		IDaoService iDaoService;
		@Autowired
		DaoService idaoService;
		
		//---------------------------------  GET  ONE --------------------------------------------- 
		@RequestMapping(value = "/getwineevent/{id}",  produces = "application/json", method = RequestMethod.GET)
		@ResponseBody
		public WineEvent getWineEvent(@RequestParam long id) throws SQLException {
			
			WineEvent wineEvent = null;
			try {
				wineEvent = (WineEvent)iDaoService.getById("wineEvent", id);
				return wineEvent;
			} catch(Exception e) {
				log.info("--------ERROR: " + e);
				return null;
			}
		}
		@RequestMapping(value = "/savewineevent", method = RequestMethod.POST)
		   public int saveWineEvent(@RequestBody HashMap<Object, WineEventFromClient> wineEventFromClient) {
			 
			WineEventFromClient receivedWineEvent = wineEventFromClient.get("wineEvent");
			 	 
			 	log.info("----------------------------------------RECEIVED : " + receivedWineEvent.getName());
			 	log.info("----------------------------------------RECEIVED : " + receivedWineEvent.getEvent_date_end());
			 	log.info("----------------------------------------RECEIVED : " + receivedWineEvent.getEvent_date_start());
			 	log.info("----------------------------------------RECEIVED : " + receivedWineEvent.getFirms().get(0).getName());
			
			
				 	
			 	WineEvent wineEvent = new WineEvent();
		 			wineEvent.setEventDateEnd(receivedWineEvent.getEvent_date_end());
		 			wineEvent.setEventDateStart(receivedWineEvent.getEvent_date_start());
		 			wineEvent.setName(receivedWineEvent.getName());
		 			
		 	
					 int successFirmsAddOnWineEvents = 0;
					 try {
						int idOfLastWineEventInserted = iDaoService.add("wineEvent", wineEvent);
						 if(idOfLastWineEventInserted >= 1) {
							 try {
								 	 wineEvent.setId_wine_event(idOfLastWineEventInserted);
								 	
									 idaoService.addWineEventOnFirm(receivedWineEvent.getFirms(), idOfLastWineEventInserted);
								 	
								 return successFirmsAddOnWineEvents;
								 
							 } catch(Exception e) {
								 log.info("Problem with saving firms in table wine_event_firm " + e);
								 return -1;
							 }
						 } else {
							 log.info("Problem saving  events in WINE EVENT table!");
							 return -1;
						 }
						 
						 
					 } catch(Exception e) {
						 log.info("------------------------------Problem saving unit! " + e);
							return -1;
					 }
		 }
		
		
		@RequestMapping(value = "/editwineevent", method = RequestMethod.POST)
		   public WineEvent editWineEvent(@RequestBody HashMap<Object, WineEvent> wineEvent) {
			 
				WineEvent receivedWineEvent = wineEvent.get("wineEvent");
			 	 
			 	log.info("----------------------------------------RECEIVED : " + receivedWineEvent);
				 
			 	
				try { 
					WineEvent editedWineEvent =  (WineEvent) iDaoService.edit("wineEvent", receivedWineEvent);
					return editedWineEvent;
				} catch(Exception e) {
					log.info("------------------------------Problem editing wineEvent! " + e);
					return null;
				}		
		 }
		
		@RequestMapping(value = "/deletewineevent", method = RequestMethod.POST)
		   public int deleteWineEvent(@RequestBody HashMap<Object, WineEvent> wineEvent) {
			 
				WineEvent receivedWineEvent = wineEvent.get("wineEvent");
			 	 
			 	log.info("----------------------------------------RECEIVED : " + receivedWineEvent);
				 
			 	int success = 0;
				try {
					success =  iDaoService.delete("wineEvent", receivedWineEvent.getId_wine_event());
					return success;
				} catch(Exception e) {
					log.info("------------------------------WINEEVENT CONTROLLER ERROR - Problem with delete wineEvent! " + e);
					return -1;
				}		
		 }
		
		@RequestMapping(value = "/getallwineevents", method = RequestMethod.GET)
		@ResponseBody
		public ArrayList<WineEvent> getAllWineEvent() {
			
			ArrayList<WineEvent> allWineEvents = new ArrayList<WineEvent>();
			try {
				allWineEvents = (ArrayList<WineEvent>) iDaoService.getAll("wineEvent");
				return allWineEvents;
			} catch (Exception e) {
				log.info("------------------------------Problem getting allWineEvents! " + e);
				return null;
			}
		}	
		
		@RequestMapping(value = "/getallfirmsonwineevents", method = RequestMethod.GET)
		@ResponseBody
		public ArrayList<WineEventFirm> getAllFirmsOnWineEvent() {
			
			ArrayList<WineEventFirm> firmsOnWineEvents = new ArrayList<WineEventFirm>();
			try {
				firmsOnWineEvents = (ArrayList<WineEventFirm>) idaoService.getAllFirmsOnWineEvents();
				return firmsOnWineEvents;
			} catch (Exception e) {
				log.info("------------------------------Problem getting allWineEvents! " + e);
				return null;
			}
		}	

}
