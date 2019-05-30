package btb.hr.vinoljupci.service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import IService.IUnitFirmService;
import btb.hr.vinoljupci.DAO.DaoUnitFirm;
import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.UnitFirm;
import btb.hr.vinoljupci.model.UnitFromClient;
import btb.hr.vinoljupci.rowMapper.UnitFirmRowMapper;

@Service("iUnitFirmService")
public class UnitFirmService implements IUnitFirmService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(UnitFirmService.class);
	
	@Autowired
	DaoUnitFirm daoUnitFirm;
	
	@Override
	public void setDataSource(DataSource ds) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int add(UnitFromClient unitFirm) throws SQLException {
		 
		return 0;
		
	}

	@Override
	public int insertOne(UnitFromClient unitFirm) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(long id) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object edit(UnitFromClient unitFirm) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<?> getAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public ArrayList<UnitFromClient> getAllFirmsOnUnits() throws SQLException {
		
		ArrayList<UnitFromClient> unitFirmList = daoUnitFirm.getAllFirmsOnUnits();
		
		return unitFirmList;
	}
	
	@Override
	public Object getOne(long id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertMany(UnitFromClient unitFirm, int idOfLastUnitInserted) throws SQLException {
		
		return daoUnitFirm.insertUnitAndFirms(unitFirm, idOfLastUnitInserted);
	}

}
