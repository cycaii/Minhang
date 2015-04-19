package minhang.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseSupport {

	/**
	 * 获取connection
	 * type 1:Oracle
	 * type 2:mysql
	 * @param type
	 * @return
	 */
	public static Connection getConnection(int type) {
		if (type == 1)
			return getOracleConnection();
		if (type == 2)
			return getMysqlConnection();
		return null;
	}

	// oracle
	public static Connection getOracleConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:minhang";
		String user = "system";// scott为登陆oracle数据库的用户名system
		String password = "Admin123"; // 1234为用户名scott的密码 Myzhou2012
		Connection conn = null;
		
		try {
			// Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			// Class.forName("oracle.jdbc.OracleDriver").newInstance();
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// conn = DriverManager.getConnection("jdbc:odbc:minhang", "scott",
			// "1234");
			 conn = DriverManager.getConnection(url, user, password);
			System.out.println("connect successful!" + conn.toString());
		} catch (Exception e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}

		return conn;
	}

	/**
	 * 获取连接
	 */
	// mysql
	public static Connection getMysqlConnection() {
		String url = "jdbc:mysql://localhost:3306/minhang?useUnicode=true&characterEncoding=UTF-8";
		String user = "root";
		String password = "root";
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("connect successful!" + conn.toString());
			return conn;
		} catch (Exception e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		}
		return conn;
		 
	}

	public static void main(String[] args) {
		DatabaseSupport.getConnection(2);
	}
}
