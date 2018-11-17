package com.simple.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.google.gson.Gson;
import com.simple.po.Blog;
import com.simple.po.Comment;
import com.simple.po.Result;
import com.simple.service.BlogService;
import com.simple.service.CommentService;
import com.simple.util.ResponseUtil;


/**
 * ����Controller��
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/comment")
public class CommentController {

	@Resource
	private CommentService commentService;

	@Resource
	private BlogService blogService;

	/**
	 * ��ӻ����޸�����
	 * 
	 * @param comment
	 * @param imageCode
	 * @param request
	 * @param response
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save")
	public String save(Comment comment, @RequestParam("imageCode") String imageCode, HttpServletRequest request,
			HttpServletResponse response, HttpSession session) throws Exception {
		String sRand = (String) session.getAttribute("sRand");

		Gson gson = new Gson();
		Result result = new Result();

		int resultTotal = 0;
		if (!imageCode.equals(sRand)) {
			result.setSuccess(true);
			result.setMsg("��֤����д����!");
		} else {
			String userIp = request.getRemoteAddr(); // ��ȡ�û�IP
			comment.setUserIp(userIp);
			if (comment.getId() == null) {
				resultTotal = commentService.add(comment);
				// ���͵Ļظ�������1
				Blog blog = blogService.findById(comment.getBlog().getId());
				blog.setReplyHit(blog.getReplyHit() + 1);
				blogService.update(blog);
			} else {

			}
		}
		if (resultTotal > 0) {
			result.setSuccess(true);
		} else {
			result.setSuccess(false);
		}
		ResponseUtil.write(response, gson.toJson(result));
		return null;
	}
}
