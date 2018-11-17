package com.simple.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.simple.po.Blog;
import com.simple.po.PageBean;
import com.simple.service.BlogService;
import com.simple.util.PageUtil;
import com.simple.util.StringUtil;

/**
 * 主页Contrller
 * @author Administrator
 */

@Controller
@RequestMapping("/")
public class IndexContrller {

	@Resource
	private BlogService blogService;
	
	
	private static Logger debugLogger = null;
	
	
	
	
	
	/**
	 * 请求主页
	 * @return
	 */
	@RequestMapping("/index")
	public ModelAndView index(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="typeId",required=false)String typeId,
			@RequestParam(value="releaseDateStr",required=false)String releaseDateStr,
			HttpServletRequest request)throws Exception{
		
	
		ModelAndView mav=new ModelAndView();
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		
		String webPath = request.getServletContext().getRealPath("/");
		debugLogger = Logger.getLogger("chenhao");
		debugLogger.debug("【【系统目录是】】" + webPath);
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		map.put("typeId", typeId);
		map.put("releaseDateStr", releaseDateStr);
		
		List<Blog> blogList=blogService.list(map);
		
		for(Blog blog:blogList){
			List<String> imageList=blog.getImageList();
			String blogInfo=blog.getContent();
			Document doc=Jsoup.parse(blogInfo);
			Elements jpgs=doc.select("img[src$=.jpg]");
			Elements pngs=doc.select("img[src$=.png]");
			//blog.setSummary(StringEscapeUtils.escapeHtml(blog.getSummary()));
			int imgSize = 0 ; 
			for(int i=0;i<jpgs.size();i++){
				Element jpg=jpgs.get(i);
				imageList.add(jpg.toString());
				imgSize++;
				if(imgSize>=2){
					break;
				}
			}
			
			for(int i=0;i<pngs.size();i++){
				if(imgSize>=2){
					break;
				}
				Element png=pngs.get(i);
				imageList.add(png.toString());
				imgSize++;
			}
			
			
			
		}
		
		StringBuffer param=new StringBuffer();
		if(StringUtil.isNotEmpty(typeId)){
			param.append("typeId="+typeId+"&");
		}
		if(StringUtil.isNotEmpty(releaseDateStr)){
			param.append("releaseDateStr="+releaseDateStr+"&");
		}
		
		mav.addObject("blogList", blogList);
		//mav.addObject("pageCode", PageUtil.genPagination(request.getContextPath()+"/index.html", 600, 10, 10, param.toString()));
		mav.addObject("pageCode", PageUtil.genPagination(request.getContextPath()+"/index.html", blogService.getTotal(map), Integer.parseInt(page), pageBean.getPageSize(), param.toString()));
		mav.addObject("pageTitle", "简单爱");
		mav.addObject("mainPage", "foreground/blog/list.jsp");
		mav.setViewName("mainTemp");
		return mav;
	}
	
	
	/**
	 * 源码下载
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/download")
	public ModelAndView download()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("pageTitle", "本站源码下载页面_陈豪-博客系统-chenhao.tv");
		mav.addObject("mainPage", "foreground/system/download.jsp");
		mav.setViewName("mainTemp");
		return mav;
	}
	
	
}
