package btb.hr.vinoljupci.DAO;

import java.util.ArrayList;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import btb.hr.vinoljupci.IDAO.IDaoCRUD;
import btb.hr.vinoljupci.model.User;
import btb.hr.vinoljupci.rowMapper.UserRowMapper;

@Component
public class DaoUser implements IDaoCRUD{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(DaoUser.class);
	
	@Override
	public void setDataSource(DataSource ds) {
	
	}

	@Override
	public int insertOne(Object obj) {
		
		User user = (User)obj;
		
		try { 
			String sqlUserSave = "INSERT INTO user (role, oib, name, surname, mail, password, mobile, firm, region) VALUES (?,?,?,?,?,?,?,?,?)";	
			Object[] args = new Object[] {user.getRole().getId_role(), user.getOib(), user.getName(), user.getSurname(), user.getMail(), user.getPassword(), 
										  user.getMobile(), user.getFirm().getId_firm(), user.getRegion().getId_region()};
			int success = jdbcTemplate.update(sqlUserSave, args);
			return success;

		} catch(Exception e) {
			log.info("Error inserting new record in USER table" + e);
			return -1;
		}
	}
	
	@Override
	public int delete(long id) {
		
		int success = 0;
		//getting idFirm
		String sqlFindFirmId = "SELECT firm from user WHERE id_user=?";
		Object[] arguments = new Object[] {id};
		long firmId = jdbcTemplate.queryForObject(sqlFindFirmId, arguments, Integer.class);
		
		try {
			String sqlDeleteUser = "DELETE from user WHERE id_user=?";
			Object[] args = new Object[] {id};
			success = jdbcTemplate.update(sqlDeleteUser, args);
			// ----------------------------------------------------------
			if(success >= 0) {
				String sqlDeleteArtikli = "DELETE from artikli WHERE firm=?";
				Object[] argss = new Object[] {firmId};
				success = jdbcTemplate.update(sqlDeleteArtikli, argss);
				if(success >= 0) {
					String sqlDeleteFirm = "DELETE from firm WHERE id_firm=?";
					Object[] arg = new Object[] {firmId};
					success = jdbcTemplate.update(sqlDeleteFirm, arg);
					return success;
				} else {
					return -1;
				}
				
			} else {
				return -1;
			}
			//-------------------------------------------------------------
//			return success;
		} catch(Exception e) {
			log.info("ERROR SQL QUERY delete from table USER" + e);
			return success;
		}
	}

	@Override
	public Object edit(Object obj) {
		
		User user = (User)obj;
		
		try {
			String sqlUpdate = "UPDATE user " +
								"SET role=?, oib=?, name=?, surname=?, mail=?, password=?, mobile=?, firm=?, region=? WHERE id_user=?"; 
			Object[] args = new Object[] {user.getRole().getId_role(), user.getOib(), user.getName(), user.getSurname(), user.getMail(), user.getPassword(),
										  user.getMobile(), user.getFirm().getId_firm(), user.getRegion().getId_region(), user.getId_user()};
			int success = jdbcTemplate.update(sqlUpdate, args);
			return obj;
		} catch(Exception e) {
			log.info("Problem with SQL - edit USER, error: " + e);
			return null;
		}		
	}


	@Override
	public ArrayList<?> getAll() {
		
		ArrayList<?> list = new ArrayList<>();
		String sqlGetAll = "SELECT * FROM user u, role r, firm f, region reg WHERE u.role=r.id_role AND u.firm=f.id_firm AND u.region=reg.id_region ORDER BY u.id_user ASC";				
		list = (ArrayList<?>) jdbcTemplate.query(sqlGetAll, new UserRowMapper());
		
		return list;
	}
	
	@Override
	public Object getOne(long id) {
		
		String sqlGetOne = "SELECT r.name, u.oib, u.name, u.surname, u.mail, u.password, u.mobile, f.name, reg.name FROM user u " + 
							"INNER JOIN role r ON u.role=r.id_role " + 
							" INNER JOIN firm f ON u.firm=f.id_firm " + 
							" INNER JOIN region reg ON u.region=reg.id_region"
							+ " WHERE u.id_user=?";
		Object[] args = new Object[] {id};
		Object obj = jdbcTemplate.queryForObject(sqlGetOne, args, new UserRowMapper());
		return obj;
	}

	@Override
	public int insertMany(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}


}
