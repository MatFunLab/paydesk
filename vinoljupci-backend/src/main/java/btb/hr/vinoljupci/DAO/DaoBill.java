package btb.hr.vinoljupci.DAO;

import java.util.ArrayList;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import btb.hr.vinoljupci.IDAO.IDaoCRUD;
import btb.hr.vinoljupci.model.Bill;
import btb.hr.vinoljupci.rowMapper.BillRowMapper;

@Component
public class DaoBill implements IDaoCRUD{

	
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(DaoBill.class);
	
	@Override
	public void setDataSource(DataSource ds) {
	}

	@Override
	public int insertOne(Object obj) {
		
		Bill bill = (Bill)obj;
		log.info("-------------"+bill.getCard().getId_card());
		try { 
			String sqlSave = "INSERT INTO bill (card, timestamp, event, bill_sum) VALUES (?,?,?,?)";					   
			Object[] args = new Object[] { bill.getCard().getId_card(),
										  bill.getTimestamp(), bill.getWineEvent().getId_wine_event(),  bill.getBillSum()};
			int success = jdbcTemplate.update(sqlSave, args);
			 if(success >= 0) {
				 int idOfLastBill = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
					return idOfLastBill;
			 } else {
				 return success;
			 }
			

		} catch(Exception e) {
			log.info("Error inserting new record in table BILL ---" + e);
			return -1;
		}
	}


	@Override
	public int delete(long id) {
		
		try {
			String sqlDelete = "DELETE from bill WHERE id_bill=?";
			Object[] args = new Object[] {id};
			int success = jdbcTemplate.update(sqlDelete, args);
			return success;
		} catch(Exception e) {
			log.info("ERROR SQL QUERY delete from table bill" + e);
			return -1;
		}
	}

	@Override
	public Object edit(Object obj) {
		
		Bill bill = (Bill)obj;
		try {
			String sqlUpdate = "UPDATE bill ( card, timestamp, firm, bill_sum) SET bill_parts=?, card=?, timestamp=?, firm=?, bill_sum=?"; 
			Object[] args = new Object[] { bill.getCard().getId_card(), bill.getTimestamp(), bill.getWineEvent().getId_wine_event(), bill.getBillSum()};
			int success = jdbcTemplate.update(sqlUpdate, args);
				if(success == 1) {
					try {
						String sqlGetUpdatedObject = "SELECT * FROM bill WHERE id_bill= ?";
					    Object[] arg=  new Object[]{bill.getId_bill()}; 
					    Bill updatedBill = jdbcTemplate.queryForObject(sqlGetUpdatedObject, arg, new BillRowMapper());
					      return updatedBill;
					} catch(Exception e) {
						log.info("Problem with getting updated bill - DaoBill.edit()");
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
		String sqlGetAllBills = "SELECT b.id_bill, bp.bill_parts, c.card, b.timestamp, w.wine_event, b.bill_sum FROM bill b" + 
								"INNER JOIN bill_parts bp ON bill.bill_parts=bill_parts.id_bill_parts" +
								"INNER JOIN card c ON bill.card=card.id_card" +
								"Inner JOIN wine_event w ON bill.wine_event=wine_event.id_wine_event";
		
		list = (ArrayList<?>)jdbcTemplate.queryForList(sqlGetAllBills);	
		
		return list;
	}

	@Override
	public Object getOne(long id) {
		
		String sqlGetOne = "SELECT * FROM bill b INNER JOIN card c ON b.card=c.id_card INNER JOIN wine_event w ON b.event=w.id_wine_event WHERE b.id_bill=?";
		Object[] args = new Object[] {id};
		Object obj = jdbcTemplate.queryForObject(sqlGetOne, args, new BillRowMapper());
	
		return obj;
	}

	@Override
	public int insertMany(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

}
