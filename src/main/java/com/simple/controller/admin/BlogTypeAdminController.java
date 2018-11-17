package com.simple.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.simple.po.BlogType;
import com.simple.po.PageBean;
import com.simple.po.Result;
import com.simple.service.BlogService;
import com.simple.service.BlogTypeService;
import com.simple.util.ResponseUtil;

/**
 * 管理员博客类别Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/blogType")
public class BlogTypeAdminController {

	@Resource
	private BlogTypeService blogTypeService;
	
	
	@Resource
	private  BlogService blogService;
	
	/**
	 * 分页查询博客类别信息
	 * @param page
	 * @param rows
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(
			@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<BlogType> blogTypeList=blogTypeService.list(map);
		Long total=blogTypeService.getTotal(map);
		
		
		Gson gson = new Gson();
		map.clear();
		map.put("rows", blogTypeList);
		map.put("total", total);
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	
	/**
	 * 添加或者修改博客类别信息
	 * @param blogType
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(BlogType blogType,HttpServletResponse response)throws Exception{
		int resultTotal=0; 
		if(blogType.getId()==null){
			resultTotal=blogTypeService.add(blogType);
		}else{
			resultTotal=blogTypeService.update(blogType);
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
	 * 博客类别信息删除
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");

		Gson gson = new Gson();
		Result result = new Result();
		
		for(int i=0;i<idsStr.length;i++){
			if(blogService.getBlogByTypeId(Integer.parseInt(idsStr[i]))>0){
				result.setMsg("博客类别下有博客，不能删除！");
			}else{
				blogTypeService.delete(Integer.parseInt(idsStr[i]));				
			}
		}
		
		result.setSuccess(true); 
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	
	
	
}
