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
	 
	/* 分页获得File列表
	 * @param map
	 * @return
	 */
	public List<File> list(Map<String,Object> map);
	/**
	 * 获得文件总数
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	/**
	 * 模糊查询文件
	 * @param id
	 * @return
	 */
	public List<File> searchFile(String word);
	/**
	 * 更新文件
	 * @param blog
	 * @return
	 */
	public int updateFile(File file);
	
	/**
	 * 删除文件
	 */
	public int deleteFile(Integer id);
	
	/**
	 * 上传文件信息
	 * @param file
	 * @return
	 */
	public int insertFile(File file) ; 
	
}
