package btb.hr.vinoljupci.DAO;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import btb.hr.vinoljupci.IDAO.IDaoLogin;
import btb.hr.vinoljupci.controller.LoginController;
import btb.hr.vinoljupci.model.Admin;
import btb.hr.vinoljupci.model.Credentials;
import btb.hr.vinoljupci.model.Staff;
import btb.hr.vinoljupci.model.User;
import btb.hr.vinoljupci.rowMapper.AdminRowMapper;
import btb.hr.vinoljupci.rowMapper.StaffRowMapper;
import btb.hr.vinoljupci.rowMapper.UserRowMapper;

@Component
public class DaoLogin implements IDaoLogin{

	private static final Logger log = LoggerFactory.getLogger(DaoLogin.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;	
	
public Object login(Credentials credentials) {
		
	try {
		String sqlLoginAdmin = "SELECT * FROM organiser a, role r WHERE a.mail=? AND a.password=? AND a.role=r.id_role";
		Object[] args = new Object[] {credentials.getUserMail(), credentials.getUserPassword()};
		Admin adminLogin = jdbcTemplate.queryForObject(sqlLoginAdmin, args, new AdminRowMapper());
			return adminLogin;
		} catch(EmptyResultDataAccessException e) {
			log.info("Admin not found--- " + e);
			
			try {
				String sqlLoginUsers = "SELECT * FROM user u, role r, firm f, region reg WHERE u.role = r.id_role AND u.firm = f.id_firm AND u.region = reg.id_region AND u.mail=? AND u.password=?";  
				Object[] args = new Object[] {credentials.getUserMail(), credentials.getUserPassword()};
				User usrLogin = jdbcTemplate.queryForObject(sqlLoginUsers, args, new UserRowMapper());
				return usrLogin;
			} catch(EmptyResultDataAccessException e1) {
				log.info("User not found--- " + e1);
				try {
					//query for staff
					String sqlLoginStaff = "SELECT * FROM staff st, shift sh, role r WHERE st.mail=? AND st.password=? AND st.shift=sh.id_shift and st.role=r.id_role";
					Object[] args = new Object[] {credentials.getUserMail(), credentials.getUserPassword()};
					Staff staffLogin = jdbcTemplate.queryForObject(sqlLoginStaff, args, new StaffRowMapper());
					return staffLogin;
					} catch(EmptyResultDataAccessException e2) {
						log.info("Staff not found--- " + e2);
						return null;
					}
			}
		}
		
	}

	

@Override
public void setDataSource(DataSource ds) {
	// TODO Auto-generated method stub
	
}
	
}
