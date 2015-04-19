package minhang.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import minhang.entity.PredictProbability;
import minhang.util.DatabaseSupport;
import minhang.util.GlobalConstant;

public class PredictProbabilityDao {
	Connection c;
	public static PredictProbabilityDao predictProbabilityDao;

	public static PredictProbabilityDao getInstance() {
		if (predictProbabilityDao == null) {
			predictProbabilityDao = new PredictProbabilityDao();
		}
		return predictProbabilityDao;
	}

	public PredictProbabilityDao() {
		c = DatabaseSupport.getConnection(GlobalConstant.DBTYPE);
	}

	public List<PredictProbability> getPredictProbabilityList() {
		List<PredictProbability> results = new ArrayList<PredictProbability>();
		String sql = "select * from predictprobability";
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			int i = 0;
			while (rs.next()) {
				PredictProbability d = new PredictProbability();
				d.setDimvalue(rs.getString("dimvalue"));
				d.setId(rs.getInt("id"));
				d.setProbability(rs.getDouble("probability"));
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

	/**
	 * 
	 * @param predictProbability
	 * @return
	 */
	public boolean insertPredictProbability(
			PredictProbability predictProbability) {
		boolean result = false;
		String sql = "insert into  predictprobability(id ,probability ,dimvalue) values(?,?,?)";
		try {
			PreparedStatement pstmt = c.prepareStatement(sql);
			pstmt.setInt(1, predictProbability.getId());
			pstmt.setDouble(2, predictProbability.getProbability());
			pstmt.setString(3, predictProbability.getDimvalue());
			int i = pstmt.executeUpdate();
			if (i == 1) {
				result = true;
			}
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * clear the table
	 * 
	 */
	public void clearAllPredictProbability() {
		String sql = "delete from  predictprobability";
		PreparedStatement stmt;
		try {
			stmt = c.prepareStatement(sql);
			int num = stmt.executeUpdate();
			System.out.println("clear  PredictProbability num:" + num);
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		PredictProbabilityDao d = new PredictProbabilityDao();

	}
}
