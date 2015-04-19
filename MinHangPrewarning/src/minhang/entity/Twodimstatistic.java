package minhang.entity;

import java.io.Serializable;


/**
 * The persistent class for the twodimstatistic database table.
 * 
 */
public class Twodimstatistic implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String according;

	private int count;

	private String dimensionone;

	private String dimensiontwo;

	private String dvalueone;

	private String dvaluetwo;

	private int year;
	
	private int num;

	public Twodimstatistic() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccording() {
		return this.according;
	}

	public void setAccording(String according) {
		this.according = according;
	}

	public int getCount() {
		return this.count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getDimensionone() {
		return this.dimensionone;
	}

	public void setDimensionone(String dimensionone) {
		this.dimensionone = dimensionone;
	}

	public String getDimensiontwo() {
		return this.dimensiontwo;
	}

	public void setDimensiontwo(String dimensiontwo) {
		this.dimensiontwo = dimensiontwo;
	}

	public String getDvalueone() {
		return this.dvalueone;
	}

	public void setDvalueone(String dvalueone) {
		this.dvalueone = dvalueone;
	}

	public String getDvaluetwo() {
		return this.dvaluetwo;
	}

	public void setDvaluetwo(String dvaluetwo) {
		this.dvaluetwo = dvaluetwo;
	}

	public int getYear() {
		return this.year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

}