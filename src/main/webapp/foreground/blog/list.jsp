<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="data_list">
	<div class="data_list_title">
		<img
			src="${pageContext.request.contextPath}/static/images/list_icon.png" />
		最新博客
	</div>
	<!--文章内容部分-->
	<div class="datas">
		<ul>
		<c:forEach var="blog" items="${blogList }">
			<li style="margin-bottom: 30px" class="layui-anim layui-anim-upbit">
				<!-- 右侧图片 -->
				<c:forEach var="image"	items="${blog.imageList[0] }">
				<a class="wrap-img"	href="/blog/articles/${blog.id }.html" target="_blank"> ${image[0]}	</a>		  	
				</c:forEach>
				<div class="content">
				<a	href="${pageContext.request.contextPath}/blog/articles/${blog.id }.html" class="title" target="_blank">${blog.title }</a>
					<p class="abstract">
						<c:out value="${blog.summary }" escapeXml="true"></c:out>
					</p>
					<div class="meta">
						<a class="nickname" target="_blank" href="/u/9d424e3a4965">简单爱</a>
						<%-- 发表于 <fmt:formatDate	 value="${blog.releaseDate }" type="date"pattern="yyyy-MM-dd HH:mm" /> --%>
						<!-- 评论数 -->
						<a target="_blank" href="/p/54481f669991#comments"> <i class="layui-icon">&#xe611;</i>  ${blog.replyHit } 			</a> 
						<!-- 点击数  -->
						<span><i class="layui-icon">&#xe609;</i> ${blog.clickHit}</span>
					</div>
				</div>		
			</li>
	</c:forEach>
		</ul>
	</div>
</div>
<!-- 下面是分页 -->
<div>
	<nav>
		<ul class="pagination pagination-sm">${pageCode }
		</ul>
	</nav>
</div>
