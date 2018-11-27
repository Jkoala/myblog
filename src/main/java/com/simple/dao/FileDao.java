package com.simple.dao;

import java.util.List;
import java.util.Map;
import com.simple.po.File;

/**
 * �ܹ��л���б�   ͨ��id����  �ϴ��ļ�  �ϴ��ļ�  �õ������������
 * @author simple
 *
 */
public interface FileDao {
	
	/**
	 * ����ļ��б�
	 * @param map
	 * @return
	 */
	public List<File> list(Map<String,Object> map);
	
	/**
	 * ����id�����ļ�
	 * @param id
	 * @return
	 */
	public List<File> searchFile(String word);
	
	/**
	 * �����ļ���Ϣ
	 * @param file
	 * @return
	 */
	public int updateFile(File file);
	
	/**
	 * �õ�ĳ���������ļ�������
	 * @param map
	 * @return
	 */
	public long getTotal(Map<String,Object> map);
	
	/**
	 * ɾ���ļ�
	 * @param id
	 * @return
	 */
	public int deletFile(Integer id);
	
	/**�����ļ�
	 * @param file
	 * @return
	 */
	public int insertFile(File file); 
	
}
