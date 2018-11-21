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
 * 博客Controller层
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
	 * 请求博客详细信息
	 * 
	 * @return springmvc会自动把这个id提出来 /articles/55.html 会自动 提出55 当id
	 * @throws Exception
	 */
	@RequestMapping("/articles/{id}")
	public ModelAndView details(@PathVariable("id") Integer id, HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		Blog blog = blogService.findById(id);
		mav.addObject("blog", blog);
		//关键字切割
		String keyWords = blog.getKeyWord();
		if (StringUtil.isNotEmpty(keyWords)) {
			String arr[] = keyWords.split(" ");
			mav.addObject("keyWords", StringUtil.filterWhite(Arrays.asList(arr)));// 这也是一个list集合
		} else {
			mav.addObject("keyWords", null);
		}
//		点击事件+1
		blog.setClickHit(blog.getClickHit() + 1);// 点击+1
		blogService.update(blog);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("blogId", blog.getId());
		map.put("state", 1);
		//通过id和状态获得该片文章评论
		mav.addObject("commentList", commentService.list(map));
		mav.addObject("pageTitle", blog.getTitle() + "长琴的博客系统");
		mav.addObject("pageCode", this.getUpAndDownPageCode(blogService.getLastBlog(id), blogService.getNextBlog(id),
				request.getServletContext().getContextPath()));
		mav.addObject("mainPage", "foreground/blog/view.jsp");
		mav.setViewName("mainTemp");
		return mav;
	}

	/**
	 * 获取上一篇博客和下一篇博客
	 */
	private String getUpAndDownPageCode(Blog lastBlog, Blog nextBlog, String projectContext) {
		StringBuffer pageCode = new StringBuffer();
		if (lastBlog == null || lastBlog.getId() == null) {
			pageCode.append("<p>上一篇：没有了</p>");
		} else {
			pageCode.append("<p>上一篇：<a href='" + projectContext + "/blog/articles/" + lastBlog.getId() + ".html'>"
					+ lastBlog.getTitle() + "</a></p>");
		}

		if (nextBlog == null || nextBlog.getId() == null) {
			pageCode.append("<p>下一篇：没有了</p>");
		} else {
			pageCode.append("<p>下一篇：<a href='" + projectContext + "/blog/articles/" + nextBlog.getId() + ".html'>"
					+ nextBlog.getTitle() + "</a></p>");
		}
		return pageCode.toString();
	}

	/**
	 * 根据关键字查询相关博客信息
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
		mav.addObject("pageTitle", "搜索关键字'" + q + "'查询博客");
		mav.addObject("mainPage", "foreground/blog/result.jsp");
		List<Blog> blogList = blogIndex.searchBlog(q);
		long totalPage = blogList.size() % pageSize == 0 ? blogList.size()  / pageSize : blogList.size()  / pageSize + 1;
		if(Integer.parseInt(page)>totalPage){
			page = "1";
		}
		
		//这个toindex就是 截取的start end 的end位置。 
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
			pageCode.append("<li><a href='"+projectContext+"/blog/q.html?page="+(curr_page-1)+"&q="+q+"'>上一页</a></li>");			
		}else{
			pageCode.append("<li class='disabled'><a>上一页</a></li>");		
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
			pageCode.append("<li><a href='"+projectContext+"/blog/q.html?page="+(curr_page+1)+"&q="+q+"'>下一页</a></li>");		
		}else{
			pageCode.append("<li class='disabled'><a>下一页</a></li>");	
		}
		return pageCode.toString();
	}
	
	

}
