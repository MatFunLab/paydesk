package btb.hr.vinoljupci.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.Unit;
import btb.hr.vinoljupci.model.WineEvent;
import btb.hr.vinoljupci.model.WineEventFirm;

public class WineEventFirmRowMapper implements RowMapper<WineEventFirm> {

	@Override
	public WineEventFirm mapRow(ResultSet rs, int rowNum) throws SQLException {
		WineEventFirm wineEventFirm = new WineEventFirm();
		
		Unit unit = new Unit();
		unit.setId_unit(rs.getLong("u.id_unit"));
		unit.setName(rs.getString("u.name"));
		
		Firm firm = new Firm();
			firm.setId_firm(rs.getInt("f.id_firm")); 
			firm.setName(rs.getString("f.name"));
			firm.setOib(rs.getLong("f.oib"));
			firm.setAddress(rs.getString("f.address"));
			firm.setUnit_id(unit);
			firm.setPercentage(rs.getInt("f.percentage"));
			
			WineEvent wineEvent = new WineEvent();
			
			wineEvent.setId_wine_event(rs.getLong("w.id_wine_event"));
			wineEvent.setName(rs.getString("w.name"));
			wineEvent.setEventDateStart(rs.getDate("w.event_date_start"));
			wineEvent.setEventDateEnd(rs.getDate("w.event_date_end"));
			
			wineEventFirm.setFirm(firm);
			wineEventFirm.setWineEvent(wineEvent);
		return wineEventFirm;
	}
	
}
