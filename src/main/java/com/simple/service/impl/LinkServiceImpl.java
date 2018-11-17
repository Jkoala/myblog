package com.simple.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simple.dao.LinkDao;
import com.simple.po.Link;
import com.simple.service.LinkService;

/**
 * 友情链接Service实现类
 * @author Administrator
 *
 */
@Service("linkService")
public class LinkServiceImpl implements LinkService{

	@Resource
	private LinkDao linkDao;

	public List<Link> list(Map<String, Object> map) {
		return linkDao.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return linkDao.getTotal(map);
	}

	public Integer add(Link link) {
		// TODO Auto-generated method stub
		return linkDao.add(link);
	}

	public Integer update(Link link) {
		// TODO Auto-generated method stub
		return linkDao.update(link);
	}

	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return linkDao.delete(id);
	}

	
	
	

}
