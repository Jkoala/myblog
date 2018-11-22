package com.simple.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.google.gson.Gson;
import com.simple.po.Blog;
import com.simple.po.BlogType;
import com.simple.po.Blogger;
import com.simple.po.Link;
import com.simple.po.Result;
import com.simple.service.BlogService;
import com.simple.service.BlogTypeService;
import com.simple.service.BloggerService;
import com.simple.service.LinkService;
import com.simple.util.ResponseUtil;


/**
 * 管理员系统Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/system")
public class SystemAdminController {
	
	@Resource
	private BloggerService bloggerService;
	
	@Resource
	private LinkService linkService;
	
	@Resource
	private BlogTypeService blogTypeService;
	
	@Resource
	private BlogService blogService;
	
	/**
	 * 刷新系统缓存
	 */
	@RequestMapping("/refreshSystem")
	public String refreshSystem(HttpServletRequest request,HttpServletResponse response)throws Exception{
		//这是拿到application对象 spring提供的。 HttpServletResponse response
		ServletContext application=RequestContextUtils.getWebApplicationContext(request).getServletContext();
		
		
		Blogger blogger=bloggerService.find(); // 获取博主信息
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);
		
		List<Link> linkList=linkService.list(null); // 查询所有的友情链接信息
		application.setAttribute("linkList", linkList);
		
		List<BlogType> blogTypeCountList=blogTypeService.countList(); // 查询博客类别以及博客的数量
		application.setAttribute("blogTypeCountList", blogTypeCountList);
		
		List<Blog> blogCountList=blogService.countList(); // 根据日期分组查询博客
		application.setAttribute("blogCountList", blogCountList);
		
		
		Result result = new Result();
		Gson gson = new Gson();
		result.setSuccess(true);
		
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	/**
	 * 刷新系统缓存
	 */
	@RequestMapping("/refreshLucene")
	public String refreshLucene(HttpServletRequest request,HttpServletResponse response)throws Exception{
		//这是拿到application对象 spring提供的。 HttpServletResponse response
		/*
		 * 这里写Lucene 代码
		 * */
		Result result = new Result();
		Gson gson = new Gson();
		result.setSuccess(true);
		
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
}
