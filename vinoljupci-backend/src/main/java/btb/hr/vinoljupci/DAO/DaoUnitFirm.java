package btb.hr.vinoljupci.DAO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import btb.hr.vinoljupci.IDAO.IDaoCRUD;
import btb.hr.vinoljupci.controller.UnitController;
import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.UnitFirm;
import btb.hr.vinoljupci.model.UnitFromClient;
import btb.hr.vinoljupci.rowMapper.ArtiklRowMapper;
import btb.hr.vinoljupci.rowMapper.UnitFirmRowMapper;
import btb.hr.vinoljupci.rowMapper.UnitFirmToClientRowMapper;

@Component
public class DaoUnitFirm {
	
	private static final Logger log = LoggerFactory.getLogger(DaoUnitFirm.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	
	public void setDataSource(DataSource ds) {
		
	}

	
	public int insertOne(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int delete(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public Object edit(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public ArrayList<?> getAll() {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	public ArrayList<UnitFromClient> getAllFirmsOnUnits() {
		String sqlSelectFirmsOnUnits = "SELECT * FROM unit_firm uf, unit u, firm f WHERE  uf.id_unit=u.id_unit AND uf.id_firm=f.id_firm ORDER BY u.id_unit ASC";
		ArrayList<UnitFromClient> list = (ArrayList<UnitFromClient>) jdbcTemplate.query(sqlSelectFirmsOnUnits, new UnitFirmToClientRowMapper());
		log.info("-------------LIST " + list.get(0));
		return list;	
	}
	*/
	public ArrayList<UnitFromClient> getAllFirmsOnUnits() {
		String sqlSelectFirmsOnUnits = "SELECT f.id_firm, f.name, f.address, f.oib, u.id_unit, u.name FROM firm f, unit u WHERE f.unit_id=u.id_unit";  
		
		ArrayList<UnitFromClient> list =  (ArrayList<UnitFromClient>) jdbcTemplate.query(sqlSelectFirmsOnUnits, new UnitFirmToClientRowMapper());
		
		return list;	
	}

	public Object getOne(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int insertUnitAndFirms(UnitFromClient receivedUnit, int idOfLastUnitInserted) {
		try {
			 jdbcTemplate.batchUpdate("INSERT INTO unit_firm (id_unit, id_firm) VALUES (?, ?)",
				        new BatchPreparedStatementSetter() {
				            @Override
				            public void setValues(PreparedStatement ps, int i) throws SQLException {
				                
				                ps.setLong(1, idOfLastUnitInserted);
				                ps.setLong(2, receivedUnit.getFirm().get(i).getId_firm());
				                
				            }
				            @Override
				            public int getBatchSize() {
				                return receivedUnit.getFirm().size();
				            }
				        });
			 return 1;
		} catch(Exception e) {
			log.info("Error in DAOUNITFIRM, problem with multiple insertions in unit_firm table----" + e);
			return -1;
		}
		
		
	}

}
