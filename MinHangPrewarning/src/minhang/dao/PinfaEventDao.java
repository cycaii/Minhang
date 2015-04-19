/**
 * 
 */
package minhang.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import minhang.bean.PinfaElem;
import minhang.bean.PinfaEvent;
import minhang.entity.Totalbase;
import minhang.util.DatabaseSupport;
import minhang.util.GlobalConstant;

import org.apache.commons.lang.StringUtils;

/**
 * 频发事件
 * 
 * @author Cycai
 * 
 */
public class PinfaEventDao {
	Connection c;
	public static PinfaEventDao pinfaEventDao;

	public static PinfaEventDao getInstance() {
		if (pinfaEventDao == null) {
			pinfaEventDao = new PinfaEventDao();
		}
		return pinfaEventDao;
	}

	/**
	 * 
	 */
	public PinfaEventDao() {
		c = DatabaseSupport.getConnection(GlobalConstant.DBTYPE);
	}

	public List<PinfaEvent> queryOneDimPinfaEvent(String dimtype, String date) {
		String colName = getcolName(dimtype);
		List<PinfaEvent> pinfalist = new ArrayList<PinfaEvent>();

		String basesql = "select * from totalbase where fashengriqi =";
		String sql;
		try {
			for (int i = 7; i >= 0; i--) {
				// mysql
				sql = basesql + "date_sub('" + date + "',interval " + i
						+ " day)";
				// oracle
				// sql = basesql + "to_date('" + date + "','yyyy-mm-dd')-" + i;
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Date dayDate = new Date();
				while (rs.next()) {
					// 判断选中的维度是否有值
					String v1 = rs.getString(colName);
					if (v1 != null && v1.trim().length() > 0) {// !StringUtils.isBlank(v1)
						// 判断是否在list中
						int pos = existTwoColvalue(pinfalist, v1);
						if (pos >= 0) {
							dayDate = rs.getDate("fashengriqi");
							pinfalist.get(pos).getDays().get(7 - i)
									.setDay(dayDate);
							pinfalist
									.get(pos)
									.getDays()
									.get(7 - i)
									.setNum(pinfalist.get(pos).getDays()
											.get(7 - i).getNum() + 1);
						} else {// 不存在，添加入list
							dayDate = rs.getDate("fashengriqi");
							PinfaElem dayElem = new PinfaElem(1, 0, 0, dayDate);// num=1
							PinfaEvent pinfaEvent = new PinfaEvent(dimtype, v1,
									new ArrayList<PinfaElem>(), 0, false, "",
									new ArrayList<PinfaElem>());
							pinfaEvent.getDays().add(dayElem);
							dayElem = new PinfaElem(0, 0, 0, dayDate);
							pinfaEvent.getDays().add(dayElem);
							dayElem = new PinfaElem(0, 0, 0, dayDate);
							pinfaEvent.getDays().add(dayElem);
							dayElem = new PinfaElem(0, 0, 0, dayDate);
							pinfaEvent.getDays().add(dayElem);
							dayElem = new PinfaElem(0, 0, 0, dayDate);
							pinfaEvent.getDays().add(dayElem);
							dayElem = new PinfaElem(0, 0, 0, dayDate);
							pinfaEvent.getDays().add(dayElem);
							dayElem = new PinfaElem(0, 0, 0, dayDate);
							pinfaEvent.getDays().add(dayElem);
							dayElem = new PinfaElem(0, 0, 0, dayDate);
							pinfaEvent.getDays().add(dayElem);
							pinfalist.add(pinfaEvent);
						}

					}

				}
				rs.close();
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pinfalist;
	}

	/**
	 * 按维度值查询频发事件
	 * 
	 * 填充PinfaEvent里PinfaElem的num 与 dimvalue 初始color=0
	 * 
	 * @param dimvalue
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public PinfaEvent queryOneDimPinfaEvent(String dimtype, String dimvalue,
			String date) {
		String colName = getcolName(dimtype);
		PinfaEvent pinfaEvent = new PinfaEvent(dimtype, dimvalue,
				new ArrayList<PinfaElem>(), 0, false, "",
				new ArrayList<PinfaElem>());

		String basesql = "select * from totalbase where " + colName + "= '"
				+ dimvalue + "' and  fashengriqi =";
		String sql;
		try {
			for (int i = 7; i >= 0; i--) {
				// mysql
				// sql = basesql + "date_sub('" + date + "',interval " + i
				// + " day)";
				// oracle
				sql = basesql + "to_date('" + date + "','yyyy-mm-dd')-" + i;
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				int num = 0;
				Date dayDate = new Date();
				while (rs.next()) {
					dayDate = rs.getDate("fashengriqi");
					num++;
				}
				PinfaElem dayElem = new PinfaElem(num, 0, 0, dayDate);
				pinfaEvent.getDays().add(dayElem);
				rs.close();
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pinfaEvent;
	}

	public List<Totalbase> queryOneDimPinfaEventDetails(String dimtype,
			String dimvalue, Date day) {
		String colName = getcolName(dimtype);
		List<Totalbase> results = new ArrayList<Totalbase>();
		Totalbase totalbase = null;
		/** mysql */
		String basesql = "select * from totalbase where " + colName + "= '"
				+ dimvalue + "' and  fashengriqi ='"
				+ new java.sql.Date(day.getTime()) + "'";

		/** oracle */
		// String basesql = "select * from totalbase where " + colName + "= '"
		// + dimvalue + "' and  to_char(fashengriqi,'YYYY-MM-DD') ='"
		// + new java.sql.Date(day.getTime()) + "'";
		// select * from mytable where to_char(install_date,'YYYYMMDD') >
		// '20050101'
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(basesql);
			Date dayDate = null;
			while (rs.next()) {
				dayDate = rs.getDate("fashengriqi");
				totalbase = new Totalbase(rs.getString("myindex"),
						rs.getString("biaoti"), rs.getString("dengjidalei"),
						rs.getString("fashengdidian"),
						rs.getString("fashengdiqu"), day,
						rs.getString("feixingjieduan"),
						rs.getString("fengxianzhi"),
						rs.getString("hangkongqijihao"),
						rs.getString("hangkongqijixing"),
						rs.getString("hangkongqishiyongdanwei"),
						rs.getString("jihuamudidi"),
						rs.getString("jixingdalei"),
						rs.getString("leixingdalei"),
						rs.getString("shangbaoriqi"),
						rs.getString("shijiandengji"),
						rs.getString("shijianhouguo"),
						rs.getString("shijianleixing"),
						rs.getString("shijiantiaokuan"),
						rs.getString("shijianxingzhi"),
						rs.getString("shijianyuanyin"),
						rs.getString("tiaokuandalei"),
						rs.getString("yuanyindalei"),
						rs.getString("yuanyinyinsu"),
						rs.getString("zerendanwei"), rs.getString("zerendiqu"),
						rs.getString("zuihouqifeidian"));
				results.add(totalbase);
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return results;
	}

	/**
	 * 按维度值查询频发事件
	 * 
	 * 填充PinfaEvent里PinfaElem的num 与 dimvalue 初始color=0
	 * 
	 * @param dimvalue
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public List<PinfaEvent> queryTwoDimPinfaEvent(String dimtype1,
			String dimtype2, String date) {
		String colName1 = getcolName(dimtype1);
		String colName2 = getcolName(dimtype2);
		List<PinfaEvent> pinfalist = new ArrayList<PinfaEvent>();

		String basesql = "select * from totalbase where fashengriqi =";
		String sql;
		try {
			for (int i = 7; i >= 0; i--) {
				/** mysql */
				sql = basesql + "date_sub('" + date + "',interval " + i
						+ " day)";
				/** oracle */
				// sql = basesql + "to_date('" + date
				// + "','yyyy-mm-dd')-"+i;
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				Date dayDate = new Date();
				while (rs.next()) {
					// 判断选中的两个维度是否有值
					String v1 = rs.getString(colName1);
					String v2 = rs.getString(colName2);
					if ((v1 != null && v1.trim().length() > 0)
							&& (v2 != null && v2.trim().length() > 0)) {// !StringUtils.isBlank(v1)
																		// &&
																		// !StringUtils.isBlank(v2)

						// 判断是否在list中
						int pos = existTwoColvalue(pinfalist, v1 + " " + v2);
						if (pos >= 0) {
							dayDate = rs.getDate("fashengriqi");
							pinfalist.get(pos).getDays().get(7 - i)
									.setDay(dayDate);
							pinfalist
									.get(pos)
									.getDays()
									.get(7 - i)
									.setNum(pinfalist.get(pos).getDays()
											.get(7 - i).getNum() + 1);
						} else {// 不存在，添加入list
							dayDate = rs.getDate("fashengriqi");
							PinfaElem dayElem = new PinfaElem(1, 0, 0, dayDate);// num=1
							PinfaEvent pinfaEvent = new PinfaEvent(dimtype1
									+ " " + dimtype2, v1 + " " + v2,
									new ArrayList<PinfaElem>(), 0, false, "",
									new ArrayList<PinfaElem>());
							pinfaEvent.getDays().add(dayElem);
							dayElem = new PinfaElem(0, 0, 0, dayDate);
							pinfaEvent.getDays().add(dayElem);
							dayElem = new PinfaElem(0, 0, 0, dayDate);
							pinfaEvent.getDays().add(dayElem);
							dayElem = new PinfaElem(0, 0, 0, dayDate);
							pinfaEvent.getDays().add(dayElem);
							dayElem = new PinfaElem(0, 0, 0, dayDate);
							pinfaEvent.getDays().add(dayElem);
							dayElem = new PinfaElem(0, 0, 0, dayDate);
							pinfaEvent.getDays().add(dayElem);
							dayElem = new PinfaElem(0, 0, 0, dayDate);
							pinfaEvent.getDays().add(dayElem);
							dayElem = new PinfaElem(0, 0, 0, dayDate);
							pinfaEvent.getDays().add(dayElem);
							pinfalist.add(pinfaEvent);
						}

					}

				}
				rs.close();
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pinfalist;
	}

	private int existTwoColvalue(List<PinfaEvent> pinfalist, String twocolvalue) {
		int r = -1;
		for (int i = 0; i < pinfalist.size(); i++) {
			PinfaEvent pe = pinfalist.get(i);
			if (pe.getDimvalue().equals(twocolvalue))
				return i;
		}
		return r;
	}

	public PinfaEvent queryTwoDimPinfaEvent(String dimtype1, String dimvalue1,
			String dimtype2, String dimvalue2, String date) {
		String colName1 = getcolName(dimtype1);
		String colName2 = getcolName(dimtype2);
		PinfaEvent pinfaEvent = new PinfaEvent(dimtype1 + " " + dimtype2,
				dimvalue1 + " " + dimvalue2, new ArrayList<PinfaElem>(), 0,
				false, "", new ArrayList<PinfaElem>());

		String basesql = "select * from totalbase where " + colName1 + "= '"
				+ dimvalue1 + "' and " + colName2 + "= '" + dimvalue2
				+ "' and  fashengriqi =";
		String sql;
		try {
			for (int i = 7; i >= 0; i--) {
				/** mysql */
				sql = basesql + "date_sub('" + date + "',interval " + i
						+ " day)";
				/** oracle */
				// sql = basesql + "to_date('" + date
				// + "','yyyy-mm-dd')-"+i;
				Statement stmt = c.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				int num = 0;
				Date dayDate = new Date();
				while (rs.next()) {
					dayDate = rs.getDate("fashengriqi");
					num++;
				}
				PinfaElem dayElem = new PinfaElem(num, 0, 0, dayDate);
				pinfaEvent.getDays().add(dayElem);
				rs.close();
				stmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pinfaEvent;
	}

	public List<Totalbase> queryTwoDimPinfaEventDetails(String dimtype1,
			String dimvalue1, String dimtype2, String dimvalue2, Date day) {
		String colName1 = getcolName(dimtype1);
		String colName2 = getcolName(dimtype2);
		List<Totalbase> results = new ArrayList<Totalbase>();
		Totalbase totalbase = null;
		/** mysql */
		String basesql = "select * from totalbase where " + colName1 + "= '"
				+ dimvalue1 + "' and " + colName2 + "= '" + dimvalue2
				+ "' and  fashengriqi ='" + new java.sql.Date(day.getTime())
				+ "'";
		/** oracle */
		// String basesql = "select * from totalbase where " + colName1 + "= '"
		// + dimvalue1 + "' and " + colName2 + "= '" + dimvalue2
		// + "' and  to_char(fashengriqi,'YYYY-MM-DD') ='"
		// + new java.sql.Date(day.getTime()) + "'";
		try {
			Statement stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery(basesql);
			Date dayDate = null;
			while (rs.next()) {
				dayDate = rs.getDate("fashengriqi");
				totalbase = new Totalbase(rs.getString("myindex"),
						rs.getString("biaoti"), rs.getString("dengjidalei"),
						rs.getString("fashengdidian"),
						rs.getString("fashengdiqu"), day,
						rs.getString("feixingjieduan"),
						rs.getString("fengxianzhi"),
						rs.getString("hangkongqijihao"),
						rs.getString("hangkongqijixing"),
						rs.getString("hangkongqishiyongdanwei"),
						rs.getString("jihuamudidi"),
						rs.getString("jixingdalei"),
						rs.getString("leixingdalei"),
						rs.getString("shangbaoriqi"),
						rs.getString("shijiandengji"),
						rs.getString("shijianhouguo"),
						rs.getString("shijianleixing"),
						rs.getString("shijiantiaokuan"),
						rs.getString("shijianxingzhi"),
						rs.getString("shijianyuanyin"),
						rs.getString("tiaokuandalei"),
						rs.getString("yuanyindalei"),
						rs.getString("yuanyinyinsu"),
						rs.getString("zerendanwei"), rs.getString("zerendiqu"),
						rs.getString("zuihouqifeidian"));
				results.add(totalbase);
			}
			rs.close();
			stmt.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return results;
	}

	String getcolName(String dimtype) {
		String colName;
		switch (dimtype) {
		case "发生地区":
			colName = "fashengdiqu";
			break;
		case "发生地点":
			colName = "fashengdidian";
			break;
		case "航空器机型":
			colName = "hangkongqijixing";
			break;
		case "机型大类":
			colName = "jixingdalei";
			break;
		case "航空器机号":
			colName = "hangkongqijihao";
			break;
		case "航空器使用单位":
			colName = "hangkongqishiyongdanwei";
			break;
		case "事件后果":
			colName = "shijianhouguo";
			break;
		case "事件等级":
			colName = "shijiandengji";
			break;
		case "等级大类":
			colName = "dengjidalei";
			break;
		case "事件条款":
			colName = "shijiantiaokuan";
			break;
		case "条款大类":
			colName = "tiaokuandalei";
			break;
		case "责任单位":
			colName = "zerendanwei";
			break;
		case "事件类型":
			colName = "shijianleixing";
			break;
		case "类型大类":
			colName = "leixingdalei";
			break;
		case "事件原因":
			colName = "shijianyuanyin";
			break;
		case "原因大类":
			colName = "yuanyindalei";
			break;
		case "原因因素":
			colName = "yuanyinyinsu";
			break;
		case "风险值":
			colName = "fengxianzhi";
			break;
		case "飞行阶段":
			colName = "feixingjieduan";
			break;
		case "事件性质":
			colName = "shijianxingzhi";
			break;
		case "最后起飞点":
			colName = "zuihouqifeidian";
			break;
		case "计划目的地":
			colName = "jihuamudidi";
			break;
		case "责任地区":
			colName = "zerendiqu";
			break;
		default:
			colName = null;
			System.out.println("default");
		}

		return colName;
	}

	public static void main(String[] args) throws ParseException {
		PinfaEventDao pd = PinfaEventDao.getInstance();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date day = df.parse("2011-5-9");
		List<Totalbase> r = pd.queryOneDimPinfaEventDetails("发生地区", "西南", day);

		List<PinfaEvent> list = pd.queryOneDimPinfaEvent("发生地区", "2011-5-9");
		for (PinfaEvent pe : list) {
			System.out.println(pe.getDimtype() + " " + pe.getDimvalue() + " "
					+ pe.getDays().get(0).getNum());
		}
	}
}
