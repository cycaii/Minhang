package minhang.entity;

public class YiweiResult {
	String weidulx = null;
	String weiduvalue = null;
	int pinshu = 0;
	float avgpinshu = 0;
	float percent = 0;
	double zhishu = 0;

	public double getZhishu() {
		return zhishu;
	}

	public void setZhishu(double zhishu) {
		this.zhishu = zhishu;
	}

	public String getWeidulx() {
		return weidulx;
	}

	public void setWeidulx(String weidulx) {
		this.weidulx = weidulx;
	}

	public String getWeiduvalue() {
		return weiduvalue;
	}

	public void setWeiduvalue(String weiduvalue) {
		this.weiduvalue = weiduvalue;
	}

	public int getPinshu() {
		return pinshu;
	}

	public void setPinshu(int pinshu) {
		this.pinshu = pinshu;
	}

	public float getAvgpinshu() {
		return avgpinshu;
	}

	public void setAvgpinshu(float avgpinshu) {
		this.avgpinshu = avgpinshu;
	}

	public float getPercent() {
		return percent;
	}

	public void setPercent(float percent) {
		this.percent = percent;
	}

}
