package minhang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import minhang.entity.User;
import minhang.util.DatabaseDao;
import minhang.util.DatabaseSupport;
import minhang.util.UUIDGenerator;
import minhang.util.GlobalConstant;

public class UserDao {
	Connection c;
	public static UserDao userDao;

	public static UserDao getInstance() {
		if (userDao == null) {
			userDao = new UserDao();
		}
		return userDao;
	}

	public UserDao() {
		c = DatabaseSupport.getConnection(GlobalConstant.DBTYPE);
	}

	/**
	 * 用户登陆验证
	 */
	public User login(String name, String password) {
		User u = null;
		String sql = "select  userid,username,password,permission,"
				+ "state,gender,description from userinfo  where username = ? and  password  = ? ";
		PreparedStatement pstmt = null;
		try {
			pstmt = c.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			System.out.println("sql语句 : " + sql);

			ResultSet rs = pstmt.executeQuery();// 查询

			if (rs.next()) {
				u = new User();
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setGender(rs.getInt("gender"));
				u.setPermission(rs.getInt("permission"));
				u.setDescription(rs.getString("description"));
				u.setState(rs.getInt("state"));
				u.setUserid(rs.getString("userid"));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return u;

	}

	/**
	 * 注册 添加用户 向数据库中的表UserInfor插入新记录
	 */
	public boolean addUser(User user) {
		boolean rtn = false;
		String sql = "insert into userinfo(userid,username,password,description,gender,permission,state) values(?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setString(1, UUIDGenerator.getUUID());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getDescription());
			pstmt.setInt(5, user.getGender());
			pstmt.setInt(6, 0);
			pstmt.setInt(7, 0);
			System.out.println("sql语句 : " + sql);

			int i = pstmt.executeUpdate();
			if (i == 1) {
				rtn = true;
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rtn;

	}

	/**
	 * 删除用户
	 */
	public boolean deleteUser(User user) {
		boolean result = false;
		String sql = "delete from userinfo where userid = '" + user.getUserid()
				+ "'";
		Statement stmt = null;
		try {
			stmt = c.createStatement();
			result = stmt.execute(sql);

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<User> getAllUsers() {
		List<User> list = new ArrayList<User>();
		String sql = "select userid,username,password,gender,description,permission,state from userinfo";
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				User user = new User();
				user.setUserid(rs.getString("userid"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setGender(rs.getInt("gender"));
				user.setPermission(rs.getInt("permission"));
				user.setState(rs.getInt("state"));
				user.setDescription(rs.getString("description"));

				list.add(user);
			}
			rs.close();
			stmt.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}

		System.out.println(list.size());
		return list;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User user = new User(UUIDGenerator.getUUID(), "minhang", "123456",
				"manager", 1, 1, 0);
		boolean r = UserDao.getInstance().addUser(user);
		System.out.println(r);
	}

}
