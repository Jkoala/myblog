package com.simple.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;

import com.simple.po.Blog;

/**
 * ����Dao�ӿ�
 * @author Administrator
 *
 */
public interface BlogDao {

	/**
	 * �������ڷ��·����ѯ
	 * @return
	 */
	public List<Blog> countList();
	
	/**
	 * ��ҳ��ѯ����
	 * @param map
	 * @return
	 */
	public List<Blog> list(Map<String,Object> map);
	
	/**
	 * ��ȡ�ܼ�¼��
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);
	
	
	/**
	 * ����id����ʵ��
	 * @param id
	 * @return
	 */
	public Blog findById(Integer id);
	
	/**
	 * ���²�����Ϣ
	 * @param blog
	 * @return
	 */
	public Integer update(Blog blog);
	
	/**
	 * ��ȡ��һ������
	 * @param id
	 * @return
	 */
	public Blog getLastBlog(Integer id);
	
	/**
	 * ��ȡ��һ������
	 */
	public Blog getNextBlog(Integer id);
	
	/**
	 * ��Ӳ�����Ϣ
	 */
	public Integer add(Blog blog);
	
	/**
	 * ɾ��������Ϣ
	 */
	@Delete("delete from t_blog where id=#{id}")
	public Integer delete(Integer id);
	
	
	
	/**
	 * ��ѯָ���Ĳ�������µĲ�������
	 */
	public Integer getBlogByTypeId(Integer typeId);
	
	
	
	
}
