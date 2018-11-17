package com.simple.service;

import java.util.List;
import java.util.Map;

import com.simple.po.Link;

/**
 * 友情链接Service接口
 * @author Administrator
 *
 */
public interface LinkService {

	/**
	 * 查找友情链接信息
	 * @param map
	 * @return
	 */
	public List<Link> list(Map<String,Object> map);
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	/**
	 * 添加友情链接信息
	 * @param blogType
	 * @return
	 */
	public Integer add(Link link);
	
	/**
	 * 修改友情链接信息
	 * @param blogType
	 * @return
	 */
	public Integer update(Link link);
	
	/**
	 * 删除友情链接信息
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
	
	
}
