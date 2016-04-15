package uet.khodulieu.quanpho;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * For stored database subbject
 * @author Nguyễn Văn Đô K57CB
 * @Date 2016/04/14
 *
 */

public class ConnectDb {
	
	private static ConnectDb connectDb = new ConnectDb();
	
	private Connection conn = null;
	private Statement stmt = null; 
	
	private ConnectDb() {
		// TODO Auto-generated constructor stub
		loadConnection();
	}
	
	private void loadConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/nhom_quanpho",
					"root", "xxxx");
			
			stmt = conn.createStatement();
		
		} catch (ClassNotFoundException e) {
		} catch (SQLException e) {
			close();
		}
	}
	
	public static ConnectDb getConn() {
		return connectDb;
	}
	
	public void insertToDB(QuanPho quanPho) {
		String sql = "insert into QuanPho(name,address,xLoc,yLoc) values('" +
				quanPho.getName() + "','" + quanPho.getAddress() + "','" + 
				quanPho.getxLoc() + "','" + quanPho.getyLoc() + "')";
		try {
			stmt.executeUpdate(sql);
			System.out.println("insert success!!!");
		} catch (SQLException e) {
			//e.printStackTrace();
		}
	}
	
	public void close() {
		try {
			if(stmt != null) {
				conn.close();
			}
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			//e.printStackTrace();
		}
		System.out.println("Goodbye!!!!");
	}
}
