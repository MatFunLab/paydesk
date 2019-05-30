package btb.hr.vinoljupci.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import btb.hr.vinoljupci.model.Artikl;
import btb.hr.vinoljupci.model.BillPart;
import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.Unit;
import btb.hr.vinoljupci.model.UnitFirm;

public class UnitFirmRowMapper implements RowMapper<UnitFirm>{

	@Override
	public UnitFirm mapRow(ResultSet rs, int rowNum) throws SQLException {
		UnitFirm unitFirm = new UnitFirm();
			Firm firm = new Firm();
				firm.setId_firm(rs.getInt("f.id_firm"));
				firm.setName(rs.getString("f.name"));
				firm.setAddress(rs.getString("f.address"));
				firm.setOib(rs.getLong("f.oib"));
				
			Unit unit = new Unit();	
				unit.setId_unit(rs.getLong("u.id_unit"));
				unit.setName(rs.getString("u.name"));
			unitFirm.setId_firm(firm);
			unitFirm.setId_unit(unit);
			return unitFirm;
	}

}
