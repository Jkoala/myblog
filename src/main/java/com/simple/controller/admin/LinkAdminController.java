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
import com.simple.po.Link;
import com.simple.po.PageBean;
import com.simple.po.Result;
import com.simple.service.LinkService;
import com.simple.util.ResponseUtil;

/**
 * 管理员友情链接Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/admin/link")
public class LinkAdminController {

	@Resource
	private LinkService linkService;
	
	
	/**
	 * 分页查询友情链接信息
	 * @param page
	 * @param rows
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Link> linkList=linkService.list(map);
		Long total=linkService.getTotal(map);
		
		Gson gson = new Gson();
		
		map.clear();
		map.put("rows", linkList);
		map.put("total", total);
	  
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	
	/**
	 * 添加或者修改友情链接信息
	 * @param link
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(Link link,HttpServletResponse response)throws Exception{
		int resultTotal=0; 
		if(link.getId()==null){
			resultTotal=linkService.add(link);
		}else{
			resultTotal=linkService.update(link);
		}
		Result result = new Result();
		
		Gson gson = new Gson();
		if(resultTotal>0){
			result.setSuccess(true);
			 
		}else{
			result.setSuccess(false);
		}
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	/**
	 * 友情链接信息删除
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/delete")
	public String delete(@RequestParam(value="ids",required=false)String ids,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		Result result = new Result();
		Gson gson = new Gson();
		
		for(int i=0;i<idsStr.length;i++){
			linkService.delete(Integer.parseInt(idsStr[i]));				
		}
		result.setSuccess(true);
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
}
