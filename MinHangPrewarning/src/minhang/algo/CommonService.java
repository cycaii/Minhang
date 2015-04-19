package minhang.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import minhang.dao.OnedimstatisticDao;
import minhang.dao.TwodimstatisticDao;
import minhang.entity.Onedimstatistic;
import minhang.entity.Twodimstatistic;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer3D;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.TextAnchor;

public class CommonService {
	OnedimstatisticDao od;
	TwodimstatisticDao od2;

	public CommonService() {
		od = new OnedimstatisticDao();
		od2 = new TwodimstatisticDao();
	}

	public CommonService(OnedimstatisticDao d, TwodimstatisticDao d2) {
		od = d;
		od2 = d2;
	}

	/* =================================一维 ================================= */
	// 一维 时间判断并获取结果列表 输入：界面输入的查询条件 输出：统计的结果集合
	public List<Onedimstatistic> getOneDimResult(int radioNum, String dimtype,
			String dimvalue, String startYear, String endYear, String startNum,
			String endNum) {
		List<Onedimstatistic> result = new ArrayList<Onedimstatistic>();

		switch (radioNum) {
		case 1:
			result = od.getOneDimResultByYear(startYear, endYear, dimtype,
					dimvalue);
			break;
		case 2:
			result = od.getOneDimResultByJijie(startYear, endYear, startNum,
					endNum, dimtype, dimvalue);
			break;
		case 3:
			result = od.getOneDimResultBySeason(startYear, endYear, startNum,
					endNum, dimtype, dimvalue);
			break;
		case 4:
			result = od.getOneDimResultByMonth(startYear, endYear, startNum,
					endNum, dimtype, dimvalue);
			break;
		case 5:
			result = od.getOneDimResultByXun(startYear, endYear, startNum,
					endNum, dimtype, dimvalue);
			break;
		case 6:
			result = od.getOneDimResultByWeek(startYear, endYear, startNum,
					endNum, dimtype, dimvalue);
			break;

		}
		return result;
	}

	// 一维 数据处理 输入：查询的结果列表result 输出：统计后的结果集合
	private Map<String, Integer> OneDimDataStatistic(
			List<Onedimstatistic> result) {
		Map<String, Integer> results = new HashMap<String, Integer>();
		for (int k = 0; k < result.size(); k++) {
			String key = result.get(k).getDvalue();
			int value = result.get(k).getCount();
			Integer count = null;
			count = results.get(key);
			if (count == null) {
				results.put(key, value);
			} else {
				int v = value + count;
				results.put(key, v);
			}
		}

		// Iterator iter = results.entrySet().iterator();
		// while (iter.hasNext()) {
		// Map.Entry entry = (Map.Entry) iter.next();
		// System.out.println(entry.getKey() + "::::" + entry.getValue());
		// }
		return results;
	}

	/**
	 * 折线图 --一维统计查询结果合并 输入一维查询结果 合并数据 输出查询结果Map <一维统计对象，数量>
	 * 
	 * @param result
	 * @return
	 */
	private Map<Onedimstatistic, Integer> OneDimDataStatisticforLine(
			List<Onedimstatistic> result) {
		Map<Onedimstatistic, Integer> results = new HashMap<Onedimstatistic, Integer>();

		for (int k = 0; k < result.size(); k++) {
			Onedimstatistic key = result.get(k);
			int value = result.get(k).getCount();
			Integer count = null;
			count = results.get(key);
			// System.out.println("Map原有count：" + key + " " + count);
			if (count == null) {
				results.put(key, value);
				// System.out.println("新添加到Map：" + key + " " + value);
			} else {
				int v = value + count;
				results.put(key, v);
				// System.out.println("再次添加到Map：" + key + " " + v);
			}
		}

		// Iterator iter = results.entrySet().iterator();
		// while (iter.hasNext()) {
		// Map.Entry entry = (Map.Entry) iter.next();
		// System.out.println(entry.getKey() + "::::" + entry.getValue());
		// }
		return results;
	}

	// private ArrayList<OnedimstatisticForLine> OneDimDataStatisticforLine(
	// List<Onedimstatistic> result) {
	// ArrayList<OnedimstatisticForLine> rs = new
	// ArrayList<OnedimstatisticForLine>();
	// Map<Onedimstatistic, Integer> results = new HashMap<Onedimstatistic,
	// Integer>();
	//
	// for (int k = 0; k < result.size(); k++) {
	// Onedimstatistic key = result.get(k);
	// int value = result.get(k).getCount();
	// Integer count = null;
	// count = results.get(key);
	// // System.out.println("Map原有count：" + key + " " + count);
	// if (count == null) {
	// results.put(key, value);
	// // System.out.println("新添加到Map：" + key + " " + value);
	// } else {
	// int v = value + count;
	// results.put(key, v);
	// // System.out.println("再次添加到Map：" + key + " " + v);
	// }
	// }
	//
	// // Iterator iter = results.entrySet().iterator();
	// // while (iter.hasNext()) {
	// // Map.Entry entry = (Map.Entry) iter.next();
	// // System.out.println(entry.getKey() + "::::" + entry.getValue());
	// // }
	// return rs;
	// }

	// 生成一维的柱状图 输入：维度和结果列表 输出：柱状图
	public JFreeChart getOneDimBarChart(String dimtype,
			List<Onedimstatistic> result) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Map<String, Integer> results = this.OneDimDataStatistic(result);
		Iterator iter1 = results.entrySet().iterator();
		while (iter1.hasNext()) {
			Map.Entry entry = (Map.Entry) iter1.next();
			Integer value = Integer.parseInt(entry.getValue().toString());
			// System.out.println(entry.getKey() + "::::" + value);
			dataset.addValue(value, "", entry.getKey().toString());
		}
		JFreeChart chart = ChartFactory.createBarChart(dimtype + "柱状图",
				dimtype, "数量", dataset, PlotOrientation.VERTICAL, false, true,
				true);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setNoDataMessage("无数据显示");
		BarRenderer3D renderer = new BarRenderer3D();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		renderer.setItemLabelAnchorOffset(10D);
		plot.setRenderer(renderer);
		return chart;
	}

	// 生成一维的饼状图 输入：维度和结果列表 输出：饼状图
	public JFreeChart getOneDimPieChart(String dimtype,
			List<Onedimstatistic> result) {
		DefaultPieDataset dataset2 = new DefaultPieDataset();
		Map<String, Integer> results = this.OneDimDataStatistic(result);
		Iterator iter1 = results.entrySet().iterator();
		while (iter1.hasNext()) {
			Map.Entry entry = (Map.Entry) iter1.next();
			Integer value = Integer.parseInt(entry.getValue().toString());
			// System.out.println(entry.getKey() + "::::" + value);
			dataset2.setValue(entry.getKey().toString(), value);
		}
		JFreeChart chart2 = ChartFactory.createPieChart(dimtype + "饼状图",
				dataset2, true, true, false);
		PiePlot pieplot = (PiePlot) chart2.getPlot();
		pieplot.setCircular(true);
		pieplot.setNoDataMessage("无数据显示");
		return chart2;
	}

	// 生成一维的折线图 输入：维度和统计结果集合 输出：折线图
	public JFreeChart getOneDimLineChart(String dimtype,
			List<Onedimstatistic> result) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// 获取一维统计结果
		Map<Onedimstatistic, Integer> results = this
				.OneDimDataStatisticforLine(result);
		// 将统计结果按年排序
		ArrayList<Entry<Onedimstatistic, Integer>> sortResultList = new ArrayList<Entry<Onedimstatistic, Integer>>(
				results.entrySet());
		Collections.sort(sortResultList,
				new Comparator<Map.Entry<Onedimstatistic, Integer>>() {
					@Override
					public int compare(Entry<Onedimstatistic, Integer> o1,
							Entry<Onedimstatistic, Integer> o2) {
						double result = o2.getKey().getYear()
								- o1.getKey().getYear();
						int r = 0;
						if (result > 0)
							r = -1;
						if (result < 0)
							r = 1;
						return r;
					}
				});

		// 将按年份排序后的结果填充入折线图数据集
		for (Entry<Onedimstatistic, Integer> one : sortResultList) {
			Integer value = Integer.parseInt(one.getValue().toString());// 发生数量
			Onedimstatistic o = one.getKey();
			System.out.println("一维-折线:" + o.getYear() + "年，数量：" + value);
			dataset.addValue(value, o.getDvalue().toString(), o.getYear() + "");// 加入折线图数据集
		}
		// 无序遍历map加入折线图数据集
		// Iterator iter1 = results.entrySet().iterator();
		// while (iter1.hasNext()) {
		// Map.Entry entry = (Map.Entry) iter1.next();
		// Integer value = Integer.parseInt(entry.getValue().toString());
		// Onedimstatistic o = (Onedimstatistic) entry.getKey();
		//
		// System.out.println("折线:"+o.getYear() + "::::" + value);
		// dataset.addValue(value, o.getDvalue().toString(), o.getYear() +
		// "");//加入折线图数据集
		// }
		JFreeChart chart = ChartFactory.createLineChart(dimtype + "折线图", "年份",
				"数量", dataset, PlotOrientation.VERTICAL, true, true, false);
		CategoryPlot mPlot = (CategoryPlot) chart.getPlot();
		// mPlot.setBackgroundPaint(Color.LIGHT_GRAY);
		// mPlot.setRangeGridlinePaint(Color.BLUE);//背景底部横虚线
		mPlot.setNoDataMessage("无数据显示");
		return chart;
	}

	/* =================================二维 ================================= */
	/**
	 * 二维 时间判断并获取结果列表 输入：界面输入的查询条件 输出：统计的结果集合
	 * 
	 * @param radioNum
	 * @param firstDimtype
	 * @param firstValue
	 * @param secondDimtype
	 * @param secondValue
	 * @param startYear
	 * @param endYear
	 * @param startNum
	 * @param endNum
	 * @return
	 */
	public List<Twodimstatistic> getTwoDimResult(int radioNum,
			String firstDimtype, String firstValue, String secondDimtype,
			String secondValue, String startYear, String endYear,
			String startNum, String endNum) {
		List<Twodimstatistic> result = new ArrayList<Twodimstatistic>();

		switch (radioNum) {
		case 1:
			result = od2.getTwoDimResultByYear(startYear, endYear,
					firstDimtype, firstValue, secondDimtype, secondValue);
			break;
		case 2:
			result = od2.getTwoDimResultByAccording("季节", startYear, endYear,
					startNum, endNum, firstDimtype, firstValue, secondDimtype,
					secondValue);
			break;
		case 3:
			result = od2.getTwoDimResultByAccording("季", startYear, endYear,
					startNum, endNum, firstDimtype, firstValue, secondDimtype,
					secondValue);
			break;
		case 4:
			result = od2.getTwoDimResultByAccording("月", startYear, endYear,
					startNum, endNum, firstDimtype, firstValue, secondDimtype,
					secondValue);
			break;
		case 5:
			result = od2.getTwoDimResultByAccording("旬", startYear, endYear,
					startNum, endNum, firstDimtype, firstValue, secondDimtype,
					secondValue);
			break;
		case 6:
			result = od2.getTwoDimResultByAccording("周", startYear, endYear,
					startNum, endNum, firstDimtype, firstValue, secondDimtype,
					secondValue);
			break;
		
		}
		// Map<String, Integer> results = TwoDimDataStatistic(result);
		return result;
	}

	// 二维 数据处理 输入：查询的结果列表result 输出：统计后的结果
	private Map<String, Integer> TwoDimDataStatistic(
			List<Twodimstatistic> result) {
		Map<String, Integer> results = new HashMap<String, Integer>();
		for (int k = 0; k < result.size(); k++) {
			String key = result.get(k).getDvalueone() + "."
					+ result.get(k).getDvaluetwo();
			int value = result.get(k).getCount();
			Integer count = null;
			count = results.get(key);
			if (count == null) {
				results.put(key, value);
			} else {
				int v = value + count;
				results.put(key, v);
			}
		}

		Iterator iter = results.entrySet().iterator();
		while (iter.hasNext()) {
			Map.Entry entry = (Map.Entry) iter.next();
			System.out.println(entry.getKey() + "::::" + entry.getValue());
		}
		return results;
	}

	private Map<Twodimstatistic, Integer> TwoDimDataStatisticforLine(
			List<Twodimstatistic> result) {
		Map<Twodimstatistic, Integer> results = new HashMap<Twodimstatistic, Integer>();
		for (int k = 0; k < result.size(); k++) {
			Twodimstatistic key = result.get(k);
			int value = result.get(k).getCount();
			Integer count = null;
			count = results.get(key);
			if (count == null) {
				results.put(key, value);
			} else {
				int v = value + count;
				results.put(key, v);
			}
		}
		return results;
	}

	/**
	 * 生成二维的柱状图 输入：维度和统计结果集合 输出：柱状图
	 * 
	 * @param dimtype1
	 * @param dimtype2
	 * @param dimvalue1
	 * @param dimvalue2
	 * @param result
	 * @return
	 */
	public JFreeChart getTwoDimBarChart(String dimtype1, String dimtype2,
			String dimvalue1, String dimvalue2, List<Twodimstatistic> result) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		Map<String, Integer> results = this.TwoDimDataStatistic(result);
		Iterator iter1 = results.entrySet().iterator();
		while (iter1.hasNext()) {
			Map.Entry entry = (Map.Entry) iter1.next();
			Integer value = Integer.parseInt(entry.getValue().toString());
			dataset.addValue(value, "", entry.getKey().toString());
		}
		JFreeChart chart = ChartFactory.createBarChart(dimtype1 + "与"
				+ dimtype2 + "柱状图", dimtype1 + ":" + dimvalue1 + "  "
				+ dimtype2 + ":" + dimvalue2, "数量", dataset,
				PlotOrientation.VERTICAL, false, true, true);

		CategoryPlot plot = chart.getCategoryPlot();
		plot.setNoDataMessage("无数据显示");
		BarRenderer3D renderer = new BarRenderer3D();
		renderer.setBaseItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		renderer.setBaseItemLabelsVisible(true);
		renderer.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_LEFT));
		renderer.setItemLabelAnchorOffset(10D);
		plot.setRenderer(renderer);
		return chart;
	}

	/**
	 * 生成二维的饼状图 输入：维度和统计结果集合 输出：饼状图
	 * 
	 * @param dimtype1
	 * @param dimtype2
	 * @param result
	 * @return
	 */
	public JFreeChart getTwoDimPieChart(String dimtype1, String dimtype2,
			List<Twodimstatistic> result) {
		DefaultPieDataset dataset2 = new DefaultPieDataset();
		Map<String, Integer> results = this.TwoDimDataStatistic(result);
		Iterator iter1 = results.entrySet().iterator();
		while (iter1.hasNext()) {
			Map.Entry entry = (Map.Entry) iter1.next();
			Integer value = Integer.parseInt(entry.getValue().toString());
			dataset2.setValue(entry.getKey().toString(), value);
		}
		JFreeChart chart2 = ChartFactory.createPieChart(dimtype1 + "与"
				+ dimtype2 + "饼状图", dataset2, true, true, false);
		PiePlot pieplot = (PiePlot) chart2.getPlot();
		pieplot.setCircular(true);
		pieplot.setNoDataMessage("无数据显示");
		return chart2;
	}

	/**
	 * 生成二维的折线图 输入：维度和统计结果集合 输出：折线图
	 * 
	 * @param dimtype1
	 * @param dimtype2
	 * @param result
	 * @return
	 */
	public JFreeChart getTwoDimLineChart(String dimtype1, String dimtype2,
			List<Twodimstatistic> result) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		// 获取二维统计结果
		Map<Twodimstatistic, Integer> results = this
				.TwoDimDataStatisticforLine(result);
		// 将统计结果按年排序
		ArrayList<Entry<Twodimstatistic, Integer>> sortResultList = new ArrayList<Entry<Twodimstatistic, Integer>>(
				results.entrySet());
		Collections.sort(sortResultList,
				new Comparator<Map.Entry<Twodimstatistic, Integer>>() {
					@Override
					public int compare(Entry<Twodimstatistic, Integer> o1,
							Entry<Twodimstatistic, Integer> o2) {
						double result = o2.getKey().getYear()
								- o1.getKey().getYear();
						int r = 0;
						if (result > 0)
							r = -1;
						if (result < 0)
							r = 1;
						return r;
					}
				});

		// 将按年份排序后的结果填充入折线图数据集
		for (Entry<Twodimstatistic, Integer> one : sortResultList) {
			Integer value = Integer.parseInt(one.getValue().toString());// 发生数量
			Twodimstatistic o = one.getKey();
			System.out.println("二维-折线:" + o.getYear() + "年，数量：" + value);
			dataset.addValue(value, o.getDvalueone() + "." + o.getDvaluetwo(),
					o.getYear() + "");// 加入折线图数据集
		}
		// 无序遍历map加入折线图数据集
		// Iterator iter1 = results.entrySet().iterator();
		// while (iter1.hasNext()) {
		// Map.Entry entry = (Map.Entry) iter1.next();
		// Integer value = Integer.parseInt(entry.getValue().toString());
		// Twodimstatistic o = (Twodimstatistic) entry.getKey();
		// dataset.addValue(value, o.getDvalueone() + "." + o.getDvaluetwo(),
		// o.getYear() + "");
		// }
		JFreeChart chart = ChartFactory.createLineChart(dimtype1 + "与"
				+ dimtype2 + "折线图", "年份", "数量", dataset,
				PlotOrientation.VERTICAL, true, true, false);
		CategoryPlot mPlot = (CategoryPlot) chart.getPlot();
		mPlot.setNoDataMessage("无数据显示");
		return chart;
	}
}
