package btb.hr.vinoljupci.DAO;

import java.util.ArrayList;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import btb.hr.vinoljupci.IDAO.IDaoCRUD;
import btb.hr.vinoljupci.model.Unit;
import btb.hr.vinoljupci.rowMapper.UnitRowMapper;

@Component
public class DaoUnit implements IDaoCRUD{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(DaoUnit.class);
	@Override
	public void setDataSource(DataSource ds) {
	
	}

	@Override
	public int insertOne(Object obj) {
		
		Unit unit = (Unit)obj;
		try { 
			String sqlUnitSave = "INSERT INTO unit (name) VALUES (?)";	
			Object[] args = new Object[] {unit.getName()};
			int success = jdbcTemplate.update(sqlUnitSave, args);
			
			int idOfLastUnit = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
			log.info("ID is: ----------------"+idOfLastUnit);
			return idOfLastUnit;
		} catch(Exception e) {
			log.info("Error inserting new record in UNIT table, error:  " + e);
			return -1;
		}
	}
	
	@Override
	public int delete(long id) {
		
		int success = 0;
		log.info(""+id);
		try {
			String sqlDeleteFirmsOnUnit = "UPDATE firm SET unit_id=null WHERE unit_id=?";
			Object[] arg = new Object[] {id};
			success = jdbcTemplate.update(sqlDeleteFirmsOnUnit, arg);
			log.info("----------SUCCESS DELETE UNIT"+success);
			if(success >= 0) {
				String sqlDelete = "DELETE from unit WHERE id_unit=?";
				Object[] args = new Object[] {id};
				success = jdbcTemplate.update(sqlDelete, args);		
			}
			return success;
		} catch(Exception e) {
			log.info("ERROR SQL QUERY delete from table UNIT" + e);
			return success;
		}
	}

	@Override
	public Object edit(Object obj) {
		
		Unit unit = (Unit)obj;
		
		try {
			String sqlUpdate = "UPDATE unit " +
								"SET name=? WHERE id_unit=?"; 
			Object[] args = new Object[] {unit.getName(), unit.getId_unit()};
			int success = jdbcTemplate.update(sqlUpdate, args);
			return obj;
		} catch(Exception e) {
			log.info("Problem with SQL - edit UNIT, error: " + e);
			return null;
		}		
	}


	@Override
	public ArrayList<?> getAll() {
		
		ArrayList<?> list = new ArrayList<>();
		String sqlGetAll = "SELECT * FROM unit";
		list = (ArrayList<?>) jdbcTemplate.query(sqlGetAll, new UnitRowMapper());
		return list;
	}
	
	@Override
	public Object getOne(long id) {
		
		String sqlGetOne = "SELECT id_unit, name FROM unit WHERE id_unit=?";
		Object[] args = new Object[] {id};
		Object obj = jdbcTemplate.queryForObject(sqlGetOne, args, new UnitRowMapper());
		return obj;
	}

	@Override
	public int insertMany(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}
}
