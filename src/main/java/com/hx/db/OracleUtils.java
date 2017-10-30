package com.hx.db;

import java.sql.*;
import java.util.ResourceBundle;

public class OracleUtils {
	private final static String URL;
	private final static String USERNAME;
	private final static String PASSWORD;
	private final static String DRIVER;
	private final static ResourceBundle RB;

	private OracleUtils() {
	}

	static {
		RB = ResourceBundle.getBundle("com.hx.db.jdbc");
		URL = RB.getString("jdbc.url");
		USERNAME = RB.getString("jdbc.username");
		PASSWORD = RB.getString("jdbc.password");
		DRIVER = RB.getString("jdbc.driverClassName");
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static void close(Statement st, Connection conn, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (st != null) {
				st.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
