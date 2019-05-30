package btb.hr.vinoljupci.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import IService.ILoginService;
import btb.hr.vinoljupci.DAO.DaoArtikl;
import btb.hr.vinoljupci.DAO.DaoFirm;
import btb.hr.vinoljupci.DAO.DaoLogin;
import btb.hr.vinoljupci.model.Admin;
import btb.hr.vinoljupci.model.Artikl;
import btb.hr.vinoljupci.model.ArtikliFirmsOnUnit;
import btb.hr.vinoljupci.model.Credentials;
import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.Staff;
import btb.hr.vinoljupci.model.User;

@Service("iLoginService")
public class LoginService implements ILoginService {
	
	private static final Logger log = LoggerFactory.getLogger(LoginService.class);
	
	@Autowired
	DaoLogin daoLogin;
	
	@Autowired
	DaoFirm daoFirm;
	
	@Autowired
	DaoArtikl daoArtikl;
	
	
	@Override
	public Object checkLogin(Credentials credentials) throws SQLException {
		
		Object obj	= daoLogin.login(credentials);
			
			 if(obj instanceof User) {
				 
				long idFirm = ((User) obj).getFirm().getId_firm();
			ArrayList<Firm> firmsUnit = daoFirm.getAllFirmsOnUnit(idFirm);
			
			ArrayList<Long> ids = new ArrayList<Long>();
			
				for(int i = 0; i < firmsUnit.size(); i++) {
					long idFirmToQueryArtikl = firmsUnit.get(i).getId_firm();
					ids.add(idFirmToQueryArtikl);
					}
				
						if(firmsUnit.size() == 4) {	
							ArrayList<Artikl> artikliFirmOne = daoArtikl.getArtikliOfSpecificFirm(ids.get(0));
							ArrayList<Artikl> artikliFirmTwo = daoArtikl.getArtikliOfSpecificFirm(ids.get(1));
							ArrayList<Artikl> artikliFirmThree = daoArtikl.getArtikliOfSpecificFirm(ids.get(2));
							ArrayList<Artikl> artikliFirmFour = daoArtikl.getArtikliOfSpecificFirm(ids.get(3));
							
							ArtikliFirmsOnUnit artikliFirmsOnUnit = new ArtikliFirmsOnUnit();
							artikliFirmsOnUnit.setFirmOneArtikli(artikliFirmOne);
							artikliFirmsOnUnit.setFirmTwoArtikli(artikliFirmTwo);
							artikliFirmsOnUnit.setFirmThreeArtikli(artikliFirmThree);
							artikliFirmsOnUnit.setFirmFourArtikli(artikliFirmFour);
					
							return artikliFirmsOnUnit;
							
						} else if(firmsUnit.size() == 3) {
							ArrayList<Artikl> artikliFirmOne = daoArtikl.getArtikliOfSpecificFirm(ids.get(0));
							ArrayList<Artikl> artikliFirmTwo = daoArtikl.getArtikliOfSpecificFirm(ids.get(1));
							ArrayList<Artikl> artikliFirmThree = daoArtikl.getArtikliOfSpecificFirm(ids.get(2));
							
							ArtikliFirmsOnUnit artikliFirmsOnUnit = new ArtikliFirmsOnUnit();
							artikliFirmsOnUnit.setFirmOneArtikli(artikliFirmOne);
							artikliFirmsOnUnit.setFirmTwoArtikli(artikliFirmTwo);
							artikliFirmsOnUnit.setFirmThreeArtikli(artikliFirmThree);
					
							return artikliFirmsOnUnit;
							
						} else if(firmsUnit.size() == 2) {
							ArrayList<Artikl> artikliFirmOne = daoArtikl.getArtikliOfSpecificFirm(ids.get(0));
							ArrayList<Artikl> artikliFirmTwo = daoArtikl.getArtikliOfSpecificFirm(ids.get(1));
							
							ArtikliFirmsOnUnit artikliFirmsOnUnit = new ArtikliFirmsOnUnit();
							artikliFirmsOnUnit.setFirmOneArtikli(artikliFirmOne);
							artikliFirmsOnUnit.setFirmTwoArtikli(artikliFirmTwo);
					
							return artikliFirmsOnUnit;
							
						} else if(firmsUnit.size() == 1) {
							ArrayList<Artikl> artikliFirmOne = daoArtikl.getArtikliOfSpecificFirm(ids.get(0));
							
							ArtikliFirmsOnUnit artikliFirmsOnUnit = new ArtikliFirmsOnUnit();
							artikliFirmsOnUnit.setFirmOneArtikli(artikliFirmOne);
					
							return artikliFirmsOnUnit;
							
						} 
					
			 } else if(obj instanceof Staff) {	 
				 return obj;
			 } else if(obj instanceof Admin) {		
				 return obj;
			 } else {
				 return null;
			 }
			return obj;	 
		}
	}
