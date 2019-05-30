package btb.hr.vinoljupci.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import btb.hr.vinoljupci.model.FilterQueryToClient;

public class FilterQueryEventRowMapper implements RowMapper<FilterQueryToClient>{

	@Override
	public FilterQueryToClient mapRow(ResultSet rs, int rowNum) throws SQLException {
		FilterQueryToClient filterQueryToClient = new FilterQueryToClient();
		filterQueryToClient.setWineEventName(rs.getString("w.name"));
		
		filterQueryToClient.setBillSum(rs.getInt("b.bill_sum"));
		
		filterQueryToClient.setTimestamp(rs.getDate("b.timestamp"));
		return filterQueryToClient;
	}

}
