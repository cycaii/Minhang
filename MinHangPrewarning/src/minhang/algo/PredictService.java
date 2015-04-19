package minhang.algo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import minhang.dao.OnedimstatisticDao;
import minhang.dao.RiskvalueDao;
import minhang.entity.Avgriskvalue;
import minhang.entity.Onedimstatistic;
import minhang.entity.PredictProbability;
import minhang.algo.ExponentialSmoothing;
import minhang.algo.LinearRegression;

public class PredictService {
	private double w1 = 0.5;
	private double w2 = 0.5;
	private int year, month;
	OnedimstatisticDao od;
	RiskvalueDao riskvalueDao;
	String dimType;
	/** 线性回归预测 */
	LinearRegression linearRegression;
	/** 指数平滑预测 */
	ExponentialSmoothing exponentialSmoothing;

	public static PredictService predictService;

	public PredictService() {
		this.od = OnedimstatisticDao.getInstance();
		this.riskvalueDao = RiskvalueDao.getInstance();
		this.linearRegression = LinearRegression.getInstance();
	}

	public static PredictService getInstance() {
		if (predictService == null) {
			predictService = new PredictService();
		}
		return predictService;
	}

	/**
	 * 旧方法 获取所有维度的预警 Entry<String:维度类型+' ' +维度值，Double:预警值> ---用同比环比的预测值作为预警值
	 * 
	 * @param year
	 * @param month
	 * @param dimType
	 * @param dimValues
	 * @return
	 */
	/*
	 * public ArrayList<Entry<String, Double>> getYujingPredictValuesOld(int
	 * year, int month) {
	 * System.out.println("PredictService-getYujingPredictValues!!!!!!!!!");
	 * Map<String, Double> predictMap = new HashMap<String, Double>();
	 * Map<String, Double> onePredictMap = null; ArrayList<Entry<String,
	 * Double>> predictList = null; this.year = year; this.month = month;
	 * 
	 * DimtypeDao d = DimtypeDao.getInstance(); List<String> dimStrs =
	 * d.getAllDimtypesStr(); for (String dimtype : dimStrs) { // 目前只取事件类型做预警 if
	 * (dimtype.equals("事件类型")) continue; System.out.println("dimtype:" +
	 * dimtype); onePredictMap = predictService.getDimPredictValuesforYujingOld(
	 * year, month, dimtype, d.getDimtypeValue(dimtype)); predictList = new
	 * ArrayList<Entry<String, Double>>( onePredictMap.entrySet()); for (int i =
	 * 0; i < predictList.size(); i++) { if (predictList.get(i).getValue() > 0)
	 * { predictMap.put(predictList.get(i).getKey(), predictList
	 * .get(i).getValue()); } } }
	 * 
	 * predictList = new ArrayList<Entry<String, Double>>(
	 * predictMap.entrySet()); Collections.sort(predictList, new
	 * Comparator<Map.Entry<String, Double>>() { public int
	 * compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
	 * double result = o2.getValue() - o1.getValue(); int r = 0; if (result > 0)
	 * r = 1; if (result < 0) r = -1; return r; } });
	 * 
	 * return predictList; }
	 * 
	 * private Map<String, Double> getDimPredictValuesforYujingOld(int year, int
	 * month, String dimType, List<String> dimValues) {
	 * System.out.println("PredictService-getDimPredictValues!!!!!!!!!");
	 * Map<String, Double> predictMap = new HashMap<String, Double>(); this.year
	 * = year; this.month = month; this.dimType = dimType;
	 * 
	 * for (String dimvalue : dimValues) { double p = getPredictValue(dimvalue);
	 * if (p > 0) predictMap.put(dimType + ";" + dimvalue, p); } return
	 * predictMap; }
	 */

	/**
	 * 获取所有维度的预警 ArrayList<PredictProbability> ---概率加风险值作为预警值,仅求事件类型这一维度的预警
	 * 
	 * @param year
	 * @param month
	 * @param dimType
	 * @param dimValues
	 * @param algotype
	 *            预测算法
	 * @return
	 */
	public ArrayList<PredictProbability> getYujingPredictValues(int year,
			int month, int algotype) {
		System.out.println("PredictService-getYujingPredictValues!!!!!!!!!");
		return predictService.getDimPredictValuesforYujing(year, month, "事件类型",
				algotype);

	}

	private ArrayList<PredictProbability> getDimPredictValuesforYujing(
			int year, int month, String dimType, int algotype) {
		System.out.println("PredictService-getDimPredictValues!!!!!!!!!");
		ArrayList<PredictProbability> predictList = new ArrayList<PredictProbability>();
		double totalPredict = 0;
		this.year = year;
		this.month = month;
		this.dimType = dimType;

		PredictProbability predictProbability = null;
		List<Avgriskvalue> avgriskvalueList = riskvalueDao.getAvgRiskvalues();// 获取该维度的平均风险值列表，即可获得按风险值大小排序的维度值
		for (Avgriskvalue item : avgriskvalueList) {
			System.out.println("Avgriskvalue item:" + item.getTypevalue() + " "
					+ item.getAvgriskvalue());
			double p = getPredictValue(item.getTypevalue(), algotype);
			// 格式化p,保留两位小数
			BigDecimal b1 = new BigDecimal(p);
			double newp = b1.setScale(6, BigDecimal.ROUND_HALF_UP).doubleValue();
			if (newp> 0) {
				totalPredict +=newp;
				// 格式化Avgriskvalue,保留两位小数
				BigDecimal b2 = new BigDecimal(item.getAvgriskvalue());
				double avgriskvalue = b2.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
				
				predictProbability = new PredictProbability(
						item.getTypevalue(), newp, avgriskvalue);
				predictList.add(predictProbability);
			}
		}

		// 计算概率
		for (PredictProbability item : predictList) {
			item.setProbability(item.getProbability() / totalPredict);
			System.out.println("predictProbability:"
					+ predictProbability.getDimvalue() + " P:"
					+ predictProbability.getProbability() + " avgRiskvalue:"
					+ predictProbability.getRiskvalue());
		}

		return predictList;
	}

	/**
	 * 获取某个维度的预测值
	 * 
	 * @param year
	 * @param month
	 * @param dimType
	 * @param dimValues
	 * @param algotype  0-线性回归，1-指数平滑
	 *            预测算法
	 * @return
	 */
	public Map<String, Double> getDimPredictValues(int year, int month,
			String dimType, List<String> dimValues, int algotype) {
		System.out.println("PredictService-getDimPredictValues!!!!!!!!!");
		Map<String, Double> predictMap = new HashMap<String, Double>();
		this.year = year;
		this.month = month;
		this.dimType = dimType;

		for (String dimvalue : dimValues) {
			double p = getPredictValue(dimvalue, algotype);// 获取该维度值的预测值
			// 格式化p,保留两位小数
			BigDecimal b = new BigDecimal(p);
			double newp = b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
			predictMap.put(dimvalue, newp);
		}

		return predictMap;
	}

	// 获取某维度值的预测值
	public double getPredictValue(String dimValue, int algotype) {
		double p = 0;
		double t = getTPredictValue(dimValue, algotype);
		double h = getHPredictValue(dimValue, algotype);
		// System.out.println("---------------predict value----------------\nTongbi:"+t+"\nhuanbi:"+h);
		p = w1 * t + w2 * h;
		p = h;// for testing
		if (p > 0)
			System.out.println("Predict:" + dimType + "  " + dimValue + "  P:"
					+ p);
		return p;
	}

	// 同比 今年第n月与去年第n月比
	private double getTPredictValue(String dimValue, int algotype) {
		double p = 0;
		List<Onedimstatistic> oneDimList = od.getTPredictOneDimResult(
				year - 10, year - 1, month, dimType, dimValue);
		if (oneDimList.size() < 2)
			return p;
		double[] x = new double[oneDimList.size()];
		double[] y = new double[oneDimList.size()];
		int i = 0;
		for (Onedimstatistic one : oneDimList) {
			x[i] = one.getYear();// year
			y[i] = one.getCount();
			i++;
		}
		// 判断预测算法
		if (algotype == 0)
			p = linearRegression.estimate(x, y, year);
		else if (algotype == 1)
			p = exponentialSmoothing.estimate(x, y, year);
		return p;
	}

	// 环比 今年第1月至M月-- 前一年12月起
	private double getHPredictValue(String dimValue, int algotype) {
		double p = 0;
		List<Onedimstatistic> oneDimList = od.getHPredictOneDimResult(year,
				month, dimType, dimValue);
		if (oneDimList.size() < 2)
			return p;
		double[] x = new double[oneDimList.size()];
		double[] y = new double[oneDimList.size()];
		int i = 0;
		for (Onedimstatistic one : oneDimList) {
			x[i] = one.getNum();// month
			y[i] = one.getCount();
			i++;
		}
		// 判断预测算法
		if (algotype == 0)
			p = linearRegression.estimate(x, y, month);
		else if (algotype == 1)
			p = exponentialSmoothing.estimate(x, y, month);
		return p;
	}

	public static void main(String[] args) {
		PredictService ps = PredictService.getInstance();
		ps.year = 2011;
		ps.month = 1;
		ps.getPredictValue("鸟击", 0);
	}
}
