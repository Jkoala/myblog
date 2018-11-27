package com.simple.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.simple.po.Blog;
import com.simple.po.PageBean;
import com.simple.po.Result;
import com.simple.lucene.BlogIndex;
import com.simple.service.BlogService;
import com.simple.util.ResponseUtil;
import com.simple.util.StringUtil;

/**
 * 管理员博客Controller层
 * @author Administrator
 *
 */

@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

	@Resource
	private BlogService blogService;
	
	private BlogIndex blogIndex=new BlogIndex();
	
	@RequestMapping("/refresh")
	public void refresh(HttpServletResponse response,HttpServletRequest request) {
		Map<String,Object> map=new HashMap<String,Object>();
		List<Blog> blogList= blogService.list(map);
		//System.out.println("refresh:"+blogList.size());

		Gson gson = new Gson();
		Result result = new Result();
		try {
			blogIndex.IndexManager(blogList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setSuccess(false);
		}
		result.setSuccess(true);
		
		try {
			ResponseUtil.write(response, gson.toJson(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void list(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 添加或者修改博客信息
	 * @param blog
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(Blog blog,HttpServletResponse response,HttpServletRequest request)throws Exception{
		int resultTotal=0; 
/*//		String rootPath=request.getServletContext().getRealPath("/");
*/		if(blog.getId()==null){
			resultTotal=blogService.add(blog);
			blogIndex.addIndex(blog);
		}else{
			resultTotal = blogService.update(blog);
			blogIndex.updateIndex(blog);
		}
		Gson gson = new Gson();
		Result result = new Result();
		
		if(resultTotal>0){
			result.setSuccess(true);
		}else{
			result.setSuccess(false);
		}
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	
	/**
	 * 分页查询博客信息
	 * @param page
	 * @param rows
	 * @param s_blog
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,Blog s_blog,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("title", StringUtil.formatLike(s_blog.getTitle()));
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Blog> blogList=blogService.list(map);
		Long total=blogService.getTotal(map);
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		map.clear();
		map.put("rows", blogList);
		map.put("total", total);
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	
	
	/**
	 * 博客信息删除
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response,HttpServletRequest request)throws Exception{
		String rootPath=request.getServletContext().getRealPath("/");
		
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			blogService.delete(Integer.parseInt(idsStr[i]));
			blogIndex.deleteIndex(idsStr[i]);
		}
		
		Gson gson = new Gson();
		Result result = new Result();
		result.setSuccess(true);
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	
	/**
	 * 通过Id查找实体
	 * @param id
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/findById")
	public String findById(@RequestParam(value="id")String id,HttpServletResponse response)throws Exception{
		Blog blog=blogService.findById(Integer.parseInt(id));
		Gson gson =new Gson();
		ResponseUtil.write(response, gson.toJson(blog));
		return null;
	}
	
	
}
