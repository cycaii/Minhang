/**
 * 
 */
package minhang.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import minhang.entity.Avgriskvalue;
import minhang.entity.Onedimstatistic;
import minhang.entity.Riskvaluebase;
import minhang.util.DatabaseSupport;
import minhang.util.GlobalConstant;


/**
 * @author cyc
 * 
 */
public class RiskvalueDao {
	Connection c;
	public static RiskvalueDao riskvalueBaseDao;

	public static RiskvalueDao getInstance() {
		if (riskvalueBaseDao == null) {
			riskvalueBaseDao = new RiskvalueDao();
		}
		return riskvalueBaseDao;
	}

	public RiskvalueDao() {
		c = DatabaseSupport.getConnection(GlobalConstant.DBTYPE);
	}

	/**
	 * delete records from riskvalueBase where riskvalue is null
	 * */
	public boolean clearUp() {
		String sql = "delete from riskvaluebase where  ISNULL(riskvalue);";
		Statement stmt = null;
		boolean result = false;
		try {
			stmt = c.createStatement();
			result = stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	//Oracle wait to modify
	/**
	 * Compute and update risk value in the table[avgRiskvalue].
	 * Run this method when the basic table[riskvalueBase] has something changed.
	 * */
	public int updateRiskValue() {
		int result = 0;
		String selectSql = "select sjtype,avg(riskvalue) as avgrisk from riskvaluebase where  date_sub(now(),interval 10 year) <= fsdate group by sjtype;";
		String deleteSql = "delete from avgriskvalue";
		String updateSql = "insert into avgriskvalue(typevalue,avgriskvalue) values(?,?)";
		try {
			Statement stmt = c.createStatement();
			stmt.execute(deleteSql);
			PreparedStatement pstmt = null;
			ResultSet rs = stmt.executeQuery(selectSql);
			while (rs.next()) {
				pstmt=c.prepareStatement(updateSql);
				pstmt.setString(1, rs.getString("sjtype"));
				pstmt.setDouble(2, rs.getDouble("avgrisk"));
				pstmt.executeUpdate();
				result++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * get average risk value from table[avgriskvalue] order by desc
	 * //若将来扩充平均风险值表，不仅仅求事件类型维度的预警，可在此方法基础上添加方法 List<Avgriskvalue> getAvgRiskvalues(String dimtype)
	 * */
	public List<Avgriskvalue> getAvgRiskvalues()
	{
		List<Avgriskvalue> avgRiskvalueList=new ArrayList<Avgriskvalue>();
		String selectSql = "select typevalue,avgriskvalue from avgriskvalue order by avgriskvalue desc";
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(selectSql);
			Avgriskvalue avgriskvalue;
			while (rs.next()) {
				avgriskvalue = new Avgriskvalue();
				avgriskvalue.setTypevalue(rs.getString("typevalue"));
				avgriskvalue.setAvgriskvalue(rs.getDouble("avgriskvalue"));
				avgRiskvalueList.add(avgriskvalue);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return avgRiskvalueList;
	}
	
	/**
	 * get risk value by dimvalue  获取某个维度值的平均风险值
	 * */
	public double getRiskvalueforDimvalue(String dimvalue){
		String selectSql = "select avgriskvalue from avgriskvalue where typevalue like '"+dimvalue+"'";
		double riskvalue = 0;
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(selectSql);
			while (rs.next()) {
				riskvalue= rs.getDouble("avgriskvalue");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return riskvalue;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RiskvalueDao rd =RiskvalueDao.getInstance();
//		int r = rd.updateRiskValue();
//		System.out.println("r:"+r);
		
		List<Avgriskvalue> avgRiskvalueList = rd.getAvgRiskvalues();
		for(Avgriskvalue a:avgRiskvalueList)System.out.println("typevalue:"+a.getTypevalue()+"  "+a.getAvgriskvalue());
	}

}
