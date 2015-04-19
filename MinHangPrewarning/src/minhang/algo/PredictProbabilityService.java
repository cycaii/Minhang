package minhang.algo;

import java.util.List;

import minhang.dao.PredictProbabilityDao;
import minhang.entity.PredictProbability;

public class PredictProbabilityService {
	private PredictProbabilityDao predictProbabilityDao = PredictProbabilityDao.getInstance();
	public static PredictProbabilityService predictProbabilityService;

	public static PredictProbabilityService getInstance() {
		if (predictProbabilityService == null) {
			predictProbabilityService = new PredictProbabilityService();
		}
		return predictProbabilityService;
	}

	public void computeProbability() {
		//清空表
		predictProbabilityDao.clearAllPredictProbability();
		// 加入到概率表中 
		PredictProbability predictProbability = new PredictProbability();
		predictProbabilityDao.insertPredictProbability(predictProbability);

	}

	public List<PredictProbability> getPredictProbabilityList() {
		return predictProbabilityDao.getPredictProbabilityList();
	}
}
