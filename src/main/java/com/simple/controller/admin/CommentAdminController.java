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
import com.google.gson.GsonBuilder;
import com.simple.po.Comment;
import com.simple.po.PageBean;
import com.simple.po.Result;
import com.simple.service.CommentService;
import com.simple.util.ResponseUtil;


/**
 * 管理员评论Controller层
 */
@Controller
@RequestMapping("/admin/comment")
public class CommentAdminController {

	@Resource
	private CommentService commentService;
	
	/**
	 * 分页查询评论信息
	 * @param page
	 * @param rows
	 * @param state
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public String list(@RequestParam(value="page",required=false)String page,@RequestParam(value="rows",required=false)String rows,@RequestParam(value="state",required=false)String state,HttpServletResponse response)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("state", state); // 评论状态
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Comment> commentList=commentService.list(map);
		Long total=commentService.getTotal(map);
		

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		map.clear();
		map.put("rows", commentList);
		map.put("total", total);
		ResponseUtil.write(response, gson.toJson(map));
		return null;
	}
	
	
	/**
	 * 评论审核 通过  或者不通过。
	 */
	@RequestMapping("/review")
	public String review(@RequestParam(value="ids",required=false)String ids,@RequestParam(value="state",required=false)Integer state,HttpServletResponse response)throws Exception{
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			Comment comment=new Comment();
			comment.setId(Integer.parseInt(idsStr[i]));
			comment.setState(state);
			commentService.update(comment);
		}
		
		Result result = new Result();
		Gson gson = new Gson();
		result.setSuccess(true);
		 
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	
	
	/**
	 * 评论信息删除
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
			commentService.delete(Integer.parseInt(idsStr[i]));				
		}
		result.setSuccess(true);
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
	
	
	
}
