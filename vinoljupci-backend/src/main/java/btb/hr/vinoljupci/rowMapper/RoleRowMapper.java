package btb.hr.vinoljupci.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import btb.hr.vinoljupci.model.Role;

public class RoleRowMapper implements RowMapper<Role>{

	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		Role role = new Role();
			role.setId_role(rs.getInt("id_role"));
			role.setName(rs.getString("name"));
			
		return role;
	}

}
