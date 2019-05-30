package btb.hr.vinoljupci.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import btb.hr.vinoljupci.controller.UnitController;
import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.UnitFromClient;
import btb.hr.vinoljupci.model.WineEvent;
import btb.hr.vinoljupci.model.WineEventFirm;
import btb.hr.vinoljupci.model.WineEventFromClient;
import btb.hr.vinoljupci.rowMapper.UnitFirmToClientRowMapper;
import btb.hr.vinoljupci.rowMapper.WineEventFirmRowMapper;

@Component
public class DaoWineEventFirm {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(DaoWineEventFirm.class);
	
		public void setDataSource(DataSource ds) {	
		}
	
		public int insertWineEventAndFirms(ArrayList<Firm> firms, long wineEventId) {
				
			
			
			try {
				 jdbcTemplate.batchUpdate("INSERT INTO wine_event_firm (wine_event_id, firm_id) VALUES (?, ?)",
					        new BatchPreparedStatementSetter() {
					            @Override
					            public void setValues(PreparedStatement ps, int i) throws SQLException {
					                
					                ps.setLong(1, wineEventId);
					                ps.setLong(2, firms.get(i).getId_firm());
					                
					            }
					            @Override
					            public int getBatchSize() {
					                return firms.size();
					            }
					        });
				 return 1;
			} catch(Exception e) {
				log.info("Error in DAO WINEEVENT FIRM, problem with multiple insertions in wine_event_firm table----" + e);
				return -1;
			}	
		}
		
		public ArrayList<WineEventFirm> getAllFirmsOnWineEvents() {
			String sqlSelectFirmsOnWineEvents = "SELECT * FROM wine_event_firm wf, wine_event w, firm f, "
												+ "unit u where wf.wine_event_id=w.id_wine_event and wf.firm_id=f.id_firm "
												+ "and f.unit_id=u.id_unit";  
			
			ArrayList<WineEventFirm> list =  (ArrayList<WineEventFirm>) jdbcTemplate.query(sqlSelectFirmsOnWineEvents, new WineEventFirmRowMapper());
			
			return list;	
		}
}
