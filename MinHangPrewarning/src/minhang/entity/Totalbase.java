package minhang.entity;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the totalbase database table.
 * 
 */
public class Totalbase implements Serializable {
	private static final long serialVersionUID = 1L;

	private String biaoti;

	private String dengjidalei;

	private String fashengdidian;

	private String fashengdiqu;

	private Date fashengriqi;

	private String feixingjieduan;

	private String fengxianzhi;

	private String hangkongqijihao;

	private String hangkongqijixing;

	private String hangkongqishiyongdanwei;

	private String jihuamudidi;

	private String jixingdalei;

	private String leixingdalei;

	private String myindex;

	private String shangbaoriqi;

	private String shijiandengji;

	private String shijianhouguo;

	private String shijianleixing;

	private String shijiantiaokuan;

	private String shijianxingzhi;

	private String shijianyuanyin;

	private String tiaokuandalei;

	private String yuanyindalei;

	private String yuanyinyinsu;

	private String zerendanwei;

	private String zerendiqu;

	private String zuihouqifeidian;

	public Totalbase() {
	}

	public Totalbase(String myindex,String biaoti, String dengjidalei, String fashengdidian,
			String fashengdiqu, Date fashengriqi, String feixingjieduan,
			String fengxianzhi, String hangkongqijihao,
			String hangkongqijixing, String hangkongqishiyongdanwei,
			String jihuamudidi, String jixingdalei, String leixingdalei,
			 String shangbaoriqi, String shijiandengji,
			String shijianhouguo, String shijianleixing,
			String shijiantiaokuan, String shijianxingzhi,
			String shijianyuanyin, String tiaokuandalei, String yuanyindalei,
			String yuanyinyinsu, String zerendanwei, String zerendiqu,
			String zuihouqifeidian) {
		super();
		this.biaoti = biaoti;
		this.dengjidalei = dengjidalei;
		this.fashengdidian = fashengdidian;
		this.fashengdiqu = fashengdiqu;
		this.fashengriqi = fashengriqi;
		this.feixingjieduan = feixingjieduan;
		this.fengxianzhi = fengxianzhi;
		this.hangkongqijihao = hangkongqijihao;
		this.hangkongqijixing = hangkongqijixing;
		this.hangkongqishiyongdanwei = hangkongqishiyongdanwei;
		this.jihuamudidi = jihuamudidi;
		this.jixingdalei = jixingdalei;
		this.leixingdalei = leixingdalei;
		this.myindex = myindex;
		this.shangbaoriqi = shangbaoriqi;
		this.shijiandengji = shijiandengji;
		this.shijianhouguo = shijianhouguo;
		this.shijianleixing = shijianleixing;
		this.shijiantiaokuan = shijiantiaokuan;
		this.shijianxingzhi = shijianxingzhi;
		this.shijianyuanyin = shijianyuanyin;
		this.tiaokuandalei = tiaokuandalei;
		this.yuanyindalei = yuanyindalei;
		this.yuanyinyinsu = yuanyinyinsu;
		this.zerendanwei = zerendanwei;
		this.zerendiqu = zerendiqu;
		this.zuihouqifeidian = zuihouqifeidian;
	}

	public String getBiaoti() {
		return this.biaoti;
	}

	public void setBiaoti(String biaoti) {
		this.biaoti = biaoti;
	}

	public String getDengjidalei() {
		return this.dengjidalei;
	}

	public void setDengjidalei(String dengjidalei) {
		this.dengjidalei = dengjidalei;
	}

	public String getFashengdidian() {
		return this.fashengdidian;
	}

	public void setFashengdidian(String fashengdidian) {
		this.fashengdidian = fashengdidian;
	}

	public String getFashengdiqu() {
		return this.fashengdiqu;
	}

	public void setFashengdiqu(String fashengdiqu) {
		this.fashengdiqu = fashengdiqu;
	}

	public Date getFashengriqi() {
		return this.fashengriqi;
	}

	public void setFashengriqi(Date fashengriqi) {
		this.fashengriqi = fashengriqi;
	}

	public String getFeixingjieduan() {
		return this.feixingjieduan;
	}

	public void setFeixingjieduan(String feixingjieduan) {
		this.feixingjieduan = feixingjieduan;
	}

	public String getFengxianzhi() {
		return this.fengxianzhi;
	}

	public void setFengxianzhi(String fengxianzhi) {
		this.fengxianzhi = fengxianzhi;
	}

	public String getHangkongqijihao() {
		return this.hangkongqijihao;
	}

	public void setHangkongqijihao(String hangkongqijihao) {
		this.hangkongqijihao = hangkongqijihao;
	}

	public String getHangkongqijixing() {
		return this.hangkongqijixing;
	}

	public void setHangkongqijixing(String hangkongqijixing) {
		this.hangkongqijixing = hangkongqijixing;
	}

	public String getHangkongqishiyongdanwei() {
		return this.hangkongqishiyongdanwei;
	}

	public void setHangkongqishiyongdanwei(String hangkongqishiyongdanwei) {
		this.hangkongqishiyongdanwei = hangkongqishiyongdanwei;
	}

	public String getJihuamudidi() {
		return this.jihuamudidi;
	}

	public void setJihuamudidi(String jihuamudidi) {
		this.jihuamudidi = jihuamudidi;
	}

	public String getJixingdalei() {
		return this.jixingdalei;
	}

	public void setJixingdalei(String jixingdalei) {
		this.jixingdalei = jixingdalei;
	}

	public String getLeixingdalei() {
		return this.leixingdalei;
	}

	public void setLeixingdalei(String leixingdalei) {
		this.leixingdalei = leixingdalei;
	}

	public String getMyindex() {
		return this.myindex;
	}

	public void setMyindex(String myindex) {
		this.myindex = myindex;
	}

	public String getShangbaoriqi() {
		return this.shangbaoriqi;
	}

	public void setShangbaoriqi(String shangbaoriqi) {
		this.shangbaoriqi = shangbaoriqi;
	}

	public String getShijiandengji() {
		return this.shijiandengji;
	}

	public void setShijiandengji(String shijiandengji) {
		this.shijiandengji = shijiandengji;
	}

	public String getShijianhouguo() {
		return this.shijianhouguo;
	}

	public void setShijianhouguo(String shijianhouguo) {
		this.shijianhouguo = shijianhouguo;
	}

	public String getShijianleixing() {
		return this.shijianleixing;
	}

	public void setShijianleixing(String shijianleixing) {
		this.shijianleixing = shijianleixing;
	}

	public String getShijiantiaokuan() {
		return this.shijiantiaokuan;
	}

	public void setShijiantiaokuan(String shijiantiaokuan) {
		this.shijiantiaokuan = shijiantiaokuan;
	}

	public String getShijianxingzhi() {
		return this.shijianxingzhi;
	}

	public void setShijianxingzhi(String shijianxingzhi) {
		this.shijianxingzhi = shijianxingzhi;
	}

	public String getShijianyuanyin() {
		return this.shijianyuanyin;
	}

	public void setShijianyuanyin(String shijianyuanyin) {
		this.shijianyuanyin = shijianyuanyin;
	}

	public String getTiaokuandalei() {
		return this.tiaokuandalei;
	}

	public void setTiaokuandalei(String tiaokuandalei) {
		this.tiaokuandalei = tiaokuandalei;
	}

	public String getYuanyindalei() {
		return this.yuanyindalei;
	}

	public void setYuanyindalei(String yuanyindalei) {
		this.yuanyindalei = yuanyindalei;
	}

	public String getYuanyinyinsu() {
		return this.yuanyinyinsu;
	}

	public void setYuanyinyinsu(String yuanyinyinsu) {
		this.yuanyinyinsu = yuanyinyinsu;
	}

	public String getZerendanwei() {
		return this.zerendanwei;
	}

	public void setZerendanwei(String zerendanwei) {
		this.zerendanwei = zerendanwei;
	}

	public String getZerendiqu() {
		return this.zerendiqu;
	}

	public void setZerendiqu(String zerendiqu) {
		this.zerendiqu = zerendiqu;
	}

	public String getZuihouqifeidian() {
		return this.zuihouqifeidian;
	}

	public void setZuihouqifeidian(String zuihouqifeidian) {
		this.zuihouqifeidian = zuihouqifeidian;
	}

}