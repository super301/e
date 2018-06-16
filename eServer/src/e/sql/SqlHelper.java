package e.sql;
import java.util.*;
import java.sql.*;
public class SqlHelper {

	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	private int sum;
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url="jdbc:mysql://127.0.0.1/test?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=Hongkong";
	private String username = "3011";
	private String password = "3011";
	
	public SqlHelper() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public ResultSet queryExecute(String sql, String[] params) {
		try {
			ps = conn.prepareStatement(sql);
			for(int i = 0; i < params.length; i++) {
				ps.setString(i+1, params[i]);
			}
			rs = ps.executeQuery();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	public ResultSet queryExecute(String sql) {
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
//			if(rs.next()) {
//				sum = rs.getInt(1);
//			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return rs;
	}
	public boolean updateExecute(String sql, String[] params) {
		boolean b = true;
		try {
			ps = conn.prepareStatement(sql);
			for(int i=0; i < params.length; i++) {
				ps.setString(i+1, params[i]);
			}
			if(ps.executeUpdate() > 0) {
				b = false;
			}
		}catch(Exception e) {
			b = false;
			e.printStackTrace();
		}finally {
			this.close();
		}
		return b;
	}
	public void close() {
		try {
			if(rs != null)
				rs.close();
			if(ps != null)
				ps.close();
			if(conn != null)
				conn.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub

		SqlTest sql;
//		SqlHelper sql = new SqlHelper();
//		String insert = "insert into foods(name, price, uri) values(?, ?, ?)";
//		String []params = {"«—÷≠»‚ÕË∑π", "10.0", "E:/java-eclipse/se/eServer/images/4.jpg"};
//		sql.updateExecute(insert, params);
		sql = new SqlTest();
		int i = sql.queryExecute("select count(*) from foods");
		System.out.println(i);
		sql = new SqlTest();
		String select = "select * from foods where id=?";
		String [] params = {"1"};
		ResultSet rs = sql.queryExecute(select, params);
		while(rs.next()) {
			System.out.println(rs.getInt(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getDouble(3));
			System.out.println(rs.getString(4));
		}
	}

}
