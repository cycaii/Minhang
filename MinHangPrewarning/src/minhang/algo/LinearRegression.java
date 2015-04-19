package minhang.algo;

/**
 * 最小二乘法 线性回归 y = a x + b
 * 
 * b = sum( y ) / n - a * sum( x ) / n
 * 
 * a = ( n * sum( xy ) - sum( x ) * sum( y ) ) / ( n * sum( x^2 ) - sum(x) ^ 2 )
 * 
 * 
 */
public class LinearRegression {

	private double a;// x 系数
	private double b;
	public static LinearRegression linearRegression;

	public static void main(String[] args) {
		LinearRegression l = new LinearRegression();
		double[] x = { 102, 96, 97, 102, 91, 158, 54, 83, 123, 106, 129, 138,
				81, 92, 64 };
		double[] y = { 27, 26, 25, 28, 27, 36, 19, 26, 31, 31, 34, 38, 27, 28,
				20 };
		double r = l.estimate(x, y, 0);
		System.out.println("a:" + l.getA() + "\nb:" + l.getB() + "\nresult:"
				+ r);
	}

	public static LinearRegression getInstance()
	{
		if (linearRegression == null)
			linearRegression = new LinearRegression();
		return linearRegression;
	}

	/**
	 * 预测
	 * 
	 * @param x
	 * @param y
	 * @param i
	 * @return
	 */
	public double estimate(double[] x, double[] y, double preX) {
		a = countA(x, y);
		b = countB(x, y, a);
		return a * preX + b;
	}

	/**
	 * 计算 x 的系数
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public double countA(double[] x, double[] y) {
		int n = x.length;
		return (n * pSum(x, y) - sum(x) * sum(y))
				/ (n * sqSum(x) - Math.pow(sum(x), 2));
	}

	/**
	 * 计算常量系数
	 * 
	 * @param x
	 * @param y
	 * @param a
	 * @return
	 */
	public double countB(double[] x, double[] y, double a) {
		int n = x.length;
		return sum(y) / n - a * sum(x) / n;
	}

	/**
	 * 计算常量系数
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public double countB(double[] x, double[] y) {
		int n = x.length;
		double a = countA(x, y);
		return sum(y) / n - a * sum(x) / n;
	}

	private double sum(double[] ds) {
		double s = 0;
		for (double d : ds)
			s = s + d;
		return s;
	}

	private double sqSum(double[] ds) {
		double s = 0;
		for (double d : ds)
			s = s + Math.pow(d, 2);
		return s;
	}

	private double pSum(double[] x, double[] y) {
		double s = 0;
		for (int i = 0; i < x.length; i++)
			s = s + x[i] * y[i];
		return s;
	}

	public double getA() {
		return a;
	}

	public void setA(double a) {
		this.a = a;
	}

	public double getB() {
		return b;
	}

	public void setB(double b) {
		this.b = b;
	}

}
