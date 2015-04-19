/**
 * 
 */
package minhang.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import minhang.bean.PinfaEvent;
import minhang.entity.Onedimstatistic;
import minhang.entity.Twodimstatistic;
import minhang.util.DatabaseDao;
import minhang.util.DatabaseSupport;
import minhang.util.GlobalConstant;

/**
 * @author cyc
 * 
 */
public class OuFaEventDao {
	Connection c;
	public static OuFaEventDao ouFaEventDao;

	public static OuFaEventDao getInstance() {
		if (ouFaEventDao == null) {
			ouFaEventDao = new OuFaEventDao();
		}
		return ouFaEventDao;
	}

	public OuFaEventDao() {
		c = DatabaseSupport.getConnection(GlobalConstant.DBTYPE);
	}

	/**
	 * 查询一维偶发事件
	 * 
	 * @param dimtype
	 * @param year
	 * @param according
	 * @param num
	 * @return
	 */
	public List<Onedimstatistic> queryOneDimOufaEvent(String dimtype,
			String year, String according, String num) {
		List<Onedimstatistic> results = new ArrayList<Onedimstatistic>();
		String sql = "select * from onedimstatistic where according='"
				+ according + "' and yearnum=" + year + " and num=" + num;
		if (dimtype != null && !dimtype.trim().equals(""))
			sql += " and dimension='" + dimtype + "'";
		sql += " and dvalue not in( select DISTINCT dvalue from onedimstatistic where according='"
				+ according + "' and yearnum<" + year + " and num=" + num;
		if (dimtype != null && !dimtype.trim().equals(""))
			sql += " and dimension='" + dimtype + "'";
		sql += ")";
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

	/**
	 * 查询二维偶发事件
	 * 
	 * @param dimtype1
	 * @param dimtype2
	 * @param year
	 * @param according
	 * @param num
	 * @return
	 */
	public List<Twodimstatistic> queryTwoDimOufaEvent(String dimtype1,
			String dimtype2, String year, String according, String num) {
		List<Twodimstatistic> results = new ArrayList<Twodimstatistic>();
		String sql = "select * from twodimstatistic where according='"
				+ according + "' and yearnum=" + year + " and num=" + num;
		String sql1 = "", sql2 = "";
		if (dimtype1 != null && !dimtype1.trim().equals(""))
			sql1 = " and dimensionone='" + dimtype1 + "'";
		if (dimtype2 != null && !dimtype2.trim().equals(""))
			sql2 = " and dimensiontwo='" + dimtype2 + "'";
		sql += sql1 + sql2;
		sql += " and dvalueone not in( select DISTINCT dvalueone from twodimstatistic where according='"
				+ according + "' and yearnum<" + year + " and num=" + num;
		sql += sql1 + sql2 + ")";
		sql += " and dvaluetwo not in( select DISTINCT dvaluetwo from twodimstatistic where according='"
				+ according + "' and yearnum<" + year + " and num=" + num;
		sql += sql1 + sql2 + ")";
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
			}
			rs.close();
			stmt.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return results;
	}

	public static void main(String[] args) {
		OuFaEventDao od = OuFaEventDao.getInstance();
		// List<Onedimstatistic> result = od.queryOneDimOufaEvent("", 2009+"",
		// "周", 1+"");
		// for(Onedimstatistic o:result){
		// System.out.println("dimvaue:"+o.getDimension()+o.getDvalue()+" year:"+o.getYear()+"  "+o.getNum()+o.getAccording()+" count:"+o.getCount());
		// }

		List<Twodimstatistic> result2 = od.queryTwoDimOufaEvent("发生地点",
				"航空器机型", 2009 + "", "周", 1 + "");
		for (Twodimstatistic o : result2) {
			System.out.println("dimvaue:" + o.getDimensionone()
					+ o.getDvalueone() + "  " + o.getDimensiontwo()
					+ o.getDvaluetwo() + " year:" + o.getYear() + "  "
					+ o.getNum() + o.getAccording() + " count:" + o.getCount());
		}
	}
}
