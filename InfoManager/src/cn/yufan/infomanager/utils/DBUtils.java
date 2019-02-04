package cn.yufan.infomanager.utils;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBUtils {
	private static DataSource dataSource = null;
	static{
		try {
			InitialContext context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/test");
		}catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection(){
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
		}  catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void closeConnection(Connection conn){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
