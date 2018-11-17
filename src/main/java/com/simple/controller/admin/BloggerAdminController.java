package com.simple.controller.admin;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.simple.po.Blogger;
import com.simple.po.Tree;
import com.simple.service.BloggerService;
import com.simple.service.TreeService;
import com.simple.util.CryptographyUtil;
import com.simple.util.DateUtil;
import com.simple.util.MyUtil;
import com.simple.util.ResponseUtil;

/**
 * 管理员博主Controller层
 * @author Administrator
 */
@Controller
@RequestMapping("/admin/blogger")
public class BloggerAdminController {

	@Resource
	private BloggerService bloggerService;
	
	@Resource
	private TreeService  treeService;
	
	
	/**
	 * 拿到菜单 
	 */
	@RequestMapping("/getTreeMenu")
	public String getTreeMenu(HttpServletRequest requset,HttpServletResponse response)throws Exception{
		//先找parent是-1的顶级菜单
				//不能从session中拿Blogger 因为如果数据更新了。session没有更新
				
				Blogger blogger = (Blogger) SecurityUtils.getSubject().getSession().getAttribute("currentUser");
				blogger = bloggerService.getByUserName(blogger.getUserName());
				
				List<Integer> ids =MyUtil.Str_ids_To_ListInteger_ids(blogger.getTreeMenuIds());  
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", "-1");
				map.put("ids", ids);
				List<Tree> list  = getTreesByParentId(map);
				Gson g = new Gson();
				ResponseUtil.write(response, g.toJson(list));
				
				return null ; 
	}
	/**
	 * 拿到菜单 
	 */
	public List<Tree> getTreesByParentId(Map<String,Object> map) throws Exception {
		//String parentId,String ids  = map
		List<Tree> list = treeService.getTreeByParentId(map);//
		for(Tree tree : list){
			//如果 是复选框  可以在这里判断   
			//tree.setChecked(true);
			if("open".equals(tree.getState())){
				continue;
			}else{
				map.put("id", tree.getId()+"");//更换id不换ids继续查
				tree.setChildren(getTreesByParentId(map));
			}
		}
		return list;
	}
	
	
	/**
	 * 查询博主信息
	 */
	@RequestMapping("/find")
	public String find(HttpServletResponse response)throws Exception{
		Blogger blogger=bloggerService.find();
		Gson gson = new Gson();
		ResponseUtil.write(response, gson.toJson(blogger));
		return null;
	}
	
	/**
	 * 修改博主信息
	 */
	@RequestMapping("/save")
	public String save(@RequestParam("imageFile") MultipartFile imageFile,Blogger blogger,HttpServletRequest request,HttpServletResponse response)throws Exception{
		if(!imageFile.isEmpty()){
			String filePath=request.getServletContext().getRealPath("/");
			String imageName=DateUtil.getCurrentDateStr()+"."+imageFile.getOriginalFilename().split("\\.")[1];
			imageFile.transferTo(new File(filePath+"static/userImages/"+imageName));
			blogger.setImageName(imageName);
		}
		int resultTotal=bloggerService.update(blogger);
		StringBuffer result=new StringBuffer();
		if(resultTotal>0){
			result.append("<script language='javascript'>alert('修改成功！');</script>");
		}else{
			result.append("<script language='javascript'>alert('修改失败！');</script>");
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	
	/**
	 * 修改博主密码
	 */
	@RequestMapping("/modifyPassword")
	public String modifyPassword(String newPassword,HttpServletResponse response)throws Exception{
		Blogger blogger=new Blogger();
		blogger.setPassword(CryptographyUtil.md5(newPassword,"simple"));
		int resultTotal=bloggerService.update(blogger);

		JSONObject result=new JSONObject();
		if(resultTotal>0){
			result.put("success", true);
		}else{
			result.put("success", false);
		}
		ResponseUtil.write(response, result);
		return null;
	}
	
	/**
	 * 注销
	 */
	@RequestMapping("/logout")
	public String logout()throws Exception{
		SecurityUtils.getSubject().logout(); //shiro的退出
		return "redirect:/login.jsp";
	}
	
	
	
	
}
