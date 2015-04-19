/**
 * 
 */
package minhang.bean;

import java.util.List;


/**
 * 频发事件 PinfaEvent代表表格中的一行，即一个维度值行
 * 
 * @author Cycai
 *
 */
public class PinfaEvent {
	/** 维度类型 */
	private String dimtype;
	/** 维度值 */
	private String dimvalue;

//	private int day0;
	private List<PinfaElem>  days; 
//	private PinfaElem day1;
//	private PinfaElem day2;
//	private PinfaElem day3;
//	private PinfaElem day4;
//	private PinfaElem day5;
//	private PinfaElem day6;
//	private PinfaElem day7;
	private double avgNum;
	/**是频发维度值*/
    private boolean isRed; 
    /** 频发时间段*/
	private String pinfaPeriod;
	 /** 对应数量值*/
	private List<PinfaElem>  countValue; 
	/**
	 * 
	 */
	public PinfaEvent() {
		isRed = false;
	}

	public PinfaEvent( String dimvalue, List<PinfaElem> days, double avgNum) {
		super();
		this.dimvalue = dimvalue;
		this.days = days;
		this.avgNum = avgNum;
		isRed = false;
	}

	public PinfaEvent(String dimvalue) {
		this.dimvalue = dimvalue;
		isRed = false;
	}


 

	public PinfaEvent(String dimtype,String dimvalue, List<PinfaElem> days, double avgNum,
			boolean isRed, String pinfaPeriod, List<PinfaElem> countValue) {
		super();
		this.dimtype = dimtype;
		this.dimvalue = dimvalue;
		this.days = days;
		this.avgNum = avgNum;
		this.isRed = isRed;
		this.pinfaPeriod = pinfaPeriod;
		this.countValue = countValue;
	}

	/**
	 * @return the dimvalue
	 */
	public String getDimvalue() {
		return dimvalue;
	}

	/**
	 * @param dimvalue
	 *            the dimvalue to set
	 */
	public void setDimvalue(String dimvalue) {
		this.dimvalue = dimvalue;
	}


	public double getAvgNum() {
		return avgNum;
	}

	public void setAvgNum(double avgNum) {
		this.avgNum = avgNum;
	}

	public List<PinfaElem> getDays() {
		return days;
	}

	public void setDays(List<PinfaElem> days) {
		this.days = days;
	}

	public boolean isRed() {
		return isRed;
	}

	public void setRed(boolean isRed) {
		this.isRed = isRed;
	}

	public String getPinfaPeriod() {
		return pinfaPeriod;
	}

	public void setPinfaPeriod(String pinfaPeriod) {
		this.pinfaPeriod = pinfaPeriod;
	}

	public List<PinfaElem> getCountValue() {
		return countValue;
	}

	public void setCountValue(List<PinfaElem> countValue) {
		this.countValue = countValue;
	}

	public String getDimtype() {
		return dimtype;
	}

	public void setDimtype(String dimtype) {
		this.dimtype = dimtype;
	}

}

 