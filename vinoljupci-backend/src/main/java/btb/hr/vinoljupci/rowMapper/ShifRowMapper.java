package btb.hr.vinoljupci.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import btb.hr.vinoljupci.model.Shift;

public class ShifRowMapper implements RowMapper<Shift>{

	public Shift mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Shift shift = new Shift();
			shift.setId_shift(rs.getLong("id_shift"));
			shift.setName(rs.getString("name"));
			
		return shift;
	}

}
