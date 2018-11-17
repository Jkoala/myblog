package com.simple.service;

import java.util.List;
import java.util.Map;

import com.simple.po.Comment;

/**
 * ����Service�ӿ�
 * @author Administrator
 *
 */
public interface CommentService {

	/**
	 * ��ѯ�û�������Ϣ
	 * @param map
	 * @return
	 */
	public List<Comment> list(Map<String,Object> map);
	
	
	/**
	 * �������
	 * @param comment
	 * @return
	 */
	public int add(Comment comment);


	/**
	 * ��ȡ�ܼ�¼��
	 * @param map
	 * @return
	 */
	public Long getTotal(Map<String,Object> map);

	/**
	 * �޸�����
	 * @param comment
	 * @return
	 */
	public int update(Comment comment);
	
	/**
	 * ɾ�� 
	 * @param id
	 * @return
	 */
	public Integer delete(Integer id);
	
	
}
