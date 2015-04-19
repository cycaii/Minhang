/**
 * 
 */
package minhang.action;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import minhang.algo.PinfaEventCompute;
import minhang.bean.PinfaEvent;
import minhang.dao.DimtypeDao;
import minhang.dao.OuFaEventDao;
import minhang.dao.PinfaEventDao;
import minhang.entity.Onedimstatistic;
import minhang.entity.Totalbase;
import minhang.entity.Twodimstatistic;
import minhang.entity.User;
import minhang.util.GlobalConstant;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author cyc
 * 
 */
public class OufaAction extends ActionSupport {
	private PinfaEventCompute pinfaEventCompute = PinfaEventCompute
			.getInstance();
	private DimtypeDao dimtypeDao = DimtypeDao.getInstance();
	private OuFaEventDao oufaDao = OuFaEventDao.getInstance();
	private	PinfaEventDao pinfaEventDao = PinfaEventDao.getInstance();
	private List<String> dimStrs = null;
	private List<PinfaEvent> pinfaList = null;

	private String frameContent;
	private String msg = null;
	/* 获取页面输入参数 */
	// 一维偶发
	/**
	 * 维度类型
	 */
	private String dimSelect;
	private String pinfaDate;
	// 二维偶发
	private String dtype1Select;
	private String dtype2Select;
	private PinfaEvent PinfaEvent;
	//偶发详情
	private String dimtype;
	private String	dimvalue;
	private Date	day;
	private int	dim;
	private String dimtype1;
	private	String dimvalue1;
	private String dimtype2  ;
	private String dimvalue2 ;
	List<Totalbase> totals = null;
	// 一维突发
	private String[] years = GlobalConstant.years;
	private String year;
	private String accordingSel;
	private String numSel;
	private List<Onedimstatistic> tufaOneDimResults=null;
	// 二维突发
	List<Twodimstatistic> tufaTwoDimResults = null;
	
	public String showOufaMain() throws Exception {
		ActionContext ctx = ActionContext.getContext();
		String username = (String) ctx.get("username");
		 System.out.println("username:"+username);
//		 if (username == null || username.equals("")) {
//		 return LOGIN;
//		 }
		if (frameContent == null || frameContent.equals(""))
			frameContent = "oufaEvent1";
		return SUCCESS;
	}

	/**
	 * 一维偶发事件页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showOufaOneDim() throws Exception {
		System.out.println("showOufaOneDim--dimSelect:" + dimSelect
				+ "  pinfaDate:" + pinfaDate);

		dimStrs = dimtypeDao.getAllDimtypesStr();

		if (dimSelect != null && pinfaDate != null && pinfaDate.trim() != null) {
			pinfaList = pinfaEventCompute.computeOneDimPinfa(dimSelect,
					pinfaDate);
			if (pinfaList == null || pinfaList.size() == 0) {
				setMsg("抱歉，" + pinfaDate + "此周 【" + dimSelect + "】没有结果。");
			}
		}
		frameContent = "oufaEvent1";
		return frameContent;
	}

	/**
	 * 二维偶发事件页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showOufaTwoDim() throws Exception {
		System.out.println("showOufaTwoDim--dimtype1:" + dtype1Select
				+ "  dimtype2:" + dtype2Select + "  pinfaDate:" + pinfaDate);

		dimStrs = dimtypeDao.getAllDimtypesStr();

		if (dtype1Select != null && dtype2Select != null && pinfaDate != null
				&& pinfaDate.trim() != null) {
			pinfaList = pinfaEventCompute.computeTwoDimPinfa(dtype1Select,
					dtype2Select, pinfaDate);
			if (pinfaList == null || pinfaList.size() == 0) {
				msg = "抱歉，" + pinfaDate + "此周 【" + dtype1Select + " "
						+ dtype2Select + "】没有结果。";
			}
		}
		frameContent = "oufaEvent2";
		return frameContent;
	}

	
	public String showOufaEventDetail(){
		try {
//			dimvalue=new String(dimvalue.getBytes("utf-8"));
			dimvalue=new String(getDimvalue().getBytes("iso-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("showOufaEventDetail-dimtype:" + dimtype + "  dimvalue:"
				+ dimvalue+"  day:"+day+"  dim:"+dim);
		
		if (dim==1) {
			totals = pinfaEventDao.queryOneDimPinfaEventDetails(dimtype,
					dimvalue, day);
		} else if (dim==2) {
			 dimtype1 = dimtype.split(" ")[0];
			dimvalue1 = dimvalue.split(" ")[0];
			dimtype2 = dimtype.split(" ")[1];
			dimvalue2 = dimvalue.split(" ")[1];
			System.out.println("showOufaEventDetail-dimtype1:" + dimtype1
					+ "  dimvalue1:" + dimvalue1 + "   dimtype2:"
					+ dimtype2 + "  dimvalue2:" + dimvalue2);

			totals = pinfaEventDao.queryTwoDimPinfaEventDetails(dimtype1,
					dimvalue1, dimtype2, dimvalue2, day);
		}
		return "oufaDetail";
	}
	/**
	 * 一维突发事件页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showTufaOneDim() throws Exception {
		System.out.println("showTufaOneDim:dimtype:" + dimSelect + "  yearSel:" + year
				+ "  according:" + accordingSel + "  num:" + numSel);

		dimStrs = dimtypeDao.getAllDimtypesStr();

		if (year != null && accordingSel != null && numSel != null) {
			tufaOneDimResults = oufaDao.queryOneDimOufaEvent(dimSelect, year,
					accordingSel, numSel);
			if (tufaOneDimResults == null || tufaOneDimResults.size() == 0) {
				msg = "您好，" + dimSelect + year + "年第" + numSel + accordingSel
						+ "没有突发事件";
			}
		}
		frameContent = "tufaEvent1";
		return frameContent;
	}

	/**
	 * 二维突发事件页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showTufaTwoDim() throws Exception {
		System.out.println("showTufaTwoDim:dimtype:"+dtype1Select+dtype2Select+"  yearSel:"+year +"  according:"+accordingSel+"  num:"+numSel);
		dimStrs = dimtypeDao.getAllDimtypesStr();

		if(year !=null &&accordingSel!=null&&numSel!=null){
			tufaTwoDimResults= oufaDao.queryTwoDimOufaEvent(dtype1Select, dtype2Select,year , accordingSel, numSel);
		 if(tufaTwoDimResults==null||tufaTwoDimResults.size()==0){
		msg = "您好，"+dtype1Select+" "+dtype2Select+year +"年第"+numSel+accordingSel+"没有突发事件";
			} 
		}
		frameContent = "tufaEvent2";
		return frameContent;
	}
	
	
	 
	public List<Onedimstatistic> getTufaOneDimResults() {
		return tufaOneDimResults;
	}

	public void setTufaOneDimResults(List<Onedimstatistic> tufaOneDimResults) {
		this.tufaOneDimResults = tufaOneDimResults;
	}

	public List<Twodimstatistic> getTufaTwoDimResults() {
		return tufaTwoDimResults;
	}

	public void setTufaTwoDimResults(List<Twodimstatistic> tufaTwoDimResults) {
		this.tufaTwoDimResults = tufaTwoDimResults;
	}

	public String[] getYears() {
		return years;
	}

	public void setYears(String[] years) {
		this.years = years;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getAccordingSel() {
		return accordingSel;
	}

	public void setAccordingSel(String accordingSel) {
		this.accordingSel = accordingSel;
	}

	public String getNumSel() {
		return numSel;
	}

	public void setNumSel(String numSel) {
		this.numSel = numSel;
	}

 
	public PinfaEvent getPinfaEvent() {
		return PinfaEvent;
	}

	public void setPinfaEvent(PinfaEvent pinfaEvent) {
		PinfaEvent = pinfaEvent;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<String> getDimStrs() {
		return dimStrs;
	}

	public void setDimStrs(List<String> dimStrs) {
		this.dimStrs = dimStrs;
	}

	public List<PinfaEvent> getPinfaList() {
		return pinfaList;
	}

	public void setPinfaList(List<PinfaEvent> pinfaList) {
		this.pinfaList = pinfaList;
	}

	public String getDimSelect() {
		return dimSelect;
	}

	public void setDimSelect(String dimSelect) {
		this.dimSelect = dimSelect;
	}

	public String getPinfaDate() {
		return pinfaDate;
	}

	public void setPinfaDate(String pinfaDate) {
		this.pinfaDate = pinfaDate;
	}

	public String getFrameContent() {
		return frameContent;
	}

	public void setFrameContent(String frameContent) {
		this.frameContent = frameContent;
	}

	public String getDtype1Select() {
		return dtype1Select;
	}

	public void setDtype1Select(String dtype1Select) {
		this.dtype1Select = dtype1Select;
	}

	public String getDtype2Select() {
		return dtype2Select;
	}

	public void setDtype2Select(String dtype2Select) {
		this.dtype2Select = dtype2Select;
	}
	public String getDimtype() {
		return dimtype;
	}

	public void setDimtype(String dimtype) {
		this.dimtype = dimtype;
	}

	public String getDimvalue() {
		return dimvalue;
	}

	public void setDimvalue(String dimvalue) {
		this.dimvalue = dimvalue;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public int getDim() {
		return dim;
	}

	public void setDim(int dim) {
		this.dim = dim;
	}

	public List<Totalbase> getTotals() {
		return totals;
	}

	public void setTotals(List<Totalbase> totals) {
		this.totals = totals;
	}
}
