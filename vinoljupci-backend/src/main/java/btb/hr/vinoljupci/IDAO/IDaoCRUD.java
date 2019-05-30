package btb.hr.vinoljupci.IDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.sql.DataSource;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
public interface IDaoCRUD {
	
	public void setDataSource(DataSource ds);
	
	public int insertOne(Object obj);
	
	public int delete(long id);
	
	public Object edit(Object obj);
	
	public ArrayList<?> getAll();
	
	public Object getOne(long id);

	public int insertMany(Object obj);
	
}
