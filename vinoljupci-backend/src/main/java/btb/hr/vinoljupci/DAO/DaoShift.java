package btb.hr.vinoljupci.DAO;

import java.util.ArrayList;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import btb.hr.vinoljupci.IDAO.IDaoCRUD;
import btb.hr.vinoljupci.model.Shift;
import btb.hr.vinoljupci.rowMapper.ShifRowMapper;

@Component
public class DaoShift implements IDaoCRUD{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(DaoShift.class);
	
	@Override
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int insertOne(Object obj) {
		
		Shift shift = (Shift)obj;
		try { 
			String sqlShiftSave = "INSERT INTO shift (name) VALUES (?)";	
			Object[] args = new Object[] {shift.getName()};
			int success = jdbcTemplate.update(sqlShiftSave, args);
			return success;

		} catch(Exception e) {
			log.info("Error inserting new record in SHIFT table" + e);
			return -1;
		}
	}
	
	@Override
	public int delete(long id) {
		
		int success = 0;
		
		try {
			String sqlDelete = "DELETE from shift WHERE id_shift=?";
			Object[] args = new Object[] {id};
			success = jdbcTemplate.update(sqlDelete, args);
			return success;
		} catch(Exception e) {
			log.info("ERROR SQL QUERY delete from table SHIFT" + e);
			return success;
		}
	}

	@Override
	public Object edit(Object obj) {
		
		Shift shift = (Shift)obj;
		
		try {
			String sqlUpdate = "UPDATE shift (name) SET name=? WHERE id_shift=?"; 
			Object[] args = new Object[] {shift.getId_shift()};
			int success = jdbcTemplate.update(sqlUpdate, args, new ShifRowMapper());
			if(success == 1) {
					try {
						String sqlGetUpdatedObject = "SELECT * FROM shift WHERE id_shift= ?";
					    Object[] arg=  new Object[]{shift.getId_shift()}; 
					    Shift updatedShift = jdbcTemplate.queryForObject(sqlGetUpdatedObject, arg, new ShifRowMapper());
					      return updatedShift;
					} catch(Exception e) {
						log.info("Problem with getting updated shift - DaoShift.edit()");
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
		String sqlGetAll = "SELECT * FROM shift";
		list = (ArrayList<?>) jdbcTemplate.query(sqlGetAll, new ShifRowMapper());
		
		return list;
	}
	
	@Override
	public Object getOne(long id) {
		
		String sqlGetOne = "SELECT id_shift, name FROM shift WHERE id_shift=?";
		Object[] args = new Object[] {id};
		Object obj = jdbcTemplate.queryForObject(sqlGetOne, args, new ShifRowMapper());
		return obj;
	}

	@Override
	public int insertMany(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

}
