package emp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCUtil {
	private static String username="root";
	private static String password="chen123";
	private static String database = "stu";
	
	public static Connection getConnection(){
		Connection con =null;
		try {
			String url = "jdbc:mysql://localhost:3306/"+database;
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection con){
		try {
			if(con!=null) con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(PreparedStatement ps){
		try {
			if(ps!=null) ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs){
		try {
			if(rs!=null) rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
