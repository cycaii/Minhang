package minhang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import minhang.entity.ErweiResult;
import minhang.entity.YiweiResult;
import minhang.util.DatabaseSupport;
import minhang.util.GlobalConstant;

public class DBWorker {
	public static DBWorker dbWorker;
	Connection con = null;

	public static DBWorker getInstance() {
		if (dbWorker == null) {
			dbWorker = new DBWorker();
		}
		return dbWorker;
	}

	public DBWorker() {
		con = DatabaseSupport.getConnection(GlobalConstant.DBTYPE);
	}

	// ������
	public Vector<String> getNames() {
		con = DatabaseSupport.getConnection(GlobalConstant.DBTYPE);
		PreparedStatement ps = null;
		ResultSet rs = null;
		Vector<String> names = new Vector<String>();
		String sql = "select distinct namestr from duibijizhunbiao";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				names.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return names;
	}

	public boolean insertNames(int startyear, int startxuhao, int endyear,
			int endxuhao, String leixing, String name) {
		boolean a = false;
		con = DatabaseSupport.getConnection(GlobalConstant.DBTYPE);
		PreparedStatement ps = null;
		String sql = "insert into duibijizhunbiao  values(?,?,?,?,?,?)";
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, startyear);
			ps.setInt(2, startxuhao);
			ps.setInt(3, endyear);
			ps.setInt(4, endxuhao);
			ps.setString(5, leixing);
			ps.setString(6, name);
			ps.executeUpdate();
			a = true;
		} catch (SQLException e) {
			a = false;
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return a;
	}

	public boolean callYiweiavg() {
		boolean a = false;
		con = DatabaseSupport.getConnection(GlobalConstant.DBTYPE);
		String sql = "{call yiweiavg}";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.execute();
			a = true;
		} catch (SQLException e) {
			a = false;
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return a;
	}

	public boolean callErweiavg() {
		boolean a = false;
		con = DatabaseSupport.getConnection(GlobalConstant.DBTYPE);
		String sql = "{call erweiavg}";
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(sql);
			ps.execute();
			a = true;
		} catch (SQLException e) {
			a = false;
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return a;
	}

	public Vector<YiweiResult> getVector(int year, String leixing, int xuhao,
			String name) {
		Vector<YiweiResult> yrs = new Vector<YiweiResult>();
		con = DatabaseSupport.getConnection(GlobalConstant.DBTYPE);
		// String sql0="set @@character_set_database=utf8";
		String sql1 = "create TEMPORARY table temp1 select dimension,dvalue,countnum from onedimstatistic where onedimstatistic.yearnum="
				+ year
				+ " and onedimstatistic.according='"
				+ leixing
				+ "' and onedimstatistic.num=" + xuhao + ";";
		String sql2 = "create TEMPORARY table temp2 select * from onedimavgcount where namestr='"
				+ name + "';";
		String sql3 = "select temp1.dimension,temp1.dvalue,countnum,avgcount from temp1,temp2 where temp1.dimension=temp2.dimension and temp1.dvalue=temp2.dvalue";
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs = null;
		try {
			ps1 = con.prepareStatement(sql1);
			ps1.executeUpdate();
			ps2 = con.prepareStatement(sql2);
			ps2.executeUpdate();
			ps3 = con.prepareStatement(sql3);
			rs = ps3.executeQuery();
			while (rs.next()) {
				YiweiResult yr = new YiweiResult();
				yr.setWeidulx(rs.getString(1));
				yr.setWeiduvalue(rs.getString(2));
				yr.setPinshu(rs.getInt(3));
				yr.setAvgpinshu(rs.getFloat(4));
				yr.setPercent(yr.getPinshu() / yr.getAvgpinshu() * 100);
				yr.setZhishu(Compute.log(
						2 * (yr.getPinshu() - yr.getAvgpinshu())
								/ yr.getAvgpinshu(), 2));
				yrs.add(yr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps3 != null) {
					ps3.close();
				}
				if (ps2 != null) {
					ps2.close();
				}
				if (ps1 != null) {
					ps1.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return yrs;
	}

	public Vector<YiweiResult> getVector(String year, String leixing,
			String xuhao, String name) {
		Vector<YiweiResult> yrs = new Vector<YiweiResult>();
		con = DatabaseSupport.getConnection(GlobalConstant.DBTYPE);
		// String sql0="set @@character_set_database=utf8";
		String sql1 = "create TEMPORARY table temp1 select dimension,dvalue,countnum from onedimstatistic where onedimstatistic.yearnum="
				+ year
				+ " and onedimstatistic.according='"
				+ leixing
				+ "' and onedimstatistic.num=" + xuhao + ";";
		String sql2 = "create TEMPORARY table temp2 select * from onedimavgcount where namestr='"
				+ name + "';";
		String sql3 = "select temp1.dimension,temp1.dvalue,countnum,avgcount from temp1,temp2 where temp1.dimension=temp2.dimension and temp1.dvalue=temp2.dvalue";
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		ResultSet rs = null;
		try {
			ps1 = con.prepareStatement(sql1);
			ps1.executeUpdate();
			ps2 = con.prepareStatement(sql2);
			ps2.executeUpdate();
			ps3 = con.prepareStatement(sql3);
			rs = ps3.executeQuery();
			while (rs.next()) {
				YiweiResult yr = new YiweiResult();
				yr.setWeidulx(rs.getString(1));
				yr.setWeiduvalue(rs.getString(2));
				yr.setPinshu(rs.getInt(3));
				yr.setAvgpinshu(rs.getFloat(4));
				yr.setPercent(yr.getPinshu() / yr.getAvgpinshu() * 100);
				yr.setZhishu(Compute.log(
						2 * (yr.getPinshu() - yr.getAvgpinshu())
								/ yr.getAvgpinshu(), 2));
				yrs.add(yr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps3 != null) {
					ps3.close();
				}
				if (ps2 != null) {
					ps2.close();
				}
				if (ps1 != null) {
					ps1.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return yrs;
	}

	public Vector<ErweiResult> getVector2(int year, String leixing, int xuhao,
			String name) {
		Vector<ErweiResult> yrs = new Vector<ErweiResult>();
		con = DatabaseSupport.getConnection(GlobalConstant.DBTYPE);
		String sql = "with temp1 as (select dimensionone,dvalueone,dimensiontwo,dvaluetwo,countnum from twodimstatistic where twodimstatistic.yearnum="
				+ year
				+ " and twodimstatistic.according='"
				+ leixing
				+ "' and twodimstatistic.num="
				+ xuhao
				+ "),temp2 as(select * from twodimavgcount where namestr='"
				+ name
				+ "') select temp1.dimensionone,temp1.dvalueone,temp1.dimensiontwo,temp1.dvaluetwo,countnum,avgcount from temp1,temp2 where temp1.dimensionone=temp2.dimensionone and temp1.dvalueone=temp2.dvalueone and temp1.dimensiontwo=temp2.dimensiontwo and temp1.dvaluetwo=temp2.dvaluetwo";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				ErweiResult yr = new ErweiResult();
				yr.setWeidulx1(rs.getString(1));
				yr.setWeiduvalue1(rs.getString(2));
				yr.setWeidulx2(rs.getString(3));
				yr.setWeiduvalue2(rs.getString(4));
				yr.setPinshu(rs.getInt(5));
				yr.setAvgpinshu(rs.getFloat(6));
				yr.setPercent(yr.getPinshu() / yr.getAvgpinshu() * 100);
				yr.setZhishu(Compute.log(
						2 * (yr.getPinshu() - yr.getAvgpinshu())
								/ yr.getAvgpinshu(), 2));
				yrs.add(yr);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return yrs;
	}
}

class Compute {
	static public double log(double value, double base) {
		return Math.log(value) / Math.log(base);
	}
}