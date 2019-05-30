package btb.hr.vinoljupci.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import btb.hr.vinoljupci.IDAO.IDaoCRUD;
import btb.hr.vinoljupci.model.WineEvent;
import btb.hr.vinoljupci.rowMapper.WineEventRowMapper;

@Component
public class DaoWineEvent implements IDaoCRUD{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(DaoWineEvent.class);
	
	@Override
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int insertOne(Object obj) {
		
		WineEvent wineEvent = (WineEvent)obj;
		try { 
			String sqlWineEventSave = "INSERT INTO wine_event (name, event_date_start, event_date_end) VALUES (?,?,?)";	
			Object[] args = new Object[] {wineEvent.getName(), wineEvent.getEventDateStart(), wineEvent.getEventDateEnd()};
			int success = jdbcTemplate.update(sqlWineEventSave, args);
			int idOfLastUnit = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
			return idOfLastUnit;

		} catch(Exception e) {
			log.info("Error inserting new record in WINEEVENT table" + e);
			return -1;
		}
	}
	
	@Override
	public int delete(long id) {
		
		int success = 0;
		
		try {
			String sqlDelete = "DELETE from wine_event WHERE id_wine_event=?";
			Object[] args = new Object[] {id};
			success = jdbcTemplate.update(sqlDelete, args);
			return success;
		} catch(Exception e) {
			log.info("ERROR SQL QUERY delete from table WINEEVENT" + e);
			return success;
		}
	}

	@Override
	public Object edit(Object obj) {
		
		WineEvent wineEvent = (WineEvent)obj;
		
		try {
			String sqlUpdate = "UPDATE wine_event (name, event_date_start, event_date_end) " +
								"SET name=?, event_date_start=?, event_date_end=? WHERE id_wine_event=?"; 
			Object[] args = new Object[] {wineEvent.getName(), wineEvent.getEventDateStart(), wineEvent.getEventDateEnd()};
			int success = jdbcTemplate.update(sqlUpdate, args, new WineEventRowMapper());
			if(success == 1) {
					try {
						String sqlGetUpdatedObject = "SELECT * FROM wine_event WHERE id_wine_event=?";
					    Object[] arg=  new Object[]{wineEvent.getId_wine_event()}; 
					    WineEvent updatedWineEvent = jdbcTemplate.queryForObject(sqlGetUpdatedObject, arg, new WineEventRowMapper());
					      return updatedWineEvent;
					} catch(Exception e) {
						log.info("Problem with getting updated wineEvent - DaoWineEvent.edit()");
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
		String sqlGetAll = "SELECT * from wine_event";
		list = (ArrayList<?>) jdbcTemplate.queryForList(sqlGetAll);
		return list;
	}
	
	@Override
	public Object getOne(long id) {
		
		String sqlGetOne = "SELECT * from wine_event WHERE id_wine_event=?";
							
		Object[] args = new Object[] {id};
		Object obj = jdbcTemplate.queryForObject(sqlGetOne, args, new WineEventRowMapper());
		return obj;
	}

	@Override
	public int insertMany(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}
}
