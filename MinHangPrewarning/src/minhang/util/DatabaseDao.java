package minhang.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseDao {
	private static DatabaseDao databaseDao;
	private Connection conn = null;
	private static String url = "jdbc:oracle:thin:@localhost:1521:minhang";
	private static String user = "system";// scott为登陆oracle数据库的用户名system
	private static String password = "Admin123"; // 1234为用户名scott的密码 Myzhou2012

	public static DatabaseDao getInstance() {
		if (databaseDao == null) {
			databaseDao = new DatabaseDao();
		}
		return databaseDao;
	}

	public Connection getConnection() {
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
			e.printStackTrace();
		}

		return conn;
	}

	public void releaseConnection() throws SQLException {
		if (this.conn != null)
			this.conn.close();
	}

	public ResultSet getResultSet(String querySQL) throws SQLException,
			ClassNotFoundException {
		System.out.println("sql���:" + querySQL);
		if (conn == null) {
			this.conn = getConnection();
		}

		Statement stm = conn.createStatement();

		ResultSet thisRST = null;
		thisRST = stm.executeQuery(querySQL);

		return thisRST;
	}

	public void executeSQL(String SQL) throws SQLException,
			ClassNotFoundException {
		if (conn == null) {
			this.conn = getConnection();
		}
		Statement stm = conn.createStatement();
		stm.executeUpdate(SQL);
		stm.close();
		this.conn.close();
		this.conn = null;
	}

}