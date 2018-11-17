package com.simple.dao;

import java.util.List;
import java.util.Map;

import com.simple.po.Tree;

public interface TreeDao {

	/**
	 * 根据父节点  找树
	 * map 传 id（-1） 和   ids(1.2.5.6.9.4.4.)   
	 */
	public List<Tree> getTreeByParentId(Map<String,Object> map);
	
	
	/**
	 * 根据 ids 拿所有的节点    
	 * 应该是shire 权限 设置时用到的
	 */
	public List<Tree> getTreeByTreeIds(Map<String,Object> map);
	
	
}

