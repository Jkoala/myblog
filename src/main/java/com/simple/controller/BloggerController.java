package com.simple.controller;

import javax.servlet.http.HttpServletRequest;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.simple.po.Blogger;
import com.simple.util.CryptographyUtil;

/**
 * 博主Controller层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/blogger")
public class BloggerController {
	
	
	@RequestMapping("/login")
	public String login(Blogger blogger,HttpServletRequest request){
		Subject subject=SecurityUtils.getSubject();
		System.out.println(blogger.getPassword());
		UsernamePasswordToken token=new UsernamePasswordToken(blogger.getUserName(), CryptographyUtil.md5(blogger.getPassword(), "love"));
		System.out.println(token.getPassword());
		try{
			subject.login(token); // 登录验证
			//如果登陆成功 就不会报错  报错就是登陆失败了
			return "redirect:/admin/main.jsp";
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("blogger", blogger);
			request.setAttribute("errorInfo", "用户名或者密码错误！");
			
			return "login";
		}
	}
	
	
	/**
	 * 关于博主
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aboutMe")
	public ModelAndView aboutMe()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("pageTitle", "长琴的个人博客");
		mav.addObject("mainPage", "foreground/blogger/info.jsp");
		mav.setViewName("mainTemp");

		return mav;
	}
	

	/**
	 * 我的相册
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myAlbum")
	public ModelAndView myAlbum()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("pageTitle", "长琴的相册");
		mav.setViewName("foreground/system/myAlbum");
		return mav;
	}
	

	/**
	 * 我的代码
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myCode")
	public ModelAndView myCode()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("pageTitle", "长琴的代码");
		mav.addObject("mainPage", "foreground/system/myCode.html");
		mav.setViewName("mainTemp");
		return mav;
	}
	
	/**
	 * 我的专栏
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myColumn")
	public ModelAndView myColumn()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("pageTitle", "长琴的专栏");
		mav.addObject("mainPage", "foreground/system/myColumn");
		mav.setViewName("mainTemp");
		return mav;
	}
	
	
		
}
