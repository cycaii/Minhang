/**
 * 
 */
package minhang.algo;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import minhang.dao.DimtypeDao;
import minhang.dao.PinfaEventDao;
import minhang.bean.PinfaElem;
import minhang.bean.PinfaEvent;

/**
 * @author Cycai
 * 
 */
public class PinfaEventCompute {
	private List<PinfaEvent> pinfaEventList;
	DimtypeDao dimtypeDao;
	PinfaEventDao pinfaEventDao;
	static PinfaEventCompute pinfaEventCompute;
	public static int yellow = 1;
	public static int red = 2;

	/**
	 * 
	 */
	public PinfaEventCompute() {
		pinfaEventList = new ArrayList<PinfaEvent>();
		dimtypeDao = DimtypeDao.getInstance();
		pinfaEventDao = PinfaEventDao.getInstance();
	}

	public static PinfaEventCompute getInstance() {
		if (pinfaEventCompute == null) {
			pinfaEventCompute = new PinfaEventCompute();
		}
		return pinfaEventCompute;
	}

	/**
	 * 输入参数，获取一维频发事件结果
	 * 
	 * @param dimtype
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public List<PinfaEvent> computeOneDimPinfa(String dimtype, String date) {
		pinfaEventList = new ArrayList<PinfaEvent>();
		initOneDimPinfaEventList(dimtype, date);
		countPinfaAvg();
		countScore();
		sortScore();
		tagRed();
		return pickupResult();
	}

	/**
	 * 输入参数，获取二维频发事件结果
	 * 
	 * @param dimtype
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public List<PinfaEvent> computeTwoDimPinfa(String dimtype1,
			String dimtype2, String date) {
		pinfaEventList = new ArrayList<PinfaEvent>();
		initTwoDimPinfaEventList(dimtype1, dimtype2, date);
		countPinfaAvg();
		countScore();
		sortScore();
		tagRed();
		return pickupResult();
	}

	/**
	 * step1:初始化二维pinfaEventList 统计频发事件基础值，放入pinfaEventList
	 * 
	 * @param dimtype1
	 * @param dimtype2
	 * @param date
	 */
	private void initTwoDimPinfaEventList(String dimtype1, String dimtype2,
			String date) {
		pinfaEventList = pinfaEventDao.queryTwoDimPinfaEvent(dimtype1,
				dimtype2, date);
//		List<String> dimvalues1 = dimtypeDao.getDimtypeValue(dimtype1);
//		List<String> dimvalues2 = dimtypeDao.getDimtypeValue(dimtype2);
//		PinfaEvent pe;
//		for (String dimvalue1 : dimvalues1) {
//			for (String dimvalue2 : dimvalues2) {
//				pe = pinfaEventDao.queryTwoDimPinfaEvent(dimtype1, dimvalue1,
//						dimtype2, dimvalue2, date);
//				pinfaEventList.add(pe);
//			}
//		}
		/* test start */
		System.out
				.println("=============== step1:初始化pinfaEventList=================");
		String hang;
		for (PinfaEvent ape : pinfaEventList) {
			hang = "countPinfaAvg:";
			for (PinfaElem daya : ape.getDays()) {
				hang += " num(" + daya.getNum() + ")" + "day("
						+ daya.getDay().getDate() + ")  ";
			}
			hang += "     " + ape.getDimvalue();
			System.out.println(hang);
		}
		/* test end */
	}

	/**
	 * step1:初始化一维pinfaEventList
	 * 
	 * 统计频发事件基础值，放入pinfaEventList
	 * 
	 * @param dimvalue
	 * @param date
	 */
	private void initOneDimPinfaEventList(String dimtype, String date) {
		pinfaEventList = pinfaEventDao.queryOneDimPinfaEvent(dimtype, date);
		// List<String> dimvalues = dimtypeDao.getDimtypeValue(dimtype);
		// PinfaEvent pe;
		// for (String dimvalue : dimvalues) {
		// pe = pinfaEventDao.queryOneDimPinfaEvent(dimtype, dimvalue, date);
		// pinfaEventList.add(pe);
		// }
		/* test start */
		System.out
				.println("=============== step1:初始化pinfaEventList=================");
		String hang;
		for (PinfaEvent ape : pinfaEventList) {
			hang = "countPinfaAvg:";
			for (PinfaElem daya : ape.getDays()) {
				hang += " num(" + daya.getNum() + ")" + "day("
						+ daya.getDay().getDate() + ")  ";
			}
			hang += "     " + ape.getDimvalue();
			System.out.println(hang);
		}
		/* test end */
	}

	/**
	 * step2: 求频发事件一周七天的平均值，获得【表1】。
	 * 
	 * 即为pinfaEventList里每一个pinfaEvent的 avgNum赋予均值
	 */
	private void countPinfaAvg() {
		int total;
		// 遍历列表
		for (int i = 0; i < pinfaEventList.size(); i++) {
			PinfaEvent pe = pinfaEventList.get(i);
			// 计算该维度值行的八天事件发生总数
			total = 0;
			for (PinfaElem elem : pe.getDays()) {
				total += elem.getNum();
			}
			pinfaEventList.get(i).setAvgNum(total / 8.0);// 设置平均值
		}

		/* test start */
		System.out
				.println("===============step2: countPinfaAvg 求平均值=================");
		String hang;
		for (PinfaEvent pe : pinfaEventList) {
			hang = "countPinfaAvg:";
			for (PinfaElem day : pe.getDays()) {
				hang += " num(" + day.getNum() + ")";
			}
			hang += "  avg:" + pe.getAvgNum() + " " + pe.getDimvalue();
			System.out.println(hang);
		}
		/* test end */
	}

	/**
	 * step3: 分别给七天打分，获得【表3】。
	 * 
	 * 即为pinfaEventList里每一个pinfaEvent的 PinfaElem 的score赋值
	 */
	private void countScore() {
		// 给第一列打分
		int[] colsum = { 0, 0, 0, 0, 0, 0, 0, 0 };// 8列每一列总和 eg. colsum[1] 第一列总和
		double[] propSum = { 0, 0, 0, 0, 0, 0, 0, 0 };// 8列每一列比重和 eg.propSum[1]
														// 第一列比重和
														// 其中第0列为前置列，不需要求比重和
		for (PinfaEvent pe : pinfaEventList) {
			for (int i = 0; i < pe.getDays().size(); i++) {
				PinfaElem elem = pe.getDays().get(i);
				colsum[i] += elem.getNum();
				if (i > 0) {
					propSum[i] += (pe.getDays().get(i).getNum() + 0.0)
							/ (pe.getDays().get(i - 1).getNum() + pe.getDays()
									.get(i).getNum());
				}
			}
		}
		int size = pinfaEventList.size();// 总行数
		int dayNum = 8;// 总天数
		// 遍历行，计算并赋值每一列（天）的score
		for (int i = 0; i < size; i++) {
			PinfaEvent pe = pinfaEventList.get(i);
			double score;
			// 按列（天）求score
			for (int j = 1; j < dayNum; j++) {
				score = getScore(pe.getDays().get(j - 1).getNum(), pe.getDays()
						.get(j).getNum(), colsum[j - 1] + colsum[j],
						propSum[j], size);
				pinfaEventList.get(i).getDays().get(j).setScore(score);
			}

		}

		/* test start */
		System.out
				.println("===============step3: countScore 打分，获得【表3】=================");
		DecimalFormat df = new DecimalFormat("0.000");
		String hang;
		for (PinfaEvent pe : pinfaEventList) {
			hang = "countScore:";
			for (PinfaElem day : pe.getDays()) {
				hang += " num(" + day.getNum() + "),s(" + day.getScore() + ")";
			}
			hang += "  avg:" + pe.getAvgNum() + " " + pe.getDimvalue();
			System.out.println(hang);
		}
		/* test end */
	}

	/**
	 * 
	 * @param pre
	 *            前一列的值
	 * @param after
	 *            后一列的值
	 * @param totalSum
	 *            两列所有行总和
	 * @param porSum
	 *            后一列比重总和
	 * @param n
	 *            总行数
	 * @return
	 */
	private double getScore(int pre, int after, int totalSum, double porSum,
			int n) {
		// 前两列的和
		int sum = pre + after;
		// 前两列的总和均值
		int totalAvg = totalSum / n;
		// 后一列比重总和均值
		double porAvg = porSum / n;
		double score = (totalAvg * porAvg + after) / (totalSum + sum);
		return score;
	}

	/**
	 * step4: 给每列排序，取最高打分的六位，color标为黄色--1 。
	 * 
	 * 即为pinfaEventList里每一个pinfaEvent的 PinfaElem 的color赋值
	 */
	private void sortScore() {
		int size = pinfaEventList.size();// 总行数
		int dayNum = 7;// 总天数(列数)
		int[][] colArrays = new int[dayNum][size];// 列数组
		int[] subscript = new int[size];// 存储一个列 的排位

		// 遍历矩阵，为colArrays赋值
		for (int i = 0; i < size; i++) {// 遍历行
			PinfaEvent pe = pinfaEventList.get(i);
			for (int j = 1; j < dayNum; j++) {
				PinfaElem day = pe.getDays().get(j);// 获取天，遍历该行的列
				colArrays[j][i] = day.getNum();// 按天算列
			}
		}

		// 求每列最高六位分数，为color赋值
		int max = size < 6 ? size : 6;// max不超过行数
		for (int i = 0; i < dayNum; i++) {// 遍历列数组 i=1 第0列不处理
			subscript = selectSort(colArrays[i], max);// 排序结果存储在subscript
			// 赋值给color
			for (int k = 0; k < subscript.length; k++) {// 按行遍历
				int index = subscript[k];// subscript存储colArrays[i]的排位
				if (index < max)// 排位在前六位 color标为黄色
				{
					// + 要求num>0
					if (pinfaEventList.get(k).getDays().get(i).getNum() > 0)
						pinfaEventList.get(k).getDays().get(i).setColor(yellow);
				}
			}
		}

		/* test start */
		System.out
				.println("===============step4: sortScore 给每列排序,color标为黄色=================");
		String hang;
		for (PinfaEvent pe : pinfaEventList) {
			hang = "sortScore:";
			for (PinfaElem day : pe.getDays()) {
				hang += " num(" + day.getNum() + "),c(" + day.getColor() + ")";
			}
			hang += "  avg:" + pe.getAvgNum() + " " + pe.getDimvalue();
			System.out.println(hang);
		}
		/* test end */
	}

	private int[] selectSort(int[] array, int n) {
		int[] subscript = new int[pinfaEventList.size()];// 总行数
		for (int i = 0; i < subscript.length; ++i)
			subscript[i] = i;
		int i, j, max, tmp;
		for (i = 0; i < n; i++) {
			max = i;
			for (j = i; j < array.length - 1; j++) {
				if (array[max] < array[j + 1])
					max = j + 1;
			}
			if (max != i) {
				tmp = array[i];
				array[i] = array[max];
				array[max] = tmp;

				tmp = subscript[i];
				subscript[i] = subscript[max];
				subscript[max] = tmp;
			}
		}
		return subscript;
	}

	/**
	 * step5: 对照【表1 】，选出打分高的连续的高于平均值的列，color标为红色--2 。
	 * 
	 * 即为pinfaEventList里每一个pinfaEvent的 PinfaElem 的color赋值
	 */
	private void tagRed() {
		int dayNum = 8;// 天数（列数）
		for (int m = 0; m < pinfaEventList.size(); m++) {// 遍历行
			PinfaEvent pe = pinfaEventList.get(m);
			for (int i = 1; i < dayNum; i++) {// 遍历列
				if (pe.getDays().get(i).getColor() > 0) {// 该日是黄色或红色（峰值或处于连续的频发红色中）
					boolean isPeak = false;
					// 自身必须大于均值？
					if (pe.getDays().get(i).getNum() < pe.getAvgNum())
						continue;
					// 判断左右两天是否大于均值
					if (pe.getDays().get(i - 1).getNum() > pe.getAvgNum()) {// 前一列
						pinfaEventList.get(m).getDays().get(i - 1)
								.setColor(red);
						isPeak = true;
					}
					if (i < dayNum - 1
							&& pe.getDays().get(i + 1).getNum() > pe
									.getAvgNum()) {// 后一列
						pinfaEventList.get(m).getDays().get(i + 1)
								.setColor(red);
						isPeak = true;
					}
					if (isPeak == true) {
						pinfaEventList.get(m).getDays().get(i).setColor(red);
						pinfaEventList.get(m).setRed(true);
					}
				}
			}
		}

		/* test start */
		System.out
				.println("===============step5: tagRed 选出打分高的连续的高于平均值的列,color标为红色=================");
		String hang;
		for (PinfaEvent pe : pinfaEventList) {
			hang = "tagRed:";
			for (PinfaElem day : pe.getDays()) {
				hang += " num(" + day.getNum() + "),c(" + day.getColor() + ")";
			}
			if (pe.isRed())
				hang += "    isred" + " " + pe.getDimvalue();
			System.out.println(hang);
		}
		/* test end */
	}

	/**
	 * step6: 提取出频发事件：频发维度值+频发时间段（连续红色的天数 ）
	 * 
	 */
	private List<PinfaEvent> pickupResult() {
		List<PinfaEvent> results = new ArrayList<PinfaEvent>();
		DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
		for (PinfaEvent pe : pinfaEventList) {
			if (pe.isRed() == true) {// 是频发维度值
				// 获取频发时间段
				String pinfaPeriod = "";
				int start = 0, end = 0, flag = 0;// 频发时间段段计算
				String countValueStr = "";// 对应数量值 test
				List<PinfaElem> countValue = new ArrayList<PinfaElem>();// 对应数量值list
				for (int i = 1; i < pe.getDays().size(); i++) {
					if (pe.getDays().get(i).getColor() == red) {
						PinfaElem day = pe.getDays().get(i);
						countValue.add(day);
						countValueStr += " " + day.getNum();

						if (flag == 0) {
							if (start == 0)
								start = i;
							end = i;
							flag = 1;
						} else if (i != (end + 1)) {// 此时间段结束|| i ==
													// pe.getDays().size() - 1
							if (start != 0)
								pinfaPeriod += ",";
							pinfaPeriod += df.format(pe.getDays().get(start)
									.getDay())
									+ "——"
									+ df.format(pe.getDays().get(end).getDay())
									+ " ";
							start = i;
							flag = 0;
						} else {
							end = i;
						}
					}
				}
				if (flag == 1 && start != end) {

					pinfaPeriod += "\n"
							+ df.format(pe.getDays().get(start).getDay())
							+ "——" + df.format(pe.getDays().get(end).getDay())
							+ " ";
				}
				if (flag == 1 && start == end && start == 1)
					pinfaPeriod +=df.format(pe.getDays().get(end).getDay());
				
					pe.setPinfaPeriod(pinfaPeriod);
				pe.setCountValue(countValue);
				/* test start */
				System.out.println("频发维度：" + pe.getDimvalue() + "   频发时间段："
						+ pinfaPeriod + "  " + "  对应数量值：" + countValueStr);//
				/* test end */
				results.add(pe);
			}
		}
		return results;

	}

	public static void main(String[] args) {
		/**
		 * int[] input = { 33, 1, 3, 2, 3, 4, 5, 190, 7, 8, 9, 200, 12, 180,
		 * 190, 220, 221, 3, 5, 226 }; int[] subscript = new int[input.length];
		 * for (int i = 0; i < subscript.length; ++i) subscript[i] = i; int n =
		 * 6; new PinfaEventCompute().selectSort(input, subscript, n); for (int
		 * i = 0; i < input.length; ++i) { int index = subscript[i];
		 * System.out.print(index + "  :" + input[index] + "\n"); }
		 * System.out.println();
		 */

		/* test start */
		PinfaEventCompute compute = new PinfaEventCompute();

		// compute.pinfaEventList.add(getPE("冲出/偏出跑道", 1, 1, 1, 2, 1, 1, 1));
		// compute.pinfaEventList.add(getPE("鸟击", 34, 8, 3, 2, 2, 1, 1));
		// compute.pinfaEventList
		// .add(getPE("空中解体/爆炸/失火/冒烟", 2, 1, 10, 2, 1, 1, 3));
		// compute.pinfaEventList.add(getPE("空中颠簸", 1, 1, 11, 2, 1, 1, 1));
		// compute.pinfaEventList.add(getPE("通信中断", 9, 2, 1, 3, 4, 1, 4));
		// compute.pinfaEventList.add(getPE("货物、行李运输", 4, 2, 2, 3, 1, 3, 2));
		// compute.pinfaEventList.add(getPE("外来物击伤", 9, 15, 2, 4, 2, 1, 1));
		// compute.pinfaEventList.add(getPE("航空器与设备、设施碰撞", 2, 2, 1, 4, 1, 1,
		// 1));
		// compute.pinfaEventList.add(getPE("车辆与车辆碰撞", 1, 15, 4, 4, 1, 2, 1));
		// compute.pinfaEventList.add(getPE("重着陆", 5, 10, 26, 5, 2, 1, 1));
		// compute.pinfaEventList.add(getPE("发动机停车", 1, 2, 1, 7, 1, 6, 1));
		// compute.pinfaEventList.add(getPE("车辆与航空器碰撞", 5, 33, 18, 7, 1, 1, 2));
		// compute.pinfaEventList.add(getPE("其他", 8, 5, 14, 7, 2, 2, 1));
		// compute.pinfaEventList.add(getPE("机务维护、维修	", 1, 1, 5, 8, 4, 8, 5));
		// compute.pinfaEventList.add(getPE("爆胎/轮胎脱层/扎破", 22, 1, 3, 9, 1, 1,
		// 3));
		// compute.pinfaEventList.add(getPE("航空器牵引", 2, 6, 2, 9, 9, 3, 1));
		// compute.pinfaEventList.add(getPE("雷击", 7, 1, 3, 10, 2, 1, 2));
		// compute.pinfaEventList.add(getPE("系统失效", 43, 1, 5, 2, 3, 1, 1));
		// compute.pinfaEventList.add(getPE("跑道侵入", 6, 3, 6, 1, 2, 2, 2));
		// compute.pinfaEventList.add(getPE("危险接近/飞行冲突", 14, 4, 7, 2, 3, 2, 1));

		// initPinfaEventList(dimtype, year, month, day);
		// compute.countPinfaAvg();
		// compute.countScore();
		// compute.sortScore();
		// compute.tagRed();
		// compute.pickupResult();
		compute.computeOneDimPinfa("事件类型", "2013-5-9");

		/* test end */
	}

	static PinfaEvent getPE(String dimvalue, int num1, int num2, int num3,
			int num4, int num5, int num6, int num7) {
		PinfaElem day0 = new PinfaElem(0, 0, 0);
		PinfaElem day1 = new PinfaElem(num1, 0, 0);
		PinfaElem day2 = new PinfaElem(num2, 0, 0);
		PinfaElem day3 = new PinfaElem(num3, 0, 0);
		PinfaElem day4 = new PinfaElem(num4, 0, 0);
		PinfaElem day5 = new PinfaElem(num5, 0, 0);
		PinfaElem day6 = new PinfaElem(num6, 0, 0);
		PinfaElem day7 = new PinfaElem(num7, 0, 0);
		List<PinfaElem> days = new ArrayList<PinfaElem>();
		days.add(day0);
		days.add(day1);
		days.add(day2);
		days.add(day3);
		days.add(day4);
		days.add(day5);
		days.add(day6);
		days.add(day7);
		PinfaEvent pe = new PinfaEvent(dimvalue, days, 0);
		return pe;
	}

}
