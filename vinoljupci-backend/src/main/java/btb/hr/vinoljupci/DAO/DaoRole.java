package btb.hr.vinoljupci.DAO;

import java.util.ArrayList;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import btb.hr.vinoljupci.IDAO.IDaoCRUD;
import btb.hr.vinoljupci.model.Role;
import btb.hr.vinoljupci.rowMapper.RoleRowMapper;

@Component
public class DaoRole implements IDaoCRUD{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(DaoRole.class);
	
	@Override
	public void setDataSource(DataSource ds) {
	
	}
	
	@Override
	public int insertOne(Object obj) {
		
		Role role = (Role)obj;
		try { 
			String sqlRoleSave = "INSERT INTO role (name) VALUES (?)";	
			Object[] args = new Object[] {role.getName()};
			int success = jdbcTemplate.update(sqlRoleSave, args);
			return success;

		} catch(Exception e) {
			log.info("Error inserting new record in ROLE table" + e);
			return -1;
		}
	}
	
	@Override
	public int delete(long id) {
		
		int success = 0;
		
		try {
			String sqlDelete = "DELETE from role WHERE id_role=?";
			Object[] args = new Object[] {id};
			success = jdbcTemplate.update(sqlDelete, args);
			return success;
		} catch(Exception e) {
			log.info("ERROR SQL QUERY delete from table ROLE" + e);
			return success;
		}
	}

	@Override
	public Object edit(Object obj) {
		
		Role role = (Role)obj;
		
		try {
			String sqlUpdate = "UPDATE role (name) SET name=? WHERE id_role=?"; 
			Object[] args = new Object[] {role.getName()};
			int success = jdbcTemplate.update(sqlUpdate, args, new RoleRowMapper());
			if(success == 1) {
					try {
						String sqlGetUpdatedObject = "SELECT * FROM role WHERE id_role= ?";
					    Object[] arg=  new Object[]{role.getId_role()}; 
					    Role updatedRole = jdbcTemplate.queryForObject(sqlGetUpdatedObject, arg, new RoleRowMapper());
					      return updatedRole;
					} catch(Exception e) {
						log.info("Problem with getting updated role - DaoRole.edit()");
						return null;
					}
			} else {
				throw new Exception();
			}
			
		} catch(Exception e) {
			return null;
		}		
	}


	@Override
	public ArrayList<?> getAll() {
		
		ArrayList<?> list = new ArrayList<>();
		String sqlGetAll = "SELECT * FROM role";
		list = (ArrayList<?>) jdbcTemplate.query(sqlGetAll,new RoleRowMapper());
		return list;
	}
	
	@Override
	public Object getOne(long id) {
		
		String sqlGetOne = "SELECT id_role, name FROM role WHERE id_role=?";
		Object[] args = new Object[] {id};
		Object obj = jdbcTemplate.queryForObject(sqlGetOne, args, new RoleRowMapper());
		return obj;
	}

	@Override
	public int insertMany(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
