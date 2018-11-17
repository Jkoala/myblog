package com.simple.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simple.dao.BlogTypeDao;
import com.simple.po.BlogType;
import com.simple.service.BlogTypeService;

/**
 * 博客类型Service实现类
 * @author Administrator
 *
 */
@Service("blogTypeService")
public class BlogTypeServiceImpl implements BlogTypeService{

	@Resource
	private BlogTypeDao blogTypeDao;
	
	public List<BlogType> countList() {
		return blogTypeDao.countList();
	}

	public List<BlogType> list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return blogTypeDao.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return blogTypeDao.getTotal(map);
	}

	public Integer add(BlogType blogType) {
		// TODO Auto-generated method stub
		return blogTypeDao.add(blogType);
	}

	public Integer update(BlogType blogType) {
		// TODO Auto-generated method stub
		return blogTypeDao.update(blogType);
	}

	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return blogTypeDao.delete(id);
	}

	
}
