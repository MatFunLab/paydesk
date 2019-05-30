package btb.hr.vinoljupci.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import btb.hr.vinoljupci.model.FilterQueryToClient;

public class FilterQueryRowMapper implements RowMapper<FilterQueryToClient>{

	@Override
	public FilterQueryToClient mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		FilterQueryToClient filterQueryToClient = new FilterQueryToClient();
		filterQueryToClient.setWineEventName(rs.getString("w.name"));
		filterQueryToClient.setFirmName(rs.getString("f.name"));
		filterQueryToClient.setBillSum(rs.getInt("b.bill_sum"));
		filterQueryToClient.setPercentage(rs.getInt("f.percentage"));
		filterQueryToClient.setTimestamp(rs.getDate("b.timestamp"));
		filterQueryToClient.setUserMail(rs.getString("u.mail"));
		
		return filterQueryToClient;
	}

}
