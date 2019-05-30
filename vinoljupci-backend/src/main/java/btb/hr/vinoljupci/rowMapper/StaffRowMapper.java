package btb.hr.vinoljupci.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import btb.hr.vinoljupci.model.Role;
import btb.hr.vinoljupci.model.Shift;
import btb.hr.vinoljupci.model.Staff;


public class StaffRowMapper implements RowMapper<Staff> {

		public Staff mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			Staff staff = new Staff();
				Shift shift = new Shift();
					shift.setId_shift(rs.getInt("sh.id_shift"));
					shift.setName(rs.getString("sh.name"));
			
				Role role = new Role();
					role.setId_role(rs.getInt("r.id_role"));
					role.setName(rs.getString("r.name"));
			staff.setId_staff(rs.getInt("st.id_staff"));
			staff.setOib(rs.getLong("st.oib"));
			staff.setName(rs.getString("st.name"));
			staff.setSurname(rs.getString("st.surname"));
			staff.setMail(rs.getString("st.mail"));
			staff.setPassword(rs.getString("st.password"));
			staff.setMobile(rs.getLong("st.mobile"));
			staff.setShift(shift);
			staff.setRole_id(role);
			return staff;
		}	
}
