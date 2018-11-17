package com.simple.realm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.simple.po.Blogger;
import com.simple.po.Tree;
import com.simple.service.BloggerService;
import com.simple.service.TreeService;
import com.simple.util.MyUtil;
import com.simple.util.StringUtil;

/**
 * �Զ���Realm
 * @author Administrator
 *
 */
public class MyRealm extends AuthorizingRealm{

	@Resource
	private BloggerService bloggerService;
	@Resource
	private TreeService treeService;
	
	/**
	 * Ϊ��ǰ�ĵ�¼���û���ɫ��Ȩ��
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
				//ͨ���û�ȡ����Ӧ��ӵ�е�Ȩ��
				String userName=(String) principals.getPrimaryPrincipal();
				
				SimpleAuthorizationInfo  authorizationInfo = new SimpleAuthorizationInfo();
				
				//���ý�ɫ ����   ���Ŀǰ�ò�����
				// authorizationInfo.setRoles(roles);
				
				Blogger blogger = (Blogger) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
				blogger = bloggerService.getByUserName(blogger.getUserName());
				
				Map<String, Object> map = new HashMap<String, Object>();
				List<Integer> ids =MyUtil.Str_ids_To_ListInteger_ids(blogger.getTreeMenuIds());  
				map.put("ids", ids);
				List<Tree> treeList = treeService.getTreeByTreeIds(map);
				
				//Ȩ�޼���
				Set<String> stringPermissions = new HashSet<String>();
				for(Tree tree:treeList){
					if(StringUtil.isNotEmpty(tree.getPermissions())){
						stringPermissions.add(tree.getPermissions());
					}
				}
				//stringPermissions.add("user:add");
				//stringPermissions.add("user:del");
				//stringPermissions.add("admin:add");
				//stringPermissions.add("admin:del");
				
			 
				authorizationInfo.setStringPermissions(stringPermissions);
				
				return authorizationInfo;
				
	}

	/**
	 * ��֤��ǰ��¼���û�
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName=(String) token.getPrincipal();
		Blogger blogger=bloggerService.getByUserName(userName);
		if(blogger!=null){
			/**
			 * ������ΪʲôҪ����������Ϊ���о�
			 * �����֤�ɹ� �����û��鷳һ��   ����ֱ�������ˡ�
			 * ������ɹ�û�гɹ� ���������ˡ�
			 * ���û�гɹ�  ����Ҳ�ǰ�����  ��Ϊ��û��ͻ��shiro  ����Ҳ�ǰ�����
			 */
			SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger); // �ѵ�ǰ�û���Ϣ�浽session��
			AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(blogger.getUserName(), blogger.getPassword(), "xxx");
			return authcInfo;
		}else{
			return null;
		}
	}

}
