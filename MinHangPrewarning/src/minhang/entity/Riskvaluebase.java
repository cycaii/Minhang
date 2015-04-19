package minhang.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * @author cyc
 * The persistent class for the riskvaluebase database table.
 * base table, for risk value
 */
public class Riskvaluebase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String index;

	private Date date;

	private double riskvalue;

	private String sjtype;//事件类型

	private String title;

	public Riskvaluebase() {
	}

	public String getIndex() {
		return this.index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getRiskvalue() {
		return this.riskvalue;
	}

	public void setRiskvalue(double riskvalue) {
		this.riskvalue = riskvalue;
	}

	public String getSjtype() {
		return this.sjtype;
	}

	public void setSjtype(String sjtype) {
		this.sjtype = sjtype;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}