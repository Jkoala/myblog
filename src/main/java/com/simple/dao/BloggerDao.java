package com.simple.dao;

import com.simple.po.Blogger;

/**
 * ����Dao�ӿ�
 * @author Administrator
 *
 */
public interface BloggerDao {

	/**
	 * ͨ���û�����ѯ�û�
	 * @param userName
	 * @return
	 */
	public Blogger getByUserName(String userName);
	
	/**
	 * ��ѯ������Ϣ
	 * @return
	 */
	public Blogger find();
	
	/**
	 * ���²�����Ϣ
	 * @param blogger
	 * @return
	 */
	public Integer update(Blogger blogger);
	
	
}
