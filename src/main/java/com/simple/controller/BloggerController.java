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
 * ����Controller��
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
			subject.login(token); // ��¼��֤
			//�����½�ɹ� �Ͳ��ᱨ��  ������ǵ�½ʧ����
			return "redirect:/admin/main.jsp";
		}catch(Exception e){
			e.printStackTrace();
			request.setAttribute("blogger", blogger);
			request.setAttribute("errorInfo", "�û��������������");
			
			return "login";
		}
	}
	
	
	/**
	 * ���ڲ���
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/aboutMe")
	public ModelAndView aboutMe()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("pageTitle", "���ٵ�����");
		mav.setViewName("foreground/blogger/info");

		return mav;
	}
	

	/**
	 * �ҵ����
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myAlbum")
	public ModelAndView myAlbum()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("pageTitle", "���ٵ����");
		mav.setViewName("foreground/system/myAlbum");
		return mav;
	}
	

	/**
	 * �ҵĴ���
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myCode")
	public ModelAndView myCode()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("pageTitle", "���ٵĴ���");
		mav.addObject("mainPage", "foreground/system/myCode.html");
		mav.setViewName("mainTemp");
		return mav;
	}
	
	/**
	 * �ҵ�ר��
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/myColumn")
	public ModelAndView myColumn()throws Exception{
		ModelAndView mav=new ModelAndView();
		mav.addObject("pageTitle", "���ٵ�ר��");
		mav.addObject("mainPage", "foreground/system/myColumn");
		mav.setViewName("mainTemp");
		return mav;
	}
	
	
		
}
