package dizoo.std.db;

import java.sql.*;

public class ResultSetMetadataTest {
	
	public static void main(String[] args) {
		rsmdTest();
	}
	

	public static void rsmdTest() {
		Connection conn = OracleUtils.getConnection();
		String sql1 = "SELECT res_id,receive_time,alert_info,alert_source FROM  JCFX_V_FUSION_ALERT";
		String sql2 = "SELECT res_id,receive_time,alert_info,alert_source FROM  JCFX_V_FUSION_ALERT_table";
		ResultSet rs = null;
		PreparedStatement ps = null;
		ResultSetMetaData rsmd = null;
		DatabaseMetaData dbmd=null; 
		try {
			// 测试视图
			dbmd=conn.getMetaData();
			System.out.println("Connected to:" + dbmd.getURL()); 
			System.out.println("Driver " + dbmd.getDriverName()); 
			rs=dbmd.getTables(null, "XGS", null,new String[]{"VIEW","TABLE"});
			while (rs.next()) {
				System.out.println(rs.getString(3));
				
			}
			ps = conn.prepareStatement(sql1);
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			int numberOfColumns = rsmd.getColumnCount();
		
			System.out.println("视图列数：" + numberOfColumns);
			System.out.println("视图的第2列的名称：" + rsmd.getColumnName(2));
			System.out.println("视图的第2列的SQL类型：" + rsmd.getColumnType(2));
			//获取指定列的数据库特定的类型名称getColumnTypeName(int column) 
			System.out.println("视图的第2列的数据库特定的类型名称：" + rsmd.getColumnTypeName(2)); 
			System.out.println("视图表模式：" + rsmd.getSchemaName(2)); 
			System.out.println("视图列的名称：" + rsmd.getTableName(2)); 
			//检查列是否可以更新
			System.out.println("视图的第2列是否可以写入：" + rsmd.isReadOnly(2)); 
			System.out.println("视图的第2列是否可以更新：" + rsmd.isWritable(2)); 
			System.out.println("视图的第2列是否可以更新：" + rsmd.isDefinitelyWritable(2)); 


			System.out.println("");
			

			// 测试表
			ps = conn.prepareStatement(sql2);
			rs = ps.executeQuery();
			rsmd = rs.getMetaData();
			numberOfColumns = rsmd.getColumnCount();
			System.out.println("表列数：" + numberOfColumns);
			System.out.println("表的第2列的名称：" + rsmd.getColumnName(2));
			System.out.println("表的第2列的SQL类型：" + rsmd.getColumnType(2));
			System.out.println("表的第2列的数据库特定的类型名称：" + rsmd.getColumnTypeName(2));  
			System.out.println("表的表模式：" + rsmd.getSchemaName(2)); 
			System.out.println("表的第2列是否可以写入：" + rsmd.isReadOnly(2)); 
			System.out.println("表的第2列是否可以更新：" + rsmd.isWritable(2)); 
			System.out.println("表的第2列是否可以更新：" + rsmd.isDefinitelyWritable(2)); 

			OracleUtils.close(ps, conn, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	


}
