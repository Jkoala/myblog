package com.simple.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.simple.dao.TreeDao;
import com.simple.po.Tree;
import com.simple.service.TreeService;
import javax.annotation.Resource;



@Service("treeService")
public class TreeServiceImpl implements TreeService {

	@Resource
	private TreeDao  treeDao;
	
	public List<Tree> getTreeByParentId(Map<String, Object> map) {
		return treeDao.getTreeByParentId(map);
	}
	

	/**
	 * 根据 ids 拿所有的节点 
	 */
	public List<Tree> getTreeByTreeIds(Map<String,Object> map) {
		return treeDao.getTreeByTreeIds(map);
	}
	
}
