package com.simple.dao;

import java.util.List;
import java.util.Map;
import com.simple.po.File;

/**
 * 总共有获得列表   通过id查找  上传文件  上传文件  得到总数五个方法
 * @author simple
 *
 */
public interface FileDao {
	
	/**
	 * 获得文件列表
	 * @param map
	 * @return
	 */
	public List<File> list(Map<String,Object> map);
	
	/**
	 * 根据id查找文件
	 * @param id
	 * @return
	 */
	public List<File> searchFile(String word);
	
	/**
	 * 更新文件信息
	 * @param file
	 * @return
	 */
	public int updateFile(File file);
	
	/**
	 * 得到某种条件下文件的总数
	 * @param map
	 * @return
	 */
	public long getTotal(Map<String,Object> map);
	
	/**
	 * 删除文件
	 * @param id
	 * @return
	 */
	public int deletFile(Integer id);
	
	/**插入文件
	 * @param file
	 * @return
	 */
	public int insertFile(File file); 
	
}
