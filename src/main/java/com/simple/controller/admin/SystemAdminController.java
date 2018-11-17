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
 * ����ԱϵͳController��
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
	 * ˢ��ϵͳ����
	 */
	@RequestMapping("/refreshSystem")
	public String refreshSystem(HttpServletRequest request,HttpServletResponse response)throws Exception{
		//�����õ�application���� spring�ṩ�ġ� HttpServletResponse response
		ServletContext application=RequestContextUtils.getWebApplicationContext(request).getServletContext();
		
		
		Blogger blogger=bloggerService.find(); // ��ȡ������Ϣ
		blogger.setPassword(null);
		application.setAttribute("blogger", blogger);
		
		List<Link> linkList=linkService.list(null); // ��ѯ���е�����������Ϣ
		application.setAttribute("linkList", linkList);
		
		List<BlogType> blogTypeCountList=blogTypeService.countList(); // ��ѯ��������Լ����͵�����
		application.setAttribute("blogTypeCountList", blogTypeCountList);
		
		List<Blog> blogCountList=blogService.countList(); // �������ڷ����ѯ����
		application.setAttribute("blogCountList", blogCountList);
		
		
		Result result = new Result();
		Gson gson = new Gson();
		result.setSuccess(true);
		
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	
}
