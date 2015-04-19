package minhang.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import minhang.util.DatabaseDao;
import minhang.util.DatabaseSupport;
import minhang.util.GlobalConstant;

public class DimValue {
	private Connection c;
	public static List<String> DimTypes;// 維度
	public static List<String> DiquValue;// 发生地区
	public static List<String> DidianValue;// 发生地点
	public static List<String> JixingValue;// 航空器机型
	public static List<String> JixingDaleiValue;// 机型大类
	public static List<String> JihaoValue;// 航空器机号
	public static List<String> ShiyongDanweiValue;// 航空器使用单位
	public static List<String> HouguoValue;// 事件后果
	public static List<String> DengjiValue;// 事件等级
	public static List<String> DengjiDaleiValue;// 等级大类
	public static List<String> TiaokuanValue;// 事件条款
	public static List<String> TiaokuanDaleiValue;// 条款大类
	public static List<String> ZerenDanweiValue;// 责任单位
	public static List<String> ShijianLeixingValue;// 事件类型
	public static List<String> LeixingDaileiValue;// 类型大类
	public static List<String> YuanyinValue;// 事件原因
	public static List<String> YuanyinDaileiValue;// 原因大类
	public static List<String> YuanyinYinsuValue;// 原因因素
	public static List<String> FengxiangValue;// 风险值
	public static List<String> FeixingJieduanValue;// 飞行阶段
	public static List<String> XingzhiValue;// 事件性质
	public static List<String> ZuihouQifeiValue;// 最后起飞点
	public static List<String> MudidiValue;// 计划目的地
	public static List<String> ZerenDiquValue;// 责任地区

	public DimValue() {
		c = DatabaseSupport.getConnection(GlobalConstant.DBTYPE);
	}

	public List<String> getValue(int i) {
		switch (i) {
		case 1:
			return DiquValue;
		case 2:
			return DidianValue;
		case 3:
			return JixingValue;
		case 4:
			return JixingDaleiValue;
		case 5:
			return JihaoValue;
		case 6:
			return ShiyongDanweiValue;
		case 7:
			return HouguoValue;
		case 8:
			return DengjiValue;
		case 9:
			return DengjiDaleiValue;
		case 10:
			return TiaokuanValue;
		case 11:
			return TiaokuanDaleiValue;
		case 12:
			return ZerenDanweiValue;
		case 13:
			return ShijianLeixingValue;
		case 14:
			return LeixingDaileiValue;
		case 15:
			return YuanyinValue;
		case 16:
			return YuanyinDaileiValue;
		case 17:
			return YuanyinYinsuValue;
		case 18:
			return FengxiangValue;
		case 19:
			return FeixingJieduanValue;
		case 20:
			return XingzhiValue;
		case 21:
			return ZuihouQifeiValue;
		case 22:
			return MudidiValue;
		case 23:
			return ZerenDiquValue;
		}
		return null;

	}

	public void Intialize() {
//		// c = new DatabaseSupport().getConnection();//mysql
//		db = DatabaseDao.getInstance();// oracle
//		c = db.getConnection();
		
		DimTypes = this.getAllDimtypesStr();
		DiquValue = getDimtypeValue("发生地区");
		DidianValue = getDimtypeValue("发生地点");
		JixingValue = getDimtypeValue("航空器机型");
		JixingDaleiValue = getDimtypeValue("机型大类");
		JihaoValue = getDimtypeValue("航空器机号");
		ShiyongDanweiValue = getDimtypeValue("航空器使用单位");
		HouguoValue = getDimtypeValue("事件后果");
		DengjiValue = getDimtypeValue("事件等级");
		DengjiDaleiValue = getDimtypeValue("等级大类");
		TiaokuanValue = getDimtypeValue("事件条款");
		TiaokuanDaleiValue = getDimtypeValue("条款大类");
		ZerenDanweiValue = getDimtypeValue("责任单位");
		ShijianLeixingValue = getDimtypeValue("事件类型");
		LeixingDaileiValue = getDimtypeValue("类型大类");
		YuanyinValue = getDimtypeValue("事件原因");
		YuanyinDaileiValue = getDimtypeValue("原因大类");
		YuanyinYinsuValue = getDimtypeValue("原因因素");
		FengxiangValue = getDimtypeValue("风险值");
		FeixingJieduanValue = getDimtypeValue("飞行阶段");
		XingzhiValue = getDimtypeValue("事件性质");
		ZuihouQifeiValue = getDimtypeValue("最后起飞点");
		MudidiValue = getDimtypeValue("计划目的地");
		ZerenDiquValue = getDimtypeValue("责任地区");

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
				if (value != null && value.trim().length() > 0) {
					results.add(value);
					System.out.println("dvalue" + dimtype + " :" + value);
				}
			}
			rs.close();
			stmt.close();

		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		return results;
	}

}
