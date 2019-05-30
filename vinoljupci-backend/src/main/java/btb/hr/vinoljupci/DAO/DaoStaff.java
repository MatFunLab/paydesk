package btb.hr.vinoljupci.DAO;

import java.util.ArrayList;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import btb.hr.vinoljupci.IDAO.IDaoCRUD;
import btb.hr.vinoljupci.model.Staff;
import btb.hr.vinoljupci.rowMapper.StaffRowMapper;

@Component
public class DaoStaff implements IDaoCRUD{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(DaoStaff.class);
	
	@Override
	public void setDataSource(DataSource ds) {
	
	}

	@Override
	public int insertOne(Object obj) {
		
		Staff staff = (Staff)obj;
		try { 
			String sqlStaffSave = "INSERT INTO staff (oib, name, surname, mail, password, mobile, shift, role) VALUES (?,?,?,?,?,?,?,?)";	
			Object[] args = new Object[] {staff.getOib(), staff.getName(), staff.getSurname(), staff.getMail(), 
										   staff.getPassword(), staff.getMobile(), staff.getShift().getId_shift(), 3};
			int success = jdbcTemplate.update(sqlStaffSave, args);
			return success;

		} catch(Exception e) {
			log.info("Error inserting new record in STAFF table" + e);
			return -1;
		}
	}
	
	@Override
	public int delete(long id) {
		
		int success = 0;
		
		try {
			String sqlDelete = "DELETE from staff WHERE id_staff=?";
			Object[] args = new Object[] {id};
			success = jdbcTemplate.update(sqlDelete, args);
			return success;
		} catch(Exception e) {
			log.info("ERROR SQL QUERY delete from table STAFF" + e);
			return success;
		}
	}

	@Override
	public Object edit(Object obj) {
		
		Staff staff = (Staff)obj;
		log.info("ID STAFF TO EDIT----" + staff.getId_staff());
		try {
			String sqlUpdate = "UPDATE staff " +
								"SET oib=?, name=?, surname=?, mail=?, password=?, mobile=?, shift=? WHERE id_staff=?"; 
			Object[] args = new Object[] {staff.getOib(), staff.getName(), staff.getSurname(), staff.getMail(), staff.getPassword(),
										  staff.getMobile(), staff.getShift().getId_shift(), staff.getId_staff()};
			int success = jdbcTemplate.update(sqlUpdate, args);
			log.info("--SUCCESS STAFF EDIT: "+success);
			return obj;
		} catch(Exception e) {
			log.info("Problem with SQL - edit STAFF, error: " + e);
			return null;
		}		
	}

	@Override
	public ArrayList<?> getAll() {
		
		ArrayList<?> list = new ArrayList<>();
		String sqlGetAll = "SELECT * FROM staff st, shift sh, role r WHERE st.shift=sh.id_shift AND st.role=r.id_role ORDER BY st.id_staff ASC";
		list = (ArrayList<?>) jdbcTemplate.query(sqlGetAll, new StaffRowMapper());
		return list;
	}
	
	@Override
	public Object getOne(long id) {
		
		String sqlGetOne = "SELECT st.oib, st.name, st.surname, st.mail, st.password, st.mobile, sh.name FROM staff st INNER JOIN shift sh ON st.shift=sh.id_shift WHERE st.id_staff=?";
		Object[] args = new Object[] {id};
		Object obj = jdbcTemplate.queryForObject(sqlGetOne, args, new StaffRowMapper());
		return obj;
	}

	@Override
	public int insertMany(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}
}
