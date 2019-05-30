package IService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

import btb.hr.vinoljupci.model.Card;
import btb.hr.vinoljupci.model.Firm;
import btb.hr.vinoljupci.model.User;


public interface IDaoService {

	public int add(String objType, Object obj) throws SQLException;

	public Object edit(String objType, Object obj) throws SQLException;

	public int delete(String objType, long id) throws SQLException;

	public Object getById(String objType, long id) throws SQLException;

	public ArrayList<?> getAll(String objType) throws SQLException;
	
}

