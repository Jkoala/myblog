package com.simple.po;

public class Remark {

	//�ҵı�ע ��Ϣ 
	
	private Integer id;
	private String remark;//��ע��Ϣ
	private Integer yinsi ; //��˽���� Ĭ��0    �����1 �Ǿ�����˽  ��Ҫ������ܷ��ʡ�
	
	
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
