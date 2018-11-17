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
import com.simple.po.Link;
import com.simple.po.PageBean;
import com.simple.po.Remark;
import com.simple.po.Result;
import com.simple.service.LinkService;
import com.simple.service.RemarkService;
import com.simple.util.ResponseUtil;
import com.simple.util.StringUtil;

@Controller
@RequestMapping("/admin/remark")
public class RemarkController {
	@Resource
	private RemarkService remarkService;
	
	
	@RequestMapping("/save")
	public String save(Remark remark,HttpServletResponse response)throws Exception{
		int resultTotal=0; 
		if(remark.getId()==null){
			resultTotal=remarkService.add(remark);
		}else{
			resultTotal=remarkService.update(remark);
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
	
	@RequestMapping("/list")
	public String list(
			@RequestParam(value="q",required=false)String q,
			HttpServletResponse response)throws Exception{
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("q", StringUtil.formatLike(q));
		List<Remark> list=remarkService.list(map);
		Gson gson = new Gson();
		ResponseUtil.write(response, gson.toJson(list));
		return null;
	}
	
	
}
