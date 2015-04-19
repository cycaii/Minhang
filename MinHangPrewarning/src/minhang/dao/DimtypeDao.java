package minhang.dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import minhang.entity.Dimtype;
import minhang.util.DatabaseDao;
import minhang.util.DatabaseSupport;
import minhang.util.GlobalConstant;


public class DimtypeDao {
	Connection c;
	public static DimtypeDao dimtypeDao;

	public static DimtypeDao getInstance() {
		if (dimtypeDao == null) {
			dimtypeDao = new DimtypeDao();
		}
		return dimtypeDao;
	}

	public DimtypeDao() {
		c = DatabaseSupport.getConnection(GlobalConstant.DBTYPE);
	}

	// get all types for list
	public List<Dimtype> getAllDimtypes() {
		List<Dimtype> results = new ArrayList<Dimtype>();
		String sql = "select * from dimtypes";
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				Dimtype d = new Dimtype();
				d.setDimType(rs.getString("dimType"));
				results.add(d);
				// System.out.println("OneDimAnalysis :"+o.getDimType()+" "+o.getTime());
			}
			rs.close();
			stmt.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return results;
	}

	// get all types num
	public int getAllDimtypesCount() {
		int i = 0;
		String sql = "select count(*) from dimtypes";
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				i = rs.getInt(1);
			}
			rs.close();
			stmt.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		 System.out.println("num:"+i);
		return i;
	}

	// get all types string for list
	public List<String> getAllDimtypesStr() {
		List<String> results = new ArrayList<String>();
		String sql = "select * from dimtypes";
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String type = rs.getString("dimType");
				results.add(type);
				// System.out.println("type :"+type);
			}
			rs.close();
			stmt.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return results;
	}

	// get value strings for special type (for list)
	public List<String> getDimtypeValue(String dimtype) {
		List<String> results = new ArrayList<String>();
		String sql = "select distinct dvalue from onedimstatistic where dimension='"
				+ dimtype + "'";
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String value = rs.getString("dvalue");
				results.add(value);
				// System.out.println("dvalue" + dimtype + " :" + value);
			}
			rs.close();
			stmt.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return results;
	}

	// get value strings for special type (for list)
	public String[] getDimtypeValueStr(String dimtype) {
		List<String> results = new ArrayList<String>();
		String sql = "select distinct dvalue from onedimstatistic where dimension='"
				+ dimtype + "'";
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String value = rs.getString("dvalue");
				results.add(value);
				// System.out.println("dvalue"+dimtype+" :"+value);
			}
			rs.close();
			stmt.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		String[] resultStr = new String[results.size()];
		for (int i = 0; i < results.size(); i++) {
			resultStr[i] = results.get(i);
			System.out.println("dvaluestr" + dimtype + " :" + resultStr[i]);
		}
		return resultStr;
	}

	// get value strings for special type (for list)
	public String[][] getDimtypeValueStrArray() {
		// String[][] result = new String[getAllDimtypesCount()][];
		String[][] result = new String[23][];
		DimValue dv = new DimValue();
		dv.Intialize();
		for (int i = 0; i < result.length; i++) {
			List<String> vList = dv.getValue(i + 1);
			result[i] = new String[vList.size()];
			for (int j = 0; j < vList.size(); j++) {
				result[i][j] = vList.get(j);
			}
		}
		return result;
	}

	public static void main(String[] args) {
		DimtypeDao d = new DimtypeDao();
		// d.getAllDimtypesStr();
//		d.getDimtypeValueStr("原因因素");
		d.getAllDimtypesCount();
		// d.getDimtypeValueStrArray();

	}
}
