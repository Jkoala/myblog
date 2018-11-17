package com.simple.service;

import java.util.List;
import java.util.Map;

import com.simple.po.Remark;

public interface RemarkService {

	public Integer add(Remark remark);
	
	public  Integer update(Remark remark);
	
	public List<Remark> list(Map<String,Object > map);
	
}
