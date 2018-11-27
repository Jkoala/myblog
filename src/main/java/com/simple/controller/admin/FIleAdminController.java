package com.simple.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.weaver.AjAttribute.PrivilegedAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.simple.po.File;
import com.simple.po.Result;
import com.simple.service.FileService;
import com.simple.util.ResponseUtil;

@Controller
@RequestMapping("/admin/file")
public class FIleAdminController {
	@Resource
	private FileService fileService;
	//文件的更新和添加
	@RequestMapping("save")
	public String update(File file,HttpServletResponse response,HttpServletRequest request) throws Exception {
		//书写更新文件内容   文件的上传直接在前台写  
		int resultTotal=0; 
		if(file.getId()==null){
			resultTotal=fileService.insertFile(file);
			
		}else{
			resultTotal = fileService.updateFile(file);
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
	//"code":0,"msg":"","count":1000,"data":  列表需要返回的数据
	//@RequestMapping("list")
	//public String list()
}
