package com.simple.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.simple.po.Blog;
import com.simple.lucene.BlogIndex;
import com.simple.service.BlogService;
import com.simple.service.CommentService;
import com.simple.util.StringUtil;

/**
 * ����Controller��
 * @author Administrator
 *
 */


@Controller
@RequestMapping("/blog")
public class BlogController {

	@Resource
	private BlogService blogService;
	
	@Resource
	private CommentService commentService;

	private BlogIndex blogIndex = new BlogIndex();
	
	
	/**
	 * ���󲩿���ϸ��Ϣ
	 * 
	 * @return springmvc���Զ������id����� /articles/55.html ���Զ� ���55 ��id
	 * @throws Exception
	 */
	@RequestMapping("/articles/{id}")
	public ModelAndView details(@PathVariable("id") Integer id, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		Blog blog = blogService.findById(id);
		mav.addObject("blog", blog);
		//�ؼ����и�
		String keyWords = blog.getKeyWord();
		if (StringUtil.isNotEmpty(keyWords)) {
			String arr[] = keyWords.split(" ");
			mav.addObject("keyWords", StringUtil.filterWhite(Arrays.asList(arr)));// ��Ҳ��һ��list����
		} else {
			mav.addObject("keyWords", null);
		}
//		����¼�+1
		blog.setClickHit(blog.getClickHit() + 1);// ���+1
		blogService.update(blog);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blogId", blog.getId());
		map.put("state", 1);
		//ͨ��id��״̬��ø�Ƭ��������
		mav.addObject("commentList", commentService.list(map));
		mav.addObject("pageTitle", blog.getTitle() + "java��Դ����ϵͳ");
		mav.addObject("pageCode", this.getUpAndDownPageCode(blogService.getLastBlog(id), blogService.getNextBlog(id),
				request.getServletContext().getContextPath()));
		mav.addObject("mainPage", "foreground/blog/view.jsp");
		mav.setViewName("mainTemp");
		return mav;
	}

	/**
	 * ��ȡ��һƪ���ͺ���һƪ����
	 */
	private String getUpAndDownPageCode(Blog lastBlog, Blog nextBlog, String projectContext) {
		StringBuffer pageCode = new StringBuffer();
		if (lastBlog == null || lastBlog.getId() == null) {
			pageCode.append("<p>��һƪ��û����</p>");
		} else {
			pageCode.append("<p>��һƪ��<a href='" + projectContext + "/blog/articles/" + lastBlog.getId() + ".html'>"
					+ lastBlog.getTitle() + "</a></p>");
		}

		if (nextBlog == null || nextBlog.getId() == null) {
			pageCode.append("<p>��һƪ��û����</p>");
		} else {
			pageCode.append("<p>��һƪ��<a href='" + projectContext + "/blog/articles/" + nextBlog.getId() + ".html'>"
					+ nextBlog.getTitle() + "</a></p>");
		}
		return pageCode.toString();
	}

	/**
	 * ���ݹؼ��ֲ�ѯ��ز�����Ϣ
	 * 
	 * @param q
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/q")
	public ModelAndView search(
			@RequestParam(value = "q", required = false) String q,
			@RequestParam(value = "page", required = false) String page,
			HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		String rootPath=request.getServletContext().getRealPath("/");
		
		if (StringUtil.isEmpty(page)){
			page = "1";
		}
		int pageSize = 100;
		mav.addObject("pageTitle", "�����ؼ���'" + q + "'���ҳ��_java��Դ����ϵͳ");
		mav.addObject("mainPage", "foreground/blog/result.jsp");
		List<Blog> blogList = blogIndex.searchBlog(q);
		long totalPage = blogList.size() % pageSize == 0 ? blogList.size()  / pageSize : blogList.size()  / pageSize + 1;
		if(Integer.parseInt(page)>totalPage){
			page = "1";
		}
		
		//���toindex���� ��ȡ��start end ��endλ�á� 
		Integer toIndex = blogList.size() >= Integer.parseInt(page) * pageSize ? Integer.parseInt(page) * pageSize
				: blogList.size();
		
		mav.addObject("blogList", blogList.subList((Integer.parseInt(page)-1)*pageSize, toIndex));
		mav.addObject("pageCode",this.getFenYe(Integer.parseInt(page), blogList.size(), q, pageSize, request.getServletContext().getContextPath()));
		
		mav.addObject("q", q);
		mav.addObject("resultTotal", blogList.size());
		mav.setViewName("mainTemp");
		return mav;
	}

	

	private String getFenYe(Integer curr_page, Integer totalNum, String q, Integer pageSize,String projectContext){
		StringBuffer pageCode=new StringBuffer();
		long totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		if(curr_page>1){
			pageCode.append("<li><a href='"+projectContext+"/blog/q.html?page="+(curr_page-1)+"&q="+q+"'>��һҳ</a></li>");			
		}else{
			pageCode.append("<li class='disabled'><a>��һҳ</a></li>");		
		}
		for(int i=curr_page-2;i<=curr_page+2;i++){
			if(i<1||i>totalPage){
				continue;
			}
			if(i==curr_page){
				pageCode.append("<li class='active'><a>"+i+"</a></li>");	
			}else{
				pageCode.append("<li><a href='"+projectContext+"/blog/q.html?page="+i+"&q="+q+"'>"+i+"</a></li>");	
			}
		}
		if(curr_page<totalPage){
			pageCode.append("<li><a href='"+projectContext+"/blog/q.html?page="+(curr_page+1)+"&q="+q+"'>��һҳ</a></li>");		
		}else{
			pageCode.append("<li class='disabled'><a>��һҳ</a></li>");	
		}
		return pageCode.toString();
	}
	
	

}
