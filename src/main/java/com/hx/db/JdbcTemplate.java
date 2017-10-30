package com.hx.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcTemplate {
	/**
	 * 更新方法
	 * 
	 * @param sql
	 * @param obj
	 * @return
	 */
	public static int update(String sql, Object... obj) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = OracleUtils.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally{
			OracleUtils.close(ps, conn, null);
		}

	}
	/**
	 * 查询
	 */
	public static Object  query(String sql,ResultSetHandle handle, Object... obj) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs =null;
		try {
			conn = OracleUtils.getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
			 rs =  ps.executeQuery();
			return handle.doHandle(rs);			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally{
			OracleUtils.close(ps, conn, null);
		}

	}

}
