package btb.hr.vinoljupci.service;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import IService.IFilterQueryService;
import btb.hr.vinoljupci.DAO.DaoFilterQuery;
import btb.hr.vinoljupci.controller.ArtiklController;
import btb.hr.vinoljupci.model.FilterQueryFromClient;
import btb.hr.vinoljupci.model.FilterQueryToClient;
import btb.hr.vinoljupci.model.FilterQueryFromClient;
import btb.hr.vinoljupci.model.FilterQueryFromClient;

@Service("IFilterQueryService")
public class FilterQueryService implements IFilterQueryService {
	
	private static final Logger log = LoggerFactory.getLogger(FilterQueryService.class);
	
	@Autowired
	DaoFilterQuery daoFilterQuery;

	@Override
	public  ArrayList<FilterQueryToClient> filter(FilterQueryFromClient filterQuery) throws SQLException {
		
		if(filterQuery.getEndDate() == null && filterQuery.getStartDate() == null && filterQuery.getFirm() != null) {
			ArrayList<FilterQueryToClient> filter = daoFilterQuery.getTotalRevenueOfFirmOnSpecificEvent(filterQuery.getWineEvent().getId_wine_event(), filterQuery.getFirm().getId_firm());
			
			int sum = 0;
			for (FilterQueryToClient item : filter) {
				sum += item.getBillSum();
			}
			FilterQueryToClient filterQueryToClient = new FilterQueryToClient();
			filterQueryToClient.setBillSum(sum);
			filterQueryToClient.setFirmName(filter.get(0).getFirmName());
			filterQueryToClient.setPercentage(filter.get(0).getPercentage());
			filterQueryToClient.setTimestamp(filter.get(0).getTimestamp());
			filterQueryToClient.setWineEventName(filter.get(0).getWineEventName());
			filterQueryToClient.setUserMail(filter.get(0).getUserMail());
			
			ArrayList<FilterQueryToClient> result = new ArrayList<FilterQueryToClient>();
			result.add(filterQueryToClient);
			 return result;
			 
		} else if(filterQuery.getFirm() == null && filterQuery.getEndDate() == null && filterQuery.getStartDate() == null) {
			
			ArrayList<FilterQueryToClient> filter = daoFilterQuery.getTotalRevenueOfSpecificEvent(filterQuery.getWineEvent().getId_wine_event());
			int sum = 0;
			for (FilterQueryToClient item : filter) {
				sum += item.getBillSum();
			}
			FilterQueryToClient filterQueryToClient = new FilterQueryToClient();
			filterQueryToClient.setBillSum(sum);
			filterQueryToClient.setTimestamp(filter.get(0).getTimestamp());
			filterQueryToClient.setWineEventName(filter.get(0).getWineEventName());
			
			ArrayList<FilterQueryToClient> result = new ArrayList<FilterQueryToClient>();
			result.add(filterQueryToClient);
			 return result;
			
		} else if(filterQuery.getFirm() != null && filterQuery.getEndDate() != null && filterQuery.getStartDate() != null) {
			
			ArrayList<FilterQueryToClient> filter = daoFilterQuery.getRevenueOfSpecificFirmOnEventInPeriod(filterQuery.getFirm().getId_firm(), filterQuery.getWineEvent().getId_wine_event(), filterQuery.getStartDate(), filterQuery.getEndDate());
			int sum = 0;
			for (FilterQueryToClient item : filter) {
				sum += item.getBillSum();
			}
			FilterQueryToClient filterQueryToClient = new FilterQueryToClient();
			filterQueryToClient.setBillSum(sum);
			
			filterQueryToClient.setTimestamp(filter.get(0).getTimestamp());
			filterQueryToClient.setWineEventName(filter.get(0).getWineEventName());
			
			ArrayList<FilterQueryToClient> result = new ArrayList<FilterQueryToClient>();
			result.add(filterQueryToClient);
			 return result;
			 
		} else if(filterQuery.getFirm() == null && filterQuery.getEndDate() != null && filterQuery.getStartDate() != null) {
			
			ArrayList<FilterQueryToClient> filter = daoFilterQuery.getRevenueOfSpecificEventInPeriod(filterQuery.getWineEvent().getId_wine_event(), filterQuery.getStartDate(), filterQuery.getEndDate());
			int sum = 0;
			for (FilterQueryToClient item : filter) {
				sum += item.getBillSum();
			}
			FilterQueryToClient filterQueryToClient = new FilterQueryToClient();
			filterQueryToClient.setBillSum(sum);
			
			filterQueryToClient.setTimestamp(filter.get(0).getTimestamp());
			filterQueryToClient.setWineEventName(filter.get(0).getWineEventName());
			
			ArrayList<FilterQueryToClient> result = new ArrayList<FilterQueryToClient>();
			result.add(filterQueryToClient);
			 return result;
		} else {
			return null;
		}
	}

}
