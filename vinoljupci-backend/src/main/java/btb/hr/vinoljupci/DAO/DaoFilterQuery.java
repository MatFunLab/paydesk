package btb.hr.vinoljupci.DAO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import btb.hr.vinoljupci.IDAO.IDaoFilterQuery;
import btb.hr.vinoljupci.controller.FirmController;
import btb.hr.vinoljupci.model.Artikl;
import btb.hr.vinoljupci.model.FilterQueryFromClient;
import btb.hr.vinoljupci.model.FilterQueryToClient;
import btb.hr.vinoljupci.model.FilterQueryFromClient;
import btb.hr.vinoljupci.model.FilterQueryFromClient;
import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.rowMapper.FilterQueryEventRowMapper;
import btb.hr.vinoljupci.rowMapper.FilterQueryRowMapper;
import btb.hr.vinoljupci.rowMapper.FirmRowMapper;

@Component
public class DaoFilterQuery implements IDaoFilterQuery{
	
	private static final Logger log = LoggerFactory.getLogger(DaoFilterQuery.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public void setDataSource(DataSource ds) {
	}

	@Override
	public ArrayList<FilterQueryToClient> getTotalRevenueOfSpecificEvent(long eventId) {
		
		String sqlFilterQuery = "SELECT w.name, b.timestamp, b.bill_sum FROM wine_event_firm wf cross join bill_parts bp " + 
				" inner JOIN wine_event w on wf.wine_event_id=w.id_wine_event " + 
				" inner join bill b on bp.bill_id=b.id_bill " + 
				" where w.id_wine_event=?";
		Object[] args = new Object[] {eventId};
		 ArrayList<FilterQueryToClient> filterQueryEvent = (ArrayList<FilterQueryToClient>) jdbcTemplate.query(sqlFilterQuery, args, new FilterQueryEventRowMapper()); 
		 return filterQueryEvent;
	}

	public ArrayList<FilterQueryToClient> getTotalRevenueOfFirmOnSpecificEvent(long eventId, long firmId) {

			
			String sqlFilterQuery = "SELECT w.name, f.name, f.percentage, b.timestamp, b.bill_sum, u.mail FROM wine_event_firm wf cross join bill_parts bp cross join user u" + 
			 		" inner join wine_event w on wf.wine_event_id=w.id_wine_event " + 
			 		" inner join firm f on wf.firm_id=f.id_firm " + 
			 		" inner join bill b on bp.bill_id=b.id_bill " + 
			 		" inner join artikli a on bp.artikl=a.id_artikli " + 
			 		" where f.id_firm=? and w.id_wine_event=? and a.firm=? and u.firm=f.id_firm";
			Object[] args = new Object[] {firmId, eventId, firmId};
			 ArrayList<FilterQueryToClient> filterQueryFirm = (ArrayList<FilterQueryToClient>) jdbcTemplate.query(sqlFilterQuery, args, new FilterQueryRowMapper()); 
			 return filterQueryFirm;
	
	}

	@Override
	public ArrayList<FilterQueryToClient> getRevenueOfSpecificFirmOnEventInPeriod(long firmId, long eventId, Date startDate, Date endDate) {
		
		String sqlFilterQuery = "SELECT w.name, f.name, f.percentage, b.timestamp, b.bill_sum, u.mail FROM wine_event_firm wf cross join bill_parts bp cross join user u" + 
		 		" inner join wine_event w on wf.wine_event_id=w.id_wine_event " + 
		 		" inner join firm f on wf.firm_id=f.id_firm " + 
		 		" inner join bill b on bp.bill_id=b.id_bill " + 
		 		" inner join artikli a on bp.artikl=a.id_artikli " + 
		 		" where f.id_firm=? and w.id_wine_event=? and a.firm=? BETWEEN " + startDate + " AND " + endDate;
		Object[] args = new Object[] {firmId, eventId, firmId};
		 ArrayList<FilterQueryToClient> filterQueryFirm = (ArrayList<FilterQueryToClient>) jdbcTemplate.query(sqlFilterQuery, args, new FilterQueryRowMapper()); 
		 return filterQueryFirm;
	}

	@Override
	public ArrayList<FilterQueryToClient> getRevenueOfSpecificEventInPeriod(long eventId, Date startDate, Date endDate) {
		
		return null;
	}
}
	


