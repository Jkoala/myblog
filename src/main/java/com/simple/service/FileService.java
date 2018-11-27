package com.simple.service;

import java.util.List;
import java.util.Map;

import com.simple.po.Blog;
import com.simple.po.File;

/**
 * @author Administrator
 *
 */
public interface FileService { 
	 
	/* ��ҳ���File�б�
	 * @param map
	 * @return
	 */
	public List<File> list(Map<String,Object> map);
	/**
	 * ����ļ�����
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	/**
	 * ģ����ѯ�ļ�
	 * @param id
	 * @return
	 */
	public List<File> searchFile(String word);
	/**
	 * �����ļ�
	 * @param blog
	 * @return
	 */
	public int updateFile(File file);
	
	/**
	 * ɾ���ļ�
	 */
	public int deleteFile(Integer id);
	
	/**
	 * �ϴ��ļ���Ϣ
	 * @param file
	 * @return
	 */
	public int insertFile(File file) ; 
	
}
