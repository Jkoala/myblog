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
 * 自定义Realm
 * @author Administrator
 *
 */
public class MyRealm extends AuthorizingRealm{

	@Resource
	private BloggerService bloggerService;
	@Resource
	private TreeService treeService;
	
	/**
	 * 为当前的登录的用户角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
				//通过用户取得他应该拥有的权限
				String userName=(String) principals.getPrimaryPrincipal();
				
				SimpleAuthorizationInfo  authorizationInfo = new SimpleAuthorizationInfo();
				
				//设置角色 集合   这个目前用不到。
				// authorizationInfo.setRoles(roles);
				
				Blogger blogger = (Blogger) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
				blogger = bloggerService.getByUserName(blogger.getUserName());
				
				Map<String, Object> map = new HashMap<String, Object>();
				List<Integer> ids =MyUtil.Str_ids_To_ListInteger_ids(blogger.getTreeMenuIds());  
				map.put("ids", ids);
				List<Tree> treeList = treeService.getTreeByTreeIds(map);
				
				//权限集合
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
	 * 验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String userName=(String) token.getPrincipal();
		Blogger blogger=bloggerService.getByUserName(userName);
		if(blogger!=null){
			/**
			 * 他这里为什么要这样做。因为他感觉
			 * 如果验证成功 再设置会麻烦一点   他是直接设置了。
			 * 不管你成功没有成功 他都设置了。
			 * 如果没有成功  设置也是白设置  因为你没有突破shiro  所以也是白设置
			 */
			SecurityUtils.getSubject().getSession().setAttribute("currentUser", blogger); // 把当前用户信息存到session中
			AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(blogger.getUserName(), blogger.getPassword(), "xxx");
			return authcInfo;
		}else{
			return null;
		}
	}

}
