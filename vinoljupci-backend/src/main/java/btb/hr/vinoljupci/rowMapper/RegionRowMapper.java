package btb.hr.vinoljupci.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import btb.hr.vinoljupci.model.Region;

public class RegionRowMapper implements RowMapper<Region>{

	public Region mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Region region = new Region();
			region.setId_region(rs.getInt("id_region"));
			region.setName(rs.getString("name"));
		
		return region;
	}

}
