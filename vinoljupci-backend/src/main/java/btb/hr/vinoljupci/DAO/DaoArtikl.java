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
import btb.hr.vinoljupci.rowMapper.ArtiklRowMapper;

@Component
public class DaoArtikl implements IDaoCRUD {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private static final Logger log = LoggerFactory.getLogger(DaoArtikl.class);
	
	@Override
	public void setDataSource(DataSource ds) {	
	}

	@Override
	public int insertMany(Object obj) {
		ArrayList<Artikl> artikli = (ArrayList<Artikl>) obj;
		/*
		 * List update
		 * List insert
		 * for(Artikl a : artikli){
		 * if(a.id == 0) {
		 * 	inser.add(a);
		 * } else {
		 * 	update.add(a);
		 * }
		 * }
		 */

		
		//Update artikle set name=?, price=? ... where id=?
		log.info("LIST ARTIKLI-----------" + artikli);
			try { 
				
				//if(inser.size() !=0)
				  jdbcTemplate.batchUpdate("INSERT INTO artikli (name, price, firm) VALUES (?, ?, ?)",
					        new BatchPreparedStatementSetter() {
					            @Override
					            public void setValues(PreparedStatement ps, int i) throws SQLException {
					                
					                ps.setString(1, artikli.get(i).getName());
					                ps.setInt(2, artikli.get(i).getPrice());
					                ps.setLong(3, artikli.get(i).getFirm().getId_firm());
					            }
					            @Override
					            public int getBatchSize() {
					                return artikli.size();
					            }
					        });
				
				return 1;

			} catch(Exception e) {
				log.info("Error inserting new record in artikl table" + e);
				return -1;
			}
		
		}


	@Override
	public int delete(long id) {
		
		int success = 0;
		
		try {
			String sqlDelete = "DELETE from artikli WHERE id_artikli=?";
			Object[] args = new Object[] {id};
			success = jdbcTemplate.update(sqlDelete, args);
			return success;
		} catch(Exception e) {
			log.info("ERROR SQL QUERY delete from table artikli" + e);
			return success;
		}
	}

	@Override
	public Object edit(Object obj) {
		
		Artikl artikl = (Artikl)obj;
		try {
			String sqlUpdate = "UPDATE artikli (name, price, firm) SET name=?, price=?, firm=? WHERE id_artikli=?"; 
			Object[] args = new Object[] {artikl.getName(), artikl.getPrice(), artikl.getFirm().getId_firm(), artikl.getId_artikli()};
			int success = jdbcTemplate.update(sqlUpdate, args, new ArtiklRowMapper());
			if(success == 1) {
					try {
						String sqlGetUpdatedObject = "SELECT * FROM artikli WHERE id_artikli= ?";
					    Object[] arg=  new Object[]{artikl.getId_artikli()}; 
					    Artikl updatedArtikl = jdbcTemplate.queryForObject(sqlGetUpdatedObject, arg, new ArtiklRowMapper());
					      return updatedArtikl;
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
		String sqlGetAll = "SELECT * FROM artikli a, firm f WHERE a.firm=f.id_firm ORDER BY a.id_artikli ASC";
		list = (ArrayList<?>) jdbcTemplate.query(sqlGetAll, new ArtiklRowMapper());
		return list;
	}

	@Override
	public Object getOne(long id) {
		
		String sqlGetOne = "SELECT a.name, a.price f.name FROM artikli a INNER JOIN firm f ON a.firm=firm.id_firm WHERE a.id_artikli=?";
		Object[] args = new Object[] {id};
		Object obj = jdbcTemplate.queryForObject(sqlGetOne, args, new ArtiklRowMapper());
		return obj;
	}

	@Override
	public int insertOne(Object obj) {
		
		return 0;
	}
	
	public ArrayList<Artikl> getArtikliOfSpecificFirm(long idFirmToQueryArtikl) {
		
		try {
			String sqlUpdate = "SELECT * FROM artikli a, firm f where a.firm=f.id_firm and a.firm=?"; 
			Object[] args = new Object[] {idFirmToQueryArtikl};
			ArrayList<Artikl> artikliFirm= (ArrayList<Artikl>) jdbcTemplate.query(sqlUpdate, args, new ArtiklRowMapper());
			return artikliFirm;
		 } catch(Exception e) {
			 log.info("---" + e);
			 return null;
			}
		}
	}
