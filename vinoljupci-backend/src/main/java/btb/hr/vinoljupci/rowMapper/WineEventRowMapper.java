package btb.hr.vinoljupci.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import btb.hr.vinoljupci.model.WineEvent;

public class WineEventRowMapper implements RowMapper<WineEvent>{

	public WineEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
		WineEvent event = new WineEvent();
		
			event.setId_wine_event(rs.getLong("id_wine_event"));
			event.setName(rs.getString("name"));
			event.setEventDateStart(rs.getDate("event_date_start"));
			event.setEventDateEnd(rs.getDate("event_date_end"));
			
		return event;
	}
	
}


