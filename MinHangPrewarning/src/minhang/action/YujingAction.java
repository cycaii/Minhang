/**
 * 
 */
package minhang.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import minhang.algo.PredictService;
import minhang.dao.DimtypeDao;
import minhang.entity.PredictProbability;
import minhang.util.GlobalConstant;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author cyc
 * 
 */
public class YujingAction extends ActionSupport {
	PredictService predictService = PredictService.getInstance();
	DimtypeDao dimtypeDao = DimtypeDao.getInstance();
	List<String> dimStrs = null;
	private String frameContent;
	// 预测
	Map<String, Double> predictMap = null;
	ArrayList<Entry<String, Double>> predictList = null;
	String[] aglorithms = GlobalConstant.PREDICT_AGLORITHMS;
	String dimtype;
	String algotype;
	String yuceTime;

	// 预警
	ArrayList<PredictProbability> predictProList = null;

	public String showYujingMain() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		String username = (String) ctx.get("username");
		System.out.println("username:" + username);
		// if (username == null || username.equals("")) {
		// return LOGIN;
		// }
		if (frameContent == null || frameContent.equals(""))
			frameContent = "yuce";
		return SUCCESS;
	}

	public String showYuce() throws Exception {
		dimStrs = dimtypeDao.getAllDimtypesStr();

		if (yuceTime != null && algotype != null) {
			String year = yuceTime.split("-")[0];
			String month = yuceTime.split("-")[1];
			System.out.println("yuceTime:" + yuceTime + "  dimtype:" + dimtype
					+ "  algoSelect:" + algotype);

			int algoSelect = algotype
					.equals(GlobalConstant.PREDICT_AGLORITHMS[0]) ? 0 : 1;

			predictMap = predictService.getDimPredictValues(
					Integer.parseInt(year), Integer.parseInt(month), dimtype,
					dimtypeDao.getDimtypeValue(dimtype), algoSelect);

			predictList = new ArrayList<Entry<String, Double>>(
					predictMap.entrySet());
			Collections.sort(predictList,
					new Comparator<Map.Entry<String, Double>>() {
						public int compare(Map.Entry<String, Double> o1,
								Map.Entry<String, Double> o2) {
							double result = o2.getValue() - o1.getValue();
							int r = 0;
							if (result > 0)
								r = 1;
							if (result < 0)
								r = -1;
							return r;
						}
					});

		}
		frameContent = "yuce";
		return frameContent;
	}

	public String showYujing() throws Exception {
		dimStrs = dimtypeDao.getAllDimtypesStr();

		if (yuceTime != null && algotype != null) {
			String year = yuceTime.split("-")[0];
			String month = yuceTime.split("-")[1];
			System.out.println("yuceTime:" + yuceTime + "  algotype:"
					+ algotype);
			int algoSelect = algotype
					.equals(GlobalConstant.PREDICT_AGLORITHMS[0]) ? 0 : 1;

			predictProList = predictService
					.getYujingPredictValues(Integer.parseInt(year),
							Integer.parseInt(month), algoSelect);
		}
		frameContent = "yujing";
		return frameContent;
	}

	public ArrayList<PredictProbability> getPredictProList() {
		return predictProList;
	}

	public void setPredictProList(ArrayList<PredictProbability> predictProList) {
		this.predictProList = predictProList;
	}

	public List<String> getDimStrs() {
		return dimStrs;
	}

	public void setDimStrs(List<String> dimStrs) {
		this.dimStrs = dimStrs;
	}

	public String getFrameContent() {
		return frameContent;
	}

	public void setFrameContent(String frameContent) {
		this.frameContent = frameContent;
	}

	public Map<String, Double> getPredictMap() {
		return predictMap;
	}

	public void setPredictMap(Map<String, Double> predictMap) {
		this.predictMap = predictMap;
	}

	public ArrayList<Entry<String, Double>> getPredictList() {
		return predictList;
	}

	public void setPredictList(ArrayList<Entry<String, Double>> predictList) {
		this.predictList = predictList;
	}

	public String[] getAglorithms() {
		return aglorithms;
	}

	public void setAglorithms(String[] aglorithms) {
		this.aglorithms = aglorithms;
	}

	public String getDimtype() {
		return dimtype;
	}

	public void setDimtype(String dimtype) {
		this.dimtype = dimtype;
	}

	public String getAlgotype() {
		return algotype;
	}

	public void setAlgotype(String algotype) {
		this.algotype = algotype;
	}

	public String getYuceTime() {
		return yuceTime;
	}

	public void setYuceTime(String yuceTime) {
		this.yuceTime = yuceTime;
	}

}
