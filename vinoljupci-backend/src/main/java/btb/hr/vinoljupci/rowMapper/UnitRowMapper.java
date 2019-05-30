package btb.hr.vinoljupci.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.Unit;

public class UnitRowMapper implements RowMapper<Unit> {

	public Unit mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Unit unit = new Unit();
		unit.setId_unit(rs.getLong("id_unit"));
		unit.setName(rs.getString("name"));
		
		return unit;
	}

}


