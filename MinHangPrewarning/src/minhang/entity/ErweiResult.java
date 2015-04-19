package minhang.entity;

public class ErweiResult {
	String weidulx1 = null;
	String weiduvalue1 = null;
	String weidulx2 = null;
	String weiduvalue2 = null;
	int pinshu = 0;
	float avgpinshu = 0;
	float percent = 0;
	double Zhishu = 0;

	public double getZhishu() {
		return Zhishu;
	}

	public void setZhishu(double zhishu) {
		Zhishu = zhishu;
	}

	public String getWeidulx1() {
		return weidulx1;
	}

	public void setWeidulx1(String weidulx1) {
		this.weidulx1 = weidulx1;
	}

	public String getWeiduvalue1() {
		return weiduvalue1;
	}

	public void setWeiduvalue1(String weiduvalue1) {
		this.weiduvalue1 = weiduvalue1;
	}

	public String getWeidulx2() {
		return weidulx2;
	}

	public void setWeidulx2(String weidulx2) {
		this.weidulx2 = weidulx2;
	}

	public String getWeiduvalue2() {
		return weiduvalue2;
	}

	public void setWeiduvalue2(String weiduvalue2) {
		this.weiduvalue2 = weiduvalue2;
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
