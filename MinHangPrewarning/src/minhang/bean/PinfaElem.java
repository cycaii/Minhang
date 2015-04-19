package minhang.bean;

import java.util.Date;

public class PinfaElem {
	/** 发生次数 */
	private int num;
	/** 打分 */
	private double score;
	/** 标志位     score较高--1，红色，num连续较高，--2，红色，default:0 */
	private int color;
	/** 日期 */
	private Date day;
	/**
	 * 
	 */
	public PinfaElem() {
	}

	public PinfaElem(int num, double score, int color) {
		super();
		this.num = num;
		this.score = score;
		this.color = color;
	}
	
	
	public PinfaElem(int num, double score, int color, Date day) {
		super();
		this.num = num;
		this.score = score;
		this.color = color;
		this.day = day;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num
	 *            the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}

	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * @return the color
	 */
	public int getColor() {
		return color;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(int color) {
		this.color = color;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

}
