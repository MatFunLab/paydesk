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
import org.springframework.web.bind.annotation.RestController;

import IService.IFilterQueryService;
import btb.hr.vinoljupci.model.FilterQueryFromClient;
import btb.hr.vinoljupci.model.FilterQueryToClient;
import btb.hr.vinoljupci.model.FilterQueryFromClient;

@CrossOrigin(origins = "http://localhost:4200")

@RestController
public class FilterQueryController {
	
	private static final Logger log = LoggerFactory.getLogger(FilterQueryController.class);
	
	@Autowired
	IFilterQueryService iFilterQueryService;
	
	@RequestMapping(value = "/getfilterquery", method = RequestMethod.POST)
	   public  ArrayList<FilterQueryToClient> getFilterQuery(@RequestBody HashMap<Object, FilterQueryFromClient> filterQuery) {
		 
		FilterQueryFromClient receivedFilterQuery = filterQuery.get("filterQuery");
		 	 
		 	log.info("----------------------------------------RECEIVED : " + receivedFilterQuery);
			 try {
				 ArrayList<FilterQueryToClient> filter = iFilterQueryService.filter(receivedFilterQuery);
				 
				 return filter;
			 } catch(Exception e) {
				 log.info("Problem getting filter" + e);
				 return null;
			 }
		
	 }
}
