package com.simple.service;

import java.util.List;
import java.util.Map;

import com.simple.po.Tree;

public interface TreeService {

	/**
	 * ���ݸ��ڵ�  ����
	 * map �� id��-1�� ��   ids(1.2.5.6.9.4.4.)   
	 */
	public List<Tree> getTreeByParentId(Map<String,Object> map);
	
	
	/**
	 * ���� ids �����еĽڵ�    
	 * Ӧ����shire Ȩ�� ����ʱ�õ���
	 */
	public List<Tree> getTreeByTreeIds(Map<String,Object> map);
}
