package btb.hr.vinoljupci.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;

import btb.hr.vinoljupci.IDAO.IDaoCRUD;
import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.Unit;
import btb.hr.vinoljupci.rowMapper.FirmRowMapper;

@Component
public class DaoFirm implements IDaoCRUD{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(DaoFirm.class);
	
	@Override
	public void setDataSource(DataSource ds) {		
	}

	@Override
	public int insertOne(Object obj) {
	
		Firm firm = (Firm)obj;
		try { 
			String sqlFirmSave = "INSERT INTO firm (name, address, oib, percentage) VALUES (?,?,?,?)";	
			Object[] args = new Object[] {firm.getName(), firm.getAddress(), firm.getOib(), firm.getPercentage()};
			int success = jdbcTemplate.update(sqlFirmSave, args);		
				if(success >= 1) {
			int idOfLastFirm = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
					return idOfLastFirm;
				} else {
					return -2;
				}
				
		} catch(Exception e) {
			log.info("Error inserting new record in FIRM table" + e);
			return -1;
		}
	}
	
	public int insertUnitId(ArrayList<Firm> firms, Unit unit) {
		
		try {
			
			 jdbcTemplate.batchUpdate("UPDATE firm SET unit_id=? WHERE id_firm=?",
				        new BatchPreparedStatementSetter() {
				            @Override
				            public void setValues(PreparedStatement ps, int i) throws SQLException {
				              
				                ps.setLong(1, unit.getId_unit());
				                ps.setLong(2, firms.get(i).getId_firm());
				            }
				            @Override
				            public int getBatchSize() {
				                return firms.size();
				            }
				        });
			
			return 1;
		} catch(Exception e) {
			log.info("Problem adding unit to firms, Error: -----" + e);
			return -1;
		}
		
	}
	
	@Override
	public int delete(long id) {
		
		int success = 0;
		
		String sqlDeleteFirmsArtikli = "DELETE from artikli WHERE firm=?";
		Object[] arg = new Object[] {id};
		success = jdbcTemplate.update(sqlDeleteFirmsArtikli, arg);
		
		try {
			String sqlDelete = "DELETE from firm WHERE id_firm=?";
			Object[] args = new Object[] {id};
			success = jdbcTemplate.update(sqlDelete, args);
			return success;
		} catch(Exception e) {
			log.info("ERROR SQL QUERY delete from table FIRM" + e);
			return success;
		}
	}

	@Override
	public Object edit(Object obj) {
		
		Firm firm = (Firm)obj;
		
		try {
			String sqlUpdate = "UPDATE firm (name, address, oib, percentage) SET name=?, address=?, oib=?, percentage=? WHERE id_firm=?"; 
			Object[] args = new Object[] {firm.getName(), firm.getAddress(), firm.getOib(), firm.getPercentage()};
			int success = jdbcTemplate.update(sqlUpdate, args, new FirmRowMapper());
			if(success == 1) {
					try {
						String sqlGetUpdatedObject = "SELECT * FROM firm WHERE id_firm= ?";
					    Object[] arg=  new Object[]{firm.getId_firm()}; 
					    Firm updatedFirm = jdbcTemplate.queryForObject(sqlGetUpdatedObject, arg, new FirmRowMapper());
					      return updatedFirm;
					} catch(Exception e) {
						log.info("Problem with getting updated artikl - DaoArtikl.edit()");
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
		String sqlGetAll = "SELECT * FROM firm f left join unit u on f.unit_id=u.id_unit";
		list = (ArrayList<?>) jdbcTemplate.query(sqlGetAll, new FirmRowMapper());
		log.info(""+list);
		return list;
	}
	

	@Override
	public Object getOne(long id) {
		try {
			String sqlGetOne = "SELECT name, address, oib, unit_id, percentage FROM firm WHERE id_firm=?";
			Object[] args = new Object[] {id};
			Object obj = jdbcTemplate.queryForObject(sqlGetOne, args, new FirmRowMapper());
			return obj;
		} catch(EmptyResultDataAccessException e) {
			log.info("Error getting one firm ---" + e);
			return null;
		}
		
	}

	@Override
	public int insertMany(Object obj) {
		
		return 0;
	}
	
	public ArrayList<Firm> getAllFirmsOnUnit(long idFirm) {
		
		try {
			String sqlGetOne = "SELECT unit_id FROM firm WHERE id_firm=?";
			Object[] args = new Object[] {idFirm};
			int idUnit = jdbcTemplate.queryForObject(sqlGetOne, args, Integer.class);
			
				try {
					String sqlGetFirmsOnUnits = "SELECT * FROM firm f, unit u where f.unit_id=u.id_unit and unit_id=?";
					Object[] arg = new Object[] {idUnit};
					ArrayList<Firm> firmsOnUnits = (ArrayList<Firm>) jdbcTemplate.query(sqlGetFirmsOnUnits, arg, new FirmRowMapper());
					
					return firmsOnUnits;
				} catch(EmptyResultDataAccessException e) {
					log.info("Error getting firms on units  --" + e);
					return null;
				}
			
		} catch(EmptyResultDataAccessException e) {
			log.info("Error getting one firm ---" + e);
			return null;
		}
			
	}
	
	
		
}














