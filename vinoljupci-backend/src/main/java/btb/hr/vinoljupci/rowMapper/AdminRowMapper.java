package btb.hr.vinoljupci.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import btb.hr.vinoljupci.model.Admin;
import btb.hr.vinoljupci.model.Role;

public class AdminRowMapper implements RowMapper<Admin> {

	@Override
	public Admin mapRow(ResultSet rs, int rowNum) throws SQLException {
		Admin admin = new Admin();
			Role role = new Role();
				role.setId_role(rs.getInt("r.id_role"));
				role.setName(rs.getString("r.name"));
			admin.setId_admin(rs.getInt("a.id_admin"));
			admin.setName(rs.getString("a.name"));
			admin.setSurname(rs.getString("a.surname"));
			admin.setRole(role);
			
		return admin;
	}

}
