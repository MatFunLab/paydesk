package btb.hr.vinoljupci.rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import btb.hr.vinoljupci.model.Artikl;
import btb.hr.vinoljupci.model.Firm;

public class ArtiklRowMapper implements RowMapper<Artikl> {

	public Artikl mapRow(ResultSet rs, int rowNum) throws SQLException {
		Artikl artikl = new Artikl();
		Firm firm = new Firm();
			firm.setId_firm(rs.getLong("f.id_firm"));
			firm.setName(rs.getString("f.name"));
			firm.setOib(rs.getLong("f.oib"));
			firm.setAddress(rs.getString("f.address"));
	
	artikl.setId_artikli(rs.getLong("a.id_artikli"));
	artikl.setName(rs.getString("a.name"));
	artikl.setPrice(rs.getInt("a.price"));
	artikl.setFirm(firm);
	
	return artikl;
	
	}
	
}
