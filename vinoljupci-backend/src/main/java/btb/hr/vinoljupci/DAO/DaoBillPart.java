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
import btb.hr.vinoljupci.model.Artikl;
import btb.hr.vinoljupci.model.BillPart;
import btb.hr.vinoljupci.rowMapper.BillPartRowMapper;

@Component
public class DaoBillPart implements IDaoCRUD{
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(DaoBillPart.class);
	
	@Override
	public void setDataSource(DataSource ds) {		
	}

	@Override
	public int insertOne(Object obj) {
		
		BillPart billPart = (BillPart)obj;
		try { 
			String sqlBillPartSave = "INSERT INTO bill_parts  (artikl, amount) VALUES (?,?)";	
			Object[] args = new Object[] {billPart.getArtikl().getId_artikli(), billPart.getAmount()};
			int success = jdbcTemplate.update(sqlBillPartSave, args);
			return success;

		} catch(Exception e) {
			log.info("Error inserting new record in bill_parts table" + e);
			return -1;
		}
	}
	

	@Override
	public int delete(long id) {
		
		int success = 0;
		
		try {
			String sqlDelete = "DELETE from bill_parts WHERE id_bill_parts=?";
			Object[] args = new Object[] {id};
			success = jdbcTemplate.update(sqlDelete, args);
			return success;
		} catch(Exception e) {
			log.info("ERROR SQL QUERY delete from table bill_parts" + e);
			return success;
		}
	}

	@Override
	public Object edit(Object obj) {
		
		BillPart billPart = (BillPart)obj;
		
		try {
			String sqlUpdate = "UPDATE bill_parts (artikl, amount) SET artikl=?, amount=? WHERE id_bill_parts=?"; 
			Object[] args = new Object[] {billPart.getArtikl().getId_artikli(), billPart.getAmount()};
			int success = jdbcTemplate.update(sqlUpdate, args, new BillPartRowMapper());
			if(success == 1) {
					try {
						String sqlGetUpdatedObject = "SELECT * FROM bill_parts WHERE id_bill_parts= ?";
					    Object[] arg=  new Object[]{billPart.getId_bill_parts()}; 
					    BillPart updatedBillPart = jdbcTemplate.queryForObject(sqlGetUpdatedObject, arg, new BillPartRowMapper());
					      return updatedBillPart;
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
		String sqlGetAll = "SELECT * FROM bill_parts bp, artikli a, WHERE bp.artikl=artikli.id_artikli";
		list = (ArrayList<?>) jdbcTemplate.query(sqlGetAll, new BillPartRowMapper());
		return list;
	}
	

	@Override
	public Object getOne(long id) {
		
		String sqlGetOne = "SELECT bp.id_bill_parts, a.name, bp.amount FROM bill_parts bp INNER JOIN artikli a ON bp.artikl=artikli.id_artikli WHERE bp.id_bill_parts=?";
		Object[] args = new Object[] {id};
		Object obj = jdbcTemplate.queryForObject(sqlGetOne, args, new BillPartRowMapper());
		return obj;
	}

	@Override
	public int insertMany(Object obj) {
		ArrayList<BillPart> billParts = (ArrayList<BillPart>) obj;
		
		log.info("LIST BILL PARTS-----------" +  billParts.get(0).getBill().getBillSum());
			try { 
				
				  jdbcTemplate.batchUpdate("INSERT INTO bill_parts (artikl, amount, bill_id) VALUES (?, ?, ?)",
					        new BatchPreparedStatementSetter() {
					            @Override
					            public void setValues(PreparedStatement ps, int i) throws SQLException {
					                
					               
					                ps.setLong(1, billParts.get(i).getArtikl().getId_artikli());
					                ps.setInt(2, billParts.get(i).getAmount());
					                ps.setLong(3, billParts.get(i).getBill().getId_bill());
					            }
					            @Override
					            public int getBatchSize() {
					                return billParts.size();
					            }
					        });
				
				return 1;

			} catch(Exception e) {
				log.info("Error inserting new records in bill_part table" + e);
				return -1;
			}
	}
}
