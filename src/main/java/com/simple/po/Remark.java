package com.simple.po;

public class Remark {

	//我的备注 信息 
	
	private Integer id;
	private String remark;//备注信息
	private Integer yinsi ; //隐私属性 默认0    如果是1 那就是隐私  需要密码才能访问。
	
	
	public Integer getYinsi() {
		return yinsi;
	}
	public void setYinsi(Integer yinsi) {
		this.yinsi = yinsi;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
