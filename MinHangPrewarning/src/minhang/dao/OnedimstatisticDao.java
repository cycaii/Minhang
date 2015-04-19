package minhang.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import minhang.entity.Onedimstatistic;
import minhang.util.DatabaseDao;
import minhang.util.DatabaseSupport;
import minhang.util.GlobalConstant;

public class OnedimstatisticDao {
	Connection c;
	public static OnedimstatisticDao oneDao;

	public OnedimstatisticDao() {
		c = DatabaseSupport.getConnection(GlobalConstant.DBTYPE);
	}
//	 public OnedimstatisticDao(DatabaseSupport dbs) {
//	  c = DatabaseSupport.getConnection(GlobalConstant.DBTYPE);
//	 }
	 

	public static OnedimstatisticDao getInstance() {
		if (oneDao == null) {
			oneDao = new OnedimstatisticDao();
		}
		return oneDao;
	}

	/**get results by year
	 * 
	 * @param startYear
	 * @param endYear
	 * @param dimtype
	 * @param dvalue
	 * @return
	 */
	public List<Onedimstatistic> getOneDimResultByYear(String startYear,
			String endYear, String dimtype, String dvalue) {
		List<Onedimstatistic> results = new ArrayList<Onedimstatistic>();
		String sql = "select * from onedimstatistic where according='年' and yearnum >='"
				+ startYear + "' and yearnum<='" + endYear + "'";
		if (dimtype != null && !dimtype.trim().equals(""))
			sql += " and dimension='" + dimtype + "'";
		if (dvalue != null && !dvalue.trim().equals(""))
			sql += " and dvalue='" + dvalue + "'";
		System.out.println("sql:" + sql);
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Onedimstatistic o = new Onedimstatistic();
				o.setId(Integer.parseInt(rs.getString("id")));
				o.setDimension(rs.getString("dimension"));
				o.setDvalue(rs.getString("dvalue"));
				o.setYear(Integer.parseInt(rs.getString("yearnum")));
				o.setCount(Integer.parseInt(rs.getString("countnum")));
				o.setAccording(rs.getString("according"));
				o.setNum(Integer.parseInt(rs.getString("num")));
				results.add(o);
			}
			rs.close();
			stmt.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return results;
	}

	/**get results by season
	 * 
	 * @param startYear
	 * @param endYear
	 * @param startNum
	 * @param endNum
	 * @param dimtype
	 * @param dvalue
	 * @return
	 */
	public List<Onedimstatistic> getOneDimResultBySeason(String startYear,
			String endYear, String startNum, String endNum, String dimtype,
			String dvalue) {
		List<Onedimstatistic> results = new ArrayList<Onedimstatistic>();
		String sql = "select * from onedimstatistic where according='季'";
		if (startYear.equals(endYear))
			sql += "and yearnum='" + startYear + "' and num>=" + startNum
					+ " and num<=" + endNum;
		else
			sql += " and (yearnum >'" + startYear + "' and yearnum<'" + endYear
					+ "' or yearnum='" + startYear + "' and num>='" + startNum
					+ "' or yearnum='" + endYear + "' and num<='" + endNum
					+ "')";
		if (dimtype != null && !dimtype.trim().equals(""))
			sql += " and dimension='" + dimtype + "'";
		if (dvalue != null && !dvalue.trim().equals(""))
			sql += " and dvalue='" + dvalue + "'";
		System.out.println("sql:" + sql);
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Onedimstatistic o = new Onedimstatistic();
				o.setId(Integer.parseInt(rs.getString("id")));
				o.setDimension(rs.getString("dimension"));
				o.setDvalue(rs.getString("dvalue"));
				o.setYear(Integer.parseInt(rs.getString("yearnum")));
				o.setCount(Integer.parseInt(rs.getString("countnum")));
				o.setAccording(rs.getString("according"));
				o.setNum(Integer.parseInt(rs.getString("num")));
				results.add(o);
			}
			rs.close();
			stmt.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return results;
	}

	/**get results by jijie
	 * 
	 * @param startYear
	 * @param endYear
	 * @param startNum
	 * @param endNum
	 * @param dimtype
	 * @param dvalue
	 * @return
	 */
	public List<Onedimstatistic> getOneDimResultByJijie(String startYear,
			String endYear, String startNum, String endNum, String dimtype,
			String dvalue) {
		List<Onedimstatistic> results = new ArrayList<Onedimstatistic>();
		String sql = "select * from onedimstatistic where according='季节'";
		if (startYear.equals(endYear))
			sql += "and yearnum='" + startYear + "' and num>=" + startNum
					+ " and num<=" + endNum;
		else
			sql += " and (yearnum >'" + startYear + "' and yearnum<'" + endYear
					+ "' or yearnum='" + startYear + "' and num>='" + startNum
					+ "' or yearnum='" + endYear + "' and num<='" + endNum
					+ "')";

		if (dimtype != null && !dimtype.trim().equals(""))
			sql += " and dimension='" + dimtype + "'";
		if (dvalue != null && !dvalue.trim().equals(""))
			sql += " and dvalue='" + dvalue + "'";
		System.out.println("sql:" + sql);
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Onedimstatistic o = new Onedimstatistic();
				o.setId(Integer.parseInt(rs.getString("id")));
				o.setDimension(rs.getString("dimension"));
				o.setDvalue(rs.getString("dvalue"));
				o.setYear(Integer.parseInt(rs.getString("yearnum")));
				o.setCount(Integer.parseInt(rs.getString("countnum")));
				o.setAccording(rs.getString("according"));
				o.setNum(Integer.parseInt(rs.getString("num")));
				results.add(o);
				// System.out.println("Onedimstatistic :" + o.getId() + "  "
				// + o.getYear() + "  "+o.getNum()+"  " + o.getDimension() + " "
				// + o.getDvalue() + "  " + o.getCount());
			}
			rs.close();
			stmt.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return results;
	}

	/**get results by Month
	 * 
	 * @param startYear
	 * @param endYear
	 * @param startNum
	 * @param endNum
	 * @param dimtype
	 * @param dvalue
	 * @return
	 */
	public List<Onedimstatistic> getOneDimResultByMonth(String startYear,
			String endYear, String startNum, String endNum, String dimtype,
			String dvalue) {
		List<Onedimstatistic> results = new ArrayList<Onedimstatistic>();
		String sql = "select * from onedimstatistic where according='月'";
		if (startYear.equals(endYear))
			sql += "and yearnum='" + startYear + "' and num>=" + startNum
					+ " and num<=" + endNum;
		else
			sql += " and (yearnum >'" + startYear + "' and yearnum<'" + endYear
					+ "' or yearnum='" + startYear + "' and num>='" + startNum
					+ "' or yearnum='" + endYear + "' and num<='" + endNum
					+ "')";
		if (dimtype != null && !dimtype.trim().equals(""))
			sql += " and dimension='" + dimtype + "'";
		if (dvalue != null && !dvalue.trim().equals(""))
			sql += " and dvalue='" + dvalue + "'";
		System.out.println("sql:" + sql);
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Onedimstatistic o = new Onedimstatistic();
				o.setId(Integer.parseInt(rs.getString("id")));
				o.setDimension(rs.getString("dimension"));
				o.setDvalue(rs.getString("dvalue"));
				o.setYear(Integer.parseInt(rs.getString("yearnum")));
				o.setCount(Integer.parseInt(rs.getString("countnum")));
				o.setAccording(rs.getString("according"));
				o.setNum(Integer.parseInt(rs.getString("num")));
				results.add(o);
				// System.out.println("Onedimstatistic :" + o.getId() + "  "
				// + o.getYear() + "  " + o.getNum() + "  "
				// + o.getDimension() + " " + o.getDvalue() + "  "
				// + o.getCount());
			}
			rs.close();
			stmt.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return results;
	}

	/**get results by Xun
	 * 
	 * @param startYear
	 * @param endYear
	 * @param startNum
	 * @param endNum
	 * @param dimtype
	 * @param dvalue
	 * @return
	 */
	public List<Onedimstatistic> getOneDimResultByXun(String startYear,
			String endYear, String startNum, String endNum, String dimtype,
			String dvalue) {
		List<Onedimstatistic> results = new ArrayList<Onedimstatistic>();
		String sql = "select * from onedimstatistic where according='旬'";
		if (startYear.equals(endYear))
			sql += "and yearnum='" + startYear + "' and num>=" + startNum
					+ " and num<=" + endNum;
		else
			sql += " and (yearnum >'" + startYear + "' and yearnum<'" + endYear
					+ "' or yearnum='" + startYear + "' and num>='" + startNum
					+ "' or yearnum='" + endYear + "' and num<='" + endNum
					+ "')";
		if (dimtype != null && !dimtype.trim().equals(""))
			sql += " and dimension='" + dimtype + "'";
		if (dvalue != null && !dvalue.trim().equals(""))
			sql += " and dvalue='" + dvalue + "'";
		System.out.println("sql:" + sql);
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Onedimstatistic o = new Onedimstatistic();
				o.setId(Integer.parseInt(rs.getString("id")));
				o.setDimension(rs.getString("dimension"));
				o.setDvalue(rs.getString("dvalue"));
				o.setYear(Integer.parseInt(rs.getString("yearnum")));
				o.setCount(Integer.parseInt(rs.getString("countnum")));
				o.setAccording(rs.getString("according"));
				o.setNum(Integer.parseInt(rs.getString("num")));
				results.add(o);
				// System.out.println("Onedimstatistic :" + o.getId() + "  "
				// + o.getYear() + "  " + o.getNum() + "  "
				// + o.getDimension() + " " + o.getDvalue() + "  "
				// + o.getCount());
			}
			rs.close();
			stmt.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return results;
	}

	/**get results by Week
	 * 
	 * @param startYear
	 * @param endYear
	 * @param startNum
	 * @param endNum
	 * @param dimtype
	 * @param dvalue
	 * @return
	 */
	public List<Onedimstatistic> getOneDimResultByWeek(String startYear,
			String endYear, String startNum, String endNum, String dimtype,
			String dvalue) {
		List<Onedimstatistic> results = new ArrayList<Onedimstatistic>();
		String sql = "select * from onedimstatistic where according='周'";
		if (startYear.equals(endYear))
			sql += "and yearnum='" + startYear + "' and num>=" + startNum
					+ " and num<=" + endNum;
		else
			sql += " and (yearnum >'" + startYear + "' and yearnum<'" + endYear
					+ "' or yearnum='" + startYear + "' and num>='" + startNum
					+ "' or yearnum='" + endYear + "' and num<='" + endNum
					+ "')";

		if (dimtype != null && !dimtype.trim().equals(""))
			sql += " and dimension='" + dimtype + "'";
		if (dvalue != null && !dvalue.trim().equals(""))
			sql += " and dvalue='" + dvalue + "'";
		System.out.println("sql:" + sql);
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Onedimstatistic o = new Onedimstatistic();
				o.setId(Integer.parseInt(rs.getString("id")));
				o.setDimension(rs.getString("dimension"));
				o.setDvalue(rs.getString("dvalue"));
				o.setYear(Integer.parseInt(rs.getString("yearnum")));
				o.setCount(Integer.parseInt(rs.getString("countnum")));
				o.setAccording(rs.getString("according"));
				o.setNum(Integer.parseInt(rs.getString("num")));
				results.add(o);
				// System.out.println("Onedimstatistic :" + o.getId() + "  "
				// + o.getYear() + "  " + o.getNum() + "  "
				// + o.getDimension() + " " + o.getDvalue() + "  "
				// + o.getCount());
			}
			rs.close();
			stmt.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return results;
	}

	/**
	 * get results --同比 10年内的m月
	 * @param startYear
	 * @param endYear
	 * @param month
	 * @param dimtype
	 * @param dvalue
	 * @return
	 */
	public List<Onedimstatistic> getTPredictOneDimResult(int startYear,
			int endYear, int month, String dimtype, String dvalue) {
		List<Onedimstatistic> results = new ArrayList<Onedimstatistic>();
		String sql = "select * from onedimstatistic where according='月' and yearnum >='"
				+ startYear
				+ "' and yearnum<='"
				+ endYear
				+ "' and num="
				+ month;
		if (dimtype != null && !dimtype.trim().equals(""))
			sql += " and dimension='" + dimtype + "'";
		if (dvalue != null && !dvalue.trim().equals(""))
			sql += " and dvalue='" + dvalue + "'";
		// System.out.println("sql:" + sql);
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Onedimstatistic o = new Onedimstatistic();
				o.setId(Integer.parseInt(rs.getString("id")));
				o.setDimension(rs.getString("dimension"));
				o.setDvalue(rs.getString("dvalue"));
				o.setYear(Integer.parseInt(rs.getString("yearnum")));
				o.setCount(Integer.parseInt(rs.getString("countnum")));
				o.setAccording(rs.getString("according"));
				o.setNum(Integer.parseInt(rs.getString("num")));
				results.add(o);
				// System.out.println("tongbi-Onedimstatistic :" + o.getId() +
				// "  "
				// + o.getYear() + "  " + o.getNum() + "  "
				// + o.getDimension() + " " + o.getDvalue() + "  "
				// + o.getCount());
			}
			rs.close();
			stmt.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return results;
	}

	/**get results --环比 y年的1-m月 y-1年的12月起
	 * 
	 * @param year
	 * @param month
	 * @param dimtype
	 * @param dvalue
	 * @return
	 */
	public List<Onedimstatistic> getHPredictOneDimResult(int year, int month,
			String dimtype, String dvalue) {
		List<Onedimstatistic> results = new ArrayList<Onedimstatistic>();
		String sql = "select * from onedimstatistic where according='月' and (yearnum ='"
				+ year
				+ "'and num>=1 and num<"
				+ month
				+ "   or yearnum='"
				+ (year - 1) + "' and num=12) ";
		if (dimtype != null && !dimtype.trim().equals(""))
			sql += " and dimension='" + dimtype + "'";
		if (dvalue != null && !dvalue.trim().equals(""))
			sql += " and dvalue='" + dvalue + "'";
		// System.out.println("sql:" + sql);
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Onedimstatistic o = new Onedimstatistic();
				o.setId(Integer.parseInt(rs.getString("id")));
				o.setDimension(rs.getString("dimension"));
				o.setDvalue(rs.getString("dvalue"));
				o.setYear(Integer.parseInt(rs.getString("yearnum")));
				o.setCount(Integer.parseInt(rs.getString("countnum")));
				o.setAccording(rs.getString("according"));
				o.setNum(Integer.parseInt(rs.getString("num")));
				results.add(o);
				// System.out.println("huanbi-Onedimstatistic :" + o.getId() +
				// "  "
				// + o.getYear() + "  " + o.getNum() + "  "
				// + o.getDimension() + " " + o.getDvalue() + "  "
				// + o.getCount());
			}
			rs.close();
			stmt.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return results;
	}

	public static void main(String[] args) {
		OnedimstatisticDao d = new OnedimstatisticDao();
		// d.getOneDimResultByYear("2012", "2012", "飞行阶段");
		// d.getOneDimResultBySeason("2011", "2011", "2", "5", "事件原因");
		// d.getOneDimResultByJijie("2011", "2011", "2", "3", "事件等级");
		// d.getOneDimResultByMonth("2008", "2008", "6", "11", "航空器使用单位");
		// d.getOneDimResultByXun("2011", "2012", "2", "4", "发生地区", "东北");
		// d.getOneDimResultByWeek("2008", "2008", "1", "15", "","");

		// d.getHPredictOneDimResult(2006, 7, "事件类型", "鸟击");
		d.getTPredictOneDimResult(2001, 2011, 3, "事件类型", "鸟击");
	}
}
