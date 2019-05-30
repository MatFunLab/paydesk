package btb.hr.vinoljupci.DAO;

import java.util.ArrayList;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import btb.hr.vinoljupci.IDAO.IDaoCRUD;
import btb.hr.vinoljupci.model.Region;
import btb.hr.vinoljupci.rowMapper.RegionRowMapper;

@Component
public class DaoRegion implements IDaoCRUD{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(DaoRegion.class);
	
	@Override
	public void setDataSource(DataSource ds) {
		
	}

	@Override
	public int insertOne(Object obj) {
		
		Region region = (Region)obj;
		try { 
			String sqlRegionSave = "INSERT INTO region (name) VALUES (?)";	
			Object[] args = new Object[] {region.getName()};
			int success = jdbcTemplate.update(sqlRegionSave, args);
			return success;

		} catch(Exception e) {
			log.info("Error inserting new record in FIRM table" + e);
			return -1;
		}
	}
	
	@Override
	public int delete(long id) {
		
		int success = 0;
		
		try {
			String sqlDelete = "DELETE from region WHERE id_region=?";
			Object[] args = new Object[] {id};
			success = jdbcTemplate.update(sqlDelete, args);
			return success;
		} catch(Exception e) {
			log.info("ERROR SQL QUERY delete from table REGION" + e);
			return success;
		}
	}

	@Override
	public Object edit(Object obj) {
		
		Region region = (Region)obj;
		
		try {
			String sqlUpdate = "UPDATE region (name) SET name=? WHERE id_region=?"; 
			Object[] args = new Object[] {region.getName()};
			int success = jdbcTemplate.update(sqlUpdate, args, new RegionRowMapper());
			if(success == 1) {
					try {
						String sqlGetUpdatedObject = "SELECT * FROM region WHERE id_region= ?";
					    Object[] arg=  new Object[]{region.getId_region()}; 
					    Region updatedRegion = jdbcTemplate.queryForObject(sqlGetUpdatedObject, arg, new RegionRowMapper());
					      return updatedRegion;
					} catch(Exception e) {
						log.info("Problem with getting updated region - DaoRegion.edit()");
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
		String sqlGetAll = "SELECT * FROM region";
		list = (ArrayList<?>) jdbcTemplate.query(sqlGetAll, new RegionRowMapper());
		return list;
	}
	
	@Override
	public Object getOne(long id) {
		
		String sqlGetOne = "SELECT id_region, name FROM region WHERE id_region=?";
		Object[] args = new Object[] {id};
		Object obj = jdbcTemplate.queryForObject(sqlGetOne, args, new RegionRowMapper());
		return obj;
	}

	@Override
	public int insertMany(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}
}
