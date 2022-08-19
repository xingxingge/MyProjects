package dizoo.std.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FusionAlertDaoImpl implements FusionAlertDao {

	@Override
	public FusionAlert findById(String id) {
		String sql = "SELECT res_id,receive_time,alert_info,alert_source FROM  JCFX_V_FUSION_ALERT where res_id=?";
		FusionAlert fusionAlert = (FusionAlert) JdbcTemplate.query(sql,
				new ResultSetHandle() {
					@Override
					public Object doHandle(ResultSet rs) throws SQLException {
						FusionAlert fa = new FusionAlert();
						if (rs.next()) {
							fa.setRes_id(rs.getString(1));
							fa.setReceive_time(rs.getString(2));
							fa.setAlert_info(rs.getString(3));
							fa.setAlert_source(rs.getString(4));
						}
						return fa;
					}
				}, id);
		return fusionAlert;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<FusionAlert> findAll() {
		String sql = "SELECT res_id,receive_time,alert_info,alert_source FROM  JCFX_V_FUSION_ALERT";
		ArrayList<FusionAlert> list = (ArrayList<FusionAlert>) JdbcTemplate
				.query(sql, new ResultSetHandle() {
					@Override
					public Object doHandle(ResultSet rs) throws SQLException {
						ArrayList<FusionAlert> list = new ArrayList<FusionAlert>();
						while (rs.next()) {
							FusionAlert fa = new FusionAlert();
							fa.setRes_id(rs.getString(1));
							fa.setReceive_time(rs.getString(2));
							fa.setAlert_info(rs.getString(3));
							fa.setAlert_source(rs.getString(4));
							list.add(fa);
						}
						return list;
					}
				});
		return list;

	}

	@Override
	public int update() {
		return 0;
	}

}
