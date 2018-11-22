<script type="text/javascript"
	src="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css">
<script type="text/javascript">
	//SyntaxHighlighter.all();
	//展示其他的评论
	function showOtherComment() {
		$(".otherComment").toggle();
	}
	//加载验证码
	function loadimage() {
		document.getElementById("randImage").src = "${pageContext.request.contextPath}/image.jsp?"
				+ Math.random();
	}

	//提交数据评论
	function submitData() {
		var content = $("#content").val();
		var imageCode = $("#imageCode").val();
		if (content == null || content == "") {
			alert("请输入评论内容！");
		} else if (imageCode == null || imageCode == "") {
			alert("请填写验证码！");
		} else {
			$.post("${pageContext.request.contextPath}/comment/save.do", {
				"content" : content,
				'imageCode' : imageCode,
				'blog.id' : '${blog.id}'
			}, function(result) {
				if (result.success) {
					alert("评论已提成成功，审核通过后显示！");
				} else {
					alert(result.msg);
				}
			}, "json");
		}
	}
</script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
.data_list_datas div {
	margin-top: 30px;
	padding-left: 30px;
}
</style>

<div class="data_list">
	<div class="data_list_title">
		<img src="/static/images/blog_show_icon.png" /> 博客信息
	</div>
	<div class="data_list_datas">
		<div class="blog_title">
			<h3>
				<strong>${blog.title }</strong>
			</h3>
		</div>
		<div class="blog_info">
			<i><img src="/static/images/ico/time.png" width="30px;"
				height="30px;"></i>：
			<fmt:formatDate value="${blog.releaseDate }" type="date"
				pattern="yyyy-MM-dd" />
			&nbsp;&nbsp; <i><img src="/static/images/ico/blogKind.png"
				width="26px;" height="26px;"></i>&nbsp;&nbsp;：${blog.blogType.typeName }&nbsp;&nbsp;
			<!-- 评论数 -->
			&nbsp;&nbsp;&nbsp;&nbsp;<a target="_blank"
				href="/p/54481f669991#comments"> <i><img
					src="/static/images/ico/comment.png" width="30px;" height="30px;"></i>：${blog.replyHit }
			</a>
			<!-- 点击数  -->
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><i><img
					src="/static/images/ico/view.png" width="30px;" height="30px;"></i>
				&nbsp;&nbsp;：${blog.clickHit}</span>
		</div>
		<div class="blog_content">${blog.content }</div>


		<div class="blog_keyWord">
			<font
				style="font-size: 20px; padding-top: 20px; display: inline-block;"><strong>关键字：</strong></font>
			<c:choose>
				<c:when test="${keyWords==null }">
					&nbsp;&nbsp;还没有关键字呢
				</c:when>
				<c:otherwise>
					<c:forEach var="keyWord" items="${keyWords }">
						<a href="#" target="_blank"><span
							class="label label-${keyWord.className }"
							style="font-size: 16px;">${keyWord.name }</span></a>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
		<div class="blog_lastAndNextPage">${pageCode }</div>
	</div>
</div>
<div class="data_list" id="comment">
	<div class="data_list_title">
		<img src="/static/images/publish_comment_icon.png" /> 发表评论
	</div>
	<div class="publish_comment">
		<div>
			<textarea style="width: 100%" rows="3" id="content" name="content"
				placeholder="来说两句吧..."></textarea>
		</div>
		<div class="verCode">
			验证码：<input type="text" value="" name="imageCode" id="imageCode"
				size="10" onkeydown="if(event.keyCode==13)form1.submit()" />&nbsp;<img
				onclick="javascript:loadimage();" title="换一张试试" name="randImage"
				id="randImage" src="/image.jsp" width="60" height="20" border="1"
				align="absmiddle">
		</div>
		<div class="publishButton">
			<button class="btn btn-primary" type="button" onclick="submitData()">发表评论</button>
		</div>
		</form>
	</div>
</div>

<div class="data_list" >
	<div class="data_list_title">
		评论
		<c:if test="${commentList.size()>10 }">
			<a href="javascript:showOtherComment()"
				style="float: right; padding-right: 40px;">显示所有评论</a>
		</c:if>
	</div>
	<div class="commentDatas">
		<c:choose>
			<c:when test="${commentList.size()==0 }">
				智慧的你不想在<a href="#comment">说点啥</a>
			</c:when>
			<c:otherwise>
				<c:forEach var="comment" items="${commentList}" varStatus="status">
					<c:choose>
						<c:when test="${status.index<10 }">
							<div class="comment">
								<span><font>${status.index+1 }楼&nbsp;&nbsp;&nbsp;&nbsp;${comment.userIp }：</font>${comment.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;<fmt:formatDate
										value="${comment.commentDate }" type="date"
										pattern="yyyy-MM-dd HH:mm" />&nbsp;]</span>
							</div>
						</c:when>
						<c:otherwise>
							<div class="otherComment">
								<span><font>${status.index+1 }楼&nbsp;&nbsp;&nbsp;&nbsp;${comment.userIp }：</font>${comment.content }&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[&nbsp;<fmt:formatDate
										value="${comment.commentDate }" type="date"
										pattern="yyyy-MM-dd HH:mm" />&nbsp;]</span>
							</div>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
</div>










