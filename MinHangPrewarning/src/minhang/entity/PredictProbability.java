package minhang.entity;

import java.io.Serializable;


/**
 * The persistent class for the predictprobability database table.
 * 
 */
public class PredictProbability implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;

	private String dimvalue;//维度值

	private double probability;//概率
	
	private double riskvalue;//平均风险值

	public PredictProbability() {
	}

	public PredictProbability(String dimvalue, double probability,
			double riskvalue) {
		super();
		this.dimvalue = dimvalue;
		this.probability = probability;
		this.riskvalue = riskvalue;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDimvalue() {
		return this.dimvalue;
	}

	public void setDimvalue(String dimvalue) {
		this.dimvalue = dimvalue;
	}

	public double getProbability() {
		return this.probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
	}

	public double getRiskvalue() {
		return riskvalue;
	}

	public void setRiskvalue(double riskvalue) {
		this.riskvalue = riskvalue;
	}

}