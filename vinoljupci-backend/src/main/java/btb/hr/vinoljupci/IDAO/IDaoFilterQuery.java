package btb.hr.vinoljupci.IDAO;

import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import btb.hr.vinoljupci.model.FilterQueryFromClient;
import btb.hr.vinoljupci.model.FilterQueryToClient;
import btb.hr.vinoljupci.model.FilterQueryFromClient;
import btb.hr.vinoljupci.model.FilterQueryFromClient;

public interface IDaoFilterQuery {
	
	public void setDataSource(DataSource ds);
	
	public ArrayList<FilterQueryToClient> getTotalRevenueOfSpecificEvent(long eventId);
	
	public  ArrayList<FilterQueryToClient> getTotalRevenueOfFirmOnSpecificEvent(long eventId, long firmId);
	
	public  ArrayList<FilterQueryToClient> getRevenueOfSpecificFirmOnEventInPeriod(long firmId, long eventId, Date startDate, Date endDate);
	
	public  ArrayList<FilterQueryToClient> getRevenueOfSpecificEventInPeriod(long eventId, Date startDate, Date endDate);
	
}
