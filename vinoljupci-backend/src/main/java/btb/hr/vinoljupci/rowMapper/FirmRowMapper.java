package btb.hr.vinoljupci.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.Unit;

public class FirmRowMapper implements RowMapper<Firm> {

	public Firm mapRow(ResultSet rs, int rowNum) throws SQLException {
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
			return firm;
	}

}
