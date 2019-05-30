package btb.hr.vinoljupci.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import IService.IDaoService;
import btb.hr.vinoljupci.DAO.DaoArtikl;
import btb.hr.vinoljupci.DAO.DaoBill;
import btb.hr.vinoljupci.DAO.DaoBillPart;
import btb.hr.vinoljupci.DAO.DaoCard;
import btb.hr.vinoljupci.DAO.DaoFirm;
import btb.hr.vinoljupci.DAO.DaoLogin;
import btb.hr.vinoljupci.DAO.DaoRegion;
import btb.hr.vinoljupci.DAO.DaoRole;
import btb.hr.vinoljupci.DAO.DaoShift;
import btb.hr.vinoljupci.DAO.DaoStaff;
import btb.hr.vinoljupci.DAO.DaoUnit;
import btb.hr.vinoljupci.DAO.DaoUser;
import btb.hr.vinoljupci.DAO.DaoWineEvent;
import btb.hr.vinoljupci.DAO.DaoWineEventFirm;
import btb.hr.vinoljupci.IDAO.IDaoCRUD;
import btb.hr.vinoljupci.IDAO.IDaoLogin;
import btb.hr.vinoljupci.controller.WineEventController;
import btb.hr.vinoljupci.model.Artikl;
import btb.hr.vinoljupci.model.Bill;
import btb.hr.vinoljupci.model.BillPart;
import btb.hr.vinoljupci.model.Card;
import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.Region;
import btb.hr.vinoljupci.model.Role;
import btb.hr.vinoljupci.model.Shift;
import btb.hr.vinoljupci.model.Staff;
import btb.hr.vinoljupci.model.Unit;
import btb.hr.vinoljupci.model.UnitFromClient;
import btb.hr.vinoljupci.model.User;
import btb.hr.vinoljupci.model.WineEvent;
import btb.hr.vinoljupci.model.WineEventFirm;
import btb.hr.vinoljupci.model.WineEventFromClient;

@Service("iDaoService")
public class DaoService implements IDaoService {
	
	private static final Logger log = LoggerFactory.getLogger(DaoService.class);
	
	@Autowired 
	DaoArtikl daoArtikl;
	@Autowired 
	DaoCard daoCard;
	@Autowired 
	DaoBill daoBill;
	@Autowired 
	DaoBillPart daoBillPart;
	@Autowired 
	DaoFirm daoFirm;
	@Autowired 
	DaoLogin daoLogin;
	@Autowired 
	DaoRegion daoRegion;
	@Autowired 
	DaoRole daoRole;
	@Autowired 
	DaoShift daoShift;
	@Autowired 
	DaoStaff daoStaff;
	@Autowired 
	DaoUnit daoUnit;
	@Autowired 
	DaoUser daoUser;
	@Autowired 
	DaoWineEvent daoWineEvent;
	@Autowired 
	DaoWineEventFirm daoWineEventFirm;
	

	public int add(String objType, Object obj) throws SQLException {
		switch(objType) {
		case "artikl":
			return daoArtikl.insertMany(obj);	
		case "bill":
			return daoBill.insertOne(obj);
		case "billParts":
			return daoBillPart.insertMany(obj);
		case "card":
			return daoCard.insertOne(obj);
		case "firm":
			return daoFirm.insertOne(obj);
		case "region":
			return daoRegion.insertOne(obj);
		case "role":
			return daoRole.insertOne(obj);
		case "shift":
			return daoShift.insertOne(obj);
		case "staff":
			return daoStaff.insertOne(obj);
		case "unit":
			return daoUnit.insertOne(obj);
		case "user":
			return daoUser.insertOne(obj);
		case "wineEvent":
			return daoWineEvent.insertOne(obj);
		default: 
			return -1;
		}
		
	}

	public Object edit(String objType, Object obj) throws SQLException {
		
		switch(objType) {
		case "artikl":
			return (Artikl)daoArtikl.edit(obj);	
		case "bill":
			return (Bill)daoBill.edit(obj);
		case "billParts":
			return (BillPart)daoBillPart.edit(obj);
		case "card":
			return (Card)daoCard.edit(obj);
		case "firm":
			return (Firm)daoFirm.edit(obj);
		case "region":
			return (Region)daoRegion.edit(obj);
		case "role":
			return (Role)daoRole.edit(obj);
		case "shift":
			return (Shift)daoShift.edit(obj);
		case "staff":
			return (Staff)daoStaff.edit(obj);
		case "unit":
			return (Unit)daoUnit.edit(obj);
		case "user":
			return (User)daoUser.edit(obj);
		case "wineEvent":
			return (WineEvent)daoWineEvent.edit(obj);
		default: 
			return null;
		}
	}

	public int delete(String objType, long id) throws SQLException {
		switch(objType) {
		case "artikl":
			return daoArtikl.delete(id);	
		case "bill":
			return daoBill.delete(id);
		case "billParts":
			return daoBill.delete(id);
		case "card":
			return daoBill.delete(id);
		case "firm":
			return daoFirm.delete(id);
		case "region":
			return daoRegion.delete(id);
		case "role":
			return daoRole.delete(id);
		case "shift":
			return daoShift.delete(id);
		case "staff":
			return daoStaff.delete(id);
		case "unit":
			return daoUnit.delete(id);
		case "user":
			return daoUser.delete(id);
		case "wineEvent":
			return daoWineEvent.delete(id);
		default: 
			return -1;
		}
	}

	public Object getById(String objType, long id) throws SQLException {
		switch(objType) {
		case "artikl":
			return (Artikl)daoArtikl.getOne(id);	
		case "bill":
			return (Bill)daoBill.getOne(id);
		case "billParts":
			return (BillPart)daoBillPart.getOne(id);
		case "card":
			return (Card)daoCard.getOne(id);
		case "firm":
			return (Firm)daoFirm.getOne(id);
		case "region":
			return (Region)daoRegion.getOne(id);
		case "role":
			return (Role)daoRole.getOne(id);
		case "shift":
			return (Shift)daoShift.getOne(id);
		case "staff":
			return (Staff)daoStaff.getOne(id);
		case "unit":
			return (Unit)daoUnit.getOne(id);
		case "user":
			return (User)daoUser.getOne(id);
		case "wineEvent":
			return (WineEvent)daoWineEvent.getOne(id);
		default: 
			return null;
		}
	}

	public ArrayList<?> getAll(String objType) throws SQLException {
		switch(objType) {
		case "artikl":
			return (ArrayList<Artikl>)daoArtikl.getAll();	
		case "bill":
			return (ArrayList<Bill>)daoBill.getAll();
		case "billParts":
			return (ArrayList<BillPart>)daoBillPart.getAll();
		case "card":
			return (ArrayList<Card>)daoCard.getAll();
		case "firm":
			return (ArrayList<Firm>)daoFirm.getAll();
		case "region":
			return (ArrayList<Region>)daoRegion.getAll();
		case "role":
			return (ArrayList<Role>)daoRole.getAll();
		case "shift":
			return (ArrayList<Shift>)daoShift.getAll();
		case "staff":
			return (ArrayList<Staff>)daoStaff.getAll();
		case "unit":
			return (ArrayList<Unit>)daoUnit.getAll();
		case "user":
			return (ArrayList<User>)daoUser.getAll();
		case "wineEvent":
			return (ArrayList<WineEvent>)daoWineEvent.getAll();
		default: 
			return null;
		}
	}
	public int addUnitOnFirm(ArrayList<Firm> firms, Unit unit) {
		
		return daoFirm.insertUnitId(firms, unit);
	}
	
	public int addWineEventOnFirm (ArrayList<Firm> firms, long wineEventId) {
		 log.info("FIRMZZZZZZZZZZZ-------------" + firms.get(0));
	 	 log.info("IDZZZZZZZZZZZ-------------" + wineEventId);
		return daoWineEventFirm.insertWineEventAndFirms(firms, wineEventId);
	}
	
	public ArrayList<WineEventFirm> getAllFirmsOnWineEvents() throws SQLException {
			
			ArrayList<WineEventFirm> wineEventFirmList = daoWineEventFirm.getAllFirmsOnWineEvents();
			
			return wineEventFirmList;
		}
}

	









