package IService;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.UnitFirm;
import btb.hr.vinoljupci.model.UnitFromClient;

public interface IUnitFirmService {
	
	public void setDataSource(DataSource ds);

	public int add( UnitFromClient unitFirm) throws SQLException;
	
	public int insertOne(UnitFromClient unitFirm) throws SQLException;
	
	public int delete(long id) throws SQLException;;
	
	public Object edit(UnitFromClient unitFirm) throws SQLException;
	
	public ArrayList<?> getAll() throws SQLException;
	
	public ArrayList<UnitFromClient> getAllFirmsOnUnits() throws SQLException;
	
	public Object getOne(long id) throws SQLException;;

	public int insertMany(UnitFromClient unitFirm, int idOfLastUnitInserted) throws SQLException;
}
