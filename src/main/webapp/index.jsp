<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="renderer" content="webkit">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap.min.css">
		<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/layui/css/layui.css">
<%-- <link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/bootstrap3/css/bootstrap-theme.min.css"> --%>
<link href="${pageContext.request.contextPath}/static/css/style.css" rel="stylesheet" type="text/css" media="all"/><!-- stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/css/blog.css">

    <script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-2.1.4.min.js"></script><!-- Required-js -->
<script
	src="${pageContext.request.contextPath}/static/bootstrap3/js/jquery.min.js"></script>
<script
	src="${pageContext.request.contextPath}/static/bootstrap3/js/bootstrap.min.js"></script>
	
	
<title>${pageTitle}</title>
<style type="text/css">
body {
	padding-top: 10px;
	padding-bottom: 40px;
}
</style>
</head>
<body>
		<jsp:include page="/foreground/common/head.jsp"/>	
		<jsp:include page="/foreground/common/menu.jsp"/>
	<div class="container" >
		<div class="row" style="background-color:#009688">
			<div class="col-md-9">
			<div class="data_list">
					<div class="data_list_title">
						<img
							src="${pageContext.request.contextPath}/static/images/list_icon.png" />
						最新博客
					</div>

					<div class="datas">
						<ul>
							<li style="margin-bottom: 30px"><span class="date"><a
									href="${pageContext.request.contextPath}/blog/articles/52.html">2016年02月15日</a></span>
								<span class="title"><a
									href="${pageContext.request.contextPath}/blog/articles/52.html">使用CXF开发WebService客户端</a></span>
								<span class="summary">摘要:
									前面一讲开发了webservice服务器端接口，今天的话，我们来开发webservice客户端，让大家来体验下过程；首先建一个Maven项目，项目名字，WS_Client；然后我们要用CXF给我们提供的工具wsdl2java
									来根据请求的url生成客户端代码；wsdl2java工具在CXF开发包里；开发下载地...</span> <span class="img">
							</span> <span class="info">发表于 2016-02-15 21:06 阅读(71) 评论(5) </span></li>
							<hr
								style="height: 5px; border: none; border-top: 1px dashed gray; padding-bottom: 10px;" />

							<li style="margin-bottom: 30px"><span class="date"><a
									href="${pageContext.request.contextPath}/blog/articles/52.html">2016年02月15日</a></span>
								<span class="title"><a
									href="${pageContext.request.contextPath}/blog/articles/52.html">使用CXF开发WebService客户端</a></span>
								<span class="summary">摘要:
									前面一讲开发了webservice服务器端接口，今天的话，我们来开发webservice客户端，让大家来体验下过程；首先建一个Maven项目，项目名字，WS_Client；然后我们要用CXF给我们提供的工具wsdl2java
									来根据请求的url生成客户端代码；wsdl2java工具在CXF开发包里；开发下载地...</span> <span class="img">

									<a href="/blog/articles/52.html"><img
										src="${pageContext.request.contextPath}/static/userImages/a.jpg"
										alt="1455539511890048174.jpg"></a> &nbsp;&nbsp; <a
									href="/blog/articles/52.html"><img
										alt="QQ鎴浘20160215203213.jpg"
										src="${pageContext.request.contextPath}/static/userImages/b.jpg"
										title="1455539700734093102.jpg" width="667" height="264"
										style="width: 667px; height: 264px;"></a> &nbsp;&nbsp; <a
									href="/blog/articles/52.html"><img
										alt="QQ鎴浘20160215203317.jpg"
										src="${pageContext.request.contextPath}/static/userImages/c.jpg"
										title="1455539761187019902.jpg"></a> &nbsp;&nbsp;

							</span> <span class="info">发表于 2016-02-15 21:06 阅读(71) 评论(5) </span></li>
							<hr
								style="height: 5px; border: none; border-top: 1px dashed gray; padding-bottom: 10px;" />


							<li style="margin-bottom: 30px"><span class="date"><a
									href="${pageContext.request.contextPath}/blog/articles/52.html">2016年02月15日</a></span>
								<span class="title"><a
									href="${pageContext.request.contextPath}/blog/articles/52.html">使用CXF开发WebService客户端</a></span>
								<span class="summary">摘要:
									前面一讲开发了webservice服务器端接口，今天的话，我们来开发webservice客户端，让大家来体验下过程；首先建一个Maven项目，项目名字，WS_Client；然后我们要用CXF给我们提供的工具wsdl2java
									来根据请求的url生成客户端代码；wsdl2java工具在CXF开发包里；开发下载地...</span> <span class="img">

									<a href="/blog/articles/52.html"><img
										src="${pageContext.request.contextPath}/static/userImages/a.jpg"
										alt="1455539511890048174.jpg"></a> &nbsp;&nbsp; <a
									href="/blog/articles/52.html"><img
										alt="QQ鎴浘20160215203213.jpg"
										src="${pageContext.request.contextPath}/static/userImages/b.jpg"
										title="1455539700734093102.jpg" width="667" height="264"
										style="width: 667px; height: 264px;"></a> &nbsp;&nbsp; <a
									href="/blog/articles/52.html"><img
										alt="QQ鎴浘20160215203317.jpg"
										src="${pageContext.request.contextPath}/static/userImages/c.jpg"
										title="1455539761187019902.jpg"></a> &nbsp;&nbsp;

							</span> <span class="info">发表于 2016-02-15 21:06 阅读(71) 评论(5) </span></li>
							<hr
								style="height: 5px; border: none; border-top: 1px dashed gray; padding-bottom: 10px;" />

						</ul>
					</div>
				</div>
			</div>

			<div class="col-md-3">
				<div class="data_list">
					<div class="data_list_title">
						<img
							src="${pageContext.request.contextPath}/static/images/user_icon.png" />
						博主信息
					</div>
					<div class="user_image">
						<img
							src="${pageContext.request.contextPath}/static/userImages/${blogger.imageName}" />
					</div>
					<div class="nickName">${blogger.nickName}</div>
					<div class="userSign">${blogger.sign}</div>
				</div>

				<div class="data_list">
					<div class="data_list_title">
						<img	src="${pageContext.request.contextPath}/static/images/byType_icon.png" />
						按日志类别
					</div>
					<div class="datas">

						<ul>
							<c:forEach var="blogTypeCount" items="${blogTypeCountList }">
								<li><span><a class="" href="${pageContext.request.contextPath}/index.html?typeId=${blogTypeCount.id}">${blogTypeCount.typeName }(${blogTypeCount.blogCount })</a></span></li>
							</c:forEach>

						</ul>

					</div>
				</div>
				
				
				<div class="data_list">
					<div class="data_list_title">
						<img
							src="${pageContext.request.contextPath}/static/images/byDate_icon.png" />
						按日志日期
					</div>
					<div class="datas">
						<ul>
							<c:forEach var="blogCount" items="${blogCountList }">
								<li><span><a href="${pageContext.request.contextPath}/index.html?releaseDateStr=${blogCount.releaseDateStr}">${blogCount.releaseDateStr }(${blogCount.blogCount })</a></span></li>
							</c:forEach>
						</ul>
					</div>
				</div>

				<div class="data_list">
					<div class="data_list_title">
						<img
							src="${pageContext.request.contextPath}/static/images/link_icon.png" />
						友情链接
					</div>
					<div class="datas">
						<ul>
							<c:forEach var="link" items="${linkList }">
								<li><span><a style=" word-wrap: break-word;" href="${link.linkUrl }" target="_blank">${link.linkName }</a></span></li>
							</c:forEach>
						</ul>
					</div>
				</div>

			</div>
		</div>

		

		<jsp:include page="/foreground/common/foot.jsp"/>

	</div>
</body>
</html>