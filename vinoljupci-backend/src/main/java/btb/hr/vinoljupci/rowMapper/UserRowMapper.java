package btb.hr.vinoljupci.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.Region;
import btb.hr.vinoljupci.model.Role;
import btb.hr.vinoljupci.model.User;

public class UserRowMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			
			//select * from user u, rola r, firm f, region reg where u.user_role = r.id and u.user_firm = f.id and u.user_region = reg.id 
			
			Role role = new Role();
			role.setId_role(rs.getInt("r.id_role"));
			role.setName(rs.getString("r.name"));
			
			Firm firm = new Firm();
				firm.setId_firm(rs.getLong("f.id_firm"));
				firm.setName(rs.getString("f.name"));
				firm.setAddress(rs.getString("f.address"));
				firm.setOib(rs.getLong("f.oib"));
						
			Region reg = new Region();
				reg.setId_region(rs.getInt("reg.id_region"));
				reg.setName(rs.getString("reg.name"));
			
			User user = new User();
			user.setId_user(rs.getLong("u.id_user"));
		 	user.setRole(role);
		 	user.setOib(rs.getLong("u.oib"));
		 	user.setName(rs.getString("u.name"));
		 	user.setSurname(rs.getString("u.surname"));
		 	user.setMail(rs.getString("u.mail"));
		 	user.setPassword(rs.getString("u.password"));
		 	user.setMobile(rs.getLong("u.mobile"));
		 	user.setFirm(firm);  
			user.setRegion(reg);
			
			return user;
		}
		

	}


