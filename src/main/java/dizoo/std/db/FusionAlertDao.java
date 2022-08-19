package dizoo.std.db;

import java.util.ArrayList;

public interface FusionAlertDao {
	public ArrayList<FusionAlert> findAll(); 

	public FusionAlert findById(String id);

	public int update();

}
