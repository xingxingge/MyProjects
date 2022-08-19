package dizoo.std.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetHandle {
	public Object doHandle(ResultSet rs)  throws SQLException;

}
