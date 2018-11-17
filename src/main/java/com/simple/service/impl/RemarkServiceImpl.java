package com.simple.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simple.dao.RemarkDao;
import com.simple.po.Remark;
import com.simple.service.RemarkService;

@Service("remarkService")
public class RemarkServiceImpl implements RemarkService {
	
	@Resource
	private RemarkDao  remarkDao;
	
	public Integer add(Remark remark) {
		return remarkDao.add(remark);
	}

	public Integer update(Remark remark) {
	
		return remarkDao.update(remark);
	}

	public List<Remark> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return remarkDao.list(map);
	}

}
