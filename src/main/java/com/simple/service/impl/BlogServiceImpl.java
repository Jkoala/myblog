 package com.simple.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.simple.dao.BlogDao;
import com.simple.po.Blog;
import com.simple.service.BlogService;

/**
 * 博客Service实现类
 * @author Administrator
 *
 */
@Service("blogService")
public class BlogServiceImpl implements BlogService{

	@Resource
	private BlogDao blogDao;
	
	public List<Blog> countList() {
		return blogDao.countList();
	}

	public List<Blog> list(Map<String, Object> map) {
		return blogDao.list(map);
	}

	public Long getTotal(Map<String, Object> map) {
		return blogDao.getTotal(map);
	}

	public Blog findById(Integer id) {
		// TODO Auto-generated method stub
		return blogDao.findById(id);
	}

	public Integer update(Blog blog) {
		// TODO Auto-generated method stub
		return blogDao.update(blog);
	}

	public Blog getLastBlog(Integer id) {
		// TODO Auto-generated method stub
		return blogDao.getLastBlog(id);
	}

	public Blog getNextBlog(Integer id) {
		// TODO Auto-generated method stub
		return blogDao.getNextBlog(id);
	}

	public Integer add(Blog blog) {
		// TODO Auto-generated method stub
		return blogDao.add(blog);
	}

	public Integer delete(Integer id) {
		// TODO Auto-generated method stub
		return blogDao.delete(id);
	}

	public Integer getBlogByTypeId(Integer typeId) {
		// TODO Auto-generated method stub
		return blogDao.getBlogByTypeId(typeId);
	}

}
