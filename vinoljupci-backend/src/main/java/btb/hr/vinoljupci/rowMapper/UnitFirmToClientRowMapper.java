package btb.hr.vinoljupci.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.jdbc.core.RowMapper;

import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.Unit;
import btb.hr.vinoljupci.model.UnitFromClient;

public class UnitFirmToClientRowMapper implements RowMapper<UnitFromClient> {

	@Override
	public UnitFromClient mapRow(ResultSet rs, int rowNum) throws SQLException {
		UnitFromClient unitFromClient = new UnitFromClient();
			Firm firm = new Firm();
			firm.setId_firm(rs.getInt("f.id_firm")); 
			firm.setName(rs.getString("f.name"));
			firm.setOib(rs.getLong("f.oib"));
			firm.setAddress(rs.getString("f.address"));
		
		ArrayList<Firm> firms = new ArrayList<Firm>();
			firms.add(firm);
			
		Unit unit = new Unit();
			unit.setId_unit(rs.getLong("u.id_unit"));
			unit.setName(rs.getString("u.name"));
			
		unitFromClient.setId(rs.getLong("u.id_unit"));
		unitFromClient.setName(rs.getString("u.name"));
		unitFromClient.setFirm(firms);
	
	return unitFromClient;
		
	}

}
