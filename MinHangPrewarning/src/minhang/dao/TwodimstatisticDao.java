package minhang.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import minhang.entity.Onedimstatistic;
import minhang.entity.Twodimstatistic;
import minhang.util.DatabaseSupport;
import minhang.util.GlobalConstant;

public class TwodimstatisticDao {
	Connection c;
	public static TwodimstatisticDao twodimstatisticDao;

	public static TwodimstatisticDao getInstance() {
		if (twodimstatisticDao == null) {
			twodimstatisticDao = new TwodimstatisticDao();
		}
		return twodimstatisticDao;
	}

	public TwodimstatisticDao() {
		c = DatabaseSupport.getConnection(GlobalConstant.DBTYPE);
	}

	// get results by year
	public List<Twodimstatistic> getTwoDimResultByYear(String startYear,
			String endYear, String firstDimtype, String firstValue,
			String secondDimtype, String secondValue) {
		List<Twodimstatistic> results = new ArrayList<Twodimstatistic>();
		String sql = "select * from twodimstatistic where according='年' and yearnum >='"
				+ startYear + "' and yearnum<='" + endYear + "'";
		if (firstDimtype != null && !firstDimtype.trim().equals(""))
			sql += " and dimensionone='" + firstDimtype + "'";
		if (firstValue != null && !firstValue.trim().equals(""))
			sql += " and dvalueone='" + firstValue + "'";
		if (secondDimtype != null && !secondDimtype.trim().equals(""))
			sql += " and dimensiontwo='" + secondDimtype + "'";
		if (secondValue != null && !secondValue.trim().equals(""))
			sql += " and dvaluetwo='" + secondValue + "'";
		System.out.println("sql:" + sql);
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Twodimstatistic o = new Twodimstatistic();
				o.setId(Integer.parseInt(rs.getString("id")));
				o.setDimensionone(rs.getString("dimensionone"));
				o.setDvalueone(rs.getString("dvalueone"));
				o.setDimensiontwo(rs.getString("dimensiontwo"));
				o.setDvaluetwo(rs.getString("dvaluetwo"));
				o.setYear(Integer.parseInt(rs.getString("yearnum")));
				o.setCount(Integer.parseInt(rs.getString("countnum")));
				o.setAccording(rs.getString("according"));
				o.setNum(Integer.parseInt(rs.getString("num")));
				results.add(o);
				System.out.println("Twodimstatistic :" + o.getId() + "  "
						+ o.getYear() + "  " + o.getDimensionone() + " "
						+ o.getDvalueone() + "  " + "  " + o.getDimensiontwo()
						+ " " + o.getDvaluetwo() + " " + o.getCount());
			}
			rs.close();
			stmt.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return results;
	}

	// get results by year
	public List<Twodimstatistic> getTwoDimResultByAccording(String accord,
			String startYear, String endYear, String startNum, String endNum,
			String firstDimtype, String firstValue, String secondDimtype,
			String secondValue) {
		List<Twodimstatistic> results = new ArrayList<Twodimstatistic>();
		String sql = "select * from twodimstatistic where according='" + accord
				+ "'";
		if (startYear.equals(endYear))
			sql += "and yearnum='" + startYear + "' and num>=" + startNum
					+ " and num<=" + endNum;
		else
			sql += " and (yearnum >'" + startYear + "' and yearnum<'" + endYear
					+ "' or yearnum='" + startYear + "' and num>='" + startNum
					+ "' or yearnum='" + endYear + "' and num<='" + endNum
					+ "')";

		if (firstDimtype != null && !firstDimtype.trim().equals(""))
			sql += " and dimensionone='" + firstDimtype + "'";
		if (firstValue != null && !firstValue.trim().equals(""))
			sql += " and dvalueone='" + firstValue + "'";
		if (secondDimtype != null && !secondDimtype.trim().equals(""))
			sql += " and dimensiontwo='" + secondDimtype + "'";
		if (secondValue != null && !secondValue.trim().equals(""))
			sql += " and dvaluetwo='" + secondValue + "'";
		System.out.println("sql:" + sql);
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Twodimstatistic o = new Twodimstatistic();
				o.setId(Integer.parseInt(rs.getString("id")));
				o.setDimensionone(rs.getString("dimensionone"));
				o.setDvalueone(rs.getString("dvalueone"));
				o.setDimensiontwo(rs.getString("dimensiontwo"));
				o.setDvaluetwo(rs.getString("dvaluetwo"));
				o.setYear(Integer.parseInt(rs.getString("yearnum")));
				o.setCount(Integer.parseInt(rs.getString("countnum")));
				o.setAccording(rs.getString("according"));
				o.setNum(Integer.parseInt(rs.getString("num")));
				results.add(o);
				System.out.println("Twodimstatistic :" + o.getId() + "  "
						+ o.getYear() + "  " + o.getDimensionone() + " "
						+ o.getDvalueone() + "  " + "  " + o.getDimensiontwo()
						+ " " + o.getDvaluetwo() + " " + o.getCount());
			}
			rs.close();
			stmt.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return results;
	}

	public static void main(String[] args) {
		TwodimstatisticDao t = new TwodimstatisticDao();
		// t.getTwoDimResultByYear("2011", "2012", "事件等级", "", "原因大类", "非人为原因");
		t.getTwoDimResultByAccording("月", "2011", "2012", "1", "2", "事件等级", "",
				"发生地区", "");
	}
}
