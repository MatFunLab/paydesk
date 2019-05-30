package IService;

import java.sql.SQLException;
import java.util.ArrayList;

import btb.hr.vinoljupci.model.FilterQueryFromClient;
import btb.hr.vinoljupci.model.FilterQueryToClient;
import btb.hr.vinoljupci.model.FilterQueryFromClient;

public interface IFilterQueryService {
	
	public  ArrayList<FilterQueryToClient> filter(FilterQueryFromClient filterQuery) throws SQLException;
	
}
